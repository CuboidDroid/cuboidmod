package com.cuboiddroid.cuboidmod.modules.powergen.tile;

import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipe;
import com.cuboiddroid.cuboidmod.setup.ModRecipeTypes;
import com.cuboiddroid.cuboidmod.setup.ModTags;
import com.cuboiddroid.cuboidmod.util.CuboidEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class SingularityPowerGeneratorTileEntityBase extends TileEntity implements ITickableTileEntity {

    private ItemStackHandler itemHandler = createHandler();
    private CuboidEnergyStorage energyStorage;

    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    public static final int SINGULARITY_INPUT = 0;

    private int counter;

    private int energyCapacity;
    private int ticksPerCycle;
    private int baseEnergyGenerated;
    private int maxEnergyOutputPerTick;
    private int energyProducedPerCycle = 80;
    private float singularityMultiplier = 0.0F;

    private PowerGeneratingRecipe cachedRecipe = null;

    public SingularityPowerGeneratorTileEntityBase(
            TileEntityType<?> tileEntityType,
            int energyCapacity,
            int ticksPerCycle,
            int baseEnergyGenerated,
            int maxEnergyOutputPerTick) {
        super(tileEntityType);

        this.energyCapacity = energyCapacity;
        this.ticksPerCycle = ticksPerCycle;
        this.baseEnergyGenerated = baseEnergyGenerated;
        this.maxEnergyOutputPerTick = maxEnergyOutputPerTick;

        energyStorage = createEnergy();
    }

    /**
     * implementing classes should do something like this:
     *
     * return new TranslationTextComponent("cuboidmod.container.[identifier]");
     *
     * @return the display name
     */
    public abstract ITextComponent getDisplayName();

    @Override
    public void setRemoved() {
        super.setRemoved();
        handler.invalidate();
        energy.invalidate();
    }

    @Override
    public void tick() {
        if (level.isClientSide) {
            return;
        }

        if (this.energyStorage.getEnergyStored() >= this.energyCapacity) {
            // we're full - don't keep working!
            counter = 0;
        } else {
            PowerGeneratingRecipe recipe = getRecipe();
            if (recipe != null) {
                if (counter > 0) {
                    counter--;
                    if (counter <= 0) {
                        energyStorage.addEnergy(this.energyProducedPerCycle, this.energyCapacity);
                    }
                    setChanged();
                }

                if (counter <= 0) {
                    this.singularityMultiplier = recipe.getPowerMultiplier();

                    if (this.singularityMultiplier > 0.0F) {
                        // simulate only as we don't consume the singularity!
                        itemHandler.extractItem(SINGULARITY_INPUT, 1, true);
                        counter = ticksPerCycle;

                        // calculate energy produced per cycle by multiplying
                        // the base energy generated by the singularity's
                        // generation rate multiplier, and by the number
                        // of ticks per generation cycle
                        energyProducedPerCycle = (int) (baseEnergyGenerated * ticksPerCycle * singularityMultiplier);
                        setChanged();
                    }
                }
            }
        }

        BlockState blockState = this.level.getBlockState(this.worldPosition);
        Boolean shouldBeLit =
                (counter > 0 || this.energyStorage.getEnergyStored() > 0)
                && !itemHandler.getStackInSlot(0).isEmpty();
        if (blockState.getValue(BlockStateProperties.LIT) != shouldBeLit) {
            this.level.setBlock(this.worldPosition, blockState.setValue(BlockStateProperties.LIT, shouldBeLit),
                    Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
        }

        sendOutPower();
    }

    private void sendOutPower() {
        AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
        if (capacity.get() > 0) {
            for (Direction direction : Direction.values()) {
                TileEntity te = this.level.getBlockEntity(this.worldPosition.relative(direction));
                Direction opposite = direction.getOpposite();
                if (te != null) {
                    boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, opposite).map(handler -> {
                                if (handler.canReceive()) {
                                    int received = handler.receiveEnergy(Math.min(capacity.get(), maxEnergyOutputPerTick), false);
                                    capacity.addAndGet(-received);
                                    energyStorage.consumeEnergy(received);
                                    setChanged();
                                    return capacity.get() > 0;
                                } else {
                                    return true;
                                }
                            }
                    ).orElse(true);
                    if (!doContinue) {
                        return;
                    }
                }
            }
        }
    }

    @Nullable
    public PowerGeneratingRecipe getRecipe() {
        if (this.level == null
                || this.itemHandler.getStackInSlot(0).isEmpty())
            return null;

        // make an inventory
        IInventory inv = getInputsAsInventory();

        if (cachedRecipe == null || !cachedRecipe.matches(inv, this.level)) {
            cachedRecipe = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.POWER_GENERATING, inv, this.level).orElse(null);
        }

        return cachedRecipe;
    }

    private Inventory getInputsAsInventory() {
        return new Inventory(this.itemHandler.getStackInSlot(0).copy());
    }

    public int getBaseEnergyGenerated() {
        return this.baseEnergyGenerated;
    }

    public int getTicksPerCycle() {
        return this.ticksPerCycle;
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        energyStorage.deserializeNBT(tag.getCompound("energy"));

        counter = tag.getInt("counter");
        super.load(state, tag);
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        tag.put("inv", itemHandler.serializeNBT());
        tag.put("energy", energyStorage.serializeNBT());

        tag.putInt("counter", counter);
        return super.save(tag);
    }

    public int getEnergyCapacity() {
        return this.energyCapacity;
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return ModTags.Items.QUANTUM_SINGULARITIES.contains(stack.getItem());
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!ModTags.Items.QUANTUM_SINGULARITIES.contains(stack.getItem())) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private CuboidEnergyStorage createEnergy() {
        return new CuboidEnergyStorage(this.energyCapacity, 0, this.maxEnergyOutputPerTick) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }

    public abstract Container createContainer(int i, World level, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity);

    public IInventory getContentDrops() {
        return getInputsAsInventory();
    }
}

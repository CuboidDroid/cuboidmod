package com.cuboiddroid.cuboidmod.modules.transmuter.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.transmuter.inventory.QuantumTransmutationChamberContainer;
import com.cuboiddroid.cuboidmod.modules.transmuter.recipe.TransmutingRecipe;
import com.cuboiddroid.cuboidmod.setup.ModRecipeTypes;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.util.CuboidEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class QuantumTransmutationChamberTileEntity extends TileEntity implements ITickableTileEntity {
    public static final int SLOT_INPUT = 0;
    public static final int SLOT_ADDITIONAL = 1;
    public static final int SLOT_OUTPUT = 2;
    private static final int INPUT_SLOTS = 1;
    private static final int ADDITIONAL_SLOTS = 1;
    private static final int OUTPUT_SLOTS = 1;
    public static final int TOTAL_SLOTS = INPUT_SLOTS + ADDITIONAL_SLOTS + OUTPUT_SLOTS;
    private ItemStackHandler inputItemHandler = createInputHandler();
    private ItemStackHandler additionalItemHandler = createAdditionalInputHandler();
    private ItemStackHandler outputItemHandler = createOutputHandler();
    private CombinedInvWrapper combinedItemHandler = new CombinedInvWrapper(inputItemHandler, additionalItemHandler, outputItemHandler);
    private CuboidEnergyStorage energyStorage;
    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private LazyOptional<IItemHandler> inputHandler = LazyOptional.of(() -> inputItemHandler);
    private LazyOptional<IItemHandler> additionalHandler = LazyOptional.of(() -> additionalItemHandler);
    private LazyOptional<IItemHandler> outputHandler = LazyOptional.of(() -> outputItemHandler);
    private LazyOptional<IItemHandler> combinedHandler = LazyOptional.of(() -> combinedItemHandler);
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private int energyCapacity = Config.quantumTransmutationChamberEnergyCapacity.get();
    private int maxEnergyReceivedPerTick = Config.quantumTransmutationChamberMaxEnergyInputPerTick.get();

    private int processingTime = 0;
    private int recipeTime = -1;
    private int energyConsumed = 0;

    private TransmutingRecipe cachedRecipe = null;

    public QuantumTransmutationChamberTileEntity() {
        super(ModTileEntities.QUANTUM_TRANSMUTATION_CHAMBER.get());
        energyStorage = createEnergy();
    }

    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("cuboidmod.container.quantum_transmutation_chamber");
    }

    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide)
            return;

        boolean didWorkThisTick = false;

        TransmutingRecipe recipe = getRecipe();

        if (recipe == null) {
            if (processingTime > 0 || energyConsumed > 0) {
                stopWork();
                setChanged();
            }
        } else {
            if (processingTime <= 0) {
                if (energyConsumed + energyStorage.getEnergyStored() < recipe.getEnergyRequired())
                    // not enough energy to do anything - do nothing!
                    return;

                if (energyConsumed < recipe.getEnergyRequired()) {
                    // consume the required amount of energy
                    int energyNeeded = recipe.getEnergyRequired() - energyConsumed;

                    if (energyNeeded > 0) {
                        int energyToConsume = Math.min(energyNeeded, energyStorage.getEnergyStored());
                        energyStorage.consumeEnergy(energyToConsume);
                        energyConsumed += energyToConsume;
                        setChanged();
                    }
                }
            }

            if (processingTime <= 0 && energyConsumed >= recipe.getEnergyRequired()) {
                // we've consumed enough energy but have not started working - start working!
                this.processingTime = recipe.getWorkTicks();
                this.recipeTime = processingTime;
                setChanged();
            }

            if (processingTime > 0 && energyConsumed >= recipe.getEnergyRequired()) {
                didWorkThisTick = doWork(recipe);
                setChanged();
            }
        }

        BlockState blockState = this.level.getBlockState(this.worldPosition);
        if (blockState.getValue(BlockStateProperties.LIT) != (processingTime > 0 || didWorkThisTick)) {
            this.level.setBlock(this.worldPosition, blockState.setValue(BlockStateProperties.LIT, processingTime > 0),
                    Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    private boolean doWork(TransmutingRecipe recipe) {
        assert this.level != null;

        ItemStack currentOutput = this.outputItemHandler.getStackInSlot(0).copy();
        ItemStack recipeOutput = getWorkOutput(recipe);

        if (!currentOutput.isEmpty()) {
            // output slot has something in it...

            // work out how many output items we'd have if we add them together...
            int newTotal = currentOutput.getCount() + recipeOutput.getCount();

            if (!currentOutput.sameItem(recipeOutput) || newTotal > recipeOutput.getMaxStackSize()) {
                // the recipe output is different to what's already in the output stack, or
                // the amount of items produced by the recipe plus the current number of items in
                // the output slot would exceed the maximum stack size for the item - stop working!
                stopWork();
                return false;
            }
        }

        if (processingTime > 0)
            processingTime--;

        if (processingTime <= 0) {
            finishWork(recipe, currentOutput, recipeOutput);

            // return true so that we don't get "flicker" for the core between recipes
            return true;
        }

        return false;
    }

    private void stopWork() {
        this.processingTime = 0;
        this.recipeTime = -1;
    }

    private void finishWork(TransmutingRecipe recipe, ItemStack currentOutput, ItemStack recipeOutput) {
        if (!currentOutput.isEmpty()) {
            currentOutput.grow(recipeOutput.getCount());
            outputItemHandler.setStackInSlot(0, currentOutput);
        } else {
            outputItemHandler.setStackInSlot(0, recipeOutput.copy());
        }

        this.energyConsumed = 0;
        this.processingTime = 0;
        this.recipeTime = -1;

        ItemStack input1 = inputItemHandler.getStackInSlot(0);
        ItemStack input2 = additionalItemHandler.getStackInSlot(0);

        if (!input1.sameItem(Items.WATER_BUCKET.getDefaultInstance())) {
            input1.shrink(1);
            inputItemHandler.setStackInSlot(0, input1);
        } else
            inputItemHandler.setStackInSlot(0, Items.BUCKET.getDefaultInstance());

        if (!input2.sameItem(Items.WATER_BUCKET.getDefaultInstance())) {
            input2.shrink(1);
            additionalItemHandler.setStackInSlot(0, input2);
        } else
            additionalItemHandler.setStackInSlot(0, Items.BUCKET.getDefaultInstance());
    }

    @Nullable
    public TransmutingRecipe getRecipe() {
        if (this.level == null
                || this.inputItemHandler.getStackInSlot(0).isEmpty()
                || this.additionalItemHandler.getStackInSlot(0).isEmpty())
            return null;

        // make an inventory
        IInventory inv = getInputsAsInventory();

        if (cachedRecipe == null || !cachedRecipe.matches(inv, this.level)) {
            cachedRecipe = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.TRANSMUTING, inv, this.level).orElse(null);
        }

        return cachedRecipe;
    }

    private Inventory getInputsAsInventory() {
        if (this.inputItemHandler.getStackInSlot(0).isEmpty()) {
            return new Inventory(this.additionalItemHandler.getStackInSlot(0).copy());
        } else if (this.additionalItemHandler.getStackInSlot(0).isEmpty()) {
            return new Inventory(this.inputItemHandler.getStackInSlot(0).copy());
        }

        return new Inventory(this.inputItemHandler.getStackInSlot(0).copy(), this.additionalItemHandler.getStackInSlot(0).copy());
    }

    private ItemStack getWorkOutput(@Nullable TransmutingRecipe recipe) {
        if (recipe != null) {
            return recipe.assemble(getInputsAsInventory());
        }

        return ItemStack.EMPTY;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            // if side is null, then it's not via automation, so provide access to everything
            if (side == null)
                return combinedHandler.cast();

            // if side is not null, then it's automation
            // BOTTOM = output, UP = base item input, sides = additional
            switch (side) {
                case DOWN:
                    return outputHandler.cast();

                case UP:
                    return inputHandler.cast();

                default:
                    return additionalHandler.cast();
            }
        }

        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        inputItemHandler.deserializeNBT(tag.getCompound("invIn"));
        additionalItemHandler.deserializeNBT(tag.getCompound("invAdd"));
        outputItemHandler.deserializeNBT(tag.getCompound("invOut"));
        energyStorage.deserializeNBT(tag.getCompound("energy"));
        processingTime = tag.getInt("procTime");
        recipeTime = tag.getInt("recTime");
        energyConsumed = tag.getInt("feConsumed");
        super.load(state, tag);
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        tag.put("invIn", inputItemHandler.serializeNBT());
        tag.put("invAdd", additionalItemHandler.serializeNBT());
        tag.put("invOut", outputItemHandler.serializeNBT());
        tag.put("energy", energyStorage.serializeNBT());
        tag.putInt("procTime", processingTime);
        tag.putInt("recTime", recipeTime);
        tag.putInt("feConsumed", energyConsumed);
        return super.save(tag);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        inputHandler.invalidate();
        additionalHandler.invalidate();
        outputHandler.invalidate();
        combinedHandler.invalidate();
        energy.invalidate();
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbtTag = new CompoundNBT();
        this.save(nbtTag);
        this.setChanged();
        return new SUpdateTileEntityPacket(getBlockPos(), -1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT tag = pkt.getTag();
        this.load(level.getBlockState(worldPosition), tag);
        this.setChanged();
        level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition).getBlock().defaultBlockState(), level.getBlockState(worldPosition), 2);
    }

    private CuboidEnergyStorage createEnergy() {
        return new CuboidEnergyStorage(energyCapacity, maxEnergyReceivedPerTick, 0) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }
        };
    }

    private ItemStackHandler createInputHandler() {
        return new ItemStackHandler(INPUT_SLOTS) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return this.getStackInSlot(slot).isEmpty() || this.getStackInSlot(slot).sameItem(stack);
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private ItemStackHandler createAdditionalInputHandler() {
        return new ItemStackHandler(ADDITIONAL_SLOTS) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return this.getStackInSlot(slot).isEmpty() || this.getStackInSlot(slot).sameItem(stack);
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private ItemStackHandler createOutputHandler() {
        return new ItemStackHandler(OUTPUT_SLOTS) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                // can't insert into the output slot
                return stack;
            }
        };
    }

    public Container createContainer(int i, World level, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new QuantumTransmutationChamberContainer(i, level, pos, playerInventory, playerEntity);
    }

    public int getEnergyCapacity() {
        return this.energyCapacity;
    }

    public int getProcessingTime() {
        return this.processingTime;
    }

    public void setProcessingTime(int value) {
        this.processingTime = value;
    }

    public int getRecipeTime() {
        return this.recipeTime == 0 ? -1 : this.recipeTime;
    }
}

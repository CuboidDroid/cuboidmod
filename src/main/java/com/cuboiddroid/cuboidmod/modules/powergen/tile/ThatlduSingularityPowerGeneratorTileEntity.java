package com.cuboiddroid.cuboidmod.modules.powergen.tile;

import com.cuboiddroid.cuboidmod.modules.powergen.inventory.ThatlduSingularityPowerGeneratorContainer;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModGeneratorTiers;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ThatlduSingularityPowerGeneratorTileEntity extends SingularityPowerGeneratorTileEntityBase {
    public ThatlduSingularityPowerGeneratorTileEntity() {
        this(BlockPos.ZERO, ModBlocks.THATLDU_SINGULARITY_POWER_GENERATOR.get().defaultBlockState());
    }

    public ThatlduSingularityPowerGeneratorTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.THATLDU_SINGULARITY_POWER_GENERATOR.get(),
                pos, state,
                ModGeneratorTiers.THATLDU.getEnergyCapacity(),
                ModGeneratorTiers.THATLDU.getTicksPerCycle(),
                ModGeneratorTiers.THATLDU.getBaseEnergyGenerated(),
                ModGeneratorTiers.THATLDU.getMaxEnergyOutputPerTick());
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("cuboidmod.container.thatldu_singularity_power_generator");
    }

    @Override
    public AbstractContainerMenu createContainer(int i, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new ThatlduSingularityPowerGeneratorContainer(i, level, pos, playerInventory, playerEntity);
    }
}

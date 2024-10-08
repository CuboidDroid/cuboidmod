package com.cuboiddroid.cuboidmod.modules.powergen.tile;

import com.cuboiddroid.cuboidmod.modules.powergen.inventory.WikidiumSingularityPowerGeneratorContainer;
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

public class WikidiumSingularityPowerGeneratorTileEntity extends SingularityPowerGeneratorTileEntityBase {
    public WikidiumSingularityPowerGeneratorTileEntity() {
        this(BlockPos.ZERO, ModBlocks.WIKIDIUM_SINGULARITY_POWER_GENERATOR.get().defaultBlockState());
    }

    public WikidiumSingularityPowerGeneratorTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.WIKIDIUM_SINGULARITY_POWER_GENERATOR.get(),
                pos, state,
                ModGeneratorTiers.WIKIDIUM.getEnergyCapacity(),
                ModGeneratorTiers.WIKIDIUM.getTicksPerCycle(),
                ModGeneratorTiers.WIKIDIUM.getBaseEnergyGenerated(),
                ModGeneratorTiers.WIKIDIUM.getMaxEnergyOutputPerTick());
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("cuboidmod.container.wikidium_singularity_power_generator");
    }

    @Override
    public AbstractContainerMenu createContainer(int i, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new WikidiumSingularityPowerGeneratorContainer(i, level, pos, playerInventory, playerEntity);
    }
}

package com.cuboiddroid.cuboidmod.modules.collapser.tile;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.inventory.WikidiumQuantumCollapserContainer;
import com.cuboiddroid.cuboidmod.setup.ModGeneratorTiers;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class WikidiumQuantumCollapserTileEntity extends QuantumCollapserTileEntityBase {
    public WikidiumQuantumCollapserTileEntity() {
        super(ModTileEntities.WIKIDIUM_QUANTUM_COLLAPSER.get(),
                ModGeneratorTiers.WIKIDIUM.getCollapserSpeed());
    }

    public WikidiumQuantumCollapserTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.WIKIDIUM_QUANTUM_COLLAPSER.get(),
                pos, state, ModGeneratorTiers.WIKIDIUM.getCollapserSpeed());
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(CuboidMod.MOD_ID + ".container.wikidium_quantum_collapser");
    }

    @Override
    public AbstractContainerMenu createContainer(int i, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new WikidiumQuantumCollapserContainer(i, level, pos, playerInventory, playerEntity);
    }

}
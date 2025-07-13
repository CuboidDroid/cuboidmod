package com.cuboiddroid.cuboidmod.modules.collapser.handler;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import com.cuboiddroid.cuboidmod.util.IColored;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ColorHandler {

    @SubscribeEvent
    public void onItemColors(RegisterColorHandlersEvent.Item event) {
        CuboidMod.LOGGER.info("CREATING ITEM COLORS");
        
        event.register(new IColored.ItemColors(), ModItems.QUANTUM_SINGULARITY.get());

        event.register(new IColored.ItemColors(), ModItems.ANDESITE_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.BASALT_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.BLACKSTONE_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.CALCITE_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.CARBON_NANOTUBE_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.CELLULOSE_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.CLAY_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.COAL_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.COBBLESTONE_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.DIORITE_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.DIRT_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.DUST_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.GLOWSTONE_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.GRANITE_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.GRAVEL_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.KUDBEBEDDA_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.LAPIS_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.NETHERRACK_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.ENDSTONE_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.TUFF_QUANTUM_SINGULARITY.get());

        event.register(new IColored.ItemColors(), ModItems.OAK_LOG_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.ACACIA_LOG_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.BIRCH_LOG_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.DARK_OAK_LOG_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.JUNGLE_LOG_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.SPRUCE_LOG_QUANTUM_SINGULARITY.get());

        event.register(new IColored.ItemColors(), ModItems.NOTARFBADIUM_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.NOTSOGUDIUM_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.REDSTONE_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.SAND_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.SILICA_DUST_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.THATLDU_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.WIKIDIUM_QUANTUM_SINGULARITY.get());

        event.register(new IColored.ItemColors(), ModItems.ALUMINIUM_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.COPPER_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.GOLD_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.IRON_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.LEAD_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.NICKEL_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.OSMIUM_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.SILVER_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.TIN_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.URANIUM_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.URANINITE_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.ZINC_QUANTUM_SINGULARITY.get());

        event.register(new IColored.ItemColors(), ModItems.DIAMOND_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.EMERALD_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.STEEL_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.BRONZE_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.ELECTRUM_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.INVAR_QUANTUM_SINGULARITY.get());
        event.register(new IColored.ItemColors(), ModItems.PLATINUM_QUANTUM_SINGULARITY.get());
    }
}

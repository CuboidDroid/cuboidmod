package com.cuboiddroid.cuboidmod.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput generatorIn, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(generatorIn, lookupProvider, CuboidMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        // notsogudium
        tag(ModTags.Blocks.ORES_NOTSOGUDIUM).add(ModBlocks.NOTSOGUDIUM_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_NOTSOGUDIUM);
        tag(Tags.Blocks.NEEDS_WOOD_TOOL).addTag(ModTags.Blocks.ORES_NOTSOGUDIUM);
        
        tag(ModTags.Blocks.STORAGE_BLOCKS_NOTSOGUDIUM).add(ModBlocks.NOTSOGUDIUM_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_NOTSOGUDIUM);
        
        tag(ModTags.Blocks.CHESTS_NOTSOGUDIUM).add(ModBlocks.NOTSOGUDIUM_CHEST.get());
        tag(Tags.Blocks.CHESTS).addTag(ModTags.Blocks.CHESTS_NOTSOGUDIUM);
        
        tag(ModTags.Blocks.CRAFTING_TABLES_NOTSOGUDIUM).add(ModBlocks.NOTSOGUDIUM_CRAFTING_TABLE.get());
        
        tag(ModTags.Blocks.FURNACES_NOTSOGUDIUM).add(ModBlocks.NOTSOGUDIUM_FURNACE.get());
        
        // kudbebedda
        tag(ModTags.Blocks.ORES_KUDBEBEDDA).add(ModBlocks.KUDBEBEDDA_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_KUDBEBEDDA);
        tag(ModTags.Blocks.NEEDS_NOTSOGUDIUM_TOOL).addTag(ModTags.Blocks.ORES_KUDBEBEDDA);
        
        tag(ModTags.Blocks.STORAGE_BLOCKS_KUDBEBEDDA).add(ModBlocks.KUDBEBEDDA_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_KUDBEBEDDA);
        
        tag(ModTags.Blocks.CHESTS_KUDBEBEDDA).add(ModBlocks.KUDBEBEDDA_CHEST.get());
        tag(Tags.Blocks.CHESTS).addTag(ModTags.Blocks.CHESTS_KUDBEBEDDA);
        
        tag(ModTags.Blocks.CRAFTING_TABLES_KUDBEBEDDA).add(ModBlocks.KUDBEBEDDA_CRAFTING_TABLE.get());
        
        tag(ModTags.Blocks.FURNACES_KUDBEBEDDA).add(ModBlocks.KUDBEBEDDA_FURNACE.get());
        
        // notarfbadium
        tag(ModTags.Blocks.ORES_NOTARFBADIUM).add(ModBlocks.NOTARFBADIUM_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_NOTARFBADIUM);
        tag(ModTags.Blocks.NEEDS_KUDBEBEDDA_TOOL).addTag(ModTags.Blocks.ORES_NOTARFBADIUM);
        
        tag(ModTags.Blocks.STORAGE_BLOCKS_NOTARFBADIUM).add(ModBlocks.NOTARFBADIUM_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_NOTARFBADIUM);
        
        tag(ModTags.Blocks.CHESTS_NOTARFBADIUM).add(ModBlocks.NOTARFBADIUM_CHEST.get());
        tag(Tags.Blocks.CHESTS).addTag(ModTags.Blocks.CHESTS_NOTARFBADIUM);
        
        tag(ModTags.Blocks.CRAFTING_TABLES_NOTARFBADIUM).add(ModBlocks.NOTARFBADIUM_CRAFTING_TABLE.get());
        
        tag(ModTags.Blocks.FURNACES_NOTARFBADIUM).add(ModBlocks.NOTARFBADIUM_FURNACE.get());
        
        // wikidium
        tag(ModTags.Blocks.ORES_WIKIDIUM).add(ModBlocks.WIKIDIUM_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_WIKIDIUM);
        tag(ModTags.Blocks.NEEDS_NOTARFBADIUM_TOOL).addTag(ModTags.Blocks.ORES_WIKIDIUM);
        
        tag(ModTags.Blocks.STORAGE_BLOCKS_WIKIDIUM).add(ModBlocks.WIKIDIUM_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_WIKIDIUM);
        
        tag(ModTags.Blocks.CHESTS_WIKIDIUM).add(ModBlocks.WIKIDIUM_CHEST.get());
        tag(Tags.Blocks.CHESTS).addTag(ModTags.Blocks.CHESTS_WIKIDIUM);
        
        tag(ModTags.Blocks.CRAFTING_TABLES_WIKIDIUM).add(ModBlocks.WIKIDIUM_CRAFTING_TABLE.get());
        
        tag(ModTags.Blocks.FURNACES_WIKIDIUM).add(ModBlocks.WIKIDIUM_FURNACE.get());
        
        // thatldu
        tag(ModTags.Blocks.ORES_THATLDU).add(ModBlocks.THATLDU_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_THATLDU);
        tag(ModTags.Blocks.NEEDS_WIKIDIUM_TOOL).addTag(ModTags.Blocks.ORES_THATLDU);
        
        tag(ModTags.Blocks.STORAGE_BLOCKS_THATLDU).add(ModBlocks.THATLDU_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_THATLDU);
        
        tag(ModTags.Blocks.CHESTS_THATLDU).add(ModBlocks.THATLDU_CHEST.get());
        tag(Tags.Blocks.CHESTS).addTag(ModTags.Blocks.CHESTS_THATLDU);
        
        tag(ModTags.Blocks.CRAFTING_TABLES_THATLDU).add(ModBlocks.THATLDU_CRAFTING_TABLE.get());
        
        tag(ModTags.Blocks.FURNACES_THATLDU).add(ModBlocks.THATLDU_FURNACE.get());
        
        tag(ModTags.Blocks.FURNACES_EUMUS).add(ModBlocks.EUMUS_FURNACE.get());
        
        // storage blocks
        tag(ModTags.Blocks.STORAGE_BLOCKS_CARBON_NANOTUBE).add(ModBlocks.CARBON_NANOTUBE_BLOCK.get());
        
        tag(ModTags.Blocks.STORAGE_BLOCKS_CELLULOSE).add(ModBlocks.CELLULOSE_BLOCK.get());
        
        tag(ModTags.Blocks.STORAGE_BLOCKS_PROTEIN_FIBER).add(ModBlocks.PROTEIN_FIBER_BLOCK.get());
        
        tag(ModTags.Blocks.STORAGE_BLOCKS_PROTEIN_PASTE).add(ModBlocks.PROTEIN_PASTE_BLOCK.get());
        
        tag(ModTags.Blocks.STORAGE_BLOCKS_SILICA).add(ModBlocks.SILICA_DUST_BLOCK.get());
        
        // break tags
        tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.FIBER_OPTIC_TREE.get());
        tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.DRYING_CUPBOARD.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.MOLECULAR_RECYCLER.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.QUANTUM_TRANSMUTATION_CHAMBER.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.REFINED_INSCRIBER.get());

        tag(BlockTags.MINEABLE_WITH_AXE).addTag(ModTags.Blocks.CHESTS_NOTSOGUDIUM);
        tag(BlockTags.MINEABLE_WITH_AXE).addTag(ModTags.Blocks.CHESTS_KUDBEBEDDA);
        tag(BlockTags.MINEABLE_WITH_AXE).addTag(ModTags.Blocks.CHESTS_NOTARFBADIUM);
        tag(BlockTags.MINEABLE_WITH_AXE).addTag(ModTags.Blocks.CHESTS_WIKIDIUM);
        tag(BlockTags.MINEABLE_WITH_AXE).addTag(ModTags.Blocks.CHESTS_THATLDU);

        tag(BlockTags.MINEABLE_WITH_AXE).addTag(ModTags.Blocks.CRAFTING_TABLES_NOTSOGUDIUM);
        tag(BlockTags.MINEABLE_WITH_AXE).addTag(ModTags.Blocks.CRAFTING_TABLES_KUDBEBEDDA);
        tag(BlockTags.MINEABLE_WITH_AXE).addTag(ModTags.Blocks.CRAFTING_TABLES_NOTARFBADIUM);
        tag(BlockTags.MINEABLE_WITH_AXE).addTag(ModTags.Blocks.CRAFTING_TABLES_WIKIDIUM);
        tag(BlockTags.MINEABLE_WITH_AXE).addTag(ModTags.Blocks.CRAFTING_TABLES_THATLDU);

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.THATLDU_SINGULARITY_RESOURCE_GENERATOR.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.KUDBEBEDDA_SINGULARITY_POWER_GENERATOR.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.WIKIDIUM_SINGULARITY_POWER_GENERATOR.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.THATLDU_SINGULARITY_POWER_GENERATOR.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.NOTSOGUDIUM_QUANTUM_COLLAPSER.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.KUDBEBEDDA_QUANTUM_COLLAPSER.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.NOTARFBADIUM_QUANTUM_COLLAPSER.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.WIKIDIUM_QUANTUM_COLLAPSER.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.THATLDU_QUANTUM_COLLAPSER.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.ORES_NOTSOGUDIUM);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.ORES_KUDBEBEDDA);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.ORES_NOTARFBADIUM);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.ORES_WIKIDIUM);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.ORES_THATLDU);

        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.STORAGE_BLOCKS_NOTSOGUDIUM);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.STORAGE_BLOCKS_KUDBEBEDDA);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.STORAGE_BLOCKS_NOTARFBADIUM);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.STORAGE_BLOCKS_WIKIDIUM);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.STORAGE_BLOCKS_THATLDU);

        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.FURNACES_NOTSOGUDIUM);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.FURNACES_KUDBEBEDDA);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.FURNACES_NOTARFBADIUM);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.FURNACES_WIKIDIUM);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.FURNACES_THATLDU);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.FURNACES_EUMUS);

        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.STORAGE_BLOCKS_CARBON_NANOTUBE);
        tag(BlockTags.MINEABLE_WITH_SHOVEL).addTag(ModTags.Blocks.STORAGE_BLOCKS_CELLULOSE);
        tag(BlockTags.MINEABLE_WITH_SHOVEL).addTag(ModTags.Blocks.STORAGE_BLOCKS_PROTEIN_FIBER);
        tag(BlockTags.MINEABLE_WITH_SHOVEL).addTag(ModTags.Blocks.STORAGE_BLOCKS_PROTEIN_PASTE);
        tag(BlockTags.MINEABLE_WITH_SHOVEL).addTag(ModTags.Blocks.STORAGE_BLOCKS_SILICA);

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.CELLULOSE_BRICKS.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.CELLULOSE_CHISELED_BRICKS.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.ORGANICALLY_ENRICHED_SAND.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.AGGREGATE.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ENERGIZED_END_STONE_BRICKS.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ENERGIZED_NETHER_BRICKS.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ENERGIZED_STONE_BRICKS.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ENERGIZED_THATLDUVIUM.get());
    }
}

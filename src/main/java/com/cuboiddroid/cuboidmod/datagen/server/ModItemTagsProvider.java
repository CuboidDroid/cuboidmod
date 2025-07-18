package com.cuboiddroid.cuboidmod.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import com.cuboiddroid.cuboidmod.setup.ModTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput generatorIn, CompletableFuture<Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(generatorIn, lookupProvider, blockTagsProvider, CuboidMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        addOreTags();
        addStorageBlockTags();
        addIngotItemTags();
        addNuggetItemTags();
        addChunkItemTags();
        addPieceItemTags();
        addDustItemTags();
        addRodItemTags();
        addChestItemTags();
        addCraftingTableItemTags();
        addFurnaceItemTags();
        addSingularityTags();
        addMiscItemTags();
        addFoodTags();

        tag(ModTags.Items.FORGE_ORES).addTag(ModTags.Items.ORES);
        // TODO: add other tags:
        // addQuantumCollapserTags();
        // addSingularityResourceGeneratorTags();
        // addSingularityPowerGeneratorTags();
        // addQuantumTransmutationChamberTags();
        // addCuboidArmorTags();
        // addCuboidToolTags();
        // addSmasherTags();
        // etc.
    }

    private void addOreTags() {
        // copy block tags to respective item tags
        copy(ModTags.Blocks.ORES_NOTSOGUDIUM, ModTags.Items.ORES_NOTSOGUDIUM);
        copy(ModTags.Blocks.ORES_KUDBEBEDDA, ModTags.Items.ORES_KUDBEBEDDA);
        copy(ModTags.Blocks.ORES_NOTARFBADIUM, ModTags.Items.ORES_NOTARFBADIUM);
        copy(ModTags.Blocks.ORES_WIKIDIUM, ModTags.Items.ORES_WIKIDIUM);
        copy(ModTags.Blocks.ORES_THATLDU, ModTags.Items.ORES_THATLDU);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);

        tag(ModTags.Items.ORES).addTag(ModTags.Items.ORES_NOTSOGUDIUM);
        tag(ModTags.Items.ORES).addTag(ModTags.Items.ORES_KUDBEBEDDA);
        tag(ModTags.Items.ORES).addTag(ModTags.Items.ORES_NOTARFBADIUM);
        tag(ModTags.Items.ORES).addTag(ModTags.Items.ORES_WIKIDIUM);
        tag(ModTags.Items.ORES).addTag(ModTags.Items.ORES_THATLDU);
    }

    private void addStorageBlockTags() {
        // copy block tags to respective item tags
        copy(ModTags.Blocks.STORAGE_BLOCKS_NOTSOGUDIUM, ModTags.Items.STORAGE_BLOCKS_NOTSOGUDIUM);
        copy(ModTags.Blocks.STORAGE_BLOCKS_KUDBEBEDDA, ModTags.Items.STORAGE_BLOCKS_KUDBEBEDDA);
        copy(ModTags.Blocks.STORAGE_BLOCKS_NOTARFBADIUM, ModTags.Items.STORAGE_BLOCKS_NOTARFBADIUM);
        copy(ModTags.Blocks.STORAGE_BLOCKS_WIKIDIUM, ModTags.Items.STORAGE_BLOCKS_WIKIDIUM);
        copy(ModTags.Blocks.STORAGE_BLOCKS_THATLDU, ModTags.Items.STORAGE_BLOCKS_THATLDU);
        copy(ModTags.Blocks.STORAGE_BLOCKS_SILICA, ModTags.Items.STORAGE_BLOCKS_SILICA);
        copy(ModTags.Blocks.STORAGE_BLOCKS_CARBON_NANOTUBE, ModTags.Items.STORAGE_BLOCKS_CARBON_NANOTUBE);
        copy(ModTags.Blocks.STORAGE_BLOCKS_PROTEIN_PASTE, ModTags.Items.STORAGE_BLOCKS_PROTEIN_PASTE);
        copy(ModTags.Blocks.STORAGE_BLOCKS_PROTEIN_FIBER, ModTags.Items.STORAGE_BLOCKS_PROTEIN_FIBER);
        copy(ModTags.Blocks.STORAGE_BLOCKS_CELLULOSE, ModTags.Items.STORAGE_BLOCKS_CELLULOSE);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        tag(ModTags.Items.STORAGE_BLOCKS).addTag(ModTags.Items.STORAGE_BLOCKS_NOTSOGUDIUM);
        tag(ModTags.Items.STORAGE_BLOCKS).addTag(ModTags.Items.STORAGE_BLOCKS_KUDBEBEDDA);
        tag(ModTags.Items.STORAGE_BLOCKS).addTag(ModTags.Items.STORAGE_BLOCKS_NOTARFBADIUM);
        tag(ModTags.Items.STORAGE_BLOCKS).addTag(ModTags.Items.STORAGE_BLOCKS_WIKIDIUM);
        tag(ModTags.Items.STORAGE_BLOCKS).addTag(ModTags.Items.STORAGE_BLOCKS_THATLDU);
        tag(ModTags.Items.STORAGE_BLOCKS).addTag(ModTags.Items.STORAGE_BLOCKS_SILICA);
        tag(ModTags.Items.STORAGE_BLOCKS).addTag(ModTags.Items.STORAGE_BLOCKS_CARBON_NANOTUBE);
        tag(ModTags.Items.STORAGE_BLOCKS).addTag(ModTags.Items.STORAGE_BLOCKS_PROTEIN_PASTE);
        tag(ModTags.Items.STORAGE_BLOCKS).addTag(ModTags.Items.STORAGE_BLOCKS_PROTEIN_FIBER);
        tag(ModTags.Items.STORAGE_BLOCKS).addTag(ModTags.Items.STORAGE_BLOCKS_CELLULOSE);
    }

    private void addIngotItemTags() {
        tag(ModTags.Items.INGOTS_NOTSOGUDIUM).add(ModItems.NOTSOGUDIUM_INGOT.get());
        tag(Tags.Items.INGOTS).addTag(ModTags.Items.INGOTS_NOTSOGUDIUM);

        tag(ModTags.Items.INGOTS_KUDBEBEDDA).add(ModItems.KUDBEBEDDA_INGOT.get());
        tag(Tags.Items.INGOTS).addTag(ModTags.Items.INGOTS_KUDBEBEDDA);

        tag(ModTags.Items.INGOTS_NOTARFBADIUM).add(ModItems.NOTARFBADIUM_INGOT.get());
        tag(Tags.Items.INGOTS).addTag(ModTags.Items.INGOTS_NOTARFBADIUM);

        tag(ModTags.Items.INGOTS_WIKIDIUM).add(ModItems.WIKIDIUM_INGOT.get());
        tag(Tags.Items.INGOTS).addTag(ModTags.Items.INGOTS_WIKIDIUM);

        tag(ModTags.Items.INGOTS_THATLDU).add(ModItems.THATLDU_INGOT.get());
        tag(Tags.Items.INGOTS).addTag(ModTags.Items.INGOTS_THATLDU);

        tag(ModTags.Items.INGOTS).addTag(ModTags.Items.INGOTS_NOTSOGUDIUM);
        tag(ModTags.Items.INGOTS).addTag(ModTags.Items.INGOTS_KUDBEBEDDA);
        tag(ModTags.Items.INGOTS).addTag(ModTags.Items.INGOTS_NOTARFBADIUM);
        tag(ModTags.Items.INGOTS).addTag(ModTags.Items.INGOTS_WIKIDIUM);
        tag(ModTags.Items.INGOTS).addTag(ModTags.Items.INGOTS_THATLDU);
    }

    private void addNuggetItemTags() {
        tag(ModTags.Items.NUGGETS_NOTSOGUDIUM).add(ModItems.NOTSOGUDIUM_NUGGET.get());
        tag(Tags.Items.NUGGETS).addTag(ModTags.Items.NUGGETS_NOTSOGUDIUM);

        tag(ModTags.Items.NUGGETS_KUDBEBEDDA).add(ModItems.KUDBEBEDDA_NUGGET.get());
        tag(Tags.Items.NUGGETS).addTag(ModTags.Items.NUGGETS_KUDBEBEDDA);

        tag(ModTags.Items.NUGGETS_NOTARFBADIUM).add(ModItems.NOTARFBADIUM_NUGGET.get());
        tag(Tags.Items.NUGGETS).addTag(ModTags.Items.NUGGETS_NOTARFBADIUM);

        tag(ModTags.Items.NUGGETS_WIKIDIUM).add(ModItems.WIKIDIUM_NUGGET.get());
        tag(Tags.Items.NUGGETS).addTag(ModTags.Items.NUGGETS_WIKIDIUM);

        tag(ModTags.Items.NUGGETS_THATLDU).add(ModItems.THATLDU_NUGGET.get());
        tag(Tags.Items.NUGGETS).addTag(ModTags.Items.NUGGETS_THATLDU);

        tag(ModTags.Items.NUGGETS).addTag(ModTags.Items.NUGGETS_NOTSOGUDIUM);
        tag(ModTags.Items.NUGGETS).addTag(ModTags.Items.NUGGETS_KUDBEBEDDA);
        tag(ModTags.Items.NUGGETS).addTag(ModTags.Items.NUGGETS_NOTARFBADIUM);
        tag(ModTags.Items.NUGGETS).addTag(ModTags.Items.NUGGETS_WIKIDIUM);
        tag(ModTags.Items.NUGGETS).addTag(ModTags.Items.NUGGETS_THATLDU);
    }

    private void addDustItemTags() {
        tag(ModTags.Items.DUSTS_NOTSOGUDIUM).add(ModItems.NOTSOGUDIUM_DUST.get());
        tag(ModTags.Items.DUSTS_KUDBEBEDDA).add(ModItems.KUDBEBEDDA_DUST.get());
        tag(ModTags.Items.DUSTS_NOTARFBADIUM).add(ModItems.NOTARFBADIUM_DUST.get());
        tag(ModTags.Items.DUSTS_WIKIDIUM).add(ModItems.WIKIDIUM_DUST.get());
        tag(ModTags.Items.DUSTS_THATLDU).add(ModItems.THATLDU_DUST.get());
        tag(ModTags.Items.DUSTS_SILICA).add(ModItems.SILICA_DUST.get());
        tag(ModTags.Items.DUSTS_ZINC).add(ModItems.ZINC_DUST.get());
        tag(ModTags.Items.DUSTS_SALT).add(ModItems.SALT.get());
        tag(ModTags.Items.CELLULOSE).add(ModItems.CELLULOSE.get());

        tag(ModTags.Items.DUSTS).addTag(ModTags.Items.DUSTS_NOTSOGUDIUM);
        tag(ModTags.Items.DUSTS).addTag(ModTags.Items.DUSTS_KUDBEBEDDA);
        tag(ModTags.Items.DUSTS).addTag(ModTags.Items.DUSTS_NOTARFBADIUM);
        tag(ModTags.Items.DUSTS).addTag(ModTags.Items.DUSTS_WIKIDIUM);
        tag(ModTags.Items.DUSTS).addTag(ModTags.Items.DUSTS_THATLDU);
        tag(ModTags.Items.DUSTS).addTag(ModTags.Items.DUSTS_SILICA);
        tag(ModTags.Items.DUSTS).addTag(ModTags.Items.DUSTS_ZINC);
        tag(ModTags.Items.DUSTS).addTag(ModTags.Items.CELLULOSE);
        tag(ModTags.Items.DUSTS).addTag(ModTags.Items.DUSTS_SALT);
        tag(ModTags.Items.DUSTS).addTag(ModTags.Items.DUSTS_SILICA);

        tag(Tags.Items.DUSTS).addTag(ModTags.Items.DUSTS);
    }

    private void addMiscItemTags() {
        tag(ModTags.Items.CARBON_NANOTUBE).add(ModItems.CARBON_NANOTUBE.get());
        tag(ModTags.Items.STICK_BUNDLE).add(ModItems.STICK_BUNDLE.get());
        tag(ModTags.Items.PROTEIN_PASTE).add(ModItems.PROTEIN_PASTE.get());
        tag(ModTags.Items.PROTEIN_FIBER).add(ModItems.PROTEIN_FIBER.get());
    }

    private void addFoodTags() {
        tag(ModTags.Items.FOOD_APPLE_CIDER_VINEGAR).add(ModItems.APPLE_CIDER_VINEGAR.get());
        tag(ModTags.Items.FOOD_ARACHNUGGET).add(ModItems.ARACHNUGGET.get());
        tag(ModTags.Items.FOOD_BROTH).add(ModItems.BROTH.get());
        tag(ModTags.Items.FOOD_GRUEL).add(ModItems.GRUEL.get());
        tag(ModTags.Items.FOOD_KEBAB_COOKED).add(ModItems.KEBAB_COOKED.get());
        tag(ModTags.Items.FOOD_KEBAB_RAW).add(ModItems.KEBAB_RAW.get());
        tag(ModTags.Items.FOOD_PROTEIN_BAR).add(ModItems.PROTEIN_BAR.get());
        tag(ModTags.Items.FOOD_PROTEIN_PASTE).add(ModItems.PROTEIN_PASTE.get());
        tag(ModTags.Items.FOOD_RATION_PACK).add(ModItems.RATION_PACK.get());
        tag(ModTags.Items.FOOD_ROTTEN_APPLE).add(ModItems.ROTTEN_APPLE.get());
        tag(ModTags.Items.FOOD_CURED_BEEF).add(ModItems.CURED_BEEF.get());
        tag(ModTags.Items.FOOD_CURED_FLESH).add(ModItems.CURED_FLESH.get());
        tag(ModTags.Items.FOOD_BEEF_BILTONG).add(ModItems.BEEF_BILTONG.get());
        tag(ModTags.Items.FOOD_ZOMBIE_BILTONG).add(ModItems.ZOMBIE_BILTONG.get());
        tag(ModTags.Items.FOOD_HARDFISKUR).add(ModItems.HARDFISKUR.get());

        tag(ModTags.Items.FOOD).addTag(ModTags.Items.FOOD_APPLE_CIDER_VINEGAR);
        tag(ModTags.Items.FOOD).addTag(ModTags.Items.FOOD_ARACHNUGGET);
        tag(ModTags.Items.FOOD).addTag(ModTags.Items.FOOD_BROTH);
        tag(ModTags.Items.FOOD).addTag(ModTags.Items.FOOD_GRUEL);
        tag(ModTags.Items.FOOD).addTag(ModTags.Items.FOOD_KEBAB_COOKED);
        tag(ModTags.Items.FOOD).addTag(ModTags.Items.FOOD_KEBAB_RAW);
        tag(ModTags.Items.FOOD).addTag(ModTags.Items.FOOD_PROTEIN_BAR);
        tag(ModTags.Items.FOOD).addTag(ModTags.Items.FOOD_PROTEIN_PASTE);
        tag(ModTags.Items.FOOD).addTag(ModTags.Items.FOOD_RATION_PACK);
        tag(ModTags.Items.FOOD).addTag(ModTags.Items.FOOD_ROTTEN_APPLE);
        tag(ModTags.Items.FOOD).addTag(ModTags.Items.FOOD_CURED_BEEF);
        tag(ModTags.Items.FOOD).addTag(ModTags.Items.FOOD_CURED_FLESH);
        tag(ModTags.Items.FOOD).addTag(ModTags.Items.FOOD_BEEF_BILTONG);
        tag(ModTags.Items.FOOD).addTag(ModTags.Items.FOOD_ZOMBIE_BILTONG);
        tag(ModTags.Items.FOOD).addTag(ModTags.Items.FOOD_HARDFISKUR);
    }

    private void addRodItemTags() {
        tag(ModTags.Items.RODS_NOTSOGUDIUM).add(ModItems.NOTSOGUDIUM_ROD.get());
        tag(Tags.Items.RODS).addTag(ModTags.Items.RODS_NOTSOGUDIUM);

        tag(ModTags.Items.RODS_KUDBEBEDDA).add(ModItems.KUDBEBEDDA_ROD.get());
        tag(Tags.Items.RODS).addTag(ModTags.Items.RODS_KUDBEBEDDA);

        tag(ModTags.Items.RODS_NOTARFBADIUM).add(ModItems.NOTARFBADIUM_ROD.get());
        tag(Tags.Items.RODS).addTag(ModTags.Items.RODS_NOTARFBADIUM);

        tag(ModTags.Items.RODS_WIKIDIUM).add(ModItems.WIKIDIUM_ROD.get());
        tag(Tags.Items.RODS).addTag(ModTags.Items.RODS_WIKIDIUM);

        tag(ModTags.Items.RODS_THATLDU).add(ModItems.THATLDU_ROD.get());
        tag(Tags.Items.RODS).addTag(ModTags.Items.RODS_THATLDU);

        tag(ModTags.Items.RODS).addTag(ModTags.Items.RODS_NOTSOGUDIUM);
        tag(ModTags.Items.RODS).addTag(ModTags.Items.RODS_KUDBEBEDDA);
        tag(ModTags.Items.RODS).addTag(ModTags.Items.RODS_NOTARFBADIUM);
        tag(ModTags.Items.RODS).addTag(ModTags.Items.RODS_WIKIDIUM);
        tag(ModTags.Items.RODS).addTag(ModTags.Items.RODS_THATLDU);
    }

    private void addChunkItemTags() {
        tag(ModTags.Items.ORES_NOTSOGUDIUM).add(ModItems.NOTSOGUDIUM_CHUNK.get());
        tag(ModTags.Items.ORES_KUDBEBEDDA).add(ModItems.KUDBEBEDDA_CHUNK.get());
        tag(ModTags.Items.ORES_NOTARFBADIUM).add(ModItems.NOTARFBADIUM_CHUNK.get());
        tag(ModTags.Items.ORES_WIKIDIUM).add(ModItems.WIKIDIUM_CHUNK.get());
        tag(ModTags.Items.ORES_THATLDU).add(ModItems.THATLDU_CHUNK.get());

        tag(ModTags.Items.ORES).addTag(ModTags.Items.ORES_NOTSOGUDIUM);
        tag(ModTags.Items.ORES).addTag(ModTags.Items.ORES_KUDBEBEDDA);
        tag(ModTags.Items.ORES).addTag(ModTags.Items.ORES_NOTARFBADIUM);
        tag(ModTags.Items.ORES).addTag(ModTags.Items.ORES_WIKIDIUM);
        tag(ModTags.Items.ORES).addTag(ModTags.Items.ORES_THATLDU);
    }

    private void addPieceItemTags() {
        tag(ModTags.Items.PIECES_NOTSOGUDIUM).add(ModItems.NOTSOGUDIUM_PIECE.get());
        tag(ModTags.Items.PIECES_KUDBEBEDDA).add(ModItems.KUDBEBEDDA_PIECE.get());
        tag(ModTags.Items.PIECES_NOTARFBADIUM).add(ModItems.NOTARFBADIUM_PIECE.get());
        tag(ModTags.Items.PIECES_WIKIDIUM).add(ModItems.WIKIDIUM_PIECE.get());
        tag(ModTags.Items.PIECES_THATLDU).add(ModItems.THATLDU_PIECE.get());

        tag(ModTags.Items.PIECES_CARBON_DEPOSIT).add(ModItems.CARBON_DEPOSIT.get());

        tag(ModTags.Items.PIECES).addTag(ModTags.Items.PIECES_NOTSOGUDIUM);
        tag(ModTags.Items.PIECES).addTag(ModTags.Items.PIECES_KUDBEBEDDA);
        tag(ModTags.Items.PIECES).addTag(ModTags.Items.PIECES_NOTARFBADIUM);
        tag(ModTags.Items.PIECES).addTag(ModTags.Items.PIECES_WIKIDIUM);
        tag(ModTags.Items.PIECES).addTag(ModTags.Items.PIECES_THATLDU);

        tag(ModTags.Items.PIECES).addTag(ModTags.Items.PIECES_CARBON_DEPOSIT);
    }

    private void addChestItemTags() {
        // copy block tags to respective item tags
        copy(ModTags.Blocks.CHESTS_NOTSOGUDIUM, ModTags.Items.CHESTS_NOTSOGUDIUM);
        copy(ModTags.Blocks.CHESTS_KUDBEBEDDA, ModTags.Items.CHESTS_KUDBEBEDDA);
        copy(ModTags.Blocks.CHESTS_NOTARFBADIUM, ModTags.Items.CHESTS_NOTARFBADIUM);
        copy(ModTags.Blocks.CHESTS_WIKIDIUM, ModTags.Items.CHESTS_WIKIDIUM);
        copy(ModTags.Blocks.CHESTS_THATLDU, ModTags.Items.CHESTS_THATLDU);
        copy(Tags.Blocks.CHESTS, Tags.Items.CHESTS);

        tag(ModTags.Items.CHESTS).addTag(ModTags.Items.CHESTS_NOTSOGUDIUM);
        tag(ModTags.Items.CHESTS).addTag(ModTags.Items.CHESTS_KUDBEBEDDA);
        tag(ModTags.Items.CHESTS).addTag(ModTags.Items.CHESTS_NOTARFBADIUM);
        tag(ModTags.Items.CHESTS).addTag(ModTags.Items.CHESTS_WIKIDIUM);
        tag(ModTags.Items.CHESTS).addTag(ModTags.Items.CHESTS_THATLDU);
    }

    private void addCraftingTableItemTags() {
        // copy block tags to respective item tags
        copy(ModTags.Blocks.CRAFTING_TABLES_NOTSOGUDIUM, ModTags.Items.CRAFTING_TABLES_NOTSOGUDIUM);
        copy(ModTags.Blocks.CRAFTING_TABLES_KUDBEBEDDA, ModTags.Items.CRAFTING_TABLES_KUDBEBEDDA);
        copy(ModTags.Blocks.CRAFTING_TABLES_NOTARFBADIUM, ModTags.Items.CRAFTING_TABLES_NOTARFBADIUM);
        copy(ModTags.Blocks.CRAFTING_TABLES_WIKIDIUM, ModTags.Items.CRAFTING_TABLES_WIKIDIUM);
        copy(ModTags.Blocks.CRAFTING_TABLES_THATLDU, ModTags.Items.CRAFTING_TABLES_THATLDU);

        tag(ModTags.Items.CRAFTING_TABLES).addTag(ModTags.Items.CRAFTING_TABLES_NOTSOGUDIUM);
        tag(ModTags.Items.CRAFTING_TABLES).addTag(ModTags.Items.CRAFTING_TABLES_KUDBEBEDDA);
        tag(ModTags.Items.CRAFTING_TABLES).addTag(ModTags.Items.CRAFTING_TABLES_NOTARFBADIUM);
        tag(ModTags.Items.CRAFTING_TABLES).addTag(ModTags.Items.CRAFTING_TABLES_WIKIDIUM);
        tag(ModTags.Items.CRAFTING_TABLES).addTag(ModTags.Items.CRAFTING_TABLES_THATLDU);
    }

    private void addFurnaceItemTags() {
        // copy block tags to respective item tags
        copy(ModTags.Blocks.FURNACES_NOTSOGUDIUM, ModTags.Items.FURNACES_NOTSOGUDIUM);
        copy(ModTags.Blocks.FURNACES_KUDBEBEDDA, ModTags.Items.FURNACES_KUDBEBEDDA);
        copy(ModTags.Blocks.FURNACES_NOTARFBADIUM, ModTags.Items.FURNACES_NOTARFBADIUM);
        copy(ModTags.Blocks.FURNACES_WIKIDIUM, ModTags.Items.FURNACES_WIKIDIUM);
        copy(ModTags.Blocks.FURNACES_THATLDU, ModTags.Items.FURNACES_THATLDU);
        copy(ModTags.Blocks.FURNACES_EUMUS, ModTags.Items.FURNACES_EUMUS);

        tag(ModTags.Items.FURNACES).addTag(ModTags.Items.FURNACES_NOTSOGUDIUM);
        tag(ModTags.Items.FURNACES).addTag(ModTags.Items.FURNACES_KUDBEBEDDA);
        tag(ModTags.Items.FURNACES).addTag(ModTags.Items.FURNACES_NOTARFBADIUM);
        tag(ModTags.Items.FURNACES).addTag(ModTags.Items.FURNACES_WIKIDIUM);
        tag(ModTags.Items.FURNACES).addTag(ModTags.Items.FURNACES_THATLDU);
        tag(ModTags.Items.FURNACES).addTag(ModTags.Items.FURNACES_EUMUS);
    }

    @SuppressWarnings("unchecked")
    private void addSingularityTags()
    {
        tag(ModTags.Items.QUANTUM_SINGULARITIES_ANDESITE).add(ModItems.ANDESITE_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_BASALT).add(ModItems.BASALT_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_BLACKSTONE).add(ModItems.BLACKSTONE_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_CARBON_NANOTUBE).add(ModItems.CARBON_NANOTUBE_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_CELLULOSE).add(ModItems.CELLULOSE_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_CLAY).add(ModItems.CLAY_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_COAL).add(ModItems.COAL_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_COBBLESTONE).add(ModItems.COBBLESTONE_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_DIORITE).add(ModItems.DIORITE_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_DIRT).add(ModItems.DIRT_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_DUST).add(ModItems.DUST_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_GLOWSTONE).add(ModItems.GLOWSTONE_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_GRANITE).add(ModItems.GRANITE_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_GRAVEL).add(ModItems.GRAVEL_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_KUDBEBEDDA).add(ModItems.KUDBEBEDDA_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_LAPIS).add(ModItems.LAPIS_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_LOG_ACACIA).add(ModItems.ACACIA_LOG_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_LOG_BIRCH).add(ModItems.BIRCH_LOG_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_LOG_DARK_OAK).add(ModItems.DARK_OAK_LOG_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_LOG_JUNGLE).add(ModItems.JUNGLE_LOG_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_LOG_OAK).add(ModItems.OAK_LOG_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_LOG_SPRUCE).add(ModItems.SPRUCE_LOG_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_NOTARFBADIUM).add(ModItems.NOTARFBADIUM_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_NOTSOGUDIUM).add(ModItems.NOTSOGUDIUM_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_REDSTONE).add(ModItems.REDSTONE_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_SAND).add(ModItems.SAND_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_SILICA_DUST).add(ModItems.SILICA_DUST_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_THATLDU).add(ModItems.THATLDU_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_WIKIDIUM).add(ModItems.WIKIDIUM_QUANTUM_SINGULARITY.get());

        tag(ModTags.Items.QUANTUM_SINGULARITIES_TUFF).add(ModItems.TUFF_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_CALCITE).add(ModItems.CALCITE_QUANTUM_SINGULARITY.get());

        tag(ModTags.Items.QUANTUM_SINGULARITIES_ENDSTONE).add(ModItems.ENDSTONE_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_NETHERRACK).add(ModItems.NETHERRACK_QUANTUM_SINGULARITY.get());

        tag(ModTags.Items.QUANTUM_SINGULARITIES_ZINC).add(ModItems.ZINC_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_TIN).add(ModItems.TIN_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_COPPER).add(ModItems.COPPER_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_NICKEL).add(ModItems.NICKEL_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_SILVER).add(ModItems.SILVER_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_ALUMINIUM).add(ModItems.ALUMINIUM_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_IRON).add(ModItems.IRON_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_GOLD).add(ModItems.GOLD_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_LEAD).add(ModItems.LEAD_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_URANIUM).add(ModItems.URANIUM_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_URANINITE).add(ModItems.URANINITE_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_OSMIUM).add(ModItems.OSMIUM_QUANTUM_SINGULARITY.get());

        tag(ModTags.Items.QUANTUM_SINGULARITIES_DIAMOND).add(ModItems.DIAMOND_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_EMERALD).add(ModItems.EMERALD_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_STEEL).add(ModItems.STEEL_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_BRONZE).add(ModItems.BRONZE_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_ELECTRUM).add(ModItems.ELECTRUM_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_INVAR).add(ModItems.INVAR_QUANTUM_SINGULARITY.get());
        tag(ModTags.Items.QUANTUM_SINGULARITIES_PLATINUM).add(ModItems.PLATINUM_QUANTUM_SINGULARITY.get());

        tag(ModTags.Items.QUANTUM_SINGULARITIES)
            .addTags(
                ModTags.Items.QUANTUM_SINGULARITIES_ANDESITE,
                ModTags.Items.QUANTUM_SINGULARITIES_BASALT,
                ModTags.Items.QUANTUM_SINGULARITIES_BLACKSTONE,
                ModTags.Items.QUANTUM_SINGULARITIES_CARBON_NANOTUBE,
                ModTags.Items.QUANTUM_SINGULARITIES_CELLULOSE,
                ModTags.Items.QUANTUM_SINGULARITIES_CLAY,
                ModTags.Items.QUANTUM_SINGULARITIES_COAL,
                ModTags.Items.QUANTUM_SINGULARITIES_COBBLESTONE,
                ModTags.Items.QUANTUM_SINGULARITIES_DIORITE,
                ModTags.Items.QUANTUM_SINGULARITIES_DIRT,
                ModTags.Items.QUANTUM_SINGULARITIES_DUST,
                ModTags.Items.QUANTUM_SINGULARITIES_GLOWSTONE,
                ModTags.Items.QUANTUM_SINGULARITIES_GRANITE,
                ModTags.Items.QUANTUM_SINGULARITIES_GRAVEL,
                ModTags.Items.QUANTUM_SINGULARITIES_KUDBEBEDDA,
                ModTags.Items.QUANTUM_SINGULARITIES_LAPIS,
                ModTags.Items.QUANTUM_SINGULARITIES_LOG_ACACIA,
                ModTags.Items.QUANTUM_SINGULARITIES_LOG_BIRCH,
                ModTags.Items.QUANTUM_SINGULARITIES_LOG_DARK_OAK,
                ModTags.Items.QUANTUM_SINGULARITIES_LOG_JUNGLE,
                ModTags.Items.QUANTUM_SINGULARITIES_LOG_OAK,
                ModTags.Items.QUANTUM_SINGULARITIES_LOG_SPRUCE,
                ModTags.Items.QUANTUM_SINGULARITIES_NOTARFBADIUM,
                ModTags.Items.QUANTUM_SINGULARITIES_NOTSOGUDIUM,
                ModTags.Items.QUANTUM_SINGULARITIES_REDSTONE,
                ModTags.Items.QUANTUM_SINGULARITIES_SAND,
                ModTags.Items.QUANTUM_SINGULARITIES_SILICA_DUST,
                ModTags.Items.QUANTUM_SINGULARITIES_THATLDU,
                ModTags.Items.QUANTUM_SINGULARITIES_WIKIDIUM,

                // xd
                ModTags.Items.QUANTUM_SINGULARITIES_CALCITE,
                ModTags.Items.QUANTUM_SINGULARITIES_TUFF,

                ModTags.Items.QUANTUM_SINGULARITIES_ENDSTONE,
                ModTags.Items.QUANTUM_SINGULARITIES_NETHERRACK,

                ModTags.Items.QUANTUM_SINGULARITIES_ZINC,
                ModTags.Items.QUANTUM_SINGULARITIES_TIN,
                ModTags.Items.QUANTUM_SINGULARITIES_COPPER,
                ModTags.Items.QUANTUM_SINGULARITIES_NICKEL,
                ModTags.Items.QUANTUM_SINGULARITIES_SILVER,
                ModTags.Items.QUANTUM_SINGULARITIES_ALUMINIUM,
                ModTags.Items.QUANTUM_SINGULARITIES_IRON,
                ModTags.Items.QUANTUM_SINGULARITIES_GOLD,
                ModTags.Items.QUANTUM_SINGULARITIES_LEAD,
                ModTags.Items.QUANTUM_SINGULARITIES_URANIUM,
                ModTags.Items.QUANTUM_SINGULARITIES_URANINITE,
                ModTags.Items.QUANTUM_SINGULARITIES_OSMIUM,

                ModTags.Items.QUANTUM_SINGULARITIES_DIAMOND,
                ModTags.Items.QUANTUM_SINGULARITIES_EMERALD,
                ModTags.Items.QUANTUM_SINGULARITIES_STEEL,
                ModTags.Items.QUANTUM_SINGULARITIES_BRONZE,
                ModTags.Items.QUANTUM_SINGULARITIES_ELECTRUM,
                ModTags.Items.QUANTUM_SINGULARITIES_INVAR,
                ModTags.Items.QUANTUM_SINGULARITIES_PLATINUM
            ).add(
                ModItems.QUANTUM_SINGULARITY.get()
            );
    }
}

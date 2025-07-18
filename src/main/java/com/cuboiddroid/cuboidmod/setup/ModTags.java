package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

public class ModTags {
    public static final class Fluids {
        public static final TagKey<Fluid> MINECRAFT_FLUID_WATER = minecraft("water");
        public static final TagKey<Fluid> MINECRAFT_FLUID_LAVA = minecraft("lava");

        private static TagKey<Fluid> forge(String path) {
            return FluidTags.create(new ResourceLocation("forge", path));
        }

        private static TagKey<Fluid> minecraft(String path) {
            return FluidTags.create(new ResourceLocation("minecraft", path));
        }

        private static TagKey<Fluid> mod(String path) {
            return FluidTags.create(new ResourceLocation(CuboidMod.MOD_ID, path));
        }
    }

    public static final class Blocks {
        public static final TagKey<Block> ORES_NOTSOGUDIUM = forge("ores/notsogudium");
        public static final TagKey<Block> STORAGE_BLOCKS_NOTSOGUDIUM = forge("storage_blocks/notsogudium");
        public static final TagKey<Block> CHESTS_NOTSOGUDIUM = forge("chests/notsogudium");
        public static final TagKey<Block> CRAFTING_TABLES_NOTSOGUDIUM = forge("crafting_tables/notsogudium");
        public static final TagKey<Block> FURNACES_NOTSOGUDIUM = mod("furnaces/notsogudium");

        public static final TagKey<Block> ORES_KUDBEBEDDA = forge("ores/kudbebedda");
        public static final TagKey<Block> STORAGE_BLOCKS_KUDBEBEDDA = forge("storage_blocks/kudbebedda");
        public static final TagKey<Block> CHESTS_KUDBEBEDDA = forge("chests/kudbebedda");
        public static final TagKey<Block> CRAFTING_TABLES_KUDBEBEDDA = forge("crafting_tables/kudbebedda");
        public static final TagKey<Block> FURNACES_KUDBEBEDDA = mod("furnaces/kudbebedda");

        public static final TagKey<Block> ORES_NOTARFBADIUM = forge("ores/notarfbadium");
        public static final TagKey<Block> STORAGE_BLOCKS_NOTARFBADIUM = forge("storage_blocks/notarfbadium");
        public static final TagKey<Block> CHESTS_NOTARFBADIUM = forge("chests/notarfbadium");
        public static final TagKey<Block> CRAFTING_TABLES_NOTARFBADIUM = forge("crafting_tables/notarfbadium");
        public static final TagKey<Block> FURNACES_NOTARFBADIUM = mod("furnaces/notarfbadium");

        public static final TagKey<Block> ORES_WIKIDIUM = forge("ores/wikidium");
        public static final TagKey<Block> STORAGE_BLOCKS_WIKIDIUM = forge("storage_blocks/wikidium");
        public static final TagKey<Block> CHESTS_WIKIDIUM = forge("chests/wikidium");
        public static final TagKey<Block> CRAFTING_TABLES_WIKIDIUM = forge("crafting_tables/wikidium");
        public static final TagKey<Block> FURNACES_WIKIDIUM = mod("furnaces/wikidium");

        public static final TagKey<Block> ORES_THATLDU = forge("ores/thatldu");
        public static final TagKey<Block> STORAGE_BLOCKS_THATLDU = forge("storage_blocks/thatldu");
        public static final TagKey<Block> CHESTS_THATLDU = forge("chests/thatldu");
        public static final TagKey<Block> CRAFTING_TABLES_THATLDU = forge("crafting_tables/thatldu");
        public static final TagKey<Block> FURNACES_THATLDU = mod("furnaces/thatldu");
        public static final TagKey<Block> FURNACES_EUMUS = mod("furnaces/eumus");

        public static final TagKey<Block> STORAGE_BLOCKS_SILICA = forge("storage_blocks/silica");
        public static final TagKey<Block> STORAGE_BLOCKS_CARBON_NANOTUBE = forge("storage_blocks/carbon_nanotube");
        public static final TagKey<Block> STORAGE_BLOCKS_PROTEIN_PASTE = forge("storage_blocks/protein_paste");
        public static final TagKey<Block> STORAGE_BLOCKS_PROTEIN_FIBER = forge("storage_blocks/protein_fiber");
        public static final TagKey<Block> STORAGE_BLOCKS_CELLULOSE = forge("storage_blocks/cellulose");

        public static final TagKey<Block> NEEDS_NOTSOGUDIUM_TOOL = BlockTags.create(new ResourceLocation(CuboidMod.MOD_ID, "needs_notsogudium_tool"));
        public static final TagKey<Block> NEEDS_KUDBEBEDDA_TOOL = BlockTags.create(new ResourceLocation(CuboidMod.MOD_ID, "needs_kudbebedda_tool"));
        public static final TagKey<Block> NEEDS_NOTARFBADIUM_TOOL = BlockTags.create(new ResourceLocation(CuboidMod.MOD_ID, "needs_notarfbadium_tool"));
        public static final TagKey<Block> NEEDS_WIKIDIUM_TOOL = BlockTags.create(new ResourceLocation(CuboidMod.MOD_ID, "needs_wikidium_tool"));
        public static final TagKey<Block> NEEDS_THATLDU_TOOL = BlockTags.create(new ResourceLocation(CuboidMod.MOD_ID, "needs_thatldu_tool"));

        private static TagKey<Block> forge(String path) {
            return BlockTags.create(new ResourceLocation("forge", path));
        }

        private static TagKey<Block> minecraft(String path) {
            return BlockTags.create(new ResourceLocation("minecraft", path));
        }

        private static TagKey<Block> mod(String path) {
            return BlockTags.create(new ResourceLocation(CuboidMod.MOD_ID, path)); 
        }
    }

    public static final class Items {
        public static final TagKey<Item> ORES = mod("ores");
        public static final TagKey<Item> FORGE_ORES = forge("ores");
        public static final TagKey<Item> STORAGE_BLOCKS = mod("storage_blocks");
        public static final TagKey<Item> INGOTS = mod("ingots");
        public static final TagKey<Item> NUGGETS = mod("nuggets");
        public static final TagKey<Item> DUSTS = mod("dusts");
        public static final TagKey<Item> PIECES = mod("pieces");
        public static final TagKey<Item> RODS = mod("rods");
        public static final TagKey<Item> CHESTS = mod("chests");
        public static final TagKey<Item> FOOD = mod("food");
        public static final TagKey<Item> CRAFTING_TABLES = mod("crafting_tables");
        public static final TagKey<Item> FURNACES = mod("furnaces");
        public static final TagKey<Item> QUANTUM_SINGULARITIES = mod("quantum_singularities");

        // notsogudium
        public static final TagKey<Item> ORES_NOTSOGUDIUM = forge("ores/notsogudium");
        public static final TagKey<Item> STORAGE_BLOCKS_NOTSOGUDIUM = forge("storage_blocks/notsogudium");
        public static final TagKey<Item> CHESTS_NOTSOGUDIUM = forge("chests/notsogudium");
        public static final TagKey<Item> CRAFTING_TABLES_NOTSOGUDIUM = forge("crafting_tables/notsogudium");
        public static final TagKey<Item> FURNACES_NOTSOGUDIUM = forge("furnaces/notsogudium");

        public static final TagKey<Item> INGOTS_NOTSOGUDIUM = forge("ingots/notsogudium");
        public static final TagKey<Item> NUGGETS_NOTSOGUDIUM = forge("nuggets/notsogudium");
        public static final TagKey<Item> DUSTS_NOTSOGUDIUM = forge("dusts/notsogudium");
        public static final TagKey<Item> RODS_NOTSOGUDIUM = forge("rods/notsogudium");

        public static final TagKey<Item> PIECES_NOTSOGUDIUM = mod("pieces/notsogudium");

        // kudbebedda
        public static final TagKey<Item> ORES_KUDBEBEDDA = forge("ores/kudbebedda");
        public static final TagKey<Item> STORAGE_BLOCKS_KUDBEBEDDA = forge("storage_blocks/kudbebedda");
        public static final TagKey<Item> CHESTS_KUDBEBEDDA = forge("chests/kudbebedda");
        public static final TagKey<Item> CRAFTING_TABLES_KUDBEBEDDA = forge("crafting_tables/kudbebedda");
        public static final TagKey<Item> FURNACES_KUDBEBEDDA = forge("furnaces/kudbebedda");

        public static final TagKey<Item> INGOTS_KUDBEBEDDA = forge("ingots/kudbebedda");
        public static final TagKey<Item> NUGGETS_KUDBEBEDDA = forge("nuggets/kudbebedda");
        public static final TagKey<Item> DUSTS_KUDBEBEDDA = forge("dusts/kudbebedda");
        public static final TagKey<Item> RODS_KUDBEBEDDA = forge("rods/kudbebedda");

        public static final TagKey<Item> PIECES_KUDBEBEDDA = mod("pieces/kudbebedda");

        // notarfbadium
        public static final TagKey<Item> ORES_NOTARFBADIUM = forge("ores/notarfbadium");
        public static final TagKey<Item> STORAGE_BLOCKS_NOTARFBADIUM = forge("storage_blocks/notarfbadium");
        public static final TagKey<Item> CHESTS_NOTARFBADIUM = forge("chests/notarfbadium");
        public static final TagKey<Item> CRAFTING_TABLES_NOTARFBADIUM = forge("crafting_tables/notarfbadium");
        public static final TagKey<Item> FURNACES_NOTARFBADIUM = forge("furnaces/notarfbadium");

        public static final TagKey<Item> INGOTS_NOTARFBADIUM = forge("ingots/notarfbadium");
        public static final TagKey<Item> NUGGETS_NOTARFBADIUM = forge("nuggets/notarfbadium");
        public static final TagKey<Item> DUSTS_NOTARFBADIUM = forge("dusts/notarfbadium");
        public static final TagKey<Item> RODS_NOTARFBADIUM = forge("rods/notarfbadium");

        public static final TagKey<Item> PIECES_NOTARFBADIUM = mod("pieces/notarfbadium");

        // wikidium
        public static final TagKey<Item> ORES_WIKIDIUM = forge("ores/wikidium");
        public static final TagKey<Item> STORAGE_BLOCKS_WIKIDIUM = forge("storage_blocks/wikidium");
        public static final TagKey<Item> CHESTS_WIKIDIUM = forge("chests/wikidium");
        public static final TagKey<Item> CRAFTING_TABLES_WIKIDIUM = forge("crafting_tables/wikidium");
        public static final TagKey<Item> FURNACES_WIKIDIUM = forge("furnaces/wikidium");

        public static final TagKey<Item> INGOTS_WIKIDIUM = forge("ingots/wikidium");
        public static final TagKey<Item> NUGGETS_WIKIDIUM = forge("nuggets/wikidium");
        public static final TagKey<Item> DUSTS_WIKIDIUM = forge("dusts/wikidium");
        public static final TagKey<Item> RODS_WIKIDIUM = forge("rods/wikidium");

        public static final TagKey<Item> PIECES_WIKIDIUM = mod("pieces/wikidium");

        // thatldu
        public static final TagKey<Item> ORES_THATLDU = forge("ores/thatldu");
        public static final TagKey<Item> STORAGE_BLOCKS_THATLDU = forge("storage_blocks/thatldu");
        public static final TagKey<Item> CHESTS_THATLDU = forge("chests/thatldu");
        public static final TagKey<Item> CRAFTING_TABLES_THATLDU = forge("crafting_tables/thatldu");
        public static final TagKey<Item> FURNACES_THATLDU = forge("furnaces/thatldu");

        public static final TagKey<Item> INGOTS_THATLDU = forge("ingots/thatldu");
        public static final TagKey<Item> NUGGETS_THATLDU = forge("nuggets/thatldu");
        public static final TagKey<Item> DUSTS_THATLDU = forge("dusts/thatldu");
        public static final TagKey<Item> RODS_THATLDU = forge("rods/thatldu");

        public static final TagKey<Item> PIECES_THATLDU = mod("pieces/thatldu");

        // eumus
        public static final TagKey<Item> FURNACES_EUMUS = forge("furnaces/eumus");

        // silica dust
        public static final TagKey<Item> DUSTS_SILICA = forge("dusts/silica");
        public static final TagKey<Item> STORAGE_BLOCKS_SILICA = forge("storage_blocks/silica");

        // additional dusts
        public static final TagKey<Item> DUSTS_ZINC = forge("dusts/zinc");

        // salt
        public static final TagKey<Item> DUSTS_SALT = forge("dusts/salt");

        // carbon deposit
        public static final TagKey<Item> PIECES_CARBON_DEPOSIT = forge("pieces/carbon_deposit");

        // carbon nanotube
        public static final TagKey<Item> STORAGE_BLOCKS_CARBON_NANOTUBE = forge("storage_blocks/carbon_nanotube");
        public static final TagKey<Item> CARBON_NANOTUBE = mod("carbon_nanotube");

        // protein paste
        public static final TagKey<Item> STORAGE_BLOCKS_PROTEIN_PASTE = forge("storage_blocks/protein_paste");
        public static final TagKey<Item> PROTEIN_PASTE = mod("protein_paste");

        // protein fiber
        public static final TagKey<Item> STORAGE_BLOCKS_PROTEIN_FIBER = forge("storage_blocks/protein_fiber");
        public static final TagKey<Item> PROTEIN_FIBER = mod("protein_fiber");

        // cellulose
        public static final TagKey<Item> STORAGE_BLOCKS_CELLULOSE = forge("storage_blocks/cellulose");
        public static final TagKey<Item> CELLULOSE = mod("cellulose");

        // stick bundle
        public static final TagKey<Item> STICK_BUNDLE = mod("stick_bundle");

        // food
        public static final TagKey<Item> FOOD_APPLE_CIDER_VINEGAR = mod("food/apple_cider_vinegar");
        public static final TagKey<Item> FOOD_ARACHNUGGET = mod("food/arachnugget");
        public static final TagKey<Item> FOOD_BROTH = mod("food/broth");
        public static final TagKey<Item> FOOD_KEBAB_COOKED = mod("food/kebab_cooked");
        public static final TagKey<Item> FOOD_GRUEL = mod("food/gruel");
        public static final TagKey<Item> FOOD_PROTEIN_BAR = mod("food/protein_bar");
        public static final TagKey<Item> FOOD_PROTEIN_PASTE = mod("food/protein_paste");
        public static final TagKey<Item> FOOD_KEBAB_RAW = mod("food/kebab_raw");
        public static final TagKey<Item> FOOD_RATION_PACK = mod("food/ration_pack");
        public static final TagKey<Item> FOOD_ROTTEN_APPLE = mod("food/rotten_apple");

        public static final TagKey<Item> FOOD_CURED_BEEF = mod("food/cured_beef");
        public static final TagKey<Item> FOOD_CURED_FLESH = mod("food/cured_flesh");
        public static final TagKey<Item> FOOD_BEEF_BILTONG = mod("food/beef_biltong");
        public static final TagKey<Item> FOOD_ZOMBIE_BILTONG = mod("food/zombie_biltong");
        public static final TagKey<Item> FOOD_HARDFISKUR = mod("food/hardfiskur");

        // quantum singularities
        public static final TagKey<Item> QUANTUM_SINGULARITIES_ANDESITE = mod("quantum_singularities/andesite");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_BASALT = mod("quantum_singularities/basalt");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_BLACKSTONE = mod("quantum_singularities/blackstone");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_CARBON_NANOTUBE = mod("quantum_singularities/carbon_nanotube");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_CELLULOSE = mod("quantum_singularities/cellulose");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_CLAY = mod("quantum_singularities/clay");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_COAL = mod("quantum_singularities/coal");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_COBBLESTONE = mod("quantum_singularities/cobblestone");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_DIORITE = mod("quantum_singularities/diorite");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_DIRT = mod("quantum_singularities/dirt");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_DUST = mod("quantum_singularities/dust");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_GLOWSTONE = mod("quantum_singularities/glowstone");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_GRANITE = mod("quantum_singularities/granite");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_GRAVEL = mod("quantum_singularities/gravel");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_KUDBEBEDDA = mod("quantum_singularities/kudbebedda");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_LAPIS = mod("quantum_singularities/lapis");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_LOG_ACACIA = mod("quantum_singularities/log_acacia");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_LOG_BIRCH = mod("quantum_singularities/log_birch");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_LOG_DARK_OAK = mod("quantum_singularities/log_dark_oak");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_LOG_JUNGLE = mod("quantum_singularities/log_jungle");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_LOG_OAK = mod("quantum_singularities/log_oak");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_LOG_SPRUCE = mod("quantum_singularities/log_spruce");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_NOTARFBADIUM = mod("quantum_singularities/notarfbadium");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_NOTSOGUDIUM = mod("quantum_singularities/notsogudium");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_REDSTONE = mod("quantum_singularities/redstone");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_SAND = mod("quantum_singularities/sand");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_SILICA_DUST = mod("quantum_singularities/silica_dust");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_THATLDU = mod("quantum_singularities/thatldu");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_WIKIDIUM = mod("quantum_singularities/wikidium");

        public static final TagKey<Item> QUANTUM_SINGULARITIES_TUFF = mod("quantum_singularities/tuff");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_CALCITE = mod("quantum_singularities/calcite");

        public static final TagKey<Item> QUANTUM_SINGULARITIES_ENDSTONE = mod("quantum_singularities/endstone");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_NETHERRACK = mod("quantum_singularities/netherrack");

        public static final TagKey<Item> QUANTUM_SINGULARITIES_ZINC = mod("quantum_singularities/zinc");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_TIN = mod("quantum_singularities/tin");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_COPPER = mod("quantum_singularities/copper");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_NICKEL = mod("quantum_singularities/nickel");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_SILVER = mod("quantum_singularities/silver");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_ALUMINIUM = mod("quantum_singularities/aluminium");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_IRON = mod("quantum_singularities/iron");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_GOLD = mod("quantum_singularities/gold");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_LEAD = mod("quantum_singularities/lead");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_URANIUM = mod("quantum_singularities/uranium");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_URANINITE = mod("quantum_singularities/uraninite");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_OSMIUM = mod("quantum_singularities/osmium");

        public static final TagKey<Item> QUANTUM_SINGULARITIES_DIAMOND = mod("quantum_singularities/diamond");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_EMERALD = mod("quantum_singularities/emerald");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_STEEL = mod("quantum_singularities/steel");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_BRONZE = mod("quantum_singularities/bronze");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_ELECTRUM = mod("quantum_singularities/electrum");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_INVAR = mod("quantum_singularities/invar");
        public static final TagKey<Item> QUANTUM_SINGULARITIES_PLATINUM = mod("quantum_singularities/platinum");

        private static TagKey<Item> forge(String path) {
            return ItemTags.create(new ResourceLocation("forge", path));
        }

        private static TagKey<Item> minecraft(String path) {
            return ItemTags.create(new ResourceLocation("minecraft", path));
        }

        private static TagKey<Item> mod(String path) {
            return ItemTags.create(new ResourceLocation(CuboidMod.MOD_ID, path));
        }
    }
}

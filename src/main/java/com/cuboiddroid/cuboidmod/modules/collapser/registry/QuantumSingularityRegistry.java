package com.cuboiddroid.cuboidmod.modules.collapser.registry;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.recipe.QuantumCollapsingRecipe;
import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.resourcegen.recipe.ResourceGeneratingRecipe;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.loading.FMLPaths;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class QuantumSingularityRegistry {

    private static boolean locked = false;

    // CRAFTING AMOUNTS
    private static final int LOG_RECIPE_AMOUNT = 320; // LOGS
    private static final int THADTASMINE_RECIPE_AMOUNT = 320; // CUBOID INGOTS
    private static final int GEM_RECIPE_AMOUNT = 256; // GEMS
    private static final int MISC_MATERIAL_RECIPE_AMOUNT = 192; // MISC MATERIALS
    private static final int INGOT_RECIPE_AMOUNT = 160; // IGNOTS
    private static final int NATUAL_ITEM_RECIPE_AMOUNT = 128; // BASE
    private static final int MANUFACTURED_MATERIAL_RECIPE_AMOUNT = 96; // Modded Materials
    private static final int NATUAL_MATERIAL_RECIPE_AMOUNT = 64; // Generic Materials
    private static final int FUEL_MATERIAL_RECIPE_AMOUNT = 32; // COAL BLOCKS
    
    // PRODUCTION WORK TIMES
    private static final float BASE_WORK_TIME = 1.0f;
    private static final float FUEL_WORK_TIME = 1.2f;
    private static final float GRAVEL_WORK_TIME = 1.25f;
    private static final float EARTH_WORK_TIME = 1.5f;
    private static final float DUST_WORK_TIME = 2.0f;
    private static final float STONE_WORK_TIME = 2.5f;
    private static final float NETHER_MATERIAL_WORK_TIME = 3.0f;
    private static final float END_MATERIAL_WORK_TIME = 3.5f;
    private static final float ORE_WORK_TIME = 4.0f;
    private static final float TOUGH_ORE_WORK_TIME = 6.0f;
    private static final float GEM_WORK_TIME = 8.0f;

    // PRODUCTION OUTPUT
    private static final float BASE_POWER_OUTPUT = 1.0f;
    private static final float ORE_POWER_OUTPUT = 0.25f;
    private static final float STONE_POWER_OUTPUT = 0.75f;
    private static final float OTHERWORLDLY_POWER_OUTPUT = 0.4f;
    private static final float DUST_POWER_OUTPUT = 0.5f;
    private static final float GRAVELY_POWER_OUTPUT = 0.85f;

    // POWER GENERATION SCALE
    private static final float LOG_FUEL = 1.0f;
    private static final float TEMPERATE_FUEL = 1.35f;
    private static final float ELECTRIC_FUEL = 1.75f;
    private static final float GLOWING_FUEL = 1.5f;
    private static final float URANIUM_FUEL = 2.5f;


    private static final boolean VERBOSE_LOGGING = Config.verboseLogging.get();

    private static final QuantumSingularity MISSING_SINGULARITY = new QuantumSingularity(null, new int[] { 0xFF0000, 0xDD0000 });

    private static final QuantumSingularityRegistry INSTANCE = new QuantumSingularityRegistry();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();

    private final Map<ResourceLocation, QuantumSingularity> singularities = new LinkedHashMap<>();

    public void registerSingularity(TempQuantumSingularity tempSingularity) {
        this.singularities.putIfAbsent(tempSingularity.id, new QuantumSingularity(tempSingularity));

        if (this.singularities.containsKey(tempSingularity.id)) {
            QuantumSingularity singularity = this.singularities.get(tempSingularity.id)
                .setDisabled(tempSingularity.isDisabled());

            if (tempSingularity.getColors() != null) singularity.setColors(tempSingularity.getColors());
            if (tempSingularity.getRecipe() != null) singularity.setRecipe(tempSingularity.getRecipe());
            if (tempSingularity.getProduction() != null) singularity.setProduction(tempSingularity.getProduction());
            if (tempSingularity.getPowerOutput() != null) singularity.setPowerOutput(tempSingularity.getPowerOutput());
        }
    }

    public List<QuantumSingularity> getSingularities() {
        return Lists.newArrayList(this.singularities.values());
    }
    
    public boolean hasSingularity(ResourceLocation id) {
        return this.singularities.containsKey(id);
    }

    public QuantumSingularity getSingularity(ResourceLocation id) {
        return this.singularities.getOrDefault(id, MISSING_SINGULARITY);
    }

    public static QuantumSingularityRegistry getInstance() {
        return INSTANCE;
    }

    public void loadSingularities() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        File dir = FMLPaths.CONFIGDIR.get().resolve(CuboidMod.MOD_ID + "/quantum_singularities/").toFile();

        if (VERBOSE_LOGGING) CuboidMod.LOGGER.info("Loading quantum singularities");

        if (!dir.exists() && dir.mkdirs()) {
            if (VERBOSE_LOGGING) CuboidMod.LOGGER.info("Creating default singularity configs");

            TempQuantumSingularity example = new TempQuantumSingularity(
                new ResourceLocation(CuboidMod.MOD_ID, "example"),
                new int[] { 0xFF00FF, 0x000000 }
            ).setRecipe("minecraft:bedrock", 1)
                .setProduction("minecraft:bedrock", 1, 1)
                .setPowerOutput(1)
                .setDisabled(true);

            JsonObject json = QuantumSingularityUtils.writeToJson(example);
            FileWriter writer = null;

            try {
                File file = new File(dir, example.getId().getPath() + ".json");
                writer = new FileWriter(file);
                GSON.toJson(json, writer);
                writer.close();
            } catch (Exception e) {
                CuboidMod.LOGGER.error("An error occurred while generating default singularities", e);
            } finally {
                IOUtils.closeQuietly(writer);
            }
        }

        for (QuantumSingularity singularity : defaults()) {
            this.singularities.put(singularity.getId(), singularity);
        }

        if (!dir.mkdirs() && dir.isDirectory()) {
            this.loadFiles(dir);
        }

        stopwatch.stop();
        CuboidMod.LOGGER.info("Loaded {} Quantum Singularity type(s) in {} ms", this.singularities.size(), stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    public void registerRecipes() {
        if (locked) return;
        Stopwatch stopwatch = Stopwatch.createStarted();

        if (VERBOSE_LOGGING) CuboidMod.LOGGER.info("Registering quantum singularity recipes");

        QuantumCollapsingRecipe.Serializer.INSTANCE.generateFromSingularities(singularities.values());
        ResourceGeneratingRecipe.Serializer.INSTANCE.generateFromSingularities(singularities.values());
        PowerGeneratingRecipe.Serializer.INSTANCE.generateFromSingularities(singularities.values());

        stopwatch.stop();
        CuboidMod.LOGGER.info("Loaded Quantum Singularity recipe(s) in {} ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private void loadFiles(File dir) {
        File[] files = dir.listFiles((FileFilter) FileFilterUtils.suffixFileFilter(".json"));
        if (files == null)
            return;

        for (File file : files) {
            JsonObject json;
            FileReader reader = null;
            TempQuantumSingularity singularity = null;

            try {
                reader = new FileReader(file);
                json = JsonParser.parseReader(reader).getAsJsonObject();
                String name = file.getName().replace(".json", "");
                if (VERBOSE_LOGGING) CuboidMod.LOGGER.info("Loading Quantum Singularity: " + name);
                singularity = QuantumSingularityUtils.loadFromJson(new ResourceLocation(CuboidMod.MOD_ID, name), json);

                reader.close();
            } catch (Exception e) {
                CuboidMod.LOGGER.error("An error occurred while loading Quantum Singularities", e);
            } finally {
                IOUtils.closeQuietly(reader);
            }

            if (singularity != null) {
                registerSingularity(singularity);
            }
        }
    }

    private static List<QuantumSingularity> defaults() {
        return Lists.newArrayList(
            defaultSingularity("acacia_log", new int[] { 0x9e552f, 0x5a554c })
                .setRecipe("minecraft:acacia_log", LOG_RECIPE_AMOUNT).setPowerOutput(LOG_FUEL)
                .setProduction("minecraft:acacia_log", BASE_WORK_TIME, BASE_POWER_OUTPUT),

            defaultSingularity("aluminium", new int[] { 0xE0F5F3, 0xA1B0AF })
                .setRecipe("#forge:ingots/aluminum", INGOT_RECIPE_AMOUNT)
                .setProduction("exnihilosequentia:raw_aluminum", ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("andesite", new int[]{ 0x88888c, 0x4a4a4a })
                .setRecipe("minecraft:andesite", NATUAL_ITEM_RECIPE_AMOUNT)
                .setProduction("minecraft:andesite", STONE_WORK_TIME, STONE_POWER_OUTPUT),

            defaultSingularity("basalt", new int[]{ 0x737373, 0x333033 })
                .setRecipe("minecraft:basalt", NATUAL_ITEM_RECIPE_AMOUNT)
                .setProduction("minecraft:basalt", STONE_WORK_TIME, STONE_POWER_OUTPUT),

            defaultSingularity("birch_log", new int[] { 0xc6b579, 0xa3a3a3 })
                .setRecipe("minecraft:birch_log", LOG_RECIPE_AMOUNT).setPowerOutput(LOG_FUEL)
                .setProduction("minecraft:birch_log", BASE_WORK_TIME, BASE_POWER_OUTPUT),

            defaultSingularity("blackstone", new int[]{0x4d4a53, 0x3b3846})
                .setRecipe("minecraft:blackstone", NATUAL_ITEM_RECIPE_AMOUNT)
                .setProduction("minecraft:blackstone", STONE_WORK_TIME, STONE_POWER_OUTPUT),

            defaultSingularity("bronze", new int[] { 0xe0b43a, 0xa37a07 })
                .setRecipe("#forge:ingots/bronze", INGOT_RECIPE_AMOUNT)
                .setProduction("mekanism:dust_bronze", ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("carbon_nanotube", new int[]{0x877787, 0x554A55})
                .setRecipe("cuboidmod:carbon_nanotube", MANUFACTURED_MATERIAL_RECIPE_AMOUNT)
                .setProduction("cuboidmod:carbon_nanotube", EARTH_WORK_TIME, STONE_POWER_OUTPUT),

            defaultSingularity("cellulose", new int[]{0xC8C0A7, 0xF3E8C8})
                .setRecipe("cuboidmod:cellulose", NATUAL_MATERIAL_RECIPE_AMOUNT)
                .setProduction("cuboidmod:cellulose", BASE_WORK_TIME, BASE_POWER_OUTPUT),

            defaultSingularity("clay", new int[] { 0xAFB9D6, 0x636973 })
                .setRecipe("minecraft:clay", NATUAL_MATERIAL_RECIPE_AMOUNT)
                .setProduction("minecraft:clay_ball", BASE_WORK_TIME, BASE_POWER_OUTPUT),

            defaultSingularity("coal", new int[] { 0x323232, 0x292828 })
                .setRecipe("minecraft:coal_block", FUEL_MATERIAL_RECIPE_AMOUNT).setPowerOutput(LOG_FUEL)
                .setProduction("minecraft:coal", FUEL_WORK_TIME, STONE_POWER_OUTPUT),

            defaultSingularity("cobblestone", new int[] { 0xb3b3b3, 0x747474 })
                .setRecipe("minecraft:cobblestone", NATUAL_ITEM_RECIPE_AMOUNT)
                .setProduction("minecraft:cobblestone", BASE_WORK_TIME, BASE_POWER_OUTPUT),

            defaultSingularity("copper", new int[] { 0xd37a5a, 0x639e76 })
                .setRecipe("minecraft:copper_ingot", INGOT_RECIPE_AMOUNT)
                .setProduction("minecraft:raw_copper", ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("dark_oak_log", new int[] { 0x482e17, 0x2f2413 })
                .setRecipe("minecraft:dark_oak_log", LOG_RECIPE_AMOUNT).setPowerOutput(LOG_FUEL)
                .setProduction("minecraft:dark_oak_log", BASE_WORK_TIME, BASE_POWER_OUTPUT),

            defaultSingularity("diamond", new int[] { 0x49ead6, 0x48a19a })
                .setRecipe("minecraft:diamond", GEM_RECIPE_AMOUNT)
                .setProduction("minecraft:diamond", GEM_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("diorite", new int[]{0xcbcbcc, 0x959595})
                .setRecipe("minecraft:diorite", NATUAL_ITEM_RECIPE_AMOUNT)
                .setProduction("minecraft:diorite", STONE_WORK_TIME, STONE_POWER_OUTPUT),

            defaultSingularity("dirt", new int[] { 0x785439, 0x77553b })
                .setRecipe("minecraft:dirt", NATUAL_ITEM_RECIPE_AMOUNT)
                .setProduction("minecraft:dirt", EARTH_WORK_TIME, STONE_POWER_OUTPUT),

            defaultSingularity("dust", new int[] { 0xF3E8C8, 0xC8C0A7 })
                .setRecipe("exnihilosequentia:dust", NATUAL_ITEM_RECIPE_AMOUNT)
                .setProduction("exnihilosequentia:dust", EARTH_WORK_TIME, STONE_POWER_OUTPUT),

            defaultSingularity("electrum", new int[] { 0xe6da6e, 0xf0d046 })
                .setRecipe("#forge:ingots/electrum", INGOT_RECIPE_AMOUNT)
                .setProduction("immersiveengineering:dust_electrum", ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("emerald", new int[] { 0x17da61, 0x0f8e3f })
                .setRecipe("minecraft:emerald", GEM_RECIPE_AMOUNT)
                .setProduction("minecraft:emerald", GEM_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("endstone", new int[] { 0xebf3b2, 0x898c5f })
                .setRecipe("minecraft:end_stone", INGOT_RECIPE_AMOUNT)
                .setProduction("minecraft:end_stone", END_MATERIAL_WORK_TIME, OTHERWORLDLY_POWER_OUTPUT),

            defaultSingularity("glowstone", new int[] { 0xfcba5d, 0xca8453 })
                .setRecipe("minecraft:glowstone_dust", MISC_MATERIAL_RECIPE_AMOUNT).setPowerOutput(GLOWING_FUEL)
                .setProduction("minecraft:glowstone_dust", DUST_WORK_TIME, DUST_POWER_OUTPUT),

            defaultSingularity("gold", new int[] { 0xfbdd47, 0xa38a28 })
                .setRecipe("minecraft:gold_ingot", INGOT_RECIPE_AMOUNT)
                .setProduction("minecraft:raw_gold", ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("granite", new int[] { 0x9d6a57, 0x6c4c40 })
                .setRecipe("minecraft:granite", NATUAL_ITEM_RECIPE_AMOUNT)
                .setProduction("minecraft:granite", STONE_WORK_TIME, STONE_POWER_OUTPUT),

            defaultSingularity("gravel", new int[] { 0x7f7d7d, 0x616161 })
                .setRecipe("minecraft:gravel", NATUAL_ITEM_RECIPE_AMOUNT)
                .setProduction("minecraft:gravel", GRAVEL_WORK_TIME, GRAVELY_POWER_OUTPUT),

            defaultSingularity("invar", new int[] { 0xa6b6ba, 0x778487 })
                .setRecipe("#forge:ingots/invar", INGOT_RECIPE_AMOUNT)
                .setProduction("thermal:invar_dust", ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("iron", new int[] { 0xd9d9d9, 0x939393 })
                .setRecipe("minecraft:iron_ingot", INGOT_RECIPE_AMOUNT)
                .setProduction("minecraft:raw_iron", ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("jungle_log", new int[] { 0xa87853, 0x392d11 })
                .setRecipe("minecraft:jungle_log", LOG_RECIPE_AMOUNT).setPowerOutput(LOG_FUEL)
                .setProduction("minecraft:jungle_log", BASE_WORK_TIME, BASE_POWER_OUTPUT),

            defaultSingularity("kudbebedda", new int[] { 0x8cd4c0, 0x72b09f })
                .setRecipe("cuboidmod:kudbebedda_ingot", THADTASMINE_RECIPE_AMOUNT)
                .setProduction("cuboidmod:kudbebedda_chunk", BASE_WORK_TIME, BASE_POWER_OUTPUT),

            defaultSingularity("lapis", new int[] { 0x1e4183, 0x12245c })
                .setRecipe("minecraft:lapis_lazuli", MISC_MATERIAL_RECIPE_AMOUNT).setPowerOutput(TEMPERATE_FUEL)
                .setProduction("minecraft:lapis_lazuli", EARTH_WORK_TIME, STONE_POWER_OUTPUT),

            defaultSingularity("lead", new int[] { 0x7D97A6, 0x393C61 })
                .setRecipe("#forge:ingots/lead", INGOT_RECIPE_AMOUNT)
                .setProduction("exnihilosequentia:raw_lead", ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("netherrack", new int[] { 0x4f1b1b, 0x552a2a })
                .setRecipe("minecraft:netherrack", INGOT_RECIPE_AMOUNT)
                .setProduction("minecraft:netherrack", NETHER_MATERIAL_WORK_TIME, OTHERWORLDLY_POWER_OUTPUT),

            defaultSingularity("nickel", new int[] { 0xE7D498, 0x997857 })
                .setRecipe("#forge:ingots/nickel", INGOT_RECIPE_AMOUNT)
                .setProduction("exnihilosequentia:raw_nickel", ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("notarfbadium", new int[] { 0xa1d6bf, 0x79a391 })
                .setRecipe("cuboidmod:notarfbadium_ingot", THADTASMINE_RECIPE_AMOUNT)
                .setProduction("cuboidmod:notarfbadium_chunk", BASE_WORK_TIME, BASE_POWER_OUTPUT),

            defaultSingularity("notsogudium", new int[] { 0x9de0d9, 0x7db5af })
                .setRecipe("cuboidmod:notsogudium_ingot", THADTASMINE_RECIPE_AMOUNT)
                .setProduction("cuboidmod:notsogudium_chunk", BASE_WORK_TIME, BASE_POWER_OUTPUT),

            defaultSingularity("oak_log", new int[] { 0xad8d54, 0x614d2f })
                .setRecipe("minecraft:oak_log", LOG_RECIPE_AMOUNT).setPowerOutput(LOG_FUEL)
                .setProduction("minecraft:oak_log", BASE_WORK_TIME, BASE_POWER_OUTPUT),

            defaultSingularity("osmium", new int[] { 0xCAE4E0, 0x9FB1C2 })
                .setRecipe("#forge:ingots/osmium", INGOT_RECIPE_AMOUNT)
                .setProduction("mekanism:raw_osmium", ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("platinum", new int[] { 0x90aba6, 0xccf0ea })
                .setRecipe("#forge:ingots/platinum", INGOT_RECIPE_AMOUNT)
                .setProduction("exnihilosequentia:raw_platinum", ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("redstone", new int[] { 0xbb2008, 0x931505 })
                .setRecipe("minecraft:redstone", INGOT_RECIPE_AMOUNT).setPowerOutput(ELECTRIC_FUEL)
                .setProduction("minecraft:redstone", DUST_WORK_TIME, DUST_POWER_OUTPUT),

            defaultSingularity("sand", new int[] { 0xe0d8ae, 0x897e60 })
                .setRecipe("minecraft:sand", NATUAL_ITEM_RECIPE_AMOUNT)
                .setProduction("minecraft:sand", EARTH_WORK_TIME, STONE_POWER_OUTPUT),

            defaultSingularity("silica_dust", new int[]{0xC8C0A7, 0xF3E8C8})
                .setRecipe("cuboidmod:silica_dust_block", MANUFACTURED_MATERIAL_RECIPE_AMOUNT)
                .setProduction("cuboidmod:silica_dust", BASE_WORK_TIME, BASE_POWER_OUTPUT),

            defaultSingularity("silver", new int[] { 0xCEDCE2, 0x8593A2 })
                .setRecipe("#forge:ingots/silver", INGOT_RECIPE_AMOUNT)
                .setProduction("exnihilosequentia:raw_silver", ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("spruce_log", new int[] { 0x795933, 0x4c3217 })
                .setRecipe("minecraft:spruce_log", LOG_RECIPE_AMOUNT).setPowerOutput(LOG_FUEL)
                .setProduction("minecraft:spruce_log", BASE_WORK_TIME, BASE_POWER_OUTPUT),

            defaultSingularity("steel", new int[] { 0xababab, 0x878a87 })
                .setRecipe("#forge:ingots/steel", INGOT_RECIPE_AMOUNT)
                .setProduction("immersiveengineering:dust_steel", ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("thatldu", new int[] { 0x50a142, 0x6bdb58 })
                .setRecipe("cuboidmod:thatldu_ingot", THADTASMINE_RECIPE_AMOUNT)
                .setProduction("cuboidmod:thatldu_chunk", NETHER_MATERIAL_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("tin", new int[] { 0xA1C6C2, 0x517C88 })
                .setRecipe("#forge:ingots/tin", INGOT_RECIPE_AMOUNT)
                .setProduction("exnihilosequentia:raw_tin", ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("uraninite", new int[] { 0x4CD54C, 0x48A048 })
                .setRecipe("powah:uraninite", GEM_RECIPE_AMOUNT)
                .setProduction("powah:uraninite_raw", TOUGH_ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("uranium", new int[] { 0x3C853C, 0x285028 })
                .setRecipe("#forge:ingots/uranium", INGOT_RECIPE_AMOUNT).setPowerOutput(URANIUM_FUEL)
                .setProduction("exnihilosequentia:raw_uranium", ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("wikidium", new int[] { 0x9ed9ac, 0x67b579 })
                .setRecipe("cuboidmod:wikidium_ingot", THADTASMINE_RECIPE_AMOUNT)
                .setProduction("cuboidmod:wikidium_chunk", DUST_WORK_TIME, DUST_POWER_OUTPUT),

            defaultSingularity("zinc", new int[] { 0xB5D1BA, 0x6F7262 })
                .setRecipe("#forge:ingots/zinc", INGOT_RECIPE_AMOUNT)
                .setProduction("exnihilosequentia:raw_zinc", ORE_WORK_TIME, ORE_POWER_OUTPUT),

            defaultSingularity("tuff", new int[]{0x4d4a53, 0x3b3846})
                .setRecipe("minecraft:tuff", NATUAL_ITEM_RECIPE_AMOUNT)
                .setProduction("minecraft:tuff", STONE_WORK_TIME, STONE_POWER_OUTPUT),

            defaultSingularity("calcite", new int[]{0x4d4a53, 0x3b3846})
                .setRecipe("minecraft:calcite", NATUAL_ITEM_RECIPE_AMOUNT)
                .setProduction("minecraft:calcite", STONE_WORK_TIME, STONE_POWER_OUTPUT)
        );
    }

    private static QuantumSingularity defaultSingularity(String identifier, int[] colors) {
        return new QuantumSingularity(new ResourceLocation(CuboidMod.MOD_ID, identifier), colors);
    }
}
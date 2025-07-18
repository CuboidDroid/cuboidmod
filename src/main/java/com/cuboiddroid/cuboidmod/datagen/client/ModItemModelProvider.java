package com.cuboiddroid.cuboidmod.datagen.client;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.cdt.block.EnergizedEndStoneBricksBlock;
import com.cuboiddroid.cuboidmod.modules.cdt.block.EnergizedNetherBricksBlock;
import com.cuboiddroid.cuboidmod.modules.cdt.block.EnergizedStoneBricksBlock;
import com.cuboiddroid.cuboidmod.modules.cdt.block.EnergizedThatlduviumBlock;
import com.cuboiddroid.cuboidmod.modules.food.*;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    private static final String[] CUBOID_MATERIALS = {
            "notsogudium", "kudbebedda", "notarfbadium", "wikidium", "thatldu"
    };

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CuboidMod.MOD_ID, existingFileHelper);
    }
    @Override
    protected void registerModels() {
        registerBlockItemModels();
        registerItemGeneratedModels();
        registerItemHandheldModels();
        registerCuboidChestModels();
        registerQuantumSingularityModels();
    }

    private void registerQuantumSingularityModels() {
        String[] singularities = new String[] {
                "acacia_log",
                "andesite",
                "basalt",
                "birch_log",
                "blackstone",
                "carbon_nanotube",
                "cellulose",
                "clay",
                "coal",
                "cobblestone",
                "dark_oak_log",
                "diorite",
                "dirt",
                "dust",
                "glowstone",
                "granite",
                "gravel",
                "jungle_log",
                "kudbebedda",
                "lapis",
                "notarfbadium",
                "notsogudium",
                "oak_log",
                "redstone",
                "sand",
                "silica_dust",
                "spruce_log",
                "thatldu",
                "wikidium",
                "endstone",
                "netherrack",

                "zinc",
                "tin",
                "copper",
                "nickel",
                "silver",
                "aluminium",
                "iron",
                "gold",
                "lead",
                "uranium",
                "uraninite",
                "osmium",

                "diamond",
                "emerald",
                "steel",
                "bronze",
                "electrum",
                "invar",
                "platinum",

                "tuff",
                "calcite"
        };

        for (String name: singularities) {
            withExistingParent(name + "_quantum_singularity", modLoc("item/quantum_singularity"));
        }
    }

    private void registerCuboidChestModels() {
        ModelFile builtinEntity = new ModelFile.UncheckedModelFile(mcLoc("builtin/entity"));

        for (String material : CUBOID_MATERIALS) {
            this.getBuilder(material + "_chest")
                    .parent(builtinEntity)
                    .transforms()
                    .transform(ItemDisplayContext.GUI)
                    .rotation(30.0F, 45.0F, 0.0F)
                    .translation(0.0F, 0.0F, 0.0F)
                    .scale(0.625F, 0.625F, 0.625F)
                    .end()
                    .transform(ItemDisplayContext.GROUND)
                    .rotation(0.0F, 0.0F, 0.0F)
                    .translation(0.0F, 3.0F, 0.0F)
                    .scale(0.25F, 0.25F, 0.25F)
                    .end()
                    .transform(ItemDisplayContext.HEAD)
                    .rotation(0.0F, 180.0F, 0.0F)
                    .translation(0.0F, 0.0F, 0.0F)
                    .scale(1.0F, 1.0F, 1.0F)
                    .end()
                    .transform(ItemDisplayContext.FIXED)
                    .rotation(0.0F, 180.0F, 0.0F)
                    .translation(0.0F, 0.0F, 0.0F)
                    .scale(0.5F, 0.5F, 0.5F)
                    .end()
                    .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
                    .rotation(75.0F, 315.0F, 0.0F)
                    .translation(0.0F, 2.5F, 0.0F)
                    .scale(0.375F, 0.375F, 0.375F)
                    .end()
                    .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                    .rotation(0.0F, 315.0F, 0.0F)
                    .translation(0.0F, 0.0F, 0.0F)
                    .scale(0.4F, 0.4F, 0.4F)
                    .end()
                    .end();
        }
    }

    private void registerItemHandheldModels() {
        ModelFile handheld = getExistingFile(mcLoc("item/handheld"));

        for (String material : CUBOID_MATERIALS) {
            builder(handheld, material + "_axe");
            builder(handheld, material + "_hoe");
            builder(handheld, material + "_pickaxe");
            builder(handheld, material + "_shovel");
            builder(handheld, material + "_sword");
            builder(handheld, material + "_smasher");
        }

        builder(handheld, "smoosher");
    }

    private void registerItemGeneratedModels() {
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        for (String material : CUBOID_MATERIALS) {
            builder(itemGenerated, material + "_ingot");
            builder(itemGenerated, material + "_nugget");
            builder(itemGenerated, material + "_chunk");
            builder(itemGenerated, material + "_piece");
            builder(itemGenerated, material + "_rod");
            builder(itemGenerated, material + "_dust");

            builder(itemGenerated, material + "_helmet");
            builder(itemGenerated, material + "_chestplate");
            builder(itemGenerated, material + "_leggings");
            builder(itemGenerated, material + "_boots");
        }

        builder(itemGenerated, "silica_dust");
        builder(itemGenerated, "carbon_deposit");
        builder(itemGenerated, "carbon_nanotube");
        builder(itemGenerated, "stick_bundle");
        builder(itemGenerated, "protein_paste");
        builder(itemGenerated, "protein_fiber");
        builder(itemGenerated, "cellulose");
        builder(itemGenerated, "salt");

        builder(itemGenerated, "thatldu_upgrade_smithing_template");

        builder(itemGenerated, "zinc_dust");

        builder(itemGenerated, ArachnuggetItem.ID_STRING);
        builder(itemGenerated, BrothItem.ID_STRING);
        builder(itemGenerated, CookedKebabItem.ID_STRING);
        builder(itemGenerated, GruelItem.ID_STRING);
        builder(itemGenerated, ProteinBarItem.ID_STRING);
        builder(itemGenerated, RawKebabItem.ID_STRING);
        builder(itemGenerated, RottenAppleItem.ID_STRING);
        builder(itemGenerated, AppleCiderVinegarItem.ID_STRING);
        builder(itemGenerated, RationPackItem.ID_STRING);

        builder(itemGenerated, CuredBeefItem.ID_STRING);
        builder(itemGenerated, CuredFleshItem.ID_STRING);
        builder(itemGenerated, BeefBiltongItem.ID_STRING);
        builder(itemGenerated, ZombieBiltongItem.ID_STRING);
        builder(itemGenerated, HardfiskurItem.ID_STRING);

        builder(itemGenerated, "notsogudium_bowl");
    }

    private void registerBlockItemModels() {
        for (String material : CUBOID_MATERIALS) {
            withExistingParent(material + "_block", modLoc("block/" + material + "_block"));
            withExistingParent(material + "_ore", modLoc("block/" + material + "_ore"));
            withExistingParent(material + "_crafting_table", modLoc("block/" + material + "_crafting_table"));
            withExistingParent(material + "_furnace", modLoc("block/" + material + "_furnace"));
            withExistingParent(material + "_quantum_collapser", modLoc("block/" + material + "_quantum_collapser"));
            withExistingParent(material + "_singularity_power_generator", modLoc("block/" + material + "_singularity_power_generator"));
            withExistingParent(material + "_singularity_resource_generator", modLoc("block/" + material + "_singularity_resource_generator"));
        }

        withExistingParent("eumus_furnace", modLoc("block/eumus_furnace"));

        withExistingParent("molecular_recycler", modLoc("block/molecular_recycler"));
        withExistingParent("quantum_transmutation_chamber", modLoc("block/quantum_transmutation_chamber"));
        withExistingParent("refined_inscriber", modLoc("block/refined_inscriber"));
        withExistingParent("drying_cupboard", modLoc("block/drying_cupboard"));
        withExistingParent("cryogenic_dimensional_teleporter", modLoc("block/cryogenic_dimensional_teleporter"));

        withExistingParent("silica_dust_block", modLoc("block/silica_dust_block"));
        withExistingParent("carbon_nanotube_block", modLoc("block/carbon_nanotube_block"));
        withExistingParent("protein_paste_block", modLoc("block/protein_paste_block"));
        withExistingParent("protein_fiber_block", modLoc("block/protein_fiber_block"));
        withExistingParent("cellulose_block", modLoc("block/cellulose_block"));
        withExistingParent("cellulose_bricks", modLoc("block/cellulose_bricks"));
        withExistingParent("cellulose_chiseled_bricks", modLoc("block/cellulose_chiseled_bricks"));
        withExistingParent("organically_enriched_sand", modLoc("block/organically_enriched_sand"));
        withExistingParent("aggregate", modLoc("block/aggregate"));

        withExistingParent(EnergizedEndStoneBricksBlock.ID_STRING, modLoc("block/" + EnergizedEndStoneBricksBlock.ID_STRING));
        withExistingParent(EnergizedNetherBricksBlock.ID_STRING, modLoc("block/" + EnergizedNetherBricksBlock.ID_STRING));
        withExistingParent(EnergizedStoneBricksBlock.ID_STRING, modLoc("block/" + EnergizedStoneBricksBlock.ID_STRING));
        withExistingParent(EnergizedThatlduviumBlock.ID_STRING, modLoc("block/" + EnergizedThatlduviumBlock.ID_STRING));
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

}

package com.cuboiddroid.cuboidmod.datagen.server;

import com.cuboiddroid.cuboidmod.datagen.server.recipes.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        MaterialsDataGenRecipes.build(this, consumer);

        FurnaceDataGenRecipes.build(this, consumer);
        ChestDataGenRecipes.build(this, consumer);
        CraftingTableDataGenRecipes.build(this, consumer);

        QuantumCollapserDataGenRecipes.build(this, consumer);
        SingularityResourceGeneratorDataGenRecipes.build(this, consumer);
        SingularityPowerGeneratorDataGenRecipes.build(this, consumer);
        QuantumTransmutationChamberDataGenRecipes.build(this, consumer);

        ToolsDataGenRecipes.build(this, consumer);
        ArmorDataGenRecipes.build(this, consumer);

        SmoosherDataGenRecipes.build(this, consumer);

        MiscDataGenRecipes.build(this, consumer);
        FoodItemDataGenRecipes.build(this, consumer);
    }

    public InventoryChangeTrigger.TriggerInstance hasItem(Tag<Item> itemITag) {
        return RecipeProvider.has(itemITag);
    }

    public InventoryChangeTrigger.TriggerInstance hasItem(ItemLike itemProvider) {
        return RecipeProvider.has(itemProvider);
    }
}

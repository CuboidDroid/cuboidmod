package com.cuboiddroid.cuboidmod.datagen.server.recipes;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;

public class DataGenRecipesBase {

    protected static ResourceLocation modId(String path) {
        return new ResourceLocation(CuboidMod.MOD_ID, path);
    }

    protected static void smeltBlockToItem(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<Block> block, RegistryObject<Item> item, String name) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(block.get()), RecipeCategory.MISC, item.get(), 0.7f, 200)
                .unlockedBy("has_item", provider.hasItem(block.get()))
                .save(consumer, modId(name + "_smelting"));

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(block.get()), RecipeCategory.MISC, item.get(), 0.7f, 100)
                .unlockedBy("has_item", provider.hasItem(block.get()))
                .save(consumer, modId(name + "_blasting"));
    }

    protected static void smeltBlockToItem(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, Block block, Item item, String name) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(block), RecipeCategory.MISC, item, 0.7f, 200)
                .unlockedBy("has_item", provider.hasItem(block))
                .save(consumer, modId(name + "_smelting"));

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(block), RecipeCategory.MISC, item, 0.7f, 100)
                .unlockedBy("has_item", provider.hasItem(block))
                .save(consumer, modId(name + "_blasting"));
    }

    protected static void smeltItemToItem(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<Item> chunk, RegistryObject<Item> ingot, String name) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(chunk.get()), RecipeCategory.MISC, ingot.get(), 0.7f, 200)
                .unlockedBy("has_item", provider.hasItem(chunk.get()))
                .save(consumer, modId(name + "_smelting"));

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(chunk.get()), RecipeCategory.MISC, ingot.get(), 0.7f, 100)
                .unlockedBy("has_item", provider.hasItem(chunk.get()))
                .save(consumer, modId(name + "_blasting"));
    }

    protected static void craftBlock3x3(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<Block> block, RegistryObject<Item> item, String name) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, block.get())
                .define('#', item.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", provider.hasItem(item.get()))
                .save(consumer, modId(name));
    }

    protected static void craftBlock2x2(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, Block block, Item item, String name) {
        craftBlock2x2(provider, consumer, block, item, name, 1);
    }

    protected static void craftBlock2x2(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, Block block, Item item, String name, int count) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, block, count)
                .define('#', item)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_item", provider.hasItem(item))
                .save(consumer, modId(name));
    }

    protected static void craftBlock2x2(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, Block outputBlock, Block inputBlock, String name) {
        craftBlock2x2(provider, consumer, outputBlock, inputBlock, name, 1);
    }

    protected static void craftBlock2x2(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, Block outputBlock, Block inputBlock, String name, int count) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, outputBlock, count)
                .define('#', inputBlock)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_item", provider.hasItem(inputBlock))
                .save(consumer, modId(name));
    }

    protected static void craftItemsFromBlock(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<Item> ingot, int amount, RegistryObject<Block> block, String name) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingot.get(), amount)
                .requires(block.get())
                .unlockedBy("has_item", provider.hasItem(ingot.get()))
                .save(consumer, modId(name));
    }

    protected static void craftItemFromItems3x3(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<Item> ingot, RegistryObject<Item> nugget, String name) {
        craftItemFromItems3x3(provider, consumer, ingot.get(), nugget.get(), name);
    }

    protected static void craftItemFromItems3x3(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, Item ingot, RegistryObject<Item> nugget, String name) {
        craftItemFromItems3x3(provider, consumer, ingot, nugget.get(), name);
    }

    protected static void craftItemFromItems3x3(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<Item> ingot, Item nugget, String name) {
        craftItemFromItems3x3(provider, consumer, ingot.get(), nugget, name);
    }

    protected static void craftItemFromItems3x3(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, Item ingot, Item nugget, String name) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ingot)
                .define('#', nugget)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", provider.hasItem(nugget))
                .save(consumer, modId(name));
    }

    protected static void craftItemsFromItem(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<Item> nugget, RegistryObject<Item> ingot, String name, int count) {
        craftItemsFromItem(provider, consumer, nugget.get(), ingot.get(), name, count);
    }

    protected static void craftItemsFromItem(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, Item nugget, RegistryObject<Item> ingot, String name, int count) {
        craftItemsFromItem(provider, consumer, nugget, ingot.get(), name, count);
    }

    protected static void craftItemsFromItem(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<Item> nugget, Item ingot, String name, int count) {
        craftItemsFromItem(provider, consumer, nugget.get(), ingot, name, count);
    }

    protected static void craftItemsFromItem(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, Item nugget, Item ingot, String name, int count) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, nugget, count)
                .requires(ingot)
                .unlockedBy("has_item", provider.hasItem(ingot))
                .save(consumer, modId(name));
    }

    protected static void craftItemFromItems2x2(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, Item chunk, Item piece, String name) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, chunk)
                .define('#', piece)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_item", provider.hasItem(piece))
                .save(consumer, modId(name));
    }

    protected static void craftItemFromItems2x2(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<Item> chunk, RegistryObject<Item> piece, String name) {
        craftItemFromItems2x2(provider, consumer, chunk.get(), piece.get(), name);
    }

    protected static void craftItemFromItems1x2(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<Item> chunk, RegistryObject<Item> piece, String name) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, chunk.get())
                .define('#', piece.get())
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_item", provider.hasItem(piece.get()))
                .save(consumer, modId(name));
    }

    protected static void stonecutterBlockFromBlock(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, Block output, Block input, String name) {
        stonecutterBlockFromBlock(provider, consumer, output, input, name, 1);
    }

    protected static void stonecutterBlockFromBlock(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, Block output, Block input, String name, int count) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), RecipeCategory.MISC, output)
                .unlockedBy("has_item", provider.hasItem(input))
                .save(consumer, modId(name));

    }

}

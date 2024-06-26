package com.cuboiddroid.cuboidmod.datagen.server.recipes;

import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import com.cuboiddroid.cuboidmod.setup.ModTags;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class FoodItemDataGenRecipes extends DataGenRecipesBase {
    public static void build(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        buildSmeltingRecipe(provider, consumer, "arachnugget_from_spider_eye", Items.SPIDER_EYE, ModItems.ARACHNUGGET.get());
        buildSmeltingRecipe(provider, consumer, "cooked_kebab_from_raw_kebab", ModItems.KEBAB_RAW.get(), ModItems.KEBAB_COOKED.get());
        buildSmeltingRecipe(provider, consumer, "protein_bar_from_protein_paste", ModItems.PROTEIN_PASTE.get(), ModItems.PROTEIN_BAR.get());

        buildRawKebabRecipe(provider, consumer);
        buildNotsogudiumBowlRecipe(provider, consumer);
        buildBrothRecipe(provider, consumer);
        buildGruelRecipe(provider, consumer);
        buildCuringRecipe(provider, consumer, "cured_flesh_from_acv_and_rotten_flesh", Items.ROTTEN_FLESH, ModItems.CURED_FLESH.get());
        buildCuringRecipe(provider, consumer, "cured_beef_from_acv_and_raw_beef", Items.BEEF, ModItems.CURED_BEEF.get());
    }

    private static void buildCuringRecipe(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, String recipeName, Item uncuredItem, Item curedItem) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, curedItem, 1)
                .requires(uncuredItem)
                .requires(ModItems.APPLE_CIDER_VINEGAR.get())
                .requires(ModTags.Items.DUSTS_SALT)
                .unlockedBy("has_item", provider.hasItem(ModItems.APPLE_CIDER_VINEGAR.get()))
                .save(consumer, modId(recipeName));
    }

    private static void buildRawKebabRecipe(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.KEBAB_RAW.get(), 1)
                .requires(ModTags.Items.RODS)
                .requires(Items.ROTTEN_FLESH)
                .requires(ModItems.PROTEIN_PASTE.get())
                .requires(Items.SPIDER_EYE)
                .unlockedBy("has_item", provider.hasItem(ModItems.PROTEIN_PASTE.get()))
                .save(consumer, modId("raw_kebab"));
    }

    private static void buildNotsogudiumBowlRecipe(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NOTSOGUDIUM_BOWL.get(), 3)
                .pattern("# #")
                .pattern(" # ")
                .define('#', ModItems.NOTSOGUDIUM_INGOT.get())
                .unlockedBy("has_item", provider.hasItem(ModItems.NOTSOGUDIUM_INGOT.get()))
                .save(consumer, modId("notsogudium_bowls_from_ingots"));
    }

    private static void buildBrothRecipe(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BROTH.get(), 1)
                .requires(Ingredient.of(ModItems.NOTSOGUDIUM_BOWL.get()), 1)
                .requires(Ingredient.of(Items.BONE_MEAL), 1)
                .requires(Ingredient.of(Items.WATER_BUCKET), 1)
                .unlockedBy("has_item", provider.hasItem(ModItems.NOTSOGUDIUM_BOWL.get()))
                .save(consumer, modId("broth_from_water_bucket_and_bone_meal"));

        /* had to remove this and move to static recipe instead
        ShapelessRecipeBuilder.shapeless(ModItems.BROTH.get(), 1)
                .requires(Ingredient.of(ModItems.NOTSOGUDIUM_BOWL.get()), 1)
                .requires(Ingredient.of(Items.BONE_MEAL), 1)
                .requires(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER).getItem())
                .unlockedBy("has_item", provider.hasItem(ModItems.NOTSOGUDIUM_BOWL.get()))
                .save(consumer, modId("broth_from_water_bottle_and_bone_meal"));
         */
    }

    private static void buildGruelRecipe(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GRUEL.get(), 1)
                .requires(Ingredient.of(ModItems.BROTH.get()))
                .requires(Ingredient.of(ModItems.PROTEIN_PASTE.get()))
                .unlockedBy("has_item", provider.hasItem(ModItems.BROTH.get()))
                .save(consumer, modId("gruel_from_broth_and_protein_paste"));
    }

    private static void buildSmeltingRecipe(
            ModRecipeProvider provider,
            Consumer<FinishedRecipe> consumer,
            String name,
            Item ingredient,
            Item output) {
        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(ingredient), RecipeCategory.MISC, output, 0.0F, 200)
                .unlockedBy("has_item", provider.hasItem(ingredient))
                .save(consumer, modId(name + "_smelting"));
    }
}

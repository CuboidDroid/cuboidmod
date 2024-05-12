package com.cuboiddroid.cuboidmod.modules.resourcegen.recipe;

import com.cuboiddroid.cuboidmod.modules.resourcegen.tile.SingularityResourceGeneratorTileEntityBase;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.google.gson.JsonObject;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class ResourceGeneratingRecipe implements Recipe<Container> {
    private final ResourceLocation recipeId;
    private Ingredient singularity;
    private ItemStack result;
    private float workTimeMultiplier;
    private float outputMultiplier;

    public ResourceGeneratingRecipe(ResourceLocation recipeId) {
        this.recipeId = recipeId;
        // This output is not required, but it can be used to detect when a recipe has been
        // loaded into the game.
        // CuboidMod.LOGGER.info("---> Loaded " + this.toString());
    }

    /*
    @Override
    public String toString () {

        // Overriding toString is not required, it's just useful for debugging.
        return "ResourceGeneratingRecipe [ingredient=" + this.ingredient + ", addition=" + this.addition +
                ", result=" + this.result + ", id=" + this.recipeId + "]";
    }
    */

    /**
     * Get the input ingredient (singularity)
     *
     * @return The input ingredient (singularity)
     */
    public Ingredient getSingularity() {
        return this.singularity;
    }

    /**
     * Get the Thatldu SRG image as the toast symbol
     *
     * @return
     */
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.THATLDU_SINGULARITY_RESOURCE_GENERATOR.get());
    }

    /**
     * Get the recipe's resource location (recipe ID)
     *
     * @return the recipe ID as a ResourceLocation
     */
    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    /**
     * Get the recipe serializer
     *
     * @return the IRecipeSerializer for the ResourceGeneratingRecipe
     */
    public RecipeSerializer<ResourceGeneratingRecipe> getSerializer() {
        return Serializer.INSTANCE;
    }

    /**
     * Get the recipe type for the ResourceGeneratingRecipe
     *
     * @return The IRecipeType for this recipe
     */
    public RecipeType<?> getType() {
        return ResourceGeneratingRecipe.Type.INSTANCE;
    }

    /**
     * Checks if there is a recipe match for the ingredient in the tile entities input slot
     *
     * @param inv   The SRG entity
     * @param level The level / world
     * @return true if there is a match, otherwise false
     */
    @Override
    public boolean matches(Container inv, Level level) {
        return this.singularity.test(inv.getItem(SingularityResourceGeneratorTileEntityBase.SINGULARITY_INPUT));
    }

    /**
     * DO NOT use this method - use the "getResults" method instead for this custom recipe.
     *
     * @param inventory
     * @return
     */
    @Override
    public ItemStack assemble(Container inventory) {
        return this.getResultItem();
    }

    /**
     * Checks if the recipe can fit in the machine. As this recipe is for single input, we'll just say yes!
     *
     * @param gridWidth
     * @param gridHeight
     * @return always returns true
     */
    public boolean canCraftInDimensions(int gridWidth, int gridHeight) {
        return true;
    }

    /**
     * DO NOT use this method - use the "getResults" method instead for this custom recipe.
     *
     * @return
     */
    @Override
    public ItemStack getResultItem() {
        return result.copy();
    }

    /**
     * Indicates that this recipe has special processing to Forge/Minecraft
     *
     * @return
     */
    @Override
    public boolean isSpecial() {
        return true;
    }

    /**
     * Gets the work time multiplier. e.g. if 2, then this SRG recipe takes twice
     * as many ticks per operation.
     *
     * @return the work time multiplier for this recipe
     */
    public float getWorkTimeMultiplier() {
        return this.workTimeMultiplier;
    }

    /**
     * Gets the output multiplier. e.g. if 0.5, then this SRG recipe produces half
     * the usual amount of items per operation, or if 1.5 it produces 50% more than usual.
     *
     * @return the output multiplier for this recipe
     */
    public float getOutputMultiplier() {
        return this.outputMultiplier;
    }

    public static class Type implements RecipeType<ResourceGeneratingRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "resource_generating";
    }


    // ---- Serializer ----

    public static class Serializer implements RecipeSerializer<ResourceGeneratingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = Type.ID;

        /*
          JSON structure:
            {
              "type": "cuboidmod:resource_generating",
              "singularity": {
                "item": "cuboidmod:dirt_quantum_singularity"
              },
              "result": {
                "item": "minecraft:dirt",
              },
              "work_time_multiplier": 1.0,
              "output_multiplier": 1.0
            }
         */
        @Override
        public ResourceGeneratingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            ResourceGeneratingRecipe recipe = new ResourceGeneratingRecipe(recipeId);

            JsonObject singularityJson = GsonHelper.getAsJsonObject(json, "singularity");
            ResourceLocation singularityItemId = new ResourceLocation(GsonHelper.getAsString(singularityJson, "item"));

            recipe.singularity = Ingredient.of(new ItemStack(ForgeRegistries.ITEMS.getValue(singularityItemId), 1));

            JsonObject resultJson = GsonHelper.getAsJsonObject(json, "result");
            ResourceLocation itemId = new ResourceLocation(GsonHelper.getAsString(resultJson, "item"));

            recipe.workTimeMultiplier = GsonHelper.getAsFloat(json, "work_time_multiplier", 1.0F);
            recipe.outputMultiplier = GsonHelper.getAsFloat(json, "output_multiplier", 1.0F);

            recipe.result = new ItemStack(ForgeRegistries.ITEMS.getValue(itemId), 1);
            return recipe;
        }

        @Override
        public ResourceGeneratingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            ResourceGeneratingRecipe recipe = new ResourceGeneratingRecipe(recipeId);
            recipe.singularity = Ingredient.fromNetwork(buffer);
            recipe.result = buffer.readItem();
            recipe.workTimeMultiplier = buffer.readFloat();
            recipe.outputMultiplier = buffer.readFloat();

            return recipe;
        }

        public void toNetwork(FriendlyByteBuf buffer, ResourceGeneratingRecipe recipe) {
            recipe.singularity.toNetwork(buffer);
            buffer.writeItem(recipe.result);
            buffer.writeFloat(recipe.workTimeMultiplier);
            buffer.writeFloat(recipe.outputMultiplier);
        }
    }
}

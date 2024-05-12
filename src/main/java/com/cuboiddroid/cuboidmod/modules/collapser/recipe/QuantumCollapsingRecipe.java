package com.cuboiddroid.cuboidmod.modules.collapser.recipe;

import org.jetbrains.annotations.Nullable;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.tile.QuantumCollapserTileEntityBase;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.google.gson.JsonObject;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
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

/*
  Recipes for collapsing items into singularities for the Quantum Singularity Collapser
 */
public class QuantumCollapsingRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation recipeId;
    private Ingredient ingredient;
    private ItemStack result;
    private int inputAmount;
    private int workTicks;

    public QuantumCollapsingRecipe(ResourceLocation recipeId, int workTicks, int inputAmount, Ingredient ingredient, ItemStack result) {
        this.recipeId = recipeId;
        this.ingredient = ingredient;
        this.result = result;
        this.inputAmount = inputAmount;
        this.workTicks = workTicks;
        // This output is not required, but it can be used to detect when a recipe has been
        // loaded into the game.
        //CuboidMod.LOGGER.info("---> Loaded " + this.toString());
    }

    /*
    @Override
    public String toString() {

        // Overriding toString is not required, it's just useful for debugging.
        return "QuantumCollapsingRecipe [ingredient=" + this.ingredient +
                ", result=" + this.result + ", id=" + this.recipeId + "]";
    }
    */

    /**
     * Get the input ingredient
     *
     * @return The input ingredient
     */
    public Ingredient getIngredient() {
        return this.ingredient;
    }

    /**
     * Get the time in ticks to recycle the input ingredient
     *
     * @return The work time in ticks
     */
    public int getWorkTicks() {
        return this.workTicks;
    }

    /**
     * Get the energy in FE needed to recycle the input ingredient
     *
     * @return The energy required in FE
     */
    public int getRequiredInputAmount() {
        return this.inputAmount;
    }

    /**
     * Get the Thatldu Quantum Collapser image as the toast symbol
     *
     * @return
     */
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.THATLDU_QUANTUM_COLLAPSER.get());
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
     * @return the IRecipeSerializer for the CollapsingRecipe
     */
    public RecipeSerializer<QuantumCollapsingRecipe> getSerializer() {
        return Serializer.INSTANCE;
    }

    /**
     * Get the recipe type for the CollapsingRecipe
     *
     * @return The IRecipeType for this recipe
     */
    public RecipeType<?> getType() {
        return QuantumCollapsingRecipe.Type.INSTANCE;
    }

    /**
     * Checks if there is a recipe match for the ingredient in the tile entities input slot
     *
     * @param inv   The collapser tile entity
     * @param level The level / world
     * @return true if there is a match, otherwise false
     */
    @Override
    public boolean matches(SimpleContainer inv, Level level) {
        return this.ingredient.test(inv.getItem(QuantumCollapserTileEntityBase.INPUT_SLOT));
    }

    /**
     * Assemble the recipe and return the result item stack
     *
     * @param inventory
     * @return
     */
    @Override
    public ItemStack assemble(SimpleContainer inventory) {
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
     * Gets the result item.
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

    public static class Type implements RecipeType<QuantumCollapsingRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "collapsing";
    }

    // ---- Serializer ----

    public static class Serializer implements RecipeSerializer<QuantumCollapsingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = Type.ID;

        @Override
        public QuantumCollapsingRecipe fromJson(ResourceLocation recipeId, JsonObject serializedRecipe) {
            int workTicks = GsonHelper.getAsInt(serializedRecipe, "work_ticks", 200);
            int inputAmount = GsonHelper.getAsInt(serializedRecipe, "count", 1024);
            Ingredient ingredient;
            
            JsonObject inputJson = GsonHelper.getAsJsonObject(serializedRecipe, "input");
            if (inputJson.has("item")) {
                ResourceLocation inputItemId = new ResourceLocation(GsonHelper.getAsString(inputJson, "item"));

                ingredient = Ingredient.of(new ItemStack(ForgeRegistries.ITEMS.getValue(inputItemId), 1));
            } else {
                try {
                    ingredient = Ingredient.fromJson(inputJson);
                } catch (Exception ex) {
                    CuboidMod.LOGGER.warn("Could not load tag: '" + GsonHelper.getAsString(inputJson, "tag") + "' - recipe: '" + recipeId.getPath() + "'\n\n" + ex);
                    ingredient = Ingredient.EMPTY;
                }
            }

            JsonObject resultJson = GsonHelper.getAsJsonObject(serializedRecipe, "result");
            ResourceLocation itemId = new ResourceLocation(GsonHelper.getAsString(resultJson, "item"));
            int resultCount = GsonHelper.getAsInt(resultJson, "count", 1);

            ItemStack result = new ItemStack(ForgeRegistries.ITEMS.getValue(itemId), resultCount);

            return new QuantumCollapsingRecipe(recipeId, workTicks, inputAmount, ingredient, result);
        }

        @Override
        public @Nullable QuantumCollapsingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buff) {
            int workTicks = buff.readVarInt();
            int inputAmount = buff.readVarInt();
            Ingredient ingredient = Ingredient.fromNetwork(buff);
            ItemStack result = buff.readItem();

            return new QuantumCollapsingRecipe(id, workTicks, inputAmount, ingredient, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, QuantumCollapsingRecipe recipe) {
            buf.writeInt(recipe.workTicks);
            buf.writeInt(recipe.inputAmount);
            recipe.ingredient.toNetwork(buf);
            buf.writeItemStack(recipe.result, false);
        }
    }
}

package com.cuboiddroid.cuboidmod.modules.dryingcupboard.recipe;

import com.cuboiddroid.cuboidmod.modules.dryingcupboard.tile.DryingCupboardTileEntity;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModRecipeSerializers;
import com.cuboiddroid.cuboidmod.setup.ModRecipeTypes;
import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.Objects;
import java.util.Random;

public class DryingRecipe implements IRecipe<IInventory> {
    private static final Random RANDOM = new Random();

    private final ResourceLocation recipeId;
    private Ingredient ingredient;
    private ItemStack resultItem = ItemStack.EMPTY;
    private int workTicks;

    public DryingRecipe(ResourceLocation recipeId) {
        this.recipeId = recipeId;
        // This output is not required, but it can be used to detect when a recipe has been
        // loaded into the game.
        // CuboidMod.LOGGER.info("---> Loaded " + this.toString());
    }

    /*
    @Override
    public String toString () {

        // Overriding toString is not required, it's just useful for debugging.
        return "DryingRecipe [ingredient=" + this.ingredient +
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
     * Get the time in ticks to dry the ingredient
     *
     * @return The work time in ticks
     */
    public int getWorkTicks() {
        return this.workTicks;
    }

    /**
     * Get the drying cupboard image as the toast symbol
     *
     * @return
     */
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.DRYING_CUPBOARD.get());
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
     * @return the IRecipeSerializer for the DryingRecipe
     */
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.DRYING.get();
    }

    /**
     * Get the recipe type for the DryingRecipe
     *
     * @return The IRecipeType for this recipe
     */
    public IRecipeType<?> getType() {
        return ModRecipeTypes.DRYING;
    }

    /**
     * Checks if there is a recipe match for the ingredient in the tile entities input slot
     *
     * @param inv   The drying cupboard tile entity
     * @param level The level / world
     * @return true if there is a match, otherwise false
     */
    @Override
    public boolean matches(IInventory inv, World level) {
        for (int i = 0; i < DryingCupboardTileEntity.INPUT_SLOTS; i++) {
            if (this.ingredient.test(inv.getItem(i)))
                return true;
        }

        return false;
    }

    /**
     * Returns the result item
     *
     * @param inventory the input ingredient
     * @return the result item
     */
    @Deprecated
    @Override
    public ItemStack assemble(IInventory inventory) {
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
     * @return the result of the recipe
     */
    @Override
    public ItemStack getResultItem() {
        return resultItem.copy();
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

    // ---- Serializer ----

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
            implements IRecipeSerializer<DryingRecipe> {

        /*
          JSON structure:
            {
              "type": "cuboidmod:drying",
              "ingredient": {
                "item": "cuboidmod:cured_rotten_flesh"
              },
              "result": {
                "item": "cuboidmod:zombie_biltong",
                "count": 3
              },
              "work_ticks": 600
            }
         */
        @Override
        public DryingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            DryingRecipe recipe = new DryingRecipe(recipeId);
            recipe.workTicks = JSONUtils.getAsInt(json, "work_ticks", 200);
            recipe.ingredient = Ingredient.fromJson(json.get("ingredient"));

            JsonObject resultJson = json.getAsJsonObject("result");
            String itemId = JSONUtils.getAsString(resultJson, "item");
            Item item = ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(itemId));
            int count = JSONUtils.getAsInt(resultJson, "count", 1);

            recipe.resultItem = new ItemStack(item, count);

            return recipe;
        }

        @Override
        public DryingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            DryingRecipe recipe = new DryingRecipe(recipeId);
            recipe.workTicks = buffer.readVarInt();
            recipe.ingredient = Ingredient.fromNetwork(buffer);

            ResourceLocation itemId = buffer.readResourceLocation();
            int count = buffer.readVarInt();
            Item item = ForgeRegistries.ITEMS.getValue(itemId);
            recipe.resultItem = new ItemStack(item, count);

            return recipe;
        }

        public void toNetwork(PacketBuffer buffer, DryingRecipe recipe) {
            buffer.writeVarInt(recipe.workTicks);
            recipe.ingredient.toNetwork(buffer);
            buffer.writeResourceLocation(Objects.requireNonNull(recipe.resultItem.getItem().getRegistryName()));
            buffer.writeVarInt(recipe.resultItem.getCount());
        }
    }
}

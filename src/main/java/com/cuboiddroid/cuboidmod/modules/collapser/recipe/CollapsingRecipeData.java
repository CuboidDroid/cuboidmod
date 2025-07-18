package com.cuboiddroid.cuboidmod.modules.collapser.recipe;

import net.minecraft.resources.ResourceLocation;

public class CollapsingRecipeData {
    public final ResourceLocation recipeInput;
    public final ResourceLocation recipeInputTag;
    public final int recipeCount;

    public final boolean usesTag;

    public CollapsingRecipeData(String identifier, int recipeCount) {
        this.recipeInput = ResourceLocation.tryParse(identifier); // Returns null if it starts with #
        this.recipeInputTag = ResourceLocation.tryParse(identifier.replaceFirst("^#", ""));

        // this.recipeInput = ForgeRegistries.ITEMS.getValue(itemIdentifier);
        // this.recipeInputTag = TagKey.create(Registries.ITEM, tagIdentifier);
        this.recipeCount = recipeCount;

        // https://github.com/CuboidDroid/cuboidmod/pull/16 thanks to Yoshi72
        this.usesTag = (this.recipeInput == null && this.recipeInputTag != null);
    }
}
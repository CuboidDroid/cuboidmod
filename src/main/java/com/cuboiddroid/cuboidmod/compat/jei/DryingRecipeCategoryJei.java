package com.cuboiddroid.cuboidmod.compat.jei;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.recipe.DryingRecipe;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.util.Constants;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Arrays;
import java.util.Collections;

public class DryingRecipeCategoryJei implements IRecipeCategory<DryingRecipe> {
    private static final ResourceLocation GUI = new ResourceLocation(CuboidMod.MOD_ID, "textures/gui/drying_cupboard_jei.png");

    private static final int GUI_START_X = 10;
    private static final int GUI_START_Y = 15;
    private static final int GUI_WIDTH = 170 - GUI_START_X;
    private static final int GUI_HEIGHT = 78 - GUI_START_Y;

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated energyBar;
    private final IDrawableAnimated dryingBar;
    private final String localizedName;

    public DryingRecipeCategoryJei(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(GUI, GUI_START_X, GUI_START_Y, GUI_WIDTH, GUI_HEIGHT);
        icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.DRYING_CUPBOARD.get()));
        energyBar = guiHelper.drawableBuilder(GUI, 176, 0, 8, 53)
                .buildAnimated(200, IDrawableAnimated.StartDirection.BOTTOM, false);
        dryingBar = guiHelper.drawableBuilder(GUI, 184, 0, 6, 13)
                .buildAnimated(200, IDrawableAnimated.StartDirection.TOP, false);
        localizedName = new TranslationTextComponent("jei.category.cuboidmod.drying").getString();
    }

    private static void renderScaledTextWithShadow(MatrixStack matrix, FontRenderer fontRenderer, IReorderingProcessor text, int x, int y, int width, float scale, int color) {
        matrix.pushPose();
        matrix.scale(scale, scale, scale);
        float xOffset = (width / scale - fontRenderer.width(text)) / 2;
        fontRenderer.drawShadow(matrix, text, xOffset + x / scale, y / scale, color);
        matrix.popPose();
    }

    private static void renderScaledText(MatrixStack matrix, FontRenderer fontRenderer, IReorderingProcessor text, int x, int y, float scale, int color) {
        matrix.pushPose();
        matrix.scale(scale, scale, scale);
        fontRenderer.draw(matrix, text, x / scale, y / scale, color);
        matrix.popPose();
    }

    @Override
    public ResourceLocation getUid() {
        return Constants.DRYING;
    }

    @Override
    public Class<? extends DryingRecipe> getRecipeClass() {
        return DryingRecipe.class;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(DryingRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(Collections.singletonList(recipe.getIngredient()));
        ingredients.setOutputs(VanillaTypes.ITEM, Collections.singletonList(recipe.getResultItem()));
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, DryingRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
        itemStacks.init(0, true, 25-GUI_START_X, 19-GUI_START_Y);
        itemStacks.init(1, false, 25-GUI_START_X, 54-GUI_START_Y);

        // Should only be one ingredient...
        itemStacks.set(0, Arrays.asList(recipe.getIngredient().getItems()));
        // Output
        itemStacks.set(1, recipe.getResultItem().copy());
    }

    @Override
    public void draw(DryingRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        FontRenderer font = Minecraft.getInstance().font;

        // energy
        energyBar.draw(matrixStack, 12 - GUI_START_X, 19 - GUI_START_Y);

        // drying time bar
        dryingBar.draw(matrixStack, 31 - GUI_START_X, 39 - GUI_START_Y);

        int workSeconds = recipe.getWorkTicks() / 20;
        int workDecimal = (recipe.getWorkTicks() % 20) / 2;
        String dryingTimeText = "" + workSeconds + "." + workDecimal + " s";
        renderScaledTextWithShadow(matrixStack, font, new StringTextComponent(dryingTimeText).getVisualOrderText(), 40 - GUI_START_X, 42 - GUI_START_Y, 24, 0.8f, 0xFFFFFF);
    }
}

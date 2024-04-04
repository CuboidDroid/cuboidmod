package com.cuboiddroid.cuboidmod.compat.jei;

import com.cuboiddroid.cuboidmod.modules.recycler.recipe.RecyclingRecipe;
import com.cuboiddroid.cuboidmod.modules.recycler.screen.MolecularRecyclerScreen;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.util.Constants;
import com.cuboiddroid.cuboidmod.util.Pair;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecyclingRecipeCategoryJei implements IRecipeCategory<RecyclingRecipe> {
    private static final int GUI_START_X = 24;
    private static final int GUI_START_Y = 28;
    private static final int GUI_WIDTH = 170 - GUI_START_X;
    private static final int GUI_HEIGHT = 78 - GUI_START_Y;

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;
    private final IDrawableAnimated energyBar;
    private final Component localizedName;

    public RecyclingRecipeCategoryJei(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(MolecularRecyclerScreen.GUI, GUI_START_X, GUI_START_Y, GUI_WIDTH, GUI_HEIGHT);
        icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.MOLECULAR_RECYCLER.get()));
        arrow = guiHelper.drawableBuilder(MolecularRecyclerScreen.GUI, 184, 0, 24, 17)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
        energyBar = guiHelper.drawableBuilder(MolecularRecyclerScreen.GUI, 176, 0, 8, 36)
                .buildAnimated(200, IDrawableAnimated.StartDirection.BOTTOM, false);
        localizedName = new TranslatableComponent("jei.category.cuboidmod.recycling");
    }

    private static void renderScaledTextWithShadow(PoseStack matrix, Font Font, Component text, int x, int y, int width, float scale, int color) {
        matrix.pushPose();
        matrix.scale(scale, scale, scale);
        float xOffset = (width / scale - Font.width(text)) / 2;
        Font.drawShadow(matrix, text, xOffset + x / scale, y / scale, color);
        matrix.popPose();
    }

    @Override
    public ResourceLocation getUid() {
        return Constants.RECYCLING;
    }

    @Override
    public Class<? extends RecyclingRecipe> getRecipeClass() {
        return RecyclingRecipe.class;
    }

    @Override
    public Component getTitle() {
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
    public void setIngredients(RecyclingRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(Collections.singletonList(recipe.getIngredient()));
        ingredients.setOutputs(VanillaTypes.ITEM, new ArrayList<>(recipe.getPossibleResults(new SimpleContainer(6))));
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, RecyclingRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
        itemStacks.init(0, true, 28, 14);
        itemStacks.init(1, false, 85, 6);
        itemStacks.init(2, false, 103, 6);
        itemStacks.init(3, false, 121, 6);
        itemStacks.init(4, false, 85, 24);
        itemStacks.init(5, false, 103, 24);
        itemStacks.init(6, false, 121, 24);

        // Should only be one ingredient...
//        recipe.getIngredients().forEach(ing -> itemStacks.set(0, Arrays.asList(ing.getMatchingStacks())));
        itemStacks.set(0, Arrays.asList(recipe.getIngredient().getItems()));
        // Outputs
        List<Pair<ItemStack, Float>> results = recipe.getPossibleResultsWithChances();
        for (int i = 0; i < results.size(); ++i) {
            itemStacks.set(i + 1, results.get(i).getKey());
        }
    }

    @Override
    public void draw(RecyclingRecipe recipe, PoseStack PoseStack, double mouseX, double mouseY) {
        Font font = Minecraft.getInstance().font;

        // arrow
        arrow.draw(PoseStack, 78 - GUI_START_X, 43 - GUI_START_Y);

        int workSeconds = recipe.getWorkTicks() / 20;
        int workDecimal = (recipe.getWorkTicks() % 20) / 2;
        String arrowText = "" + workSeconds + "." + workDecimal + " s";
        renderScaledTextWithShadow(PoseStack, font, new TextComponent(arrowText), 78 - GUI_START_X, 61 - GUI_START_Y, 24, 0.6f, 0xFFFFFF);

        // energy
        energyBar.draw(PoseStack, 32 - GUI_START_X, 34 - GUI_START_Y);

        String energyText = "" + recipe.getEnergyRequired() + " FE";
        renderScaledTextWithShadow(PoseStack, font, new TextComponent(energyText), 32 - GUI_START_X, 71 - GUI_START_Y, 8, 0.6f, 0xFFFFFF);

        // % chances on outputs
        List<Pair<ItemStack, Float>> results = recipe.getPossibleResultsWithChances();
        for (int i = 0; i < results.size(); ++i) {
            float chance = results.get(i).getValue();
            if (chance < 1) {
                int asPercent = (int) (100 * chance);
                String text = asPercent < 1 ? "<1%" : asPercent + "%";
                renderScaledTextWithShadow(PoseStack, font, new TextComponent(text), 85 + 18 * (i % 3), i < 3 ? 0 : 44, 18, 0.6f, 0xFFFFFF);
            }
        }
    }
}

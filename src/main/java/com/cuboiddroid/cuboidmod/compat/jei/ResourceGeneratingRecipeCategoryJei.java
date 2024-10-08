package com.cuboiddroid.cuboidmod.compat.jei;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.resourcegen.recipe.ResourceGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.resourcegen.screen.SingularityResourceGeneratorScreenBase;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import java.util.Arrays;

public class ResourceGeneratingRecipeCategoryJei implements IRecipeCategory<ResourceGeneratingRecipe> {
    private static final int GUI_START_X = 24;
    private static final int GUI_START_Y = 28;
    private static final int GUI_WIDTH = 170 - GUI_START_X;
    private static final int GUI_HEIGHT = 78 - GUI_START_Y;

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;
    private final IDrawableAnimated itemBar;
    private final Component localizedName;
    private final float notsogudiumItemsPerSecond;
    private final float kudbebeddaItemsPerSecond;
    private final float notarfbadiumItemsPerSecond;
    private final float wikidiumItemsPerSecond;
    private final float thatlduItemsPerSecond;

    public ResourceGeneratingRecipeCategoryJei(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(SingularityResourceGeneratorScreenBase.GUI, GUI_START_X, GUI_START_Y, GUI_WIDTH, GUI_HEIGHT);
        icon = guiHelper.createDrawableItemStack(new ItemStack(ModBlocks.THATLDU_SINGULARITY_RESOURCE_GENERATOR.get()));
        arrow = guiHelper.drawableBuilder(SingularityResourceGeneratorScreenBase.GUI, 184, 0, 24, 17)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
        itemBar = guiHelper.drawableBuilder(SingularityResourceGeneratorScreenBase.GUI, 176, 0, 8, 36)
                .buildAnimated(200, IDrawableAnimated.StartDirection.BOTTOM, false);
        localizedName = Component.translatable("jei.category." + CuboidMod.MOD_ID + ".resource_generating");

        notsogudiumItemsPerSecond =
                20.0F * Config.notsogudiumSingularityResourceGeneratorItemsPerOperation.get()
                        / Config.notsogudiumSingularityResourceGeneratorTicksPerOperation.get();

        kudbebeddaItemsPerSecond =
                20.0F * Config.kudbebeddaSingularityResourceGeneratorItemsPerOperation.get()
                        / Config.kudbebeddaSingularityResourceGeneratorTicksPerOperation.get();

        notarfbadiumItemsPerSecond =
                20.0F * Config.notarfbadiumSingularityResourceGeneratorItemsPerOperation.get()
                        / Config.notarfbadiumSingularityResourceGeneratorTicksPerOperation.get();

        wikidiumItemsPerSecond =
                20.0F * Config.wikidiumSingularityResourceGeneratorItemsPerOperation.get()
                        / Config.wikidiumSingularityResourceGeneratorTicksPerOperation.get();

        thatlduItemsPerSecond =
                20.0F * Config.thatlduSingularityResourceGeneratorItemsPerOperation.get()
                        / Config.thatlduSingularityResourceGeneratorTicksPerOperation.get();
    }

    private static void renderScaledText(GuiGraphics guiGraphics, Font font, Component text, int x, int y, float scale, int color) {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        guiGraphics.drawString(font, text, (int) (x / scale), (int) (y / scale), color, false);
        poseStack.popPose();
    }

    @Override
    public RecipeType<ResourceGeneratingRecipe> getRecipeType() {
        return CuboidModJeiPlugin.RESOURCE_GENERATING;
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

    // @Override
    // public void setIngredients(ResourceGeneratingRecipe recipe, IIngredients ingredients) {
    //     ingredients.setInputIngredients(Collections.singletonList(recipe.getSingularity()));
    //     ingredients.setOutputs(VanillaTypes.ITEM, Collections.singletonList(recipe.getResultItem()));
    // }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ResourceGeneratingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 8, 15).addItemStacks(Arrays.asList(recipe.getSingularity().getItems()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 47, 15).addItemStack(recipe.getResultItem(RegistryAccess.EMPTY).copy());
    }

    // @Override
    // public void setRecipe(IRecipeLayout recipeLayout, ResourceGeneratingRecipe recipe, IIngredients ingredients) {
    //     IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
    //     itemStacks.init(0, true, 7, 14);
    //     itemStacks.init(1, false, 46, 14);

    //     // Should only be one ingredient...
    //     itemStacks.set(0, Arrays.asList(recipe.getSingularity().getItems()));
    //     // Output
    //     itemStacks.set(1, recipe.getResultItem().copy());
    // }

    @Override
    public void draw(ResourceGeneratingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics matrix, double mouseX, double mouseY) {
        Font font = Minecraft.getInstance().font;

        // arrow
        arrow.draw(matrix, 48 - GUI_START_X, 43 - GUI_START_Y);

        float factor = recipe.getOutputMultiplier() / recipe.getWorkTimeMultiplier();

        renderScaledText(matrix, font, Component.literal(
                "Notso.: " + String.format("%.2f",
                        notsogudiumItemsPerSecond * factor
                ) + " items/s"), 74, 2, 0.6F, 0x444444);

        renderScaledText(matrix, font, Component.literal(
                "Kudbe.: " + String.format("%.2f",
                        kudbebeddaItemsPerSecond * factor
                ) + " items/s"), 74, 11, 0.6F, 0x444444);

        renderScaledText(matrix, font, Component.literal(
                "Notarf.: " + String.format("%.2f",
                        notarfbadiumItemsPerSecond * factor
                ) + " items/s"), 74, 20, 0.6F, 0x444444);

        renderScaledText(matrix, font, Component.literal(
                "Wikid.: " + String.format("%.2f",
                        wikidiumItemsPerSecond * factor
                ) + " items/s"), 74, 29, 0.6F, 0x444444);

        renderScaledText(matrix, font, Component.literal(
                "Thatl.: " + String.format("%.2f",
                        thatlduItemsPerSecond * factor
                ) + " items/s"), 74, 38, 0.6F, 0x444444);
    }
}

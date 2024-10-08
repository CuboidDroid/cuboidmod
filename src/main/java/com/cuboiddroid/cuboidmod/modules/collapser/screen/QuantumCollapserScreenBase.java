package com.cuboiddroid.cuboidmod.modules.collapser.screen;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.inventory.QuantumCollapserContainerBase;
import com.cuboiddroid.cuboidmod.modules.collapser.tile.QuantumCollapserTileEntityBase;
import net.minecraft.client.gui.GuiGraphics;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class QuantumCollapserScreenBase<T extends QuantumCollapserContainerBase> extends AbstractContainerScreen<T> {

    // coords for inside the hidden bar
    private static final int HIDDEN_BAR_TOP_LEFT_X = 177;
    private static final int HIDDEN_BAR_TOP_LEFT_Y = 1;
    private static final int BAR_WIDTH = 6;
    private static final int BAR_HEIGHT = 34;
    // coords for inside the visible bar
    private static final int BAR_TOP_LEFT_X = 33;
    private static final int BAR_TOP_LEFT_Y = 35;
    // coords for the hidden progress arrow
    private static final int HIDDEN_ARROW_TOP_LEFT_X = 184;
    private static final int HIDDEN_ARROW_TOP_LEFT_Y = 0;
    private static final int ARROW_WIDTH = 24;
    private static final int ARROW_HEIGHT = 17;
    // coords for the visible arrow
    private static final int ARROW_TOP_LEFT_X = 78;
    private static final int ARROW_TOP_LEFT_Y = 43;
    public static ResourceLocation GUI = CuboidMod.getModId("textures/gui/quantum_collapser.png");
    Inventory playerInv;
    Component name;
    QuantumCollapserTileEntityBase tile;

    public QuantumCollapserScreenBase(T container, Inventory inv, Component name) {
        super(container, inv, name);
        playerInv = inv;
        this.name = name;
    }

    @Override
    protected void init() {
        super.init();

        this.tile = this.getTileEntity();
    }

    @Override
    public void render(GuiGraphics matrix, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrix);
        super.render(matrix, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrix, mouseX, mouseY);

/*
        if (this.getItemsConsumed() >= 1) {
            if ((mouseX > this.leftPos + 56 && mouseX < this.leftPos + 70 && mouseY > this.topPos + 46 && mouseY < this.topPos + 60)
                    || (mouseX >= this.leftPos + 25 && mouseX < this.leftPos + 42 && mouseY >= this.topPos + 35 && mouseY < this.topPos + 52)) {
                List<MutableComponent> tooltip = new ArrayList<>();
                if (this.hasCurrentCollapsingItem()) {
                    tooltip.add(this.getCurrentCollapsingItemDisplayName());
                }

                Component text = Component.literal(this.getItemsConsumed() + " / " + this.getTotalItemsRequired());
                tooltip.add(text);
                matrix.renderComponentTooltip(this.font, tooltip, mouseX, mouseY);
            } else if (mouseX >= this.leftPos + 145 && mouseX < this.leftPos + 162 && mouseY >= this.topPos + 35 && mouseY < this.topPos + 52) {
                MutableComponent singularityName = getCurrentSingularityDisplayName();
                if (singularityName != null) {
                    List<MutableComponent> tooltip = new ArrayList<>();
                    tooltip.add(singularityName);
                    maytrix.renderComponentTooltip(this.font, tooltip, mouseX, mouseY);
                }
            }
        }
*/
    }

    @Override
    protected void renderLabels(GuiGraphics matrix, int mouseX, int mouseY) {
        String[] words = this.name.getString().split("\\s+");
        String firstLine = words[0] + ((words.length > 1) ? " " + words[1] : "");
        String secondLine = "";
        for (int i = 2; i < words.length; i++)
            secondLine += words[i] + (i < words.length - 1 ? " " : "");

        matrix.drawString(this.font, this.playerInv.getDisplayName(), 7, this.imageHeight - 93, 4210752, false);

        Component first = Component.literal(firstLine);
        Component second = Component.literal(secondLine);

        matrix.drawString(this.font, first, this.imageWidth / 2 - this.minecraft.font.width(firstLine) / 2, 6, 4210752, false);
        matrix.drawString(this.font, second, this.imageWidth / 2 - this.minecraft.font.width(secondLine) / 2, 18, 4210752, false);
    }

    @Override
    protected void renderBg(GuiGraphics matrix, float partialTicks, int mouseX, int mouseY) {
        // render the main container background
        // RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        matrix.blit(GUI, relX, relY, 0, 0, this.imageWidth, this.imageHeight);

        // render progress arrow
        int progress = (this.tile.getProcessingTime() * ARROW_WIDTH) / this.tile.getRecipeTime();
        if (progress > 0) {
            // draw it -
            //   matrix,
            //   top left X to place it at,
            //   top left Y to place it at
            //   top left X to copy from,
            //   top left Y to copy from,
            //   width to copy,
            //   height to copy
            matrix.blit(GUI,
                    this.leftPos + ARROW_TOP_LEFT_X,
                    this.topPos + ARROW_TOP_LEFT_Y,
                    HIDDEN_ARROW_TOP_LEFT_X,
                    HIDDEN_ARROW_TOP_LEFT_Y,
                    ARROW_WIDTH - progress,
                    ARROW_HEIGHT);
        }

        // render the inputs bar
        int amountConsumed = this.tile.getAmountConsumed();
        if (amountConsumed > 0) {
            int amountRequired = this.tile.getAmountRequired();

            if (amountRequired > 0) {
                int powerHeight = BAR_HEIGHT * amountConsumed / amountRequired;

                // draw it -
                //   matrix,
                //   top left X to place it at,
                //   top left Y to place it at
                //   top left X to copy from,
                //   top left Y to copy from,
                //   width to copy,
                //   height to copy
                matrix.blit(GUI,
                        this.leftPos + BAR_TOP_LEFT_X,
                        this.topPos + BAR_TOP_LEFT_Y + BAR_HEIGHT - powerHeight,
                        HIDDEN_BAR_TOP_LEFT_X,
                        HIDDEN_BAR_TOP_LEFT_Y + BAR_HEIGHT - powerHeight,
                        BAR_WIDTH,
                        powerHeight + 1);
            }
        }

        if (amountConsumed > 0) {
            // show picture of current item being consumed
            ItemStack collapsingItem = this.tile.getCollapsingItemStackForDisplay();
            this.renderFloatingItem(matrix, collapsingItem, this.leftPos + 12, this.topPos + 43, "", mouseX, mouseY);

            // show picture of current target item
            ItemStack singularityOutputItem = this.tile.getSingularityOutputForDisplay();
            this.renderFloatingItem(matrix, singularityOutputItem, this.leftPos + 145, this.topPos + 43, "", mouseX, mouseY);
        }
    }

    @Override
    protected void renderTooltip(GuiGraphics matrix, int mouseX, int mouseY) {
        super.renderTooltip(matrix, mouseX, mouseY);

        // tooltip to show energy stored & capacity
        if (mouseX >= this.leftPos + BAR_TOP_LEFT_X && mouseX <= this.leftPos + BAR_TOP_LEFT_X + BAR_WIDTH && mouseY >= this.topPos + BAR_TOP_LEFT_Y && mouseY <= this.topPos + BAR_TOP_LEFT_Y + BAR_HEIGHT) {
            List<Component> tooltip = new ArrayList<>();
            if (this.tile.getAmountConsumed() <= 0) {
                tooltip.add(Component.literal("Empty"));
            } else {
                Component text = Component.literal(this.tile.getAmountConsumed() + " / " + this.tile.getAmountRequired());
                tooltip.add(text);
                tooltip.add(this.tile.getCollapsingItemStackForDisplay().getHoverName());
            }

            matrix.renderComponentTooltip(this.font, tooltip, mouseX, mouseY);
        }
    }

/*
    private MutableComponent getCurrentCollapsingItemDisplayName() {
        if (this.tile == null)
            return Component.literal("");

        return this.tile.getInputDisplayName();
    }

    private MutableComponent getCurrentSingularityDisplayName() {
        if (this.tile == null)
            return null;

        ItemStack qs = this.tile.getSingularityOutputForDisplay();
        if (qs == null || qs.isEmpty())
            return null;

        return qs.getDisplayName();
    }
*/

    @SuppressWarnings("resource")
    private QuantumCollapserTileEntityBase getTileEntity() {
        ClientLevel world = this.getMinecraft().level;

        if (world != null) {
            BlockEntity tile = world.getBlockEntity(this.getMenu().getPos());

            if (tile instanceof QuantumCollapserTileEntityBase) {
                return (QuantumCollapserTileEntityBase) tile;
            }
        }

        return null;
    }

    /*
        public int getItemsConsumed() {
            if (this.tile == null)
                return 0;

            return this.tile.getItemsConsumed();
        }

        public int getTotalItemsRequired() {
            if (this.tile == null)
                return 0;

            return this.tile.getTotalItemsRequired();
        }

        public boolean hasCurrentCollapsingItem() {
            if (this.tile == null)
                return false;

            return this.tile.hasCurrentCollapsingItem();
        }

    */

    private void renderFloatingItem(GuiGraphics matrix, ItemStack itemStack, int posX, int posY, String text, int mouseX, int mouseY) {
        matrix.pose().pushPose();
        matrix.pose().translate(0.0F, 0.0F, 232.0F);
        matrix.renderItem(itemStack, posX, posY);
        matrix.renderItemDecorations(this.font, itemStack, posX, posY, text);
        if ((Math.abs(mouseX - (posX + 8)) < 8) && (Math.abs(mouseY - (posY + 8)) < 8))
            matrix.renderTooltip(this.font, itemStack, mouseX, mouseY);
        matrix.pose().popPose();
    }
}
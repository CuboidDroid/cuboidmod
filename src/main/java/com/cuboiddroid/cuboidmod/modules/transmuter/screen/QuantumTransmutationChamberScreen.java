package com.cuboiddroid.cuboidmod.modules.transmuter.screen;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.transmuter.inventory.QuantumTransmutationChamberContainer;
import com.cuboiddroid.cuboidmod.modules.transmuter.tile.QuantumTransmutationChamberTileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class QuantumTransmutationChamberScreen extends AbstractContainerScreen<QuantumTransmutationChamberContainer> {

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

    public static ResourceLocation GUI = CuboidMod.getModId("textures/gui/quantum_transmutation_chamber.png");
    Inventory playerInv;
    Component name;
    QuantumTransmutationChamberTileEntity tile;

    public QuantumTransmutationChamberScreen(QuantumTransmutationChamberContainer container, Inventory inv, Component name) {
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
    public void render(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrix);
        super.render(matrix, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrix, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack matrix, int mouseX, int mouseY) {
        String[] words = this.name.getString().split("\\s+");
        String firstLine = words[0] + ((words.length > 1) ? " " + words[1] : "");
        String secondLine = "";
        for (int i = 2; i < words.length; i++)
            secondLine += words[i] + (i < words.length - 1 ? " " : "");

        this.minecraft.font.draw(matrix, this.playerInv.getDisplayName(), 7, this.imageHeight - 93, 4210752);

        Component first = new TextComponent(firstLine);
        Component second = new TextComponent(secondLine);

        this.minecraft.font.draw(matrix, first, this.imageWidth / 2 - this.minecraft.font.width(firstLine) / 2, 6, 4210752);
        this.minecraft.font.draw(matrix, second, this.imageWidth / 2 - this.minecraft.font.width(secondLine) / 2, 18, 4210752);
    }

    @Override
    protected void renderBg(PoseStack matrix, float partialTicks, int mouseX, int mouseY) {
        // render the main container background
        // RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrix, relX, relY, 0, 0, this.imageWidth, this.imageHeight);

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
            this.blit(matrix,
                    this.leftPos + ARROW_TOP_LEFT_X,
                    this.topPos + ARROW_TOP_LEFT_Y,
                    HIDDEN_ARROW_TOP_LEFT_X,
                    HIDDEN_ARROW_TOP_LEFT_Y,
                    ARROW_WIDTH - progress,
                    ARROW_HEIGHT);
        }

        // render the energy bar
        int energy = this.menu.getEnergy();
        if (energy > 0) {
            int capacity = this.menu.getEnergyCapacity();

            if (capacity > 0) {
                int powerHeight = BAR_HEIGHT * energy / this.menu.getEnergyCapacity();

                // draw it -
                //   matrix,
                //   top left X to place it at,
                //   top left Y to place it at
                //   top left X to copy from,
                //   top left Y to copy from,
                //   width to copy,
                //   height to copy
                this.blit(matrix,
                        this.leftPos + BAR_TOP_LEFT_X,
                        this.topPos + BAR_TOP_LEFT_Y + BAR_HEIGHT - powerHeight,
                        HIDDEN_BAR_TOP_LEFT_X,
                        HIDDEN_BAR_TOP_LEFT_Y + BAR_HEIGHT - powerHeight,
                        BAR_WIDTH,
                        powerHeight + 1);
            }
        }
    }

    @Override
    protected void renderTooltip(PoseStack matrix, int mouseX, int mouseY) {
        super.renderTooltip(matrix, mouseX, mouseY);

        // tooltip to show energy stored & capacity
        if (mouseX >= this.leftPos + BAR_TOP_LEFT_X && mouseX <= this.leftPos + BAR_TOP_LEFT_X + BAR_WIDTH && mouseY >= this.topPos + BAR_TOP_LEFT_Y && mouseY <= this.topPos + BAR_TOP_LEFT_Y + BAR_HEIGHT) {
            List<Component> tooltip = new ArrayList<>();
            if (this.menu.getEnergy() <= 0) {
                tooltip.add(new TextComponent("Empty"));
            } else {
                TextComponent text = new TextComponent(this.menu.getEnergy() + " / " + this.menu.getEnergyCapacity());
                tooltip.add(text);
            }

            this.renderComponentTooltip(matrix, tooltip, mouseX, mouseY);
        }
    }

    @SuppressWarnings("resource")
    private QuantumTransmutationChamberTileEntity getTileEntity() {
        ClientLevel world = this.getMinecraft().level;

        if (world != null) {
            BlockEntity tile = world.getBlockEntity(this.getMenu().getPos());

            if (tile instanceof QuantumTransmutationChamberTileEntity) {
                return (QuantumTransmutationChamberTileEntity) tile;
            }
        }

        return null;
    }
}


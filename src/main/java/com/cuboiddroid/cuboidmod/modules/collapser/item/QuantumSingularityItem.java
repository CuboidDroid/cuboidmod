package com.cuboiddroid.cuboidmod.modules.collapser.item;

import java.util.List;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.registry.QuantumSingularity;
import com.cuboiddroid.cuboidmod.modules.collapser.registry.QuantumSingularityRegistry;
import com.cuboiddroid.cuboidmod.util.IColored;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class QuantumSingularityItem extends Item implements IColored {
    private ResourceLocation forcedSingularity = null;
    public static final String QUANTUM_ID = "quantumId";

    public QuantumSingularityItem() {
        super(new Properties());
    }

    public QuantumSingularityItem(ResourceLocation forcedSingularity) {
        this();
        this.forcedSingularity = forcedSingularity;
    }

    @Override
    public String getDescriptionId() {
        return CuboidMod.MOD_ID + ".quantum_singularity.unknown";
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public Component getName(ItemStack stack) {
        if (hasQuantumItem(stack)) {
            if (canProduceItems(stack)) {
                QuantumSingularity singularity = getSingularity(stack);
                Item productionItem = ForgeRegistries.ITEMS.getValue(singularity.getProduction().output);
                String translatedName = I18n.get(productionItem.getDescriptionId());
                return Component.translatable(CuboidMod.MOD_ID + ".quantum_singularity", translatedName);
            }
            
            return Component.translatable(CuboidMod.MOD_ID + ".quantum_singularity.unknown");
        }

        return Component.translatable(CuboidMod.MOD_ID + ".quantum_singularity.missing");
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        if (flag.isAdvanced()) {
            if (forcedSingularity != null) {
                tooltip.add(Component.literal("(Outdated Singularity)").withStyle(ChatFormatting.RED));
            }

            ResourceLocation quantumItem = getQuantumIdentifier(stack);
            tooltip.add(Component.literal("Quantum Singularity ID: " + quantumItem.toString()));
        }
    }

    @Override
    public int getColor(int tintIndex, ItemStack stack) {
        QuantumSingularity quantumSingularity = getSingularity(stack);

        if (showingExtras() && canProduceItems(stack)) return -1;

        return switch (tintIndex) {
            case 0 -> quantumSingularity.getUnderlayColor();
            case 1 -> quantumSingularity.getOverlayColor();
            default -> -1;
        };
    }

    public boolean showingExtras() {
        Minecraft minecraft = Minecraft.getInstance();
        long window = Minecraft.getInstance().getWindow().getWindow();
        KeyMapping shiftKey = minecraft.options.keyShift;
        return InputConstants.isKeyDown(window, shiftKey.getKey().getValue());
    }

    public boolean hasQuantumItem(ItemStack stack) {
        ResourceLocation singularity = getQuantumIdentifier(stack);
        return QuantumSingularityRegistry.getInstance().hasSingularity(singularity);
    }

    public ResourceLocation getQuantumIdentifier(ItemStack stack) {
        if (forcedSingularity != null)
            return forcedSingularity;

        CompoundTag tag = stack.getTag();
        if (tag != null) {
            String quantumItemId = tag.getString(QuantumSingularityItem.QUANTUM_ID);
            ResourceLocation key = ResourceLocation.tryParse(quantumItemId);
            if (key != null)
                return key;
        }

        return new ResourceLocation("air");
    }

    public QuantumSingularity getSingularity(ItemStack stack) {
        ResourceLocation identifier = getQuantumIdentifier(stack);
        return QuantumSingularityRegistry.getInstance().getSingularity(identifier);
    }

    public boolean canProduceItems(ItemStack stack) {
        QuantumSingularity singularity = getSingularity(stack);
        if (singularity.getProduction() == null) return false;
        Item item = ForgeRegistries.ITEMS.getValue(singularity.getProduction().output);

        return item != null && !item.equals(Items.AIR);
    }
}
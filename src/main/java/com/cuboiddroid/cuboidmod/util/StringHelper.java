package com.cuboiddroid.cuboidmod.util;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.google.common.collect.Lists;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

public class StringHelper {

    public static List<String> displayEnergy(int energy, int capacity) {
        List<String> text = new ArrayList<String>();
        NumberFormat format = DecimalFormat.getNumberInstance();
        String i = format.format(energy);
        String j = format.format(capacity);
        i = i.replaceAll("\u00A0", ",");
        j = j.replaceAll("\u00A0", ",");
        text.add(i + " / " + j + " RF");
        return text;
    }

    public static List<Component> getShiftInfoGui()
    {
        List<Component> list = Lists.newArrayList();
        list.add(new TranslatableComponent("tooltip." + CuboidMod.MOD_ID + ".gui_close"));
        MutableComponent tooltip1 = new TranslatableComponent("tooltip." + CuboidMod.MOD_ID + ".gui_hold_shift");
        MutableComponent shift = new TextComponent("[Shift]");
        MutableComponent tooltip2 = new TranslatableComponent("tooltip." + CuboidMod.MOD_ID + ".gui_shift_more_options");
        tooltip1.withStyle(ChatFormatting.GRAY);
        shift.withStyle(ChatFormatting.GOLD, ChatFormatting.ITALIC);
        tooltip2.withStyle(ChatFormatting.GRAY);
        list.add(tooltip1.append(shift).append(tooltip2));
        return list;
    }

    public static Component getShiftInfoText()
    {
        MutableComponent tooltip1 = new TranslatableComponent("tooltip." + CuboidMod.MOD_ID + ".hold");
        MutableComponent shift = new TextComponent("[Shift]");
        MutableComponent tooltip2 = new TranslatableComponent("tooltip." + CuboidMod.MOD_ID + ".for_details");
        tooltip1.withStyle(ChatFormatting.GRAY);
        shift.withStyle(ChatFormatting.GOLD, ChatFormatting.ITALIC);
        tooltip2.withStyle(ChatFormatting.GRAY);
        return tooltip1.append(shift).append(tooltip2);
    }

}
package com.cuboiddroid.cuboidmod.modules.food;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Made by drying cured flesh in a drying cupboard
 */
public class ZombieBiltongItem extends Item {
    public static final String ID_STRING = "biltong_zombie";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public ZombieBiltongItem() {
        super(new Properties()
                
                .food(new FoodProperties.Builder()
                        .nutrition(12)
                        .saturationMod(2.0F)
                        .meat()
                        .build()));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item." + CuboidMod.MOD_ID + ".biltong_zombie.hover_text"));
    }
}

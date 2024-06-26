package com.cuboiddroid.cuboidmod.modules.food;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;

/**
 * Made by smelting protein paste.
 */
public class ProteinBarItem extends Item {
    public static final String ID_STRING = "protein_bar";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public ProteinBarItem() {
        super(new Properties()
                
                .food(new FoodProperties.Builder()
                        .nutrition(5)
                        .saturationMod(0.6F)
                        .meat()
                        .build()));
    }
}

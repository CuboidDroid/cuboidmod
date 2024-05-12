package com.cuboiddroid.cuboidmod.util;

/*
  Shamelessly "lifted" from BlakeBr0's Cucumber library mod.
 */

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;

public interface IColored {
    default int getColor(int index) {
        return -1;
    }

    default int getColor(int index, ItemStack stack) {
        return this.getColor(index);
    }

    class BlockColors implements BlockColor {
        @Override
        public int getColor(BlockState state, BlockAndTintGetter world, BlockPos pos, int index) {
            return ((IColored) state.getBlock()).getColor(index);
        }
    }

    class ItemColors implements ItemColor {
        @Override
        public int getColor(ItemStack stack, int index) {
            return ((IColored) stack.getItem()).getColor(index, stack);
        }
    }

    class ItemBlockColors implements ItemColor {
        @Override
        public int getColor(ItemStack stack, int index) {
            return ((IColored) Block.byItem(stack.getItem())).getColor(index, stack);
        }
    }
}
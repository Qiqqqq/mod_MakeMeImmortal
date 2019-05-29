package com.mod.immortal.common.core;

import javax.annotation.Nonnull;

import com.mod.immortal.common.item.ItemLoader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ImmortalCreativeTabs {
    private ImmortalCreativeTabs()
    {
    }
    public static final CreativeTabs TAB_IMMORTAL = new CreativeTabs("immortal")
    {
        @Override
        @Nonnull
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ItemLoader.FIEND_CORE);
        }

    };
}

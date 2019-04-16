package com.mod.immortal.common.item;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.core.ImmortalCreativeTabs;

import net.minecraft.item.ItemFood;
import net.minecraft.util.ResourceLocation;

public class ItemFoodMod extends ItemFood {

    public ItemFoodMod(int amount, float saturation, String name) {
        super(amount, saturation, false);
        setUnlocalizedName(MakeMeImmortal.MODID + ":" + name);
        setRegistryName(new ResourceLocation(MakeMeImmortal.MODID, name));
        setCreativeTab(ImmortalCreativeTabs.TAB_IMMORTAL);
    }
    
}

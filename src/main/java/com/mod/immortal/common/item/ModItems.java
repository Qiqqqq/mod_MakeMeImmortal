package com.mod.immortal.common.item;

import net.minecraft.item.Item;
import com.mod.immortal.common.item.ItemLoader;

public class ModItems {
    public static final Item DREAM_GLASS;
    static{
        DREAM_GLASS = ItemLoader.Registration.getByName("item.dreamGlass");
    }
}

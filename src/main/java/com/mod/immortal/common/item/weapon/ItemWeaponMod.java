package com.mod.immortal.common.item.weapon;


import net.minecraft.item.ItemStack;

import com.mod.immortal.api.IFiveEleType;
import com.mod.immortal.common.item.ItemMod;
import com.mod.immortal.common.lib.FiveEleTypes;

public abstract class ItemWeaponMod extends ItemMod implements IFiveEleType {

	protected final FiveEleTypes fiveEletype;
	
	public ItemWeaponMod(String name, FiveEleTypes fiveEletype) {
		super(name);
        this.fiveEletype = fiveEletype;
		this.maxStackSize = 1;
        
	}
	
	@Override
	public FiveEleTypes getFiveEleType(ItemStack stack) {
		// TODO Auto-generated method stub
		return fiveEletype;
	}
}

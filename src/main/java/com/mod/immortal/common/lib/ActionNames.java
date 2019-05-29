package com.mod.immortal.common.lib;

import net.minecraft.item.EnumAction;
import net.minecraftforge.common.util.EnumHelper;

public enum ActionNames {
	SPELL;
	
	private ActionNames(){
		if (EnumAction.valueOf(this.name()) == null) {
			EnumHelper.addAction(this.name());
		}
	}

	public EnumAction parseEnumAction(){
		return EnumAction.valueOf(this.name());
	}
    
}

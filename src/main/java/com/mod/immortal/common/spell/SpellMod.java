package com.mod.immortal.common.spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.world.World;

import com.mod.immortal.api.IImmortalSpiritUser;
import com.mod.immortal.common.lib.TagNames;
import com.mod.immortal.common.util.PlayerTagManager;

public abstract class SpellMod implements IImmortalSpiritUser {
	
	public abstract EnumActionResult cast(World worldIn, EntityPlayer player);
	
	@Override
	public boolean isSpiritEnough(EntityPlayer player) {
		if(PlayerTagManager.getImmortalTagValue(player, TagNames.TAG_IMMORTAL_SPIRIT) < getSpiritCost()) {
			return false;
		} else {
			return true;
		}
	}
}

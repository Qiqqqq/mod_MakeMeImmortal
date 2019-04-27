package com.mod.immortal.common.util;

import com.mod.immortal.common.lib.FiveEleTypes;
import com.mod.immortal.common.spell.*;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.world.World;

public final class SpellHelper {
	
	public static EnumActionResult castSpell(World worldIn, EntityPlayer player, FiveEleTypes fiveEletype, int id) {
		SpellMod spell = chooseSpell(player, fiveEletype, id);
		if(!spell.isSpiritEnough(player)){
		    return EnumActionResult.FAIL;
		}
		return spell.cast(worldIn, player);
	}
	
	private static SpellMod chooseSpell(EntityPlayer player, FiveEleTypes fiveEletype, int id) {
		SpellMod res = null;
        if (fiveEletype == FiveEleTypes.JIN){
        	res = new SpellMetal01();
        }else if (fiveEletype == FiveEleTypes.MU){
        	res = new SpellWood01();
        }else if (fiveEletype == FiveEleTypes.SHUI){
        	res = new SpellWater01();
        }else if (fiveEletype == FiveEleTypes.HUO){
        	res = new SpellFire01();
        }else if (fiveEletype == FiveEleTypes.TU){
        	res = new SpellEarth01();
        }else{
        	res = new SpellMetal01();
        }
		return res;
	}

}

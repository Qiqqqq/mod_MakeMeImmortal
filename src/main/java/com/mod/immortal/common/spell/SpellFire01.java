package com.mod.immortal.common.spell;

import com.mod.immortal.common.lib.TagNames;
import com.mod.immortal.common.util.PlayerTagManager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SpellFire01 extends SpellMod {

	public SpellFire01(){}

	@Override
	public EnumActionResult cast(World worldIn, EntityPlayer player){
		if(!player.capabilities.isCreativeMode){
			boolean flag = PlayerTagManager.addImmortalTagValue(player, TagNames.TAG_IMMORTAL_SPIRIT, -getSpiritCost());
			if(!flag) return EnumActionResult.FAIL;
		}
	    Vec3d vec3d = player.getLook(1.0F);
		double acc = 32.0D;
	    double dx = vec3d.x * acc;
	    double dy = vec3d.y * acc;
	    double dz = vec3d.z * acc;

	    if(!worldIn.isRemote) {
		    EntityLargeFireball entitylargefireball = new EntityLargeFireball(worldIn, player, dx, dy, dz);
		    entitylargefireball.posX = player.posX + vec3d.x * 2.0D + 0.5D;
		    entitylargefireball.posY = player.posY + (double)(player.height / 2.0F) + vec3d.y * 2.0D;
		    entitylargefireball.posZ = player.posZ + vec3d.z * 2.0D + 0.5D;
	    	worldIn.spawnEntity(entitylargefireball);
		} else {
			player.swingArm(player.getActiveHand());
		}
	    return EnumActionResult.SUCCESS;
	}
	
	@Override
	public int getSpiritCost() {
		return 1;
	}
	
}

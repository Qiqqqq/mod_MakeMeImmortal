package com.mod.immortal.common.spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.EnumActionResult;
import net.minecraft.world.World;

public class SpellWater01 extends SpellMod {

	public SpellWater01(){}

	@Override
	public EnumActionResult cast(World worldIn, EntityPlayer player){
	    if (!worldIn.isRemote)
        {
            EntitySnowball entitysnowball = new EntitySnowball(worldIn, player);
            entitysnowball.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entitysnowball);
        }
	    return EnumActionResult.SUCCESS;
	}

	public int getSpiritCost() {
		return 1;
	}
	
}

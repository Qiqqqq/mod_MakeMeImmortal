package com.mod.immortal.common.spell;

import com.mod.immortal.common.lib.TagNames;
import com.mod.immortal.common.util.PlayerTagManager;

import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpellEarth01 extends SpellMod {

	public SpellEarth01(){}

	@Override
	public EnumActionResult cast(World worldIn, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode){
			boolean flag = PlayerTagManager.addImmortalTagValue(player, TagNames.TAG_IMMORTAL_SPIRIT, -getSpiritCost());
			if(!flag)	return EnumActionResult.FAIL;
		}
        double d0 = player.posX;
        double d1 = player.posY + (double)player.getEyeHeight();
        double d2 = player.posZ;
        Vec3d vec3d = new Vec3d(d0, d1, d2);
        
		Vec3d vec3d1 = rayTraceTarget(vec3d, player.rotationPitch, player.rotationYaw, 16.0D);
		Vec3d target = null;
		RayTraceResult mop = worldIn.rayTraceBlocks(vec3d, vec3d1, true, false, false);
		if (mop != null) {
			if (mop.typeOfHit == RayTraceResult.Type.BLOCK) {
				BlockPos hitPos1 = mop.getBlockPos();
				BlockPos placePos1 = hitPos1.offset(mop.sideHit);
				target = new Vec3d(placePos1.getX() + 0.5D, placePos1.getY(), placePos1.getZ() + 0.5D);
			}
		} else {
			Vec3d vec3d2 = rayTraceTarget(vec3d, 0f, player.rotationYaw, 16.0D);
			RayTraceResult mop1 = worldIn.rayTraceBlocks(vec3d, vec3d2, true, false, false);
			if (mop1 != null) {
				if (mop1.typeOfHit == RayTraceResult.Type.BLOCK) {
					BlockPos hitPos2 = mop1.getBlockPos();
					BlockPos placePos2 = hitPos2.offset(mop1.sideHit);
					target = new Vec3d(placePos2.getX() + 0.5D, placePos2.getY(), placePos2.getZ() + 0.5D);
				}
			}
		}
		if(target == null)	return EnumActionResult.FAIL;
		
        for (int i = 0; i < 32; ++i) {
            worldIn.spawnParticle(EnumParticleTypes.PORTAL, player.posX, player.posY + player.getRNG().nextDouble() * 2.0D, player.posZ, player.getRNG().nextGaussian(), 0.0D, player.getRNG().nextGaussian());
        }
        if (!worldIn.isRemote) {
            if (player.world == worldIn && !player.isPlayerSleeping()) {
            	if (player.isRiding()) {
            		player.dismountRidingEntity();
                }

            	player.setPositionAndUpdate(target.x, target.y, target.z);
            	player.fallDistance = 0.0F;
            }
        }
		
	    return EnumActionResult.SUCCESS;
	}

	public int getSpiritCost() {
		return 1;
	}
	
    protected Vec3d rayTraceTarget(Vec3d vsrc, float pitch, float raw, double range)
    {
        float f2 = MathHelper.cos(-raw * 0.017453292F - (float)Math.PI);
        float f3 = MathHelper.sin(-raw * 0.017453292F - (float)Math.PI);
        float f4 = -MathHelper.cos(-pitch * 0.017453292F);
        float f5 = MathHelper.sin(-pitch * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        return vsrc.addVector((double)f6 * range, (double)f5 * range, (double)f7 * range);
    }

	
}

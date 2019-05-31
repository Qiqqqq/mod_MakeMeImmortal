package com.mod.immortal.common.world;

import java.util.List;
import java.util.Random;

import com.mod.immortal.common.entity.EntityFiend1;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenRuins extends WorldGenerator{
	
	public static WorldGenRuins INSTANCE = new WorldGenRuins();
	

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		BlockPos villagePos = ((WorldServer)worldIn).getChunkProvider().getNearestStructurePos(worldIn, "Village", position, false);
		if(villagePos != null && MathHelper.abs(villagePos.getX() - position.getX()) < 16 && MathHelper.abs(villagePos.getZ() - position.getZ()) < 16) {
			int j = rand.nextInt(2) + 2;
		    int minoffset = -j - 1;
		    int maxoffset = j + 1;
		
		    for (int k3 = minoffset; k3 <= maxoffset; ++k3)
		    {
		        for (int i4 = 4; i4 >= -1; --i4)
		        {
		            for (int k4 = minoffset; k4 <= maxoffset; ++k4)
		            {
		                BlockPos blockpos1 = position.add(k3, i4, k4);
		
		                if (k3 != minoffset && i4 != -1 && k4 != minoffset && k3 != maxoffset && i4 != 4 && k4 != maxoffset)
		                {
		                	worldIn.setBlockToAir(blockpos1);
		                }
		                else
		                {
		                    worldIn.setBlockState(blockpos1, Blocks.BONE_BLOCK.getDefaultState(), 2);
		                }
		            }
		        }
		    }
		    worldIn.setBlockState(position.add(maxoffset-1, 2, 0), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST), 2);
		    worldIn.setBlockState(position.add(0, 2, maxoffset-1), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH), 2);
		    worldIn.setBlockState(position.add(minoffset+1, 2, 0), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST), 2);
		    worldIn.setBlockState(position.add(0, 2, minoffset+1), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH), 2);
            
		    EntityFiend1 pig = new EntityFiend1(worldIn);
		    pig.moveToBlockPosAndAngles(position, 0f, 0f);
	        System.out.println(position.getX()+","+position.getY()+","+position.getZ());
	    	worldIn.spawnEntity(pig);
	    	
		    return true;
		}
		else {
		    return false;
		}
	}
}
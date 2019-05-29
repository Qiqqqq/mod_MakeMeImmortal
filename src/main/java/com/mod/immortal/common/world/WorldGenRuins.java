package com.mod.immortal.common.world;

import java.util.List;
import java.util.Random;

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
	

//	public void generate(Random rand, int chunkX, int chunkZ, World worldIn,
//			IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
//        
////        BlockPos position = worldIn.getHeight(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8));
////        position = position.down(12);
//		BlockPos position = new BlockPos(chunkX * 16 + 8, rand.nextInt(224), chunkZ * 16 + 8);
//		
//		int j = rand.nextInt(2) + 2;
//        int k = -j - 1;
//        int l = j + 1;
//        int k1 = rand.nextInt(2) + 2;
//        int l1 = -k1 - 1;
//        int i2 = k1 + 1;
//        int j2 = 0;
//        for (int k2 = k; k2 <= l; ++k2)
//        {
//            for (int l2 = -1; l2 <= 4; ++l2)
//            {
//                for (int i3 = l1; i3 <= i2; ++i3)
//                {
//                    BlockPos blockpos = position.add(k2, l2, i3);
//                    Material material = worldIn.getBlockState(blockpos).getMaterial();
//                    boolean flag = material.isSolid();
//
//                    if (l2 == -1 && !flag)
//                    {
//                        return;
//                    }
//
//                    if (l2 == 4 && !flag)
//                    {
//                        return;
//                    }
//
//                    if ((k2 == k || k2 == l || i3 == l1 || i3 == i2) && l2 == 0 && worldIn.isAirBlock(blockpos) && worldIn.isAirBlock(blockpos.up()))
//                    {
//                        ++j2;
//                    }
//                }
//            }
//        }
//
//        System.out.println(chunkX+","+chunkZ);
//        if (j2 >= 1 && j2 <= 5)
//        {
//            for (int k3 = k; k3 <= l; ++k3)
//            {
//                for (int i4 = 3; i4 >= -1; --i4)
//                {
//                    for (int k4 = l1; k4 <= i2; ++k4)
//                    {
//                        BlockPos blockpos1 = position.add(k3, i4, k4);
//
//                        if (k3 != k && i4 != -1 && k4 != l1 && k3 != l && i4 != 4 && k4 != i2)
//                        {
//                            if (worldIn.getBlockState(blockpos1).getBlock() != Blocks.CHEST)
//                            {
//                                worldIn.setBlockToAir(blockpos1);
//                            }
//                        }
//                        else if (blockpos1.getY() >= 0 && !worldIn.getBlockState(blockpos1.down()).getMaterial().isSolid())
//                        {
//                            worldIn.setBlockToAir(blockpos1);
//                        }
//                        else if (worldIn.getBlockState(blockpos1).getMaterial().isSolid() && worldIn.getBlockState(blockpos1).getBlock() != Blocks.CHEST)
//                        {
//                            if (i4 == -1 && rand.nextInt(4) != 0)
//                            {
//                                worldIn.setBlockState(blockpos1, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 2);
//                            }
//                            else
//                            {
//                                worldIn.setBlockState(blockpos1, Blocks.COBBLESTONE.getDefaultState(), 2);
//                            }
//                        }
//                    }
//                }
//            }
//
//            if(!worldIn.isRemote) {
//                BlockPos blockpos2 = new BlockPos(position);
//    		    EntityPig pig = new EntityPig(worldIn);
//    		    pig.moveToBlockPosAndAngles(blockpos2, 0f, 0f);
//                System.out.println(blockpos2.getX()+","+blockpos2.getY()+","+blockpos2.getZ());
//    	    	worldIn.spawnEntity(pig);
//    		}
//
//            return;
//        }
//        else
//        {
//            return;
//        }
//	}

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
		    worldIn.setBlockState(position.add(0, 2, maxoffset-1), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH), 2);
		    worldIn.setBlockState(position.add(minoffset+1, 2, 0), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST), 2);
		    worldIn.setBlockState(position.add(0, 2, minoffset+1), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH), 2);
            
		    EntityPig pig = new EntityPig(worldIn);
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
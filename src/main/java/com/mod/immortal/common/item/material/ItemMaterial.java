package com.mod.immortal.common.item.material;

import net.minecraft.item.ItemStack;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import com.mod.immortal.common.item.ItemMod;

public abstract class ItemMaterial extends ItemMod implements IPlantable{

    private final Block crops;
    
	public ItemMaterial(String name) {
		this(Blocks.RED_FLOWER, name);
	}
    
	public ItemMaterial(Block crops, String name) {
		super(name);
        this.crops = crops;
	}


    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
        }

        stack.shrink(1);
        return stack;
    }


    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    	Block soilBlock = worldIn.getBlockState(pos).getBlock();
    	if(soilBlock == Blocks.GLASS) {
	    	ItemStack itemstack = player.getHeldItem(hand);
	        net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);
	        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && worldIn.isAirBlock(pos.up())) {
	            worldIn.setBlockState(pos.up(), this.crops.getDefaultState());
	
	            if (player instanceof EntityPlayerMP) {
	                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos.up(), itemstack);
	            }
	
	            itemstack.shrink(1);
	            return EnumActionResult.SUCCESS;
	        }
	        else {
	            return EnumActionResult.FAIL;
	        }
    	} else {
            return EnumActionResult.FAIL;
        }
    }
    
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return this.crops == net.minecraft.init.Blocks.NETHER_WART ? net.minecraftforge.common.EnumPlantType.Nether : net.minecraftforge.common.EnumPlantType.Crop;
    }

    
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return this.crops.getDefaultState();
    }
}

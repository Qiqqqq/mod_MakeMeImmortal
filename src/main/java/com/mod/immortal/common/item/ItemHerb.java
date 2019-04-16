package com.mod.immortal.common.item;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.core.ImmortalCreativeTabs;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class ItemHerb extends ItemFood implements IPlantable {
    public final float saturation;
    private final Block crops;
    private final Block soilId;

    public ItemHerb(int healAmount, float saturation, Block crops, String name) {
        super(healAmount, saturation, false);
        this.saturation = saturation;
        this.crops = crops;
        this.soilId = Blocks.FARMLAND;
        setUnlocalizedName(MakeMeImmortal.MODID + ":" + name);
        setRegistryName(new ResourceLocation(MakeMeImmortal.MODID, name));
        setCreativeTab(ImmortalCreativeTabs.TAB_IMMORTAL);
        setAlwaysEdible();
    }
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        ItemStack itemstack = player.getHeldItem(hand);
        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && worldIn.getBlockState(pos).getBlock() == this.soilId && worldIn.isAirBlock(pos.up())) {
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
    }

    public net.minecraftforge.common.EnumPlantType getPlantType(IBlockAccess world, BlockPos pos){
        return net.minecraftforge.common.EnumPlantType.Crop;
    }

    public net.minecraft.block.state.IBlockState getPlant(IBlockAccess world, BlockPos pos){
        return this.crops.getDefaultState();
    }
    
}

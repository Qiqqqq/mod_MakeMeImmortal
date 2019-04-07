package com.mod.immortal.common.item;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.core.ImmortalCreativeTabs;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemHerb extends ItemSeedFood {
    public final float saturation;
    private final Block crops;

    public ItemHerb(int healAmount, float saturation, Block crops, String name) {
        super(healAmount, saturation, crops, Blocks.FARMLAND);

        this.saturation = saturation;
        this.crops = crops;
        setUnlocalizedName(MakeMeImmortal.MODID + ":" + name);
        setRegistryName(new ResourceLocation(MakeMeImmortal.MODID, name));
        setCreativeTab(ImmortalCreativeTabs.TAB_IMMORTAL);
    }
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        Block soilBlock = worldIn.getBlockState(pos).getBlock();
        if(crops.getRegistryName().toString().equalsIgnoreCase("immortal:materialdreamglass") && soilBlock == Blocks.MYCELIUM) {
            ItemStack itemstack = player.getHeldItem(hand);
            if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) &&  worldIn.isAirBlock(pos.up())) {
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
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}

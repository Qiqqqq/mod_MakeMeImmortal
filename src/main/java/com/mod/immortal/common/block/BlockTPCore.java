package com.mod.immortal.common.block;

import java.util.Random;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.block.BlockMod;
import com.mod.immortal.common.item.ItemLoader;
import com.mod.immortal.common.lib.BlockNames;
import com.mod.immortal.common.lib.GuiIDs;
import com.mod.immortal.common.network.NetworkManager;
import com.mod.immortal.common.network.PacketImmortalMsg;
import com.mod.immortal.common.network.PacketTeleportMod;
import com.mod.immortal.common.world.ImmortalWorldSavedData;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTPCore extends BlockMod {
	
    public BlockTPCore() {
		super(Material.ROCK, BlockNames.TP_CORE);
		setHardness(2.0F);
		setSoundType(SoundType.STONE);
	}

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = playerIn.getHeldItem(hand);

    	if (!worldIn.isRemote) {
        	if (ImmortalWorldSavedData.get(worldIn).add(pos, pos.toString())) {
        		return true;
        	}
            if (!itemstack.isEmpty() && (itemstack.getItem() == ItemLoader.MATERIAL_MAKER)) {
                if (!playerIn.capabilities.isCreativeMode) {
	                    itemstack.shrink(1);
                }
	    		PacketTeleportMod msg = new PacketTeleportMod();
				ImmortalWorldSavedData.get(worldIn).writeToNBT( msg.nbt = new NBTTagCompound() );
	    		NetworkManager.instance.sendTo(msg, (EntityPlayerMP) playerIn);
            }
    	}
        return true;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
	    for (int k = 0; k < 2; ++k)
	    {
	        double d0 = (double)pos.getX() + rand.nextDouble();
	        double d1 = (double)pos.getY() + 1 + rand.nextDouble();
	        double d2 = (double)pos.getZ() + rand.nextDouble();

            double d3 = (rand.nextDouble() - 0.5D) * 0.5D;
            double d4 = (rand.nextDouble() - 0.5D) * 0.5D;
            double d5 = (rand.nextDouble() - 0.5D) * 0.5D;
	        worldIn.spawnParticle(EnumParticleTypes.HEART, d0, d1, d2, d3, d4, d5);
	    }
    }
    
}

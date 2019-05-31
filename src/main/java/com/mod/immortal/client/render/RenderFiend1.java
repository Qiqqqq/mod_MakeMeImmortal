package com.mod.immortal.client.render;

import com.mod.immortal.common.entity.EntityFiend1;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFiend1 extends RenderLiving<EntityFiend1> {

	private static final ResourceLocation PIG_TEXTURES = new ResourceLocation("textures/entity/pig/pig.png");

	public RenderFiend1(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelPig(), 0.7F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFiend1 entity) {
		// TODO Auto-generated method stub
		return PIG_TEXTURES;
	}

    
}



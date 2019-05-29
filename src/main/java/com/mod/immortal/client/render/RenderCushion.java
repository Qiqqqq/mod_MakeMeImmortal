package com.mod.immortal.client.render;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.client.model.ModelCushion;
import com.mod.immortal.common.entity.EntityCushion;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCushion extends Render<EntityCushion> {
private static final ResourceLocation CUSHION_TEXTURE = new ResourceLocation(MakeMeImmortal.MODID + ":" + "textures/entities/cushion.png");
		//new ResourceLocation("textures/entity/boat/boat_acacia.png");

	protected ModelBase modelCushion = new ModelCushion();
	
	public RenderCushion(RenderManager renderManager)
	{
	    super(renderManager);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityCushion entity)
	{
	    return RenderCushion.CUSHION_TEXTURE;
	}

	@Override
	public void doRender(EntityCushion entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
    	GlStateManager.pushMatrix();

        GlStateManager.translate(x, y, z);

        GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);

        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        
        this.bindEntityTexture(entity);

		this.modelCushion.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);


		GlStateManager.popMatrix();
     }
 }


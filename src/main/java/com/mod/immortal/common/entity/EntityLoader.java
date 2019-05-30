package com.mod.immortal.common.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.client.render.RenderCushion;
import com.mod.immortal.common.lib.EntityNames;
import com.mod.immortal.common.network.PacketImmortalMsg;
import com.mod.immortal.common.network.NetworkManager;
import com.mod.immortal.common.util.PlayerTagManager;

@Mod.EventBusSubscriber(modid = MakeMeImmortal.MODID)
public class EntityLoader {
	
	public static final EntityEntry CUSHION = EntityEntryBuilder.create()
			.entity(EntityCushion.class)
			.id(new ResourceLocation(MakeMeImmortal.MODID, EntityNames.CUSHION), 6226)
			.name(EntityNames.CUSHION)
			.tracker(64, 3, false)
			.build();
	
	@SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityEntry> event) {
		event.getRegistry().register(CUSHION);
	}
	
    @SubscribeEvent
    public static void bindEntityRenderer(ModelRegistryEvent event) {
    	RenderingRegistry.registerEntityRenderingHandler(EntityCushion.class, new EntityLoader.EntityRenderFactory<EntityCushion>(RenderCushion.class));
    }

	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if(!event.getWorld().isRemote && event.getEntity() instanceof EntityPlayer) {
			EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
			PacketImmortalMsg msg = new PacketImmortalMsg();
			msg.nbt = PlayerTagManager.getImmortalTag(player).copy();
			NetworkManager.instance.sendTo(msg, player);
		}
	}

    
    public static class EntityRenderFactory<E extends Entity> implements IRenderFactory<E>
    {
        private final Class<? extends Render<E>> renderClass;

        public EntityRenderFactory(Class<? extends Render<E>> renderClass)
        {
            this.renderClass = renderClass;
        }

        public Render<E> createRenderFor(RenderManager manager)
        {
            try
            {
                return renderClass.getConstructor(RenderManager.class).newInstance(manager);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }
    
}

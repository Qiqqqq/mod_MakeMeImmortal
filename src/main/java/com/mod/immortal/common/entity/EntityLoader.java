package com.mod.immortal.common.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
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
import com.mod.immortal.client.render.RenderFiend1;
import com.mod.immortal.common.lib.EntityNames;
import com.mod.immortal.common.network.PacketImmortalMsg;
import com.mod.immortal.common.network.NetworkManager;
import com.mod.immortal.common.util.PlayerTagManager;

@Mod.EventBusSubscriber(modid = MakeMeImmortal.MODID)
public class EntityLoader {
	
	private static int id = 6226;
	
	public static final EntityEntry CUSHION = buildEntityEntry(EntityCushion.class, EntityNames.CUSHION, 64, 3, false);
	public static final EntityEntry FIEND1 = buildEntityEntry(EntityFiend1.class, EntityNames.FIEND1, 64, 3, true);
	
	private static EntityEntry buildEntityEntry(Class<? extends Entity> clazz, String name, int range, int updateFrequency, boolean sendVelocityUpdates) {
		return EntityEntryBuilder.create()
				.entity(clazz)
				.id(new ResourceLocation(MakeMeImmortal.MODID, name), id++)
				.name(name)
				.tracker(range, updateFrequency, sendVelocityUpdates)
				.build();
	}
	
	@SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityEntry> event) {
		event.getRegistry().register(CUSHION);
		event.getRegistry().register(FIEND1);
	}
	
    @SubscribeEvent
    public static void bindEntityRenderer(ModelRegistryEvent event) {
    	RenderingRegistry.registerEntityRenderingHandler(EntityCushion.class, new EntityLoader.EntityRenderFactory<EntityCushion>(RenderCushion.class));
    	RenderingRegistry.registerEntityRenderingHandler(EntityFiend1.class, new EntityLoader.EntityRenderFactory<EntityFiend1>(RenderFiend1.class));
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

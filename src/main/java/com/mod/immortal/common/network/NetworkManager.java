package com.mod.immortal.common.network;

import com.mod.immortal.MakeMeImmortal;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkManager {
	public static SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(MakeMeImmortal.MODID);

    private static int nextID = 0;

    public NetworkManager(FMLPreInitializationEvent event)
    {
        registerMessage(PacketImmortalMsg.Handler.class, PacketImmortalMsg.class, Side.CLIENT);
        registerMessage(PacketTeleportMod.Handler.class, PacketTeleportMod.class, Side.SERVER);
        
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(
            Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side)
    {
        instance.registerMessage(messageHandler, requestMessageType, nextID++, side);
    }
}

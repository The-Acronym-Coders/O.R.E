package com.acronym.ore.networking;

import com.acronym.ore.reference.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by EwyBoy
 **/
public class PacketHandler {

    private static int packetId = 0;

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.ModInfo.MODID);

    public PacketHandler() {
    }

    public static int nextID() {
        return packetId++;
    }

    public static void registerMessages() {
        INSTANCE.registerMessage(PacketSendKey.Handler.class, PacketSendKey.class, nextID(), Side.SERVER);
    }
}

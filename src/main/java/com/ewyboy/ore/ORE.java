package com.ewyboy.ore;

import com.ewyboy.ore.common.utility.Logger;
import com.ewyboy.ore.common.utility.Reference;
import com.ewyboy.ore.proxy.CommonProxy;
import com.google.common.base.Stopwatch;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.util.concurrent.TimeUnit;

/** Created by EwyBoy**/
@Mod(modid = Reference.ModInfo.MOD_ID, name = Reference.ModInfo.MOD_NAME, version = Reference.ModInfo.BUILD_VERSION, acceptedMinecraftVersions = "["+ Reference.ModInfo.MINECRAFT_VERSION+"]")
public class ORE {

    public static Configuration configuration;
    public static FMLEventChannel packetHandler;

    @Mod.Instance(Reference.ModInfo.MOD_ID)
    public static ORE instance;

    @SidedProxy(modId = Reference.ModInfo.MOD_ID, clientSide = Reference.Path.CLIENT_PROXY_PATH, serverSide = Reference.Path.COMMON_PROXY_PATH)
    public static CommonProxy proxy;

    private long launchTime;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Pre-Initialization started");
                packetHandler = NetworkRegistry.INSTANCE.newEventDrivenChannel(Reference.ModInfo.MOD_ID);
                proxy.registerConfiguration(event.getSuggestedConfigurationFile());
                proxy.registerEvents();
                proxy.registerWorldGen();
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Pre-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        Logger.info("Pre-Initialization process successfully done");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Initialization started");
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        Logger.info("Initialization process successfully done");
    }

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Post-Initialization started");
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Post-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        Logger.info("Post-Initialization process successfully done");
        Logger.info("Total Initialization time was " + launchTime);
    }

}

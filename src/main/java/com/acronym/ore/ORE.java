package com.acronym.ore;

import com.acronym.ore.api.generation.GenerationRegistry;
import com.acronym.ore.common.commands.CommandORE;
import com.acronym.ore.common.directories.Config;
import com.acronym.ore.common.directories.Scripts;
import com.acronym.ore.common.generators.OREWG;
import com.acronym.ore.common.generators.feature.*;
import com.acronym.ore.common.generators.feature.retro.RetroGenFlatBedrock;
import com.acronym.ore.common.generators.retrogen.RetroGen;
import com.acronym.ore.common.helpers.Logger;
import com.acronym.ore.common.reference.Reference;
import com.acronym.ore.proxy.CommonProxy;
import com.ewyboy.worldstripper.common.network.PacketHandler;
import com.google.common.base.Stopwatch;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.concurrent.TimeUnit;

import static com.acronym.ore.common.reference.Reference.ModInfo.*;
import static com.acronym.ore.common.reference.Reference.Paths.CLIENT_PROXY;
import static com.acronym.ore.common.reference.Reference.Paths.COMMON_PROXY;

@Mod(modid = MODID, name = NAME, version = BUILD_VERSION)
public class ORE {

    @Mod.Instance(Reference.ModInfo.MODID)
    public static ORE INSTANCE;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy PROXY;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        Logger.info("Pre-Initialization started");

        PROXY.initEngines();
        GenerationRegistry.registerWorldGenerator("ore", new WorldGenOreMinable());
        GenerationRegistry.registerWorldGenerator("vein", new WorldGenOreVein());
        GenerationRegistry.registerWorldGenerator("geode", new WorldGenOreGeode());
        GenerationRegistry.registerWorldGenerator("cluster", new WorldGenOreCluster());
        Scripts.loadScripts(event);
        Config.loadConfig();

        PacketHandler.registerMessages(MODID);
        PROXY.registerKeybindings();
        Logger.info("Pre-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        Logger.info("Pre-Initialization process done");
        watch.stop();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.ORE_GEN_BUS.register(new VanillaOreDisabler());
        RetroGen.registerRetroGenerator(new RetroGenFlatBedrock());
        GameRegistry.registerWorldGenerator(new WorldGenFlatBedrock(), 0);
        GameRegistry.registerWorldGenerator(new OREWG(), 0);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandORE());
    }
}

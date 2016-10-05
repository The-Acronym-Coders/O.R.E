package com.acronym.ore;

import com.acronym.ore.common.commands.CommandORE;
import com.acronym.ore.common.directories.Config;
import com.acronym.ore.common.directories.Scripts;
import com.acronym.ore.common.generators.GeneratorLoader;
import com.acronym.ore.common.generators.OREWG;
import com.acronym.ore.common.generators.feature.VanillaOreDisabler;
import com.acronym.ore.common.generators.feature.WorldGenFlatBedrock;
import com.acronym.ore.common.generators.feature.retro.RetroGenFlatBedrock;
import com.acronym.ore.common.generators.retrogen.RetroGen;
import com.acronym.ore.common.helpers.FileLogger;
import com.acronym.ore.common.helpers.Logger;
import com.acronym.ore.common.reference.Reference;
import com.google.common.base.Stopwatch;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static com.acronym.ore.common.reference.Reference.ModInfo.*;

@Mod(modid = MODID, name = NAME, version = BUILD_VERSION, dependencies = "required-after:base;")
public class ORE {

    @Mod.Instance(Reference.ModInfo.MODID)
    public static ORE INSTANCE;
    public static FileLogger LOGGER = new FileLogger(new File("logs" + File.pathSeparator + "ore.log"));

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        Logger.info("Pre-Initialization started");

        GeneratorLoader.loadGenerators();
        Scripts.loadScripts(event);
        Config.loadConfig();

        Logger.info("Pre-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
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

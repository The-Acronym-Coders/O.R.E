package com.acronym.ore;

import com.acronym.ore.api.generation.GenerationRegistry;
import com.acronym.ore.commands.CommandORE;
import com.acronym.ore.config.Config;
import com.acronym.ore.proxy.CommonProxy;
import com.acronym.ore.reference.Reference;
import com.acronym.ore.world.generators.OREWG;
import com.acronym.ore.world.generators.feature.WorldGenOreGeode;
import com.acronym.ore.world.generators.feature.WorldGenOreMinable;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;

import static com.acronym.ore.reference.Reference.*;

/**
 * Created by Jared on 7/23/2016.
 */
@Mod(modid = MODID, name = NAME, version = VERSION)
public class ORE {

    @SidedProxy(clientSide = "com.acronym.ore.proxy.ClientProxy", serverSide = "com.acronym.ore.proxy.CommonProxy")
    public static CommonProxy PROXY;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
//        MinecraftForge.ORE_GEN_BUS.register(this);
//        MinecraftForge.TERRAIN_GEN_BUS.register(this);
//        MinecraftForge.EVENT_BUS.register(this);
        GenerationRegistry.registerWorldGenerator("ore", WorldGenOreMinable.class);
        GenerationRegistry.registerWorldGenerator("geode", WorldGenOreGeode.class);

        GameRegistry.registerWorldGenerator(new OREWG(), 0);
        Reference.CONFIG_DIR = new File(event.getSuggestedConfigurationFile().getParent(), File.separator + Reference.NAME + File.separator);
        Config.load();
        PROXY.initEngines();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

    @Mod.EventHandler
    public void loadcomplete(FMLLoadCompleteEvent event) {


    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandORE());
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void generate(OreGenEvent.GenerateMinable e) {
        e.setResult(Event.Result.DEFAULT);
    }

}

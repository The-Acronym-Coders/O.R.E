package com.acronym.ore;

import com.acronym.ore.api.generation.GenerationRegistry;
import com.acronym.ore.common.commands.CommandORE;
import com.acronym.ore.common.config.Config;
import com.acronym.ore.common.generators.OREWG;
import com.acronym.ore.common.generators.feature.*;
import com.acronym.ore.common.generators.feature.retro.RetroGenFlatBedrock;
import com.acronym.ore.common.generators.retrogen.RetroGen;
import com.acronym.ore.common.reference.Reference;
import com.acronym.ore.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;

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
        PROXY.initEngines();
        MinecraftForge.ORE_GEN_BUS.register(new VanillaOreDisabler());
        PacketHandler.registerMessages(MODID);
        PROXY.registerKeybindings();
        Reference.CONFIG_DIR = new File(event.getSuggestedConfigurationFile().getParent(), File.separator + NAME + File.separator);
        Config.load();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        GenerationRegistry.registerWorldGenerator("ore", WorldGenOreMinable.class);
        GenerationRegistry.registerWorldGenerator("vein", WorldGenOreVein.class);
        GenerationRegistry.registerWorldGenerator("geode", WorldGenOreGeode.class);
        MinecraftForge.ORE_GEN_BUS.register(new VanillaOreDisabler());

        RetroGen.registerRetroGenerator(new RetroGenFlatBedrock());
        GameRegistry.registerWorldGenerator(new WorldGenFlatBedrock(), 0);
        GameRegistry.registerWorldGenerator(new OREWG(), 0);
        Reference.CONFIG_DIR = new File(event.getSuggestedConfigurationFile().getParent(), File.separator + NAME + File.separator);
        Config.load();
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandORE());
    }
}

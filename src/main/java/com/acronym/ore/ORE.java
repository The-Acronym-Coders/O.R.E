package com.acronym.ore;

import com.acronym.ore.api.generation.GenerationRegistry;
import com.acronym.ore.common.commands.CommandORE;
import com.acronym.ore.common.config.Config;
import com.acronym.ore.common.generators.OREWG;
import com.acronym.ore.common.generators.feature.VanillaOreDisabler;
import com.acronym.ore.common.generators.feature.WorldGenFlatBedrock;
import com.acronym.ore.common.generators.feature.WorldGenOreMinable;
import com.acronym.ore.common.generators.feature.WorldGenOreVein;
import com.acronym.ore.common.generators.feature.retro.RetroGenFlatBedrock;
import com.acronym.ore.common.generators.retrogen.RetroGen;
import com.acronym.ore.common.helpers.Logger;
import com.acronym.ore.common.reference.Reference;
import com.acronym.ore.proxy.CommonProxy;
import com.ewyboy.worldstripper.common.network.PacketHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

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
        downloadWorldStripper();
        PROXY.initEngines();
        GenerationRegistry.registerWorldGenerator("ore", new WorldGenOreMinable());
        GenerationRegistry.registerWorldGenerator("vein", new WorldGenOreVein());
        PacketHandler.registerMessages(MODID);
        PROXY.registerKeybindings();
        Reference.CONFIG_DIR = new File(event.getSuggestedConfigurationFile().getParent(), File.separator + NAME + File.separator);
        Config.load();
    }

    private void downloadWorldStripper() {
        if (Config.autoDownloadWorldStripper) {
            Logger.info("Auto Download World Stripper");
            try {
                Logger.info("Yes");
                FileUtils.copyURLToFile(new URL("minecraft.curseforge.com/projects/world-stripper/files/2329920/download"), new File("mods/"));
            } catch (IOException e) {
                Logger.info("No");
                e.printStackTrace();
            }
        }
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

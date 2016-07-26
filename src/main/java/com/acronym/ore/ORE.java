package com.acronym.ore;

import com.acronym.ore.world.generators.OREWG;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.acronym.ore.reference.Reference.*;

/**
 * Created by Jared on 7/23/2016.
 */
@Mod(modid = MODID, name = NAME, version = VERSION)
public class ORE {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
//        MinecraftForge.ORE_GEN_BUS.register(this);
//        MinecraftForge.TERRAIN_GEN_BUS.register(this);
//        MinecraftForge.EVENT_BUS.register(this);
        GameRegistry.registerWorldGenerator(new OREWG(), 0);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

    @Mod.EventHandler
    public void loadcomplete(FMLServerStartedEvent event) {
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void generate(OreGenEvent.GenerateMinable e) {
        e.setResult(Event.Result.DEFAULT);
    }

}

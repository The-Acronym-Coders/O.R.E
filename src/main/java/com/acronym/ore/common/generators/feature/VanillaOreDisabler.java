package com.acronym.ore.common.generators.feature;

import gnu.trove.set.hash.THashSet;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Set;

import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.*;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.GenerateMinable;
import static net.minecraftforge.fml.common.eventhandler.Event.Result.DENY;

/**
 * Created by EwyBoy
 **/
public class VanillaOreDisabler {

    private static Set<EventType> vanillaOres = new THashSet<EventType>();

    static {
        vanillaOres.add(COAL);
        vanillaOres.add(IRON);
        vanillaOres.add(GOLD);
        vanillaOres.add(LAPIS);
        vanillaOres.add(DIAMOND);
        vanillaOres.add(REDSTONE);
        vanillaOres.add(EMERALD);
        vanillaOres.add(DIRT);
        vanillaOres.add(GRAVEL);
        vanillaOres.add(ANDESITE);
        vanillaOres.add(DIORITE);
        vanillaOres.add(GRANITE);
        vanillaOres.add(QUARTZ);
        vanillaOres.add(SILVERFISH);
    }
    
    @SubscribeEvent
    public void denyAllVanillaOreGeneration(GenerateMinable event) {
        if (vanillaOres.contains(event.getType()))
            event.setResult(DENY);
    }

    /**
     * TODO Add if statements here depending on boolean from config is true or false to disable specific world-gen
     */
    @SubscribeEvent
    public void denySpecificVanillaOreGeneration(GenerateMinable event) {
        switch (event.getType()) {
            case COAL:
                event.setResult(DENY);
                break;
            case IRON:
                event.setResult(DENY);
                break;
            case GOLD:
                event.setResult(DENY);
                break;
            case DIAMOND:
                event.setResult(DENY);
                break;
            case REDSTONE:
                event.setResult(DENY);
                break;
            case LAPIS:
                event.setResult(DENY);
                break;
            case EMERALD:
                event.setResult(DENY);
                break;
            case DIRT:
                event.setResult(DENY);
                break;
            case GRAVEL:
                event.setResult(DENY);
                break;
            case ANDESITE:
                event.setResult(DENY);
                break;
            case DIORITE:
                event.setResult(DENY);
                break;
            case GRANITE:
                event.setResult(DENY);
                break;
            case QUARTZ:
                event.setResult(DENY);
                break;
            case SILVERFISH:
                event.setResult(DENY);
                break;
        }
    }

}

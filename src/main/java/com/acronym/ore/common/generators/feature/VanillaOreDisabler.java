package com.acronym.ore.common.generators.feature;

import gnu.trove.set.hash.THashSet;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Set;

import static com.acronym.ore.common.directories.Config.disableAllVanillaWorldGen;
import static com.acronym.ore.common.directories.Config.disableOres;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.*;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.GenerateMinable;
import static net.minecraftforge.fml.common.eventhandler.Event.Result.DENY;

public class VanillaOreDisabler {

    public static Set<EventType> vanillaOres = new THashSet<>();

    static {
        vanillaOres.add(COAL);
        vanillaOres.add(IRON);
        vanillaOres.add(GOLD);
        vanillaOres.add(DIAMOND);
        vanillaOres.add(REDSTONE);
        vanillaOres.add(LAPIS);
        vanillaOres.add(EMERALD);
        vanillaOres.add(DIRT);
        vanillaOres.add(GRAVEL);
        vanillaOres.add(ANDESITE);
        vanillaOres.add(GRANITE);
        vanillaOres.add(DIORITE);
        vanillaOres.add(QUARTZ);
        vanillaOres.add(SILVERFISH);
    }
    
    @SubscribeEvent
    public void denyAllVanillaOreGeneration(GenerateMinable event) {
        if (disableAllVanillaWorldGen) if (vanillaOres.contains(event.getType())) event.setResult(DENY);
    }


    //TODO Add if statements here depending on boolean from directories is true or false to disable specific world-gen
    @SubscribeEvent
    public void onGenerateMinable(GenerateMinable event) {
        switch (event.getType()) {
            case COAL:
                if (disableOres[0]) event.setResult(Event.Result.DENY);
                break;
            case IRON:
                if (disableOres[1]) event.setResult(Event.Result.DENY);
                break;
            case GOLD:
                if (disableOres[2]) event.setResult(Event.Result.DENY);
                break;
            case DIAMOND:
                if (disableOres[3]) event.setResult(Event.Result.DENY);
                break;
            case REDSTONE:
                if (disableOres[4]) event.setResult(Event.Result.DENY);
                break;
            case LAPIS:
                if (disableOres[5]) event.setResult(Event.Result.DENY);
                break;
            case EMERALD:
                if (disableOres[6]) event.setResult(Event.Result.DENY);
                break;
            case DIRT:
                if (disableOres[7]) event.setResult(Event.Result.DENY);
                break;
            case GRAVEL:
                if (disableOres[8]) event.setResult(Event.Result.DENY);
                break;
            case ANDESITE:
                if (disableOres[9]) event.setResult(Event.Result.DENY);
                break;
            case GRANITE:
                if (disableOres[10]) event.setResult(Event.Result.DENY);
                break;
            case DIORITE:
                if (disableOres[11]) event.setResult(Event.Result.DENY);
                break;
            case QUARTZ:
                if (disableOres[12]) event.setResult(Event.Result.DENY);
                break;
            case SILVERFISH:
                if (disableOres[13]) event.setResult(Event.Result.DENY);
                break;
        }
    }
}

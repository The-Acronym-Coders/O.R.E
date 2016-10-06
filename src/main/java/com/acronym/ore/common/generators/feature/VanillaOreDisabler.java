package com.acronym.ore.common.generators.feature;

import gnu.trove.set.hash.THashSet;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Set;

import static com.acronym.ore.common.directories.Config.disableAllVanillaWorldGen;
import static com.acronym.ore.common.directories.Config.disabledMap;
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
        if (disableAllVanillaWorldGen) {
            if (vanillaOres.contains(event.getType())) event.setResult(DENY);
        } else {
            if (disabledMap.get(event.getType())) {
                event.setResult(DENY);
            }
        }
    }
}

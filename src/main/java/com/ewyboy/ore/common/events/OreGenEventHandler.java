package com.ewyboy.ore.common.events;

import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Created by EwyBoy **/
public class OreGenEventHandler {

    /**
     * onOreGenMinable hooks into the ore-gen part of the Minecraft World-Generator.
     * By subscribing to this event we can deny vanilla and custom ores access to spawn in the world.
     **/
    @SubscribeEvent
    public void onOreGenMinable(OreGenEvent.GenerateMinable event) {
        switch (event.getType()) {
            case COAL:
                break;
            case IRON:
                break;
            case GOLD:
                break;
            case DIAMOND:
                break;
            case REDSTONE:
                break;
            case LAPIS:
                break;
            case EMERALD:
                break;
            case DIRT:
                break;
            case GRAVEL:
                break;
            case ANDESITE:
                break;
            case GRANITE:
                break;
            case DIORITE:
                break;
            case QUARTZ:
                break;
            case SILVERFISH:
                break;
            case CUSTOM:
                //if (OreDictionary.doesOreNameExist(event.getType().name())) {}
                break;
        }
    }
}

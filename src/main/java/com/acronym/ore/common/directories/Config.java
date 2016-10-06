package com.acronym.ore.common.directories;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.terraingen.OreGenEvent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.acronym.ore.common.reference.Reference.*;

public class Config {

    public static void loadConfig() {
        Configuration config = new Configuration(new File(Directories.CONFIG_DIR, String.format("%s.cfg", ModInfo.UNACRONYMIZED_NAME)));
        config.load();
        registerConfigurations(config);
        config.save();
    }

    public static Map<OreGenEvent.GenerateMinable.EventType, Boolean> disabledMap = new HashMap<>();

    static {
        disabledMap.put(OreGenEvent.GenerateMinable.EventType.ANDESITE, false);
        disabledMap.put(OreGenEvent.GenerateMinable.EventType.COAL, false);
        disabledMap.put(OreGenEvent.GenerateMinable.EventType.DIAMOND, false);
        disabledMap.put(OreGenEvent.GenerateMinable.EventType.DIORITE, false);
        disabledMap.put(OreGenEvent.GenerateMinable.EventType.DIRT, false);
        disabledMap.put(OreGenEvent.GenerateMinable.EventType.EMERALD, false);
        disabledMap.put(OreGenEvent.GenerateMinable.EventType.GOLD, false);
        disabledMap.put(OreGenEvent.GenerateMinable.EventType.GRANITE, false);
        disabledMap.put(OreGenEvent.GenerateMinable.EventType.GRAVEL, false);
        disabledMap.put(OreGenEvent.GenerateMinable.EventType.IRON, false);
        disabledMap.put(OreGenEvent.GenerateMinable.EventType.LAPIS, false);
        disabledMap.put(OreGenEvent.GenerateMinable.EventType.QUARTZ, false);
        disabledMap.put(OreGenEvent.GenerateMinable.EventType.REDSTONE, false);
        disabledMap.put(OreGenEvent.GenerateMinable.EventType.SILVERFISH, false);
    }

    public static boolean disableAllVanillaWorldGen;
    public static boolean debugMode;
    public static boolean flatBedrock;
    public static boolean flatBedrockRetroGen;
    public static int flatBedrockLayers;

    public static void registerConfigurations(Configuration config) {
        //DEBUG
        debugMode = config.getBoolean("Debug Mode", ConfigCategories.DEBUG, false, "WARNING! ONLY FOR DEVELOPERS");

        //DISABLE WORLD GENERATION
        disableAllVanillaWorldGen = config.getBoolean("Disable All Vanilla Ore Generation", ConfigCategories.DISABLE_ALL_WORLDGEN, false, "Set to true to disable all vanilla ore generation");

        Map<OreGenEvent.GenerateMinable.EventType, Boolean> newMap = new HashMap<>();

        disabledMap.forEach((type, val) -> {
            String oreName = type.name().toUpperCase();
            newMap.put(type, config.getBoolean("Disable " + oreName, ConfigCategories.DISABLE_WORLDGEN, val, "Set to true to disable ore generation of " + oreName));
        });
        disabledMap.clear();
        disabledMap.putAll(newMap);

        //FLAT BEDROCK
        flatBedrock = config.getBoolean("Flat Bedrock", ConfigCategories.FLATBEDROCK, true, "Toggle flat bedrock generation");
        flatBedrockLayers = config.getInt("Flat Bedrock Layers", ConfigCategories.FLATBEDROCK, 5, 5, 256, "Tweak the amount of bedrock layers generated");
        flatBedrockRetroGen = config.getBoolean("Retro-Gen Flat Bedrock", ConfigCategories.FLATBEDROCK, false, "Set to true to enable retro-generation of flat bedrock");
    }

}

package com.acronym.ore.common.directories;

import com.acronym.ore.common.generators.feature.VanillaOreDisabler;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

import static com.acronym.ore.common.reference.Reference.*;

public class Config {

    public static void loadConfig() {
        Configuration config = new Configuration(new File(Directories.CONFIG_DIR, String.format("%s.cfg", ModInfo.UNACRONYMIZED_NAME)));
        config.load();
        registerConfigurations(config);
        config.save();
    }

    private static boolean disableCoal, disableIron, disableGold, disableDiamond, disableRedstone, disableLapis, disableEmerald, disableDirt, disableGravel, disableAndesite, disableGranite, disableDiorite, disableQuartz, disableSilverfish;
    public static boolean disableOres[] = {
            disableCoal,
            disableIron,
            disableGold,
            disableDiamond,
            disableRedstone,
            disableLapis,
            disableEmerald,
            disableDirt,
            disableGravel,
            disableAndesite,
            disableGranite,
            disableDiorite,
            disableQuartz,
            disableSilverfish
    };

    public static boolean disableAllVanillaWorldGen;
    public static boolean debugMode;
    public static boolean flatBedrock;
    public static boolean flatBedrockRetroGen;
    public static int flatBedrockLayers;

    public static void registerConfigurations(Configuration config) {

        //DISABLE WORLD GENERATION
        disableAllVanillaWorldGen = config.getBoolean("Disable All Vanilla Ore Generation", ConfigCategories.DISABLE_ALL_WORLDGEN, false, "Set to true to disable all vanilla ore generation");
        for (int i = 0; i < disableOres.length; i++) {
            String oreName = VanillaOreDisabler.vanillaOres.toArray()[i].toString().toUpperCase();
            disableOres[i] = config.getBoolean("Disable " + oreName, ConfigCategories.DISABLE_WORLDGEN, false, "Set to true to disable ore generation of " + oreName);
        }

        //DEBUG
        debugMode = config.getBoolean("Debug Mode", ConfigCategories.DEBUG, false, "WARNING! ONLY FOR DEVELOPERS");

        //FLAT BEDROCK
        flatBedrock = config.getBoolean("Flat Bedrock", ConfigCategories.FLATBEDROCK, true, "Toggle flat bedrock generation");
        flatBedrockLayers = config.getInt("Flat Bedrock Layers", ConfigCategories.FLATBEDROCK, 5, 5, 256, "Tweak the amount of bedrock layers generated");
        flatBedrockRetroGen = config.getBoolean("Retro-Gen Flat Bedrock", ConfigCategories.FLATBEDROCK, false, "Set to true to enable retro-generation of flat bedrock");
    }

}

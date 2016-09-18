package com.acronym.ore.common.directories;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

import static com.acronym.ore.common.reference.Reference.*;

/**
 * Created by EwyBoy & Jared
 */
public class Config {

    public static void loadConfig() {
        Configuration config = new Configuration(new File(Directories.CONFIG_DIR, String.format("%s.cfg", ModInfo.UNACRONYMICED_NAME)));
        config.load();
            registerConfigurations(config);
        config.save();
    }

    public static boolean debugMode;
    public static boolean flatBedrock;
    public static int flatBedrockLayers;

    public static void registerConfigurations(Configuration config) {
        //Debug
        debugMode = config.getBoolean("Debug Mode", ConfigCategories.DEBUG, false, "WARNING! ONLY FOR DEVELOPERS");

        //FLAT BEDROCK
        flatBedrock = config.getBoolean("Flat Bedrock", ConfigCategories.FLATBEDROCK, true, "Toggle flat bedrock generation");
        flatBedrockLayers = config.getInt("Flat Bedrock Layers", ConfigCategories.FLATBEDROCK, 5, 5, 256, "Tweak the amount of bedrock layers generated");
    }

}
package com.acronym.ore.common.directories;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

import static com.acronym.ore.common.reference.Reference.*;

/**
 * Created by Jared & Ewy
 */
public class Config {

    public static void loadConfig() {
        Configuration config = new Configuration(new File(Directories.CONFIG_DIR, String.format("%s.cfg", ModInfo.NAME)));
        config.load();
            registerConfigurations(config);
        config.save();
    }

    public static boolean debugMode;
    public static boolean autoDownloadWorldStripper;
    public static boolean flatBedrock;
    public static int flatBedrockLayers;
    public static int chuckRadius;

    public static void registerConfigurations(Configuration config) {
        //Debug
        debugMode = config.getBoolean("Debug Mode", ConfigCategories.DEBUG, false, "WARNING! ONLY FOR DEVELOPERS");
        chuckRadius = config.getInt("Chunk Radius", ConfigCategories.DEBUG, 3, 1, 12, "Sets the amount of chunks to clear (Chunk * Chunk)");

        //Toggables
        autoDownloadWorldStripper = config.getBoolean("Auto Download World-Stripper Mod", ConfigCategories.TOGGABLES, true, "Automatically downloads the World-Stripper mod made by EwyBoy and places it in your mods folder on launch.");
        flatBedrock = config.getBoolean("Flat Bedrock", ConfigCategories.TOGGABLES, true, "Toggle flat bedrock generation");

        //Values
        flatBedrockLayers = config.getInt("Flat Bedrock Layers", ConfigCategories.VALUES, 5, 5, 256, "Tweak the amount of bedrock layers generated");
    }

}
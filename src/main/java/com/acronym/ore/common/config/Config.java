package com.acronym.ore.common.config;

import com.acronym.ore.api.generation.Generation;
import com.acronym.ore.api.generation.GenerationRegistry;
import com.acronym.ore.common.reference.Reference;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.Collection;

import static com.acronym.ore.common.reference.Reference.Directories.CONFIG_DIR;

/**
 * Created by Jared & Ewy
 */
public class Config {

    public static void load() {
        Configuration config = new Configuration(new File(CONFIG_DIR, String.format("%s.cfg", Reference.ModInfo.NAME)));

        config.load();
        try {
            registerJsons();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        debugMode = config.getBoolean("Debug Mode", Reference.ConfigCategories.DEBUG, false, "WARNING! ONLY FOR DEVELOPERS");
        chuckRadius = config.getInt("Chunk Radius", Reference.ConfigCategories.DEBUG, 3, 1, 12, "Sets the amount of chunks to clear (Chunk * Chunk)");

        //Toggables
        autoDownloadWorldStripper = config.getBoolean("Auto Download World-Stripper Mod", Reference.ConfigCategories.TOGGABLES, true, "Automatically downloads the World-Stripper mod made by EwyBoy and places it in your mods folder on launch.");
        flatBedrock = config.getBoolean("Flat Bedrock", Reference.ConfigCategories.TOGGABLES, true, "Toggle flat bedrock generation");

        //Values
        flatBedrockLayers = config.getInt("Flat Bedrock Layers", Reference.ConfigCategories.VALUES, 5, 5, 256, "Tweak the amount of bedrock layers generated");
    }


    public static void registerJsons() throws Exception {
        for (File f : CONFIG_DIR.listFiles(file -> file.getName().endsWith(".json"))) {
            System.out.println("adding file: " + f.getName());
            JSONParser<Generation> readerGeneration = new JSONParser<>(f, Generation.class);
            System.out.println("reading file");
            addGenerator(readerGeneration.getElements("ore"));
        }
    }

    public static void addGenerator(Collection<? extends Generation> types) {
        for (Generation type : types) {
            System.out.println("adding type: " + type);
            System.out.println("adding" + type.getName());
            GenerationRegistry.addGeneration(type.register());
        }
    }
}
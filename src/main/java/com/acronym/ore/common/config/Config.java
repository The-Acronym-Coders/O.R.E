package com.acronym.ore.common.config;

import com.acronym.ore.api.generation.Generation;
import com.acronym.ore.api.generation.GenerationRegistry;
import com.acronym.ore.common.reference.Reference;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.Collection;

/**
 * Created by Jared & Ewy
 */
public class Config {

    public static void load() {
        Configuration config = new Configuration(new File(Reference.CONFIG_DIR, String.format("%s.cfg", Reference.ModInfo.NAME)));

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
    public static boolean flatBedrock;
    public static int flatBedrockLayers;
    public static int chuckRadius;


    public static void registerConfigurations(Configuration config) {
        //Debug
        debugMode = config.getBoolean("Debug Mode", Reference.ConfigCategories.DEBUG, false, "WARNING! ONLY FOR DEVELOPERS");
        chuckRadius = config.getInt("Chunk Radius", Reference.ConfigCategories.DEBUG, 3, 1, 12, "Sets the amount of chunks to clear (Chunk * Chunk)");

        //Toggables
        flatBedrock = config.getBoolean("Flat Bedrock", Reference.ConfigCategories.TOGGABLES, true, "Toggle flat bedrock generation");

        //Values
        flatBedrockLayers = config.getInt("Flat Bedrock Layers", Reference.ConfigCategories.VALUES, 5, 5, 256, "Tweak the amount of bedrock layers generated");
    }


    public static void registerJsons() throws Exception {
        System.out.println(">>> files");
        System.out.println(Reference.CONFIG_DIR.listFiles(file -> file.getName().endsWith(".json")));
        for (File f : Reference.CONFIG_DIR.listFiles(file -> file.getName().endsWith(".json"))) {
            JSONParser<Generation> readerGeneration = new JSONParser<>(f, Generation.class);
            addGenerator(readerGeneration.getElements("ore"));
        }
    }

    public static void addGenerator(Collection<? extends Generation> types) {
        for (Generation type : types) {
            System.out.println("adding" + type.getName());
            GenerationRegistry.addGeneration(type.register());
        }
    }
}

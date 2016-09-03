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

    public static void registerConfigurations(Configuration config) {

        //Toggables
        debugMode = config.getBoolean("Debug Mode", Reference.ConfigCategories.TOGGABLES, false, "WARNING! ONLY FOR DEVELOPERS");
        flatBedrock = config.getBoolean("Flat Bedrock", Reference.ConfigCategories.TOGGABLES, true, "Toggle flat bedrock generation");

        //Values
        flatBedrockLayers = config.getInt("Flat Bedrock Layers", Reference.ConfigCategories.VALUES, 5, 5, 256, "Tweak the amount of bedrock layers generated");

    }


    public static void registerJsons() throws Exception {


        /* TODO create file from template if it doesn't exist
         if (!seed.exists()) {
         try {
         FileUtils.copyURLToFile(FluxedCrystals.class.getResource("/assets/" + Reference.modid + "/jsons/seedData.json"), seed);
         } catch (IOException e) {
         e.printStackTrace();
         }
         }*/
        for (File f : Reference.CONFIG_DIR.listFiles(file -> file.getName().endsWith(".json"))) {
            JSONParser<Generation> readerGeneration = new JSONParser<>(f, Generation.class);
            addGenerator(readerGeneration.getElements("ore"));
        }
    }

    public static void addGenerator(Collection<? extends Generation> types) {
        for (Generation type : types) {
            GenerationRegistry.addGeneration(type.register());
        }
    }
}

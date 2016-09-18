package com.acronym.ore.api.generation;

import com.acronym.ore.common.helpers.Logger;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.List;
public class GenerationRegistry {
    private static List<Generation> generations = new ArrayList<>();
    private static BiMap<String, OreWorldGenerator> worldGeneratorMap = HashBiMap.create();

    public static void addGeneration(Generation gen) {
        if (generations.contains(gen)) {
            Logger.error(String.format("Tried registering duplicate generation with name %s.", gen.getName()));
            return;
        }
        generations.add(gen);
    }

    public static List<Generation> getGenerations() {
        return generations;
    }

    public static void registerWorldGenerator(String key,OreWorldGenerator gen) {
        getWorldGeneratorMap().put(key, gen);
    }

    public static String getKeyFromGenerator(OreWorldGenerator gen) {
        return getWorldGeneratorMap().inverse().get(gen);
    }


    public static OreWorldGenerator getGeneratorFromKey(String key) {
        return getWorldGeneratorMap().get(key);
    }

    public static Generation getGenerationFromName(String name) {
        for (Generation gen : getGenerations()) {
            if (gen.getName().equals(name)) {
                return gen;
            }
        }
        return null;
    }

    public static BiMap<String, OreWorldGenerator> getWorldGeneratorMap() {
        return worldGeneratorMap;
    }
}

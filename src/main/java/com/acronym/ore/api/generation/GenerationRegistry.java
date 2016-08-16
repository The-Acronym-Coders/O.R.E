package com.acronym.ore.api.generation;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jared on 8/4/2016.
 */
public class GenerationRegistry {
    private static BiMap<Integer, List<Generation>> generations = HashBiMap.create();
    private static BiMap<String, Class<? extends OreWorldGenerator>> worldGeneratorMap = HashBiMap.create();

    public static void addGeneratin(Generation gen) {
        for (int i : gen.getDimensions()) {
            List<Generation> gens = generations.get(i);
            if(gens ==null){
                gens = new ArrayList<Generation>();
            }
            gens.add(gen);
            generations.put(i, gens);
        }
    }

    public static BiMap<Integer, List<Generation>> getGenerations() {
        return generations;
    }

    public static void registerWorldGenerator(String key, Class<? extends OreWorldGenerator> gen) {
        getWorldGeneratorMap().put(key, gen);
    }

    public static String getKeyFromGenerator(Class<? extends OreWorldGenerator> gen) {
        return getWorldGeneratorMap().inverse().get(gen);
    }

    public static Class<? extends OreWorldGenerator> getGeneratorFromKey(String key) {
        return getWorldGeneratorMap().get(key);
    }


    public static BiMap<String, Class<? extends OreWorldGenerator>> getWorldGeneratorMap() {
        return worldGeneratorMap;
    }
}

package com.acronym.ore.common.generators;

import com.acronym.ore.api.generation.GenerationRegistry;
import com.acronym.ore.common.generators.feature.WorldGenOreCluster;
import com.acronym.ore.common.generators.feature.WorldGenOreGeode;
import com.acronym.ore.common.generators.feature.WorldGenOreMinable;
import com.acronym.ore.common.generators.feature.WorldGenOreVein;

public class GeneratorLoader {

    public static void loadGenerators() {
        GenerationRegistry.registerWorldGenerator("ore", new WorldGenOreMinable());
        GenerationRegistry.registerWorldGenerator("vein", new WorldGenOreVein());
        GenerationRegistry.registerWorldGenerator("geode", new WorldGenOreGeode());
        GenerationRegistry.registerWorldGenerator("cluster", new WorldGenOreCluster());
    }
}

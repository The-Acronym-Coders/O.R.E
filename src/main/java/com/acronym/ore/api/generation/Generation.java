package com.acronym.ore.api.generation;

import com.google.common.primitives.Ints;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Jared on 8/4/2016.
 */
public class Generation {
    private String name = "";
    private String type = "";
    private Map<String, Object> params;
    private Class<? extends OreWorldGenerator> worldGenerator = null;
    private String block = "";
    private String genTries = "0";
    private String blockCount = "0";
    private String minHeight = "0";
    private String maxHeight = "0";
    private String[] replaceable = new String[0];
    private String chunkChance = "0";
    private String biomeRestriction = "none";
    private String[] biomes = new String[0];
    private String dimensionsRestriction = "none";
    private int[] dimensions = new int[0];


    public Generation(String name, Class<? extends OreWorldGenerator> type, Map<String, Object> params, String block, String genTries, String blockCount, String[] replaceable, String minHeight, String maxHeight, String dimensionsRestriction, int[] dimensions, String chunkChance, String biomeRestriction, String[] biomes) {
        this.name = name;
        this.type = GenerationRegistry.getKeyFromGenerator(type);
        this.params = params;
        this.block = block;
        this.worldGenerator = type;
        this.genTries = genTries;
        this.blockCount = blockCount;
        this.replaceable = replaceable;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.dimensionsRestriction = dimensionsRestriction;
        this.dimensions = dimensions;
        this.chunkChance = chunkChance;
        this.biomeRestriction = biomeRestriction;
        this.biomes = biomes;
    }

    public Generation(String name, String type, Map<String, Object> params, String block, String genTries, String blockCount, String[] replaceable, String minHeight, String maxHeight, String dimensionsRestriction, int[] dimensions, String chunkChance, String biomeRestriction, String[] biomes) {
        this.name = name;
        this.type = type;
        this.params = params;
        this.block = block;
        this.worldGenerator = GenerationRegistry.getGeneratorFromKey(type);
        this.genTries = genTries;
        this.blockCount = blockCount;
        this.replaceable = replaceable;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.dimensionsRestriction = dimensionsRestriction;
        this.dimensions = dimensions;
        this.chunkChance = chunkChance;
        this.biomeRestriction = biomeRestriction;
        this.biomes = biomes;

    }

    public Generation register() {
        return new Generation(name, type, params, block, genTries, blockCount, replaceable, minHeight, maxHeight, dimensionsRestriction, dimensions, chunkChance, biomeRestriction, biomes);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Class<? extends OreWorldGenerator> getWorldGenerator() {
        return worldGenerator;
    }

    public void setWorldGenerator(Class<? extends OreWorldGenerator> worldGenerator) {
        this.worldGenerator = worldGenerator;
    }

    public Block getBlock() {
        return Block.getBlockFromName(this.block);
    }

    public void setBlock(Block block) {
        this.block = block.getRegistryName().toString();
    }

    public List<Block> getReplaceable() {
        List<Block> blocks = new ArrayList<Block>();
        for (String s : replaceable) {
            blocks.add(Block.getBlockFromName(s));
        }
        return blocks;
    }

    public void setReplaceable(Block[] replaceable) {
        String[] repl = new String[replaceable.length];
        for (int i = 0; i < replaceable.length; i++) {
            repl[i] = replaceable[i].getRegistryName().toString();
        }
        this.replaceable = repl;
    }


    public List<Integer> getDimensions() {
        return Ints.asList(dimensions);
    }


    public void setDimensions(int[] dimensions) {
        this.dimensions = dimensions;
    }

    public String getBiomeRestriction() {
        return biomeRestriction;
    }

    public void setBiomeRestriction(String biomeRestriction) {
        this.biomeRestriction = biomeRestriction;
    }

    public List<String> getBiomes() {
        return Arrays.asList(biomes);
    }

    public void setBiomes(String[] biomes) {
        this.biomes = biomes;
    }

    public String getDimensionsRestriction() {
        return dimensionsRestriction;
    }

    public void setDimensionsRestriction(String dimensionsRestriction) {
        this.dimensionsRestriction = dimensionsRestriction;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenTries() {
        return genTries;
    }

    public void setGenTries(String genTries) {
        this.genTries = genTries;
    }

    public String getBlockCount() {
        return blockCount;
    }

    public void setBlockCount(String blockCount) {
        this.blockCount = blockCount;
    }

    public String getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(String minHeight) {
        this.minHeight = minHeight;
    }

    public String getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(String maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setReplaceable(String[] replaceable) {
        this.replaceable = replaceable;
    }

    public String getChunkChance() {
        return chunkChance;
    }

    public void setChunkChance(String chunkChance) {
        this.chunkChance = chunkChance;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}





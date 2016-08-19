package com.acronym.ore.api.generation;

import com.google.common.primitives.Ints;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jared on 8/4/2016.
 */
public class Generation {
    private String type = "";
    private Map<String, Object> params;
    private Class<? extends OreWorldGenerator> worldGenerator = null;
    private String block = "";
    private int blockCount = 0;
    private int size = 0;
    private int minHeight = 0;
    private int maxHeight = 0;
    private int[] dimensions = new int[0];
    private String[] replaceable = new String[0];
    private int chunkChance = 0;
    private String biome = "__null";


    public Generation(Class<? extends OreWorldGenerator> type, Map<String, Object> params, String block, int blockCount, int size, String[] replaceable, int minHeight, int maxHeight, int[] dimensions, int chunkChance, String biome) {
        this.type = GenerationRegistry.getKeyFromGenerator(type);
        this.params = params;
        this.block = block;
        this.worldGenerator = type;
        this.blockCount = blockCount;
        this.size = size;
        this.replaceable = replaceable;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.dimensions = dimensions;
        this.chunkChance = chunkChance;
        this.biome = biome;
    }

    public Generation(String type, Map<String, Object> params, String block, int blockCount, int size, String[] replaceable, int minHeight, int maxHeight, int[] dimensions, int chunkChance, String biome) {
        this.type = type;
        this.params = params;
        this.block = block;
        this.worldGenerator = GenerationRegistry.getGeneratorFromKey(type);
        this.blockCount = blockCount;
        this.size = size;
        this.replaceable = replaceable;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.dimensions = dimensions;
        this.chunkChance = chunkChance;
        this.biome = biome;
    }

    public int getBlockCount() {
        return blockCount;
    }

    public void setBlockCount(int blockCount) {
        this.blockCount = blockCount;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public Generation register() {
        return new Generation(type, params, block, blockCount, size, replaceable, minHeight, maxHeight, dimensions, chunkChance, biome);
    }

    public List<Integer> getDimensions() {
        return Ints.asList(dimensions);
    }


    public void setDimensions(int[] dimensions) {
        this.dimensions = dimensions;
    }

    public void setChunkChance(int chunkChance) {
        this.chunkChance = chunkChance;
    }

    public int getChunkChance() {
        return chunkChance;
    }

    public String getBiome() {
        return biome;
    }

    public void setBiome(String biome) {
        this.biome = biome;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}





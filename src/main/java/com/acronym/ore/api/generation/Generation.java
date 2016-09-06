package com.acronym.ore.api.generation;

import com.google.common.primitives.Ints;
import net.minecraft.block.Block;

import java.util.*;

/**
 * Created by Jared on 8/4/2016.
 */
public class Generation {
    private String name = "defaultName";
    private String type = "defaultType";
    private Map<String, Object> params = new HashMap<>();
    private Class<? extends OreWorldGenerator> worldGenerator = null;
    private Map<String, Integer> blocks = new HashMap<>();
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

    public Generation(String name, Class<? extends OreWorldGenerator> type, Map<String, Object> params, Map<String, Integer> blocks, String genTries, String blockCount, String[] replaceable, String minHeight, String maxHeight, String dimensionsRestriction, int[] dimensions, String chunkChance, String biomeRestriction, String[] biomes) {
        this.name = name;
        this.type = GenerationRegistry.getKeyFromGenerator(type);
        this.params = params;
        this.blocks = blocks;
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

    public Generation(String name, String type, Map<String, Object> params, Map<String, Integer> blocks, String genTries, String blockCount, String[] replaceable, String minHeight, String maxHeight, String dimensionsRestriction, int[] dimensions, String chunkChance, String biomeRestriction, String[] biomes) {
        this.name = name;
        this.type = type;
        this.params = params;
        this.blocks = blocks;
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
        return new Generation(name, type, params, blocks, genTries, blockCount, replaceable, minHeight, maxHeight, dimensionsRestriction, dimensions, chunkChance, biomeRestriction, biomes);
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

    public List<Block> getBlocksList() {
        List<Block> blocks = new ArrayList<>();
        this.blocks.keySet().forEach(bl -> blocks.add(Block.getBlockFromName(bl)));
        return blocks;
    }
    public Map<Block, Integer> getBlocks(){
        Map<Block, Integer> blockMap = new HashMap<>();
        blocks.forEach((key, value)-> blockMap.put(Block.getBlockFromName(key), value));
        return blockMap;
    }


    public void setBlocks(Map<String, Integer> blocks) {
        this.blocks = blocks;
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


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Generation{");
        sb.append("name='").append(name).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", params=").append(params);
        sb.append(", worldGenerator=").append(worldGenerator);
        sb.append(", blocks=").append(blocks);
        sb.append(", genTries='").append(genTries).append('\'');
        sb.append(", blockCount='").append(blockCount).append('\'');
        sb.append(", minHeight='").append(minHeight).append('\'');
        sb.append(", maxHeight='").append(maxHeight).append('\'');
        sb.append(", replaceable=").append(Arrays.toString(replaceable));
        sb.append(", chunkChance='").append(chunkChance).append('\'');
        sb.append(", biomeRestriction='").append(biomeRestriction).append('\'');
        sb.append(", biomes=").append(Arrays.toString(biomes));
        sb.append(", dimensionsRestriction='").append(dimensionsRestriction).append('\'');
        sb.append(", dimensions=").append(Arrays.toString(dimensions));
        sb.append('}');
        return sb.toString();
    }
}





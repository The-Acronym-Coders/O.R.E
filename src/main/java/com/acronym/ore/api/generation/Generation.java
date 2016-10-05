package com.acronym.ore.api.generation;

import com.acronym.ore.ORE;
import com.google.common.primitives.Ints;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

import java.util.*;

public class Generation {
    private String name = "defaultName";
    private String type = "defaultType";
    private Map<String, Object> params = new HashMap<>();
    private Class<? extends OreWorldGenerator> worldGenerator = null;
    private Map<String, Integer> blocks = new HashMap<>();
    private int genTries = 0;
    private int blockCount = 0;
    private int minHeight = 0;
    private int maxHeight = 0;
    private String[] replaceable = new String[0];
    private int chunkChance = 0;
    private String biomeRestriction = "none";
    private String[] biomes = new String[0];
    private String dimensionsRestriction = "none";
    private int[] dimensions = new int[0];

    public Generation(String name, OreWorldGenerator type, Map<String, Object> params, Map<String, Integer> blocks, int genTries, int blockCount, String[] replaceable, int minHeight, int maxHeight, String dimensionsRestriction, int[] dimensions, int chunkChance, String biomeRestriction, String[] biomes) {
        this.name = name;
        this.type = GenerationRegistry.getKeyFromGenerator(type);
        this.params = params;
        this.blocks = blocks;
        this.worldGenerator = type.getClass();
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

    public Generation(String name, String type, Map<String, Object> params, Map<String, Integer> blocks, int genTries, int blockCount, String[] replaceable, int minHeight, int maxHeight, String dimensionsRestriction, int[] dimensions, int chunkChance, String biomeRestriction, String[] biomes) {
        this.name = name;
        this.type = type;
        this.params = params;
        this.blocks = blocks;
        this.worldGenerator = GenerationRegistry.getGeneratorFromKey(type).getClass();
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

    public OreWorldGenerator getWorldGenerator() {
        try {
            return worldGenerator.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setWorldGenerator(OreWorldGenerator worldGenerator) {
        this.worldGenerator = worldGenerator.getClass();
    }

    public List<IBlockState> getBlocksList() {
        List<IBlockState> blocks = new ArrayList<>();
        this.blocks.keySet().forEach(bl -> {
            if (bl.contains(";")) {
                blocks.add(Block.getBlockFromName(bl.split(";")[0]).getStateFromMeta(Integer.parseInt(bl.split(";")[1])));
            } else {
                blocks.add(Block.getBlockFromName(bl).getDefaultState());
            }
        });
        return blocks;
    }

    public Map<IBlockState, Integer> getBlocks() {
        Map<IBlockState, Integer> blockMap = new HashMap<>();
        blocks.forEach((bl, value) -> {
            if (bl.contains(";")) {
                Block b = Block.getBlockFromName(bl.split(";")[0]);
                if (b != null) {
                    blockMap.put(b.getStateFromMeta(Integer.parseInt(bl.split(";")[1])), value);
                } else {
                    ORE.LOGGER.logError("No block found by the name: " + bl);
                }
            } else {
                Block b = Block.getBlockFromName(bl.split(";")[0]);
                if (b != null) {
                    blockMap.put(b.getDefaultState(), value);
                } else {
                    ORE.LOGGER.logError("No block found by the name: " + bl);
                }
            }
        });
        return blockMap;
    }


    public void setBlocks(Map<String, Integer> blocks) {
        this.blocks = blocks;
    }

    public List<IBlockState> getReplaceable() {
        List<IBlockState> blocks = new ArrayList<>();
        Arrays.asList(replaceable).forEach(bl -> {
            if (bl.contains(";")) {
                Block b = Block.getBlockFromName(bl.split(";")[0]);
                if (b != null) {
                    blocks.add(b.getStateFromMeta(Integer.parseInt(bl.split(";")[1])));
                } else {
                    ORE.LOGGER.logError("No block(Replaceable)  found by the name: " + bl);
                }
            } else {
                Block b = Block.getBlockFromName(bl.split(";")[0]);
                if (b != null) {
                    blocks.add(b.getDefaultState());
                } else {
                    ORE.LOGGER.logError("No block(Replaceable) found by the name: " + bl);
                }
            }
        });
        return blocks;
    }

    public void setReplaceable(Block[] replaceable) {
        String[] replace = new String[replaceable.length];
        for (int i = 0; i < replaceable.length; i++) {
            replace[i] = replaceable[i].getRegistryName().toString();
        }
        this.replaceable = replace;
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

    public int getGenTries() {
        return genTries;
    }

    public void setGenTries(int genTries) {
        this.genTries = genTries;
    }

    public int getBlockCount() {
        return blockCount;
    }

    public void setBlockCount(int blockCount) {
        this.blockCount = blockCount;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getChunkChance() {
        return chunkChance;
    }

    public void setChunkChance(int chunkChance) {
        this.chunkChance = chunkChance;
    }

    public void setReplaceable(String[] replaceable) {
        this.replaceable = replaceable;
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





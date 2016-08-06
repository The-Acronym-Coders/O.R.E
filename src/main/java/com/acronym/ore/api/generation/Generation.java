package com.acronym.ore.api.generation;

import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jared on 8/4/2016.
 */
public class Generation {
    private String type;
    private Class<? extends OreWorldGenerator> worldGenerator;
    private String block;
    private int blockCount;
    private int size;
    private int minHeight;
    private int maxHeight;

    private List<String> replaceable;


    public Generation(Class<? extends OreWorldGenerator> type, int blockCount, int size, List<String> replaceable, int minHeight, int maxHeight) {
        this.type = GenerationRegistry.getKeyFromGenerator(type);
        this.worldGenerator = type;
        this.blockCount = blockCount;
        this.size = size;
        this.replaceable = replaceable;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    public Generation(String type, int blockCount, int size, List<String> replaceable, int minHeight, int maxHeight) {
        this.type = type;
        this.worldGenerator = GenerationRegistry.getGeneratorFromKey(type);
        this.blockCount = blockCount;
        this.size = size;
        this.replaceable = replaceable;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
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

    public void setReplaceable(List<Block> replaceable) {
        List<String> blocks = new ArrayList<String>();
        for (Block b : replaceable) {
            blocks.add(b.getRegistryName().toString());
        }
        this.replaceable = blocks;
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
        Generation gen = new Generation(type, blockCount, size, replaceable, minHeight, maxHeight);
        gen.setBlock(getBlock());
        return gen;
    }
}

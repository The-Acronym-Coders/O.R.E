package com.acronym.ore.api.generation;

import net.minecraft.block.Block;

/**
 * Created by Jared on 8/4/2016.
 */
public class Generation {
    private String type;
    private Class<? extends OreWorldGenerator> worldGenerator;
    private String block;
    private int blockCount;


    public Generation(Class<? extends OreWorldGenerator> type, int blockCount) {
        this.type = GenerationRegistry.getKeyFromGenerator(type);
        this.worldGenerator = type;
        this.blockCount = blockCount;
    }

    public Generation(String type, int blockCount) {
        this.type = type;
        this.worldGenerator = GenerationRegistry.getGeneratorFromKey(type);
        this.blockCount = blockCount;
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

    public Generation register() {
        Generation gen = new Generation(type, blockCount);
        gen.setBlock(getBlock());
        return gen;
    }
}

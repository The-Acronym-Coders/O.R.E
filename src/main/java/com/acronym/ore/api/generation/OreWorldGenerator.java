package com.acronym.ore.api.generation;

import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.WorldGenerator;

/**
 * Created by Jared on 8/4/2016.
 */
public abstract class OreWorldGenerator extends WorldGenerator {
    private Block block;
    private int size;

    public OreWorldGenerator(Block block, int size) {
        this.block = block;
        this.size = size;
    }

    public OreWorldGenerator() {
    }

    public Block getBlock() {
        return block;
    }

    public int getSize() {
        return size;
    }
}

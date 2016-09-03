package com.acronym.ore.api.generation;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Map;
import java.util.Random;

/**
 * Created by Jared on 8/4/2016.
 */
public abstract class OreWorldGenerator extends WorldGenerator {

    private Block block;
    private int size;
    private Map<String, Object> params;
    public boolean force;

    public OreWorldGenerator(Block block, int size, Map<String, Object> params) {
        this.block = block;
        this.size = size;
        this.params = params;
    }

    public OreWorldGenerator(Block block, int size, Map<String, Object> params, boolean force) {
        this.block = block;
        this.size = size;
        this.params = params;
        this.force = force;
    }


    public OreWorldGenerator() {}

    public Block getBlock() {
        return block;
    }

    public int getSize() {
        return size;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public abstract boolean generateFromCommand(World worldIn, Random rand, BlockPos position);
}

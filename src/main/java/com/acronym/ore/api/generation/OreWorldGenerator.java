package com.acronym.ore.api.generation;

import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class OreWorldGenerator extends WorldGenerator {

    public Map<Block, Integer> blocks;
    public int size;
    public Map<String, Object> params;
    public boolean force;

    public OreWorldGenerator() {
    }

    public OreWorldGenerator(Map<Block, Integer> blocks, int size, Map<String, Object> params) {
        this.blocks = blocks;
        this.size = size;
        this.params = params;
    }

    public OreWorldGenerator(Map<Block, Integer> blocks, int size, Map<String, Object> params, boolean force) {
        this.blocks = blocks;
        this.size = size;
        this.params = params;
        this.force = force;
    }


    public Map<Block, Integer> getBlocks() {
        return blocks;
    }

    public int getSize() {
        return size;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public abstract boolean generateFromCommand(World worldIn, Random rand, BlockPos position);

    public int getTotalWeight() {
        int totalWeight = 0;
        for (Integer i : blocks.values()) {
            totalWeight += i;
        }
        return totalWeight;
    }


    public Block getRandomBlock() {
        Random random = new Random();
        return blocks.entrySet().stream().map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), -Math.log(random.nextDouble()) / e.getValue()))
                .min((e0, e1) -> e0.getValue().compareTo(e1.getValue()))
                .orElseThrow(IllegalArgumentException::new).getKey();
    }

    public abstract OreWorldGenerator create(Map<Block, Integer> blocks, int blockCount, List<BlockMatcher> predicates, Map<String, Object> params);
}

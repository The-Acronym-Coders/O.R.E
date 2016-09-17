package com.acronym.ore.common.generators.feature;

import com.acronym.ore.api.generation.OreWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by EwyBoy
 **/
public class WorldGenOreCluster extends OreWorldGenerator {

    private final Map<Block, Integer> blocks;
    /**
     * The number of blocks to generate.
     */
    private final int numberOfBlocks;
    private final List<BlockMatcher> predicates;

    public WorldGenOreCluster(Map<Block, Integer> blocks, int blockCount, List<BlockMatcher> predicates, Map<String, Object> params) {
        super(blocks, blockCount, params);
        this.blocks = blocks;
        this.numberOfBlocks = blockCount;
        this.predicates = predicates;
    }

    @Override
    public OreWorldGenerator create(Map<Block, Integer> blocks, int blockCount, List<BlockMatcher> predicates, Map<String, Object> params) {
        return new WorldGenOreCluster(blocks, blockCount, predicates, params);
    }

    @Override
    public boolean generateFromCommand(World worldIn, Random rand, BlockPos position) {
        return false;
    }


    @Override
    public boolean generate(World world, Random random, BlockPos position) {
        switch (world.provider.getDimension()) {
            case 0:
                generateCluster(world, random, position);
        }
        return false;
    }

    private void generateCluster(World world, Random random, BlockPos position) {
        BlockPos genPos = new BlockPos(random.nextInt(16), 32, random.nextInt(16));
        for (int i = 0; i < 10; i++) {}
    }
}

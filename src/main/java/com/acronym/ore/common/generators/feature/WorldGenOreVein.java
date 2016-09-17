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
public class WorldGenOreVein extends OreWorldGenerator {

    private final Map<Block, Integer> blocks;
    private final int numberOfBlocks;
    private final List<BlockMatcher> predicates;

    public WorldGenOreVein(Map<Block, Integer> blocks, int blockCount, List<BlockMatcher> predicates, Map<String, Object> params) {
        super(blocks, blockCount, params);
        this.blocks = blocks;
        this.numberOfBlocks = blockCount;
        this.predicates = predicates;
    }

    @Override
    public boolean generateFromCommand(World world, Random random, BlockPos pos) {
        return false;
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos) {
        generateVein(world, random, pos);
        return true;
    }

    private boolean generateVein(World world, Random random, BlockPos pos) {
        int posX = 10;
        int negX = 5;
        int posY = 10;
        int negY = 5;
        int posZ = 10;
        int negZ = 5;

        int x = random.nextInt(16);
        int y = random.nextInt(64);
        int z = random.nextInt(16);

        for (int i = 0; i < numberOfBlocks; i++) {
            world.setBlockState(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z), getRandomBlock().getDefaultState(), 2);
        }

        /*for (int i = 0; i < numberOfBlocks; i++) {
            for (int x = pos.getX(); negX < posX; ++x) {
                for (int y = pos.getY(); negY < posY; ++y) {
                    for (int z = pos.getZ(); negZ < posZ; ++z) {
                        BlockPos spawnPos = new BlockPos(x,y,z);
                        world.setBlockState(spawnPos, getRandomBlock().getDefaultState(), 2);
                    }
                }
            }
        }*/
        return true;
    }
}

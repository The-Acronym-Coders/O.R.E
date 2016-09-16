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
    /**
     * The number of blocks to generate.
     */
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
        generateOreVein(world, random, pos);
        return true;
    }

    private void generateOreVein(World world, Random random, BlockPos pos) {
        int minVeinSize = 4; //TODO Read from file
        int maxVeinSize = 12; //TODO Read from file

        for (int i = 0; i < this.numberOfBlocks ; i++) {
            int veinSize = (int)(Math.random() * ((maxVeinSize - minVeinSize) + minVeinSize));

            int posX = pos.getX() + (veinSize / 2);
            int negX = pos.getX() - (veinSize / 2);
            int posY = pos.getY() + (veinSize / 2);
            int negY = pos.getY() - (veinSize / 2);
            int posZ = pos.getZ() + (veinSize / 2);
            int negZ = pos.getZ() - (veinSize / 2);

            for (int x = negX; negX <= posX; ++x) {
                for (int y = negY; negY <= posY; ++negY) {
                    for (int z = negZ; negZ <= posZ; ++negZ) {
                        BlockPos spawnPos = new BlockPos(x,y,z);
                        world.setBlockState(spawnPos, getRandomBlock().getDefaultState(), 2);
                    }
                }
            }
        }
    }
}

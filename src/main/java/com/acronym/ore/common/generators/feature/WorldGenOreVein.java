package com.acronym.ore.common.generators.feature;

import com.acronym.ore.api.generation.OreWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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
        int veinSize;
        int minVeinSize = 3; //TODO Read from file
        int maxVeinSize = 8; //TODO Read from file
        int minSpawnWeight = 2; //TODO Read from file
        int maxSpawnWeight = 8; //TODO Read from file
        int spawnHeight;
        int minSpawnHeight = pos.getY(); //TODO Read from file
        int maxSpawnHeight = pos.getY(); //TODO Read from file
        int chunkX;
        int chunkZ;

        for (int i = 0; i < this.numberOfBlocks ; i++) {
            spawnHeight = (int)(Math.random() * ((maxSpawnHeight - minSpawnHeight) + minSpawnHeight));
            veinSize = (int)(Math.random() * ((maxVeinSize - minVeinSize)+ minVeinSize));
            chunkX = random.nextInt(16); chunkZ = random.nextInt(16);
            BlockPos veinSingularity = pos.add(chunkX, spawnHeight, chunkZ);

            oreVeinGenerator(world, veinSingularity);
        }
    }

    private void oreVeinGenerator(World world, BlockPos pos) {
        world.setBlockState(pos, getRandomBlock().getDefaultState(), 2);
    }

    private static void helixPattern(World world, IBlockState blockState, BlockPos pos, double x, double y, double z, int flag) {
        world.setBlockState(pos.add(x, y, z), blockState, flag);
        world.setBlockState(pos.add(x, y, -z), blockState, flag);
        world.setBlockState(pos.add(-x, y, z), blockState, flag);
        world.setBlockState(pos.add(-x, y, -z), blockState, flag);
    }
}

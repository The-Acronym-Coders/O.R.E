package com.acronym.ore.common.generators.feature;

import com.acronym.ore.common.directories.Config;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

import static com.acronym.ore.common.directories.Config.flatBedrockLayers;

/**
 * Created by EwyBoy
 **/
public class WorldGenFlatBedrock implements IWorldGenerator {

    public static WorldGenFlatBedrock instance = new WorldGenFlatBedrock();

    IBlockState bedrock = Blocks.BEDROCK.getDefaultState();

    public boolean canGenerate(World world) {
        return world.getWorldType() != WorldType.FLAT && Config.flatBedrock;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (canGenerate(world)) {
            switch (world.provider.getDimensionType()) {
                case OVERWORLD:
                    generateFlatBedrockBottom(world, chunkX, chunkZ, false);
                    break;
                case NETHER:
                    generateFlatBedrockTop(world, chunkX, chunkZ, false);
                    generateFlatBedrockBottom(world, chunkX, chunkZ, false);
                    break;
                default : break;
            }
        }
    }

    public void retroGenerate(World world, int chunkX, int chunkZ) {
        if (canGenerate(world)) {
            switch (world.provider.getDimensionType()) {
                case OVERWORLD:
                    generateFlatBedrockBottom(world, chunkX, chunkZ, true);
                    break;
                case NETHER:
                    generateFlatBedrockTop(world, chunkX, chunkZ, true);
                    generateFlatBedrockBottom(world, chunkX, chunkZ, true);
                    break;
                default : break;
            }
        }
    }

    private void generateFlatBedrockTop(World world, int chunkX, int chunkZ, boolean retroGen) {
        int flag = retroGen ? 3 : 2;
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 126 - flatBedrockLayers; y < 126; y++) {
                    BlockPos pos = new BlockPos(chunkX * 16 + x, y, chunkZ * 16 + z);
                    if (!world.getBlockState(pos).equals(bedrock)) world.setBlockState(pos, bedrock, flag);
                }
            }
        }
    }

    private void generateFlatBedrockBottom(World world, int chunkX, int chunkZ, boolean retroGen) {
        int flag = retroGen ? 3 : 2;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < flatBedrockLayers; y++) {
                    BlockPos pos = new BlockPos(chunkX * 16 + x, y, chunkZ * 16 + z);
                    if (!world.getBlockState(pos).equals(bedrock)) world.setBlockState(pos, bedrock, flag);
                }
            }
        }
    }
}

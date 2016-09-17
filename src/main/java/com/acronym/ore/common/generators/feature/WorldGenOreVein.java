package com.acronym.ore.common.generators.feature;

import com.acronym.ore.api.generation.OreWorldGenerator;
import com.acronym.ore.common.helpers.Logger;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by EwyBoy
 **/
public class WorldGenOreVein extends OreWorldGenerator {

    private Map<Block, Integer> blocks;
    /**
     * The number of blocks to generate.
     */
    private int numberOfBlocks;
    private List<BlockMatcher> predicates;


    public WorldGenOreVein() {
    }
    public WorldGenOreVein(Map<Block, Integer> blocks, int blockCount, List<BlockMatcher> predicates, Map<String, Object> params) {
        super(blocks, blockCount, params);
        this.blocks = blocks;
        this.numberOfBlocks = blockCount;
        this.predicates = predicates;
    }

    @Override
    public OreWorldGenerator create(Map<Block, Integer> blocks, int blockCount, List<BlockMatcher> predicates, Map<String, Object> params) {
        return new WorldGenOreVein(blocks, blockCount, predicates, params);
    }

    @Override
    public boolean generateFromCommand(World world, Random random, BlockPos pos) {
        return false;
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos) {
        float f = random.nextFloat() * (float) Math.PI;

        double posX = (double) ((float) (pos.getX() + 8) + MathHelper.sin(f) * (float) this.numberOfBlocks / 8.0F);
        double negX = (double) ((float) (pos.getX() + 8) - MathHelper.sin(f) * (float) this.numberOfBlocks / 8.0F);
        double posZ = (double) ((float) (pos.getZ() + 8) + MathHelper.cos(f) * (float) this.numberOfBlocks / 8.0F);
        double negZ = (double) ((float) (pos.getZ() + 8) - MathHelper.cos(f) * (float) this.numberOfBlocks / 8.0F);

        double posY = (double) (pos.getY() + random.nextInt(3) - 2);
        double negY = (double) (pos.getY() - random.nextInt(3) - 2);

        for (int i = 0; i < this.numberOfBlocks; ++i) {
            float size = (float) i / (float) this.numberOfBlocks;

            double x = posX + (negX - posX) * (double) size;
            double y = posY + (negY - posY) * (double) size;
            double z = posZ + (negZ - posZ) * (double) size;

            double weight = random.nextDouble() * (double) this.numberOfBlocks / 16.0D;
            double negX0 = (double) (MathHelper.sin((float) Math.PI * size) + 1.0F) * weight + 1.0D;
            double negX1 = (double) (MathHelper.sin((float) Math.PI * size) + 1.0F) * weight + 1.0D;

            int spawnNegX = MathHelper.floor_double(x - negX0 / 2.0D);
            int spawnNegY = MathHelper.floor_double(y - negX1 / 2.0D);
            int spawnNegZ = MathHelper.floor_double(z - negX0 / 2.0D);
            int spawnPosX = MathHelper.floor_double(x + negX0 / 2.0D);
            int spawnPosY = MathHelper.floor_double(y + negX1 / 2.0D);
            int spawnPosZ = MathHelper.floor_double(z + negX0 / 2.0D);

            for (int xPos = spawnNegX; xPos <= spawnPosX; ++xPos) {
                double negX2 = ((double) xPos + 0.5D - x) / (negX0 / 2.0D);

                if (negX2 * negX2 < 1.0D) {

                    for (int yPos = spawnNegY; yPos <= spawnPosY; ++yPos) {
                        double negX3 = ((double) yPos + 0.5D - y) / (negX1 / 2.0D);

                        if (negX2 * negX2 + negX3 * negX3 < 1.0D) {

                            for (int zPos = spawnNegZ; zPos <= spawnPosZ; ++zPos) {
                                double negX4 = ((double) zPos + 0.5D - z) / (negX0 / 2.0D);

                                if (negX2 * negX2 + negX3 * negX3 + negX4 * negX4 < 1.0D) {
                                    BlockPos blockpos = new BlockPos(xPos, yPos, zPos);

                                    IBlockState state = world.getBlockState(blockpos);
                                    boolean gen = false;

                                    if (force) {
                                        gen = true;
                                        Logger.info(gen + ":" + force);
                                    } else {
                                        for (BlockMatcher match : predicates) {
                                            if (state.getBlock().isReplaceableOreGen(state, world, blockpos, match)) {
                                                gen = true;
                                                break;
                                            }
                                        }
                                    } if (gen) {
                                        world.setBlockState(blockpos, getRandomBlock().getDefaultState(), 2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}

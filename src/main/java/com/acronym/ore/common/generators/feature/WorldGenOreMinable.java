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

public class WorldGenOreMinable extends OreWorldGenerator {

    private final Map<Block, Integer> blocks;
    /**
     * The number of blocks to generate.
     */
    private final int numberOfBlocks;
    private final List<BlockMatcher> predicates;

    public WorldGenOreMinable(Map<Block, Integer> blocks, int blockCount, List<BlockMatcher> predicates, Map<String, Object> params) {
        super(blocks, blockCount, params);
        this.blocks = blocks;
        this.numberOfBlocks = blockCount;
        this.predicates = predicates;
    }

    public boolean generate(World world, Random rand, BlockPos position) {

        float f = rand.nextFloat() * (float) Math.PI;

        double posX = (double) ((float) (position.getX() + 8) + MathHelper.sin(f) * (float) this.numberOfBlocks / 8.0F);
        double negX = (double) ((float) (position.getX() + 8) - MathHelper.sin(f) * (float) this.numberOfBlocks / 8.0F);
        double posZ = (double) ((float) (position.getZ() + 8) + MathHelper.cos(f) * (float) this.numberOfBlocks / 8.0F);
        double negZ = (double) ((float) (position.getZ() + 8) - MathHelper.cos(f) * (float) this.numberOfBlocks / 8.0F);

        double d4 = (double) (position.getY() + rand.nextInt(3) - 2);
        double d5 = (double) (position.getY() + rand.nextInt(3) - 2);

        for (int i = 0; i < this.numberOfBlocks; ++i) {
            float size = (float) i / (float) this.numberOfBlocks;

            double x = posX + (negX - posX) * (double) size;
            double y = d4 + (d5 - d4) * (double) size;
            double z = posZ + (negZ - posZ) * (double) size;

            double weight = rand.nextDouble() * (double) this.numberOfBlocks / 16.0D;
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

    @Override
    public boolean generateFromCommand(World worldIn, Random rand, BlockPos position) {
        float f = rand.nextFloat() * (float) Math.PI;
        double posX = (double) ((float) (position.getX()) + MathHelper.sin(f) * (float) this.numberOfBlocks / 8.0F);
        double negX = (double) ((float) (position.getX()) - MathHelper.sin(f) * (float) this.numberOfBlocks / 8.0F);
        double posZ = (double) ((float) (position.getZ()) + MathHelper.cos(f) * (float) this.numberOfBlocks / 8.0F);
        double negZ = (double) ((float) (position.getZ()) - MathHelper.cos(f) * (float) this.numberOfBlocks / 8.0F);
        double d4 = (double) (position.getY() + rand.nextInt(3) - 2);
        double d5 = (double) (position.getY() + rand.nextInt(3) - 2);

        for (int i = 0; i < this.numberOfBlocks; ++i) {
            float size = (float) i / (float) this.numberOfBlocks;
            double x = posX + (negX - posX) * (double) size;
            double y = d4 + (d5 - d4) * (double) size;
            double z = posZ + (negZ - posZ) * (double) size;
            double d9 = rand.nextDouble() * (double) this.numberOfBlocks / 16.0D;
            double negX0 = (double) (MathHelper.sin((float) Math.PI * size) + 1.0F) * d9 + 1.0D;
            double negX1 = (double) (MathHelper.sin((float) Math.PI * size) + 1.0F) * d9 + 1.0D;
            int j = MathHelper.floor_double(x - negX0 / 2.0D);
            int k = MathHelper.floor_double(y - negX1 / 2.0D);
            int l = MathHelper.floor_double(z - negX0 / 2.0D);
            int i1 = MathHelper.floor_double(x + negX0 / 2.0D);
            int j1 = MathHelper.floor_double(y + negX1 / 2.0D);
            int k1 = MathHelper.floor_double(z + negX0 / 2.0D);

            for (int l1 = j; l1 <= i1; ++l1) {
                double negX2 = ((double) l1 + 0.5D - x) / (negX0 / 2.0D);

                if (negX2 * negX2 < 1.0D) {
                    for (int i2 = k; i2 <= j1; ++i2) {
                        double negX3 = ((double) i2 + 0.5D - y) / (negX1 / 2.0D);

                        if (negX2 * negX2 + negX3 * negX3 < 1.0D) {
                            for (int j2 = l; j2 <= k1; ++j2) {
                                double negX4 = ((double) j2 + 0.5D - z) / (negX0 / 2.0D);

                                if (negX2 * negX2 + negX3 * negX3 + negX4 * negX4 < 1.0D) {
                                    BlockPos blockpos = new BlockPos(l1, i2, j2);

                                    IBlockState state = worldIn.getBlockState(blockpos);
                                    worldIn.setBlockState(blockpos, getRandomBlock().getDefaultState(),2);
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

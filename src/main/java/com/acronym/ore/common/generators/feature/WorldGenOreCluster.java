package com.acronym.ore.common.generators.feature;

import com.acronym.ore.api.generation.OreWorldGenerator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class WorldGenOreCluster extends OreWorldGenerator {

    private Map<IBlockState, Integer> blocks;
    private int numberOfBlocks;
    private List<BlockMatcher> predicates;

    public WorldGenOreCluster(Map<IBlockState, Integer> blocks, int blockCount, List<BlockMatcher> predicates, Map<String, Object> params) {
        super(blocks, blockCount, params);
        this.blocks = blocks;
        this.numberOfBlocks = blockCount;
        this.predicates = predicates;
    }

    public WorldGenOreCluster() {
    }

    @Override
    public OreWorldGenerator create(Map<IBlockState, Integer> blocks, int blockCount, List<BlockMatcher> predicates, Map<String, Object> params) {
        return new WorldGenOreCluster(blocks, blockCount, predicates, params);
    }

    @Override
    public boolean generateFromCommand(World worldIn, Random rand, BlockPos position) {
        return false;
    }


    @Override
    public boolean generate(World world, Random random, BlockPos position) {
        return generateCluster(world, random, position);
    }

    private boolean generateCluster(World world, Random random, BlockPos pos) {
        int genClusterSize = numberOfBlocks;

        int blocks = genClusterSize;
        if (blocks < 4) { // HACK: at 1 and 2 no ores are ever generated. at 3 only 1/3 veins generate
            boolean r = false;
            // not <=; generating up to clusterSize blocks
            for (int i = 0; i < genClusterSize; i++) {
                r |= world.setBlockState(pos.add(random.nextInt(2), random.nextInt(2), random.nextInt(2)), getRandomBlock(), 2);
            }
            return r;
        }
        float f = world.rand.nextFloat() * (float) Math.PI;
        // despite naming, these are not exactly min/max. more like direction
        float xMin = pos.getX() + 8 + (MathHelper.sin(f) * blocks) / 8F;
        float xMax = pos.getX() + 8 - (MathHelper.sin(f) * blocks) / 8F;
        float zMin = pos.getX() + 8 + (MathHelper.cos(f) * blocks) / 8F;
        float zMax = pos.getZ() + 8 - (MathHelper.cos(f) * blocks) / 8F;
        float yMin = (pos.getZ() + world.rand.nextInt(3)) - 2;
        float yMax = (pos.getZ() + world.rand.nextInt(3)) - 2;

        // optimization so this subtraction doesn't occur every time in the loop
        xMax -= xMin;
        yMax -= yMin;
        zMax -= zMin;

        boolean r = false;
        for (int i = 0; i <= blocks; i++) {

            float xCenter = xMin + (xMax * i) / blocks;
            float yCenter = yMin + (yMax * i) / blocks;
            float zCenter = zMin + (zMax * i) / blocks;

            // preserved as nextDouble to ensure the rand gets ticked the same amount
            float size = ((float) world.rand.nextDouble() * blocks) / 16f;

            float hMod = ((MathHelper.sin((i * (float) Math.PI) / blocks) + 1f) * size + 1f) * .5f;
            float vMod = ((MathHelper.sin((i * (float) Math.PI) / blocks) + 1f) * size + 1f) * .5f;

            int xStart = MathHelper.floor_float(xCenter - hMod);
            int yStart = MathHelper.floor_float(yCenter - vMod);
            int zStart = MathHelper.floor_float(zCenter - hMod);

            int xStop = MathHelper.floor_float(xCenter + hMod);
            int yStop = MathHelper.floor_float(yCenter + vMod);
            int zStop = MathHelper.floor_float(zCenter + hMod);

            for (int blockX = xStart; blockX <= xStop; blockX++) {
                float xDistSq = ((blockX + .5f) - xCenter) / hMod;
                xDistSq *= xDistSq;
                if (xDistSq >= 1f) {
                    continue;
                }

                for (int blockY = yStart; blockY <= yStop; blockY++) {
                    float yDistSq = ((blockY + .5f) - yCenter) / vMod;
                    yDistSq *= yDistSq;
                    float xyDistSq = yDistSq + xDistSq;
                    if (xyDistSq >= 1f) {
                        continue;
                    }

                    for (int blockZ = zStart; blockZ <= zStop; blockZ++) {
                        float zDistSq = ((blockZ + .5f) - zCenter) / hMod;
                        zDistSq *= zDistSq;
                        if (zDistSq + xyDistSq >= 1f) {
                            continue;
                        }
                        r |= world.setBlockState(new BlockPos(blockX, blockY, blockZ), getRandomBlock(), 2);
                    }
                }
            }
        }

        return r;
    }
}

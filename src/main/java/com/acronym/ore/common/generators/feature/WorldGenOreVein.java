package com.acronym.ore.common.generators.feature;

import com.acronym.ore.api.generation.OreWorldGenerator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class WorldGenOreVein extends OreWorldGenerator {

    private Map<IBlockState, Integer> blocks;
    private int numberOfBlocks;
    private List<BlockStateMatcher> predicates;

    public WorldGenOreVein() {}

    public WorldGenOreVein(Map<IBlockState, Integer> blocks, int blockCount, List<BlockStateMatcher> predicates, Map<String, Object> params) {
        super(blocks, blockCount, params);
        this.blocks = blocks;
        this.numberOfBlocks = blockCount;
        this.predicates = predicates;
    }

    @Override
    public OreWorldGenerator create(Map<IBlockState, Integer> blocks, int blockCount, List<BlockStateMatcher> predicates, Map<String, Object> params) {
        return new WorldGenOreVein(blocks, blockCount, predicates, params);
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

        boolean sparse = false;

        for (Map.Entry<String, Object> ent : getParams().entrySet()) {
            switch (ent.getKey().toLowerCase()) {
                case "sparse":
                    sparse = Boolean.parseBoolean("" + ent.getValue());
                    break;
                default:
                    System.out.println(String.format("Unknown Param: %s, with Value: %s", ent.getKey(), ent.getValue()));
                    break;
            }
        }
        int branchSize = 1 + (numberOfBlocks / 30);
        int subBranchSize = 1 + (branchSize / 5);
        boolean r = false;
        for (int blocksVein = 0; blocksVein <= numberOfBlocks; ) {
            BlockPos curPos = pos;
            int directionChange = world.rand.nextInt(6);

            int directionX1 = -world.rand.nextInt(2);
            int directionY1 = -world.rand.nextInt(2);
            int directionZ1 = -world.rand.nextInt(2);
            directionX1 += ~directionX1 >>> 31;
            directionY1 += ~directionY1 >>> 31;
            directionZ1 += ~directionZ1 >>> 31;

            for (int blocksBranch = 0; blocksBranch <= branchSize; ) {
                if (directionChange != 1) {
                    curPos =curPos.add(world.rand.nextInt(2) * directionX1, 0, 0);
                }
                if (directionChange != 2) {
                    curPos = curPos.add(0, world.rand.nextInt(2) * directionY1, 0);
                }
                if (directionChange != 3) {
                    curPos =curPos.add(0, 0, world.rand.nextInt(2) * directionZ1);
                }

                if (world.rand.nextInt(3) == 0) {
                    BlockPos nPos = curPos;

                    int directionChange2 = world.rand.nextInt(6);

                    int directionX2 = -world.rand.nextInt(2);
                    int directionY2 = -world.rand.nextInt(2);
                    int directionZ2 = -world.rand.nextInt(2);
                    directionX2 += ~directionX2 >>> 31;
                    directionY2 += ~directionY2 >>> 31;
                    directionZ2 += ~directionZ2 >>> 31;

                    for (int blocksSubBranch = 0; blocksSubBranch <= subBranchSize; ) {
                        if (directionChange2 != 0) {
                            nPos = nPos.add(world.rand.nextInt(2) * directionX2, 0, 0);
                        }
                        if (directionChange2 != 1) {
                            nPos =nPos.add(0, world.rand.nextInt(2) * directionY2, 0);
                        }
                        if (directionChange2 != 2) {
                            nPos =nPos.add(0, 0, world.rand.nextInt(2) * directionZ2);
                        }

                        r |= world.setBlockState(nPos, getRandomBlock(), 2);
                        if (sparse) {
                            blocksVein++;
                            blocksBranch++;
                        }
                        blocksSubBranch++;
                    }
                }

                r |= world.setBlockState(curPos, getRandomBlock(), 2);

                blocksBranch++;
            }
            pos = pos.add((world.rand.nextInt(3) - 1), (world.rand.nextInt(3) - 1), (world.rand.nextInt(3) - 1));
            blocksVein++;
        }

        return r;
    }
}


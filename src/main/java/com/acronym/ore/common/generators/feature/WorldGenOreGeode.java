package com.acronym.ore.common.generators.feature;

import com.acronym.ore.api.generation.OreWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class WorldGenOreGeode extends OreWorldGenerator {
    private Map<IBlockState, Integer> blocks;
    /**
     * The number of blocks to generate.
     */
    private int numberOfBlocks;
    private List<BlockMatcher> predicates;

    public WorldGenOreGeode(Map<IBlockState, Integer> blocks, int blockCount, List<BlockMatcher> predicates, Map<String, Object> params) {
        super(blocks, blockCount, params);
        this.blocks = blocks;
        this.numberOfBlocks = blockCount;
        this.predicates = predicates;
    }

    public WorldGenOreGeode() {
    }

    @Override
    public OreWorldGenerator create(Map<IBlockState, Integer> blocks, int blockCount, List<BlockMatcher> predicates, Map<String, Object> params) {
        return new WorldGenOreGeode(blocks, blockCount, predicates, params);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {

        return generateGeode(worldIn, rand, position);
    }

    @Override
    public boolean generateFromCommand(World worldIn, Random rand, BlockPos position) {

        return generateGeode(worldIn, rand, position);
    }


    private boolean generateGeode(World worldIn, Random rand, BlockPos pos) {
        int width = 1;
        int height = 1;
        boolean hollow = false;
        Block outline = Blocks.STONE;
        for (Map.Entry<String, Object> ent : getParams().entrySet()) {
            switch (ent.getKey()) {
                case "width":
                    width = Integer.parseInt((ent.getValue() + "").replace(".0", ""));
                    break;
                case "height":
                    height = Integer.parseInt((ent.getValue() + "").replace(".0", ""));
                    break;
                case "hollow":
                    hollow = (boolean) ent.getValue();
                    break;
                case "outline":
                    outline = Block.getBlockFromName(ent.getValue() + "");
                    break;
                default:
                    System.out.println(String.format("Unknown Param: %s, with Value: %s", ent.getKey(), ent.getValue()));
                    break;
            }
        }

        int heightOff = height / 2;
        int widthOff = width / 2;
        pos = pos.add(-widthOff, 0, -widthOff);

        if (pos.getY() <= heightOff) {
            return false;
        }
        pos = pos.add(0, -heightOff, 0);

        boolean[] spawnBlock = new boolean[width * width * height];
        boolean[] hollowBlock = new boolean[width * width * height];

        int W = width - 1, H = height - 1;

        for (int i = 0, e = rand.nextInt(4) + 4; i < e; ++i) {
            double xSize = rand.nextDouble() * 6.0D + 3.0D;
            double ySize = rand.nextDouble() * 4.0D + 2.0D;
            double zSize = rand.nextDouble() * 6.0D + 3.0D;
            double xCenter = rand.nextDouble() * (width - xSize - 2.0D) + 1.0D + xSize / 2.0D;
            double yCenter = rand.nextDouble() * (height - ySize - 4.0D) + 2.0D + ySize / 2.0D;
            double zCenter = rand.nextDouble() * (width - zSize - 2.0D) + 1.0D + zSize / 2.0D;
            double minDist = hollow ? rand.nextGaussian() * 0.15 + 0.4 : 0;

            for (int x = 1; x < W; ++x) {
                for (int z = 1; z < W; ++z) {
                    for (int y = 1; y < H; ++y) {
                        double xDist = (x - xCenter) / (xSize / 2.0D);
                        double yDist = (y - yCenter) / (ySize / 2.0D);
                        double zDist = (z - zCenter) / (zSize / 2.0D);
                        double dist = xDist * xDist + yDist * yDist + zDist * zDist;

                        if (dist < 1.0D) {
                            spawnBlock[(x * width + z) * height + y] = hollow ? dist > minDist : true;
                        }
                        if (hollow) {
                            hollowBlock[(x * width + z) * height + y] = dist <= minDist;
                        }
                    }
                }
            }
        }

        int x;
        int y;
        int z;

       /* for (x = 0; x < width; ++x) {
            for (z = 0; z < width; ++z) {
                for (y = 0; y < height; ++y) {
//                    boolean flag = (getRandomBlock() != null && hollowBlock[(x * width + z) * height + y])
//                            || spawnBlock[(x * width + z) * height + y]
//                            || ((x < W && spawnBlock[((x + 1) * width + z) * height + y]) || (x > 0 && spawnBlock[((x - 1) * width + z) * height + y])
//                            || (z < W && spawnBlock[(x * width + (z + 1)) * height + y]) || (z > 0 && spawnBlock[(x * width + (z - 1)) * height + y])
//                            || (y < H && spawnBlock[(x * width + z) * height + (y + 1)]) || (y > 0 && spawnBlock[(x * width + z) * height + (y - 1)]));
//
//                    if (flag *//*&& !canGenerateInBlock(world, xStart + x, yStart + y, zStart + z, genBlock)*//*) {
//                        return false;
//                    }
                }
            }
        }*/

        boolean r = false;
        for (x = 0; x < width; ++x) {
            for (z = 0; z < width; ++z) {
                for (y = 0; y < height; ++y) {
                    if (spawnBlock[(x * width + z) * height + y]) {
                        boolean t = worldIn.setBlockState(pos.add(x, y, z), getRandomBlock(), 2);
                        r |= t;
                        if (!t) {
                            spawnBlock[(x * width + z) * height + y] = false;
                        }
                    }
                }
            }
        }

        for (x = 0; x < width; ++x) {
            for (z = 0; z < width; ++z) {
                for (y = 0; y < height; ++y) {
                    if (getRandomBlock() != null && hollowBlock[(x * width + z) * height + y]) {
                        r |= worldIn.setBlockState(pos.add(x, y, z), getRandomBlock(), 2);
                    } else {
                        boolean flag = !spawnBlock[(x * width + z) * height + y]
                                && ((x < W && spawnBlock[((x + 1) * width + z) * height + y]) || (x > 0 && spawnBlock[((x - 1) * width + z) * height + y])
                                || (z < W && spawnBlock[(x * width + (z + 1)) * height + y])
                                || (z > 0 && spawnBlock[(x * width + (z - 1)) * height + y])
                                || (y < H && spawnBlock[(x * width + z) * height + (y + 1)]) || (y > 0 && spawnBlock[(x * width + z) * height + (y - 1)]));

                        if (flag) {
                            r |= worldIn.setBlockState(pos.add(x, y, z), outline.getDefaultState(), 2);
                        }
                    }
                }
            }
        }

        return r;
    }
}

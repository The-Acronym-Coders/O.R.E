package com.acronym.ore.world.generators.feature;

import com.acronym.ore.api.generation.OreWorldGenerator;
import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Random;

/**
 * Created by Jared on 8/19/2016.
 */
public class WorldGenOreGeode extends OreWorldGenerator {
    private final IBlockState oreBlock;
    /**
     * The number of blocks to generate.
     */
    private final int numberOfBlocks;
    private final Predicate<IBlockState> predicate;

    public WorldGenOreGeode(Block block, int blockCount, Map<String, Object> params) {
        this(block.getDefaultState(), blockCount, params);
    }

    public WorldGenOreGeode(IBlockState state, int blockCount, Map<String, Object> params) {
        this(state, blockCount, BlockMatcher.forBlock(Blocks.STONE), params);
    }

    public WorldGenOreGeode(IBlockState state, int blockCount, Predicate<IBlockState> predicate, Map<String, Object> params) {
        super(state.getBlock(), blockCount, params);
        this.oreBlock = state;
        this.numberOfBlocks = blockCount;
        this.predicate = predicate;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int width = 1;
        int height = 1;
        boolean hollow = false;
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
                default:
                    System.out.println(String.format("Unknown Param: %s, with Value: %s", ent.getKey(), ent.getValue()));
                    break;
            }
        }
        //example of how to use the "params":{}
        return false;
    }

}

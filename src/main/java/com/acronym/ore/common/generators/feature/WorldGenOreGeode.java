package com.acronym.ore.common.generators.feature;

import com.acronym.ore.api.generation.OreWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class WorldGenOreGeode extends OreWorldGenerator {
    private Map<Block, Integer> blocks;
    /**
     * The number of blocks to generate.
     */
    private int numberOfBlocks;
    private List<BlockMatcher> predicates;

    public WorldGenOreGeode(Map<Block, Integer> blocks, int blockCount, List<BlockMatcher> predicates, Map<String, Object> params) {
        super(blocks, blockCount, params);
        this.blocks = blocks;
        this.numberOfBlocks = blockCount;
        this.predicates = predicates;
    }

    public WorldGenOreGeode() {
    }

    @Override
    public OreWorldGenerator create(Map<Block, Integer> blocks, int blockCount, List<BlockMatcher> predicates, Map<String, Object> params) {
        return new WorldGenOreGeode(blocks, blockCount, predicates, params);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        generateGeode();
        return false;
    }

    @Override
    public boolean generateFromCommand(World worldIn, Random rand, BlockPos position) {
        generateGeode();
        return false;
    }


    private void generateGeode() {
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
    }
}

package com.acronym.ore.common.generators.feature;

import com.acronym.ore.api.generation.OreWorldGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by EwyBoy
 **/
public class WorldGenOreVein extends OreWorldGenerator {

    @Override
    public boolean generateFromCommand(World world, Random rand, BlockPos position) {
        return false;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        return false;
    }
}

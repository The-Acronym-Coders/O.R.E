package com.acronym.ore.world.generators.feature;

import com.acronym.ore.api.generation.OreWorldGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by EwyBoy
 **/
public class WorldGenOreCluster extends OreWorldGenerator {

    @Override
    public boolean generateFromCommand(World worldIn, Random rand, BlockPos position) {
        return false;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        return false;
    }
}

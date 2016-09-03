package com.acronym.ore.common.generators.feature;

import com.acronym.ore.api.generation.OreWorldGenerator;
import net.minecraft.init.Blocks;
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
    public boolean generate(World world, Random random, BlockPos position) {
        switch (world.provider.getDimension()) {
            case 0: generateCluster(world, random, position);
        }
        return false;
    }

    private void generateCluster(World world, Random random, BlockPos position) {
        BlockPos genPos = new BlockPos(random.nextInt(16), 32, random.nextInt(16));

        for (int i = 0; i < 10; i++) {
            new WorldGenOreMinable(Blocks.EMERALD_ORE, 10);
        }
    }
}

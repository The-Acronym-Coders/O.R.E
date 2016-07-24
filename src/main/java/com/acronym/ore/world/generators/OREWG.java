package com.acronym.ore.world.generators;

import com.acronym.ore.world.generators.feature.WorldGenOreMinable;
import com.acronym.ore.world.generators.feature.WorldGenTree;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by Jared on 7/24/2016.
 */
public class OREWG implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        WorldGenTree gen = new WorldGenTree(true, 5, Blocks.DIAMOND_BLOCK.getDefaultState(), Blocks.IRON_BLOCK.getDefaultState());
        WorldGenOreMinable ore = new WorldGenOreMinable(Blocks.DIAMOND_BLOCK.getDefaultState(), 8);
        if (random.nextInt(20) == 0) {
            addOre(world, random, new BlockPos(chunkX * 16, 0, chunkZ * 16), ore);
//            addTree(world, random, new BlockPos(chunkX * 16, 0, chunkZ * 16), gen);
        }
    }

    public void addTree(World p_185379_1_, Random p_185379_2_, BlockPos p_185379_3_, WorldGenTree tree) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                int k = i * 4 + 1 + 8 + p_185379_2_.nextInt(3);
                int l = j * 4 + 1 + 8 + p_185379_2_.nextInt(3);
                BlockPos blockpos = p_185379_1_.getHeight(p_185379_3_.add(k, 0, l)).up(5);
                tree.setDecorationDefaults();
                if (tree.generate(p_185379_1_, p_185379_2_, blockpos)) {
                    tree.generateSaplings(p_185379_1_, p_185379_2_, blockpos);
                }
            }
        }
    }

    public void addOre(World world, Random rand, BlockPos pos, WorldGenOreMinable gen) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                int k = i * 4 + 1 + 8 + rand.nextInt(3);
                int l = j * 4 + 1 + 8 + rand.nextInt(3);
                BlockPos blockpos = world.getHeight(pos.add(k, 0, l));
                gen.generate(world, rand, blockpos);
            }
        }
    }
}

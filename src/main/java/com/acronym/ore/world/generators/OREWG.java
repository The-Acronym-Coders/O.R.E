package com.acronym.ore.world.generators;

import com.acronym.ore.api.generation.Generation;
import com.acronym.ore.api.generation.GenerationRegistry;
import com.acronym.ore.api.generation.OreWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

/**
 * Created by Jared on 7/24/2016.
 */
public class OREWG implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (GenerationRegistry.getGenerations().containsKey(world.provider.getDimension())) {
            List<Generation> generations = GenerationRegistry.getGenerations().get(world.provider.getDimension());
            for (Generation gen : generations) {
                int chance = random.nextInt(99);
                if (chance != 0)
                    if (chance + 1 < gen.getChunkChance()) {
                        BlockPos bp = new BlockPos(chunkX + 16, 0, chunkZ * 16).add(random.nextInt(16), 0, random.nextInt(16));
                        if (world.getBiomeGenForCoords(bp).getBiomeName().contains(gen.getBiome()))
                            try {
                                gen(world, random, bp, gen.getBlockCount(), gen.getWorldGenerator().getConstructor(Block.class, int.class).newInstance(gen.getBlock(), gen.getSize()), gen.getMinHeight(), gen.getMaxHeight());
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            }
                    }
            }
        }

    }

    /**
     * Standard ore generation helper. Generates most ores.
     */
    protected void gen(World worldIn, Random random, BlockPos pos, int blockCount, OreWorldGenerator generator, int minHeight, int maxHeight) {
        if (maxHeight < minHeight) {
            int i = minHeight;
            minHeight = maxHeight;
            maxHeight = i;
        } else if (maxHeight == minHeight) {
            if (minHeight < 255) {
                ++maxHeight;
            } else {
                --minHeight;
            }
        }

        for (int j = 0; j < blockCount; ++j) {
            //TODO remove up(50)
            BlockPos blockpos = pos.add(0, random.nextInt(maxHeight - minHeight) + minHeight, 0).up(50);
            generator.generate(worldIn, random, blockpos);
        }
    }
}

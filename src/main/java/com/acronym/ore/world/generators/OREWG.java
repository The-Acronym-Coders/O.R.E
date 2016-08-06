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
import java.util.Random;

/**
 * Created by Jared on 7/24/2016.
 */
public class OREWG implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        for (Generation gene : GenerationRegistry.getGenerations()) {
            if (random.nextInt(10) == 0) {
                try {
                    gen(world, random, new BlockPos(chunkX * 16, 0, chunkZ * 16), gene.getBlockCount(), gene.getWorldGenerator().getConstructor(Block.class, int.class).newInstance(gene.getBlock(), gene.getSize()), gene.getMinHeight(), gene.getMaxHeight());
//                    gene.getWorldGenerator().getConstructor(Block.class, int.class).newInstance(gene.getBlock(), gene.getBlockCount()).generate(world, random, world.getHeight(new BlockPos(chunkX * 16, 0, chunkZ * 16)).up(50));
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
//            addOre(world, random, new BlockPos(chunkX * 16, 0, chunkZ * 16), ore);
//            addTree(world, random, new BlockPos(chunkX * 16, 0, chunkZ * 16), gen);
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
            BlockPos blockpos = pos.add(random.nextInt(16), random.nextInt(maxHeight - minHeight) + minHeight, random.nextInt(16)).up(50);
            generator.generate(worldIn, random, blockpos);
        }
    }
}

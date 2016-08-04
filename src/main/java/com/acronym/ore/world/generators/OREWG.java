package com.acronym.ore.world.generators;

import com.acronym.ore.api.generation.Generation;
import com.acronym.ore.api.generation.GenerationRegistry;
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
                    gene.getWorldGenerator().getConstructor(Block.class, int.class).newInstance(gene.getBlock(), gene.getBlockCount()).generate(world, random, world.getHeight(new BlockPos(chunkX * 16, 0, chunkZ * 16)).up(50));
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

}

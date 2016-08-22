package com.acronym.ore.world.generators.retrogen;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

/**
 * Created by EwyBoy
 **/
public interface IRetroGen {

    public String getUniqueGenerationID();

    public boolean canGenerateIn(World world, Chunk chunk);

    public void generate(Random rand, World world, int chunkX, int chunkZ);

}

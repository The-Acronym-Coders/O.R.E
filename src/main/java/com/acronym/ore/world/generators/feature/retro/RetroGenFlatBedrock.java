package com.acronym.ore.world.generators.feature.retro;

import com.acronym.ore.reference.Reference;
import com.acronym.ore.world.generators.feature.WorldGenFlatBedrock;
import com.acronym.ore.world.generators.retrogen.IRetroGen;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

/**
 * Created by EwyBoy
 **/
public class RetroGenFlatBedrock implements IRetroGen {

    @Override
    public String getUniqueGenerationID() {
        return Reference.MODID + ":" + "flatbedrock";
    }

    @Override
    public boolean canGenerateIn(World world, Chunk chunk) {
        return WorldGenFlatBedrock.instance.canGenerate(world, chunk.xPosition, chunk.zPosition);
    }

    @Override
    public void generate(Random rand, World world, int chunkX, int chunkZ) {
        WorldGenFlatBedrock.instance.retroGenerateFlatBedrock(world, chunkX, chunkZ);
    }
}

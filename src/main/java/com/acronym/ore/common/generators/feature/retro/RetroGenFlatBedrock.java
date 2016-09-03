package com.acronym.ore.common.generators.feature.retro;

import com.acronym.ore.common.generators.feature.WorldGenFlatBedrock;
import com.acronym.ore.common.generators.retrogen.IRetroGen;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

import static com.acronym.ore.common.reference.Reference.ModInfo.MODID;

/**
 * Created by EwyBoy
 **/
public class RetroGenFlatBedrock implements IRetroGen {

    @Override
    public String getUniqueGenerationID() {
        return MODID + ":" + "flatbedrock";
    }

    @Override
    public boolean canGenerateIn(World world, Chunk chunk) {
        return WorldGenFlatBedrock.instance.canGenerate(world);
    }

    @Override
    public void generate(Random rand, World world, int chunkX, int chunkZ) {
        WorldGenFlatBedrock.instance.retroGenerateFlatBedrock(world, chunkX, chunkZ);
    }
}

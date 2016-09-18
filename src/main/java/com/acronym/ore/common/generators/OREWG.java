package com.acronym.ore.common.generators;

import com.acronym.ore.api.generation.Generation;
import com.acronym.ore.api.generation.GenerationRegistry;
import com.acronym.ore.api.generation.OreWorldGenerator;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.acronym.ore.common.reference.Reference.Directories.ENGINE_JAVASCRIPT;

public class OREWG implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        try {
            GenerationRegistry.getGenerations().stream().filter(gen -> {
                switch (gen.getDimensionsRestriction()) {
                    case "none":
                        return true;
                    case "whitelist":
                        return gen.getDimensions().contains(world.provider.getDimension());
                    default:
                        return !gen.getDimensions().contains(world.provider.getDimension());
                }
            }).filter(gen -> {
                int chance = random.nextInt(99);
                if (chance != 0)
                    try {
                        if (chance + 1 < (int) ENGINE_JAVASCRIPT.eval(gen.getChunkChance())) {
                            return true;
                        }
                    } catch (ScriptException e) {
                        e.printStackTrace();
                    }
                return false;
            }).forEach(gen -> {
                boolean canGen = false;
                BlockPos pos = new BlockPos(chunkX * 16, 0, chunkZ * 16).add(random.nextInt(16), 0, random.nextInt(16));
                switch (gen.getBiomeRestriction()) {
                    case "none":
                        canGen = true;
                        break;
                    case "whitelist":
                        canGen = checkbiome(gen, world.getBiomeGenForCoords(pos));
                        break;
                    default:
                        canGen = !checkbiome(gen, world.getBiomeGenForCoords(pos));
                        break;
                }
                if (canGen) {
                    try {
                        List<BlockMatcher> matcher = new ArrayList<>();
                        gen.getReplaceable().forEach(bl -> matcher.add(BlockMatcher.forBlock(bl)));
                        gen(world, random, pos, new Double(String.valueOf(ENGINE_JAVASCRIPT.eval(gen.getGenTries()))).intValue(), gen.getWorldGenerator().create(gen.getBlocks(), new Double(String.valueOf(ENGINE_JAVASCRIPT.eval(gen.getBlockCount()))).intValue(), matcher, gen.getParams()), new Double(String.valueOf(ENGINE_JAVASCRIPT.eval(gen.getMinHeight()))).intValue(), new Double(String.valueOf(ENGINE_JAVASCRIPT.eval(gen.getMaxHeight()))).intValue());
                    } catch (ScriptException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkbiome(Generation gen, Biome biome) {
        for (String s : gen.getBiomes()) {
            if (biome.getBiomeName().contains(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDimension(Generation gen, int dimension) {
        for (Integer i : gen.getDimensions()) {
            if (i == dimension) {
                return true;
            }
        }
        return false;
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
            BlockPos blockpos = pos.add(0, random.nextInt(maxHeight - minHeight) + minHeight, 0);
            generator.generate(worldIn, random, blockpos);
        }
    }
}

package com.acronym.ore.common.generators;

import com.acronym.ore.api.generation.Generation;
import com.acronym.ore.api.generation.GenerationRegistry;
import com.acronym.ore.api.generation.OreWorldGenerator;
import com.acronym.ore.common.reference.Reference;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import javax.script.ScriptException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Jared on 7/24/2016.
 */
public class OREWG implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        try {
            for (Generation gen : GenerationRegistry.getGenerations()) {

                for (Constructor<?> c : gen.getWorldGenerator().getConstructors()) {
                    System.out.println(">>>");
                    System.out.println(c.getDeclaringClass().getName());
                    for (Class<?> aClass : c.getParameterTypes()) {
                        System.out.println(aClass.getClass() + ":" + aClass.getName());
                    }
                    System.out.println("<<<");
                }

                switch (gen.getDimensionsRestriction()) {
                    case "none":
                        int chance = random.nextInt(99);
                        if (chance != 0)
                            if (chance + 1 < (int) Reference.ENGINE_JAVASCRIPT.eval(gen.getChunkChance())) {
                                BlockPos bp = new BlockPos(chunkX * 16, 0, chunkZ * 16).add(random.nextInt(16), 0, random.nextInt(16));
                                switch (gen.getBiomeRestriction()) {
                                    case "none":
                                        try {
                                            List<BlockMatcher> matcher = new ArrayList<BlockMatcher>();
                                            gen.getReplaceable().forEach(bl -> matcher.add(BlockMatcher.forBlock(bl)));
                                            gen(world, random, bp, new Double(""+ Reference.ENGINE_JAVASCRIPT.eval(gen.getGenTries())).intValue(), gen.getWorldGenerator().getConstructor(Map.class, int.class, List.class, Map.class).newInstance(gen.getBlocks(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getBlockCount())).intValue(), matcher, gen.getParams()), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMinHeight())).intValue(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMaxHeight())).intValue());
                                        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case "whitelist":
                                        if (checkbiome(gen, world.getBiomeGenForCoords(bp)))
                                            try {
                                                List<BlockMatcher> matcher = new ArrayList<>();
                                                gen.getReplaceable().forEach(bl -> matcher.add(BlockMatcher.forBlock(bl)));
                                                gen(world, random, bp, new Double(""+ Reference.ENGINE_JAVASCRIPT.eval(gen.getGenTries())).intValue(), gen.getWorldGenerator().getConstructor(Map.class, int.class, List.class, Map.class).newInstance(gen.getBlocks(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getBlockCount())).intValue(), matcher, gen.getParams()), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMinHeight())).intValue(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMaxHeight())).intValue());
                                            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                                                e.printStackTrace();
                                            }
                                        break;
                                    case "blacklist":
                                        if (!checkbiome(gen, world.getBiomeGenForCoords(bp)))
                                            try {
                                                List<BlockMatcher> matcher = new ArrayList<>();
                                                gen.getReplaceable().forEach(bl -> matcher.add(BlockMatcher.forBlock(bl)));
                                                gen(world, random, bp, new Double(""+ Reference.ENGINE_JAVASCRIPT.eval(gen.getGenTries())).intValue(), gen.getWorldGenerator().getConstructor(Map.class, int.class, List.class, Map.class).newInstance(gen.getBlocks(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getBlockCount())).intValue(), matcher, gen.getParams()), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMinHeight())).intValue(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMaxHeight())).intValue());
                                            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                                                e.printStackTrace();
                                            }
                                        break;
                                }
                            }
                        break;
                    case "whitelist":
                        if (checkDimension(gen, world.provider.getDimension())) {
                            int whiteListChance = random.nextInt(99);
                            if (whiteListChance != 0)
                                if (whiteListChance + 1 < (int) Reference.ENGINE_JAVASCRIPT.eval(gen.getChunkChance())) {
                                    BlockPos bp = new BlockPos(chunkX * 16, 0, chunkZ * 16).add(random.nextInt(16), 0, random.nextInt(16));
                                    switch (gen.getBiomeRestriction()) {
                                        case "none":
                                            try {
                                                List<BlockMatcher> matcher = new ArrayList<>();
                                                gen.getReplaceable().forEach(bl -> matcher.add(BlockMatcher.forBlock(bl)));
                                                gen(world, random, bp, new Double(""+ Reference.ENGINE_JAVASCRIPT.eval(gen.getGenTries())).intValue(), gen.getWorldGenerator().getConstructor(Map.class, int.class, List.class, Map.class).newInstance(gen.getBlocks(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getBlockCount())).intValue(), matcher, gen.getParams()), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMinHeight())).intValue(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMaxHeight())).intValue());
                                            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case "whitelist":
                                            if (checkbiome(gen, world.getBiomeGenForCoords(bp)))
                                                try {
                                                    List<BlockMatcher> matcher = new ArrayList<>();
                                                    gen.getReplaceable().forEach(bl -> matcher.add(BlockMatcher.forBlock(bl)));
                                                    gen(world, random, bp, new Double(""+ Reference.ENGINE_JAVASCRIPT.eval(gen.getGenTries())).intValue(), gen.getWorldGenerator().getConstructor(Map.class, int.class, List.class, Map.class).newInstance(gen.getBlocks(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getBlockCount())).intValue(), matcher, gen.getParams()), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMinHeight())).intValue(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMaxHeight())).intValue());
                                                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                                                    e.printStackTrace();
                                                }
                                            break;
                                        case "blacklist":
                                            if (!checkbiome(gen, world.getBiomeGenForCoords(bp)))
                                                try {
                                                    List<BlockMatcher> matcher = new ArrayList<>();
                                                    gen.getReplaceable().forEach(bl -> matcher.add(BlockMatcher.forBlock(bl)));
                                                    gen(world, random, bp, new Double(""+ Reference.ENGINE_JAVASCRIPT.eval(gen.getGenTries())).intValue(), gen.getWorldGenerator().getConstructor(Map.class, int.class, List.class, Map.class).newInstance(gen.getBlocks(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getBlockCount())).intValue(), matcher, gen.getParams()), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMinHeight())).intValue(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMaxHeight())).intValue());
                                                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                                                    e.printStackTrace();
                                                }
                                            break;
                                    }
                                }
                        }
                        break;
                    case "blacklist":
                        if (!checkDimension(gen, world.provider.getDimension())) {
                            int blackListChance = random.nextInt(99);
                            if (blackListChance != 0)
                                if (blackListChance + 1 < (int) Reference.ENGINE_JAVASCRIPT.eval(gen.getChunkChance())) {
                                    BlockPos bp = new BlockPos(chunkX * 16, 0, chunkZ * 16).add(random.nextInt(16), 0, random.nextInt(16));
                                    switch (gen.getBiomeRestriction()) {
                                        case "none":
                                            try {
                                                List<BlockMatcher> matcher = new ArrayList<>();
                                                gen.getReplaceable().forEach(bl -> matcher.add(BlockMatcher.forBlock(bl)));
                                                gen(world, random, bp, new Double(""+ Reference.ENGINE_JAVASCRIPT.eval(gen.getGenTries())).intValue(), gen.getWorldGenerator().getConstructor(Map.class, int.class, List.class, Map.class).newInstance(gen.getBlocks(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getBlockCount())).intValue(), matcher, gen.getParams()), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMinHeight())).intValue(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMaxHeight())).intValue());
                                            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case "whitelist":
                                            if (checkbiome(gen, world.getBiomeGenForCoords(bp)))
                                                try {
                                                    List<BlockMatcher> matcher = new ArrayList<>();
                                                    gen.getReplaceable().forEach(bl -> matcher.add(BlockMatcher.forBlock(bl)));
                                                    gen(world, random, bp, new Double(""+ Reference.ENGINE_JAVASCRIPT.eval(gen.getGenTries())).intValue(), gen.getWorldGenerator().getConstructor(Map.class, int.class, List.class, Map.class).newInstance(gen.getBlocks(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getBlockCount())).intValue(), matcher, gen.getParams()), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMinHeight())).intValue(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMaxHeight())).intValue());
                                                } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                                                    e.printStackTrace();
                                                }
                                            break;
                                        case "blacklist":
                                            if (!checkbiome(gen, world.getBiomeGenForCoords(bp)))
                                                try {
                                                    List<BlockMatcher> matcher = new ArrayList<>();
                                                    gen.getReplaceable().forEach(bl -> matcher.add(BlockMatcher.forBlock(bl)));
                                                    gen(world, random, bp, new Double(""+ Reference.ENGINE_JAVASCRIPT.eval(gen.getGenTries())).intValue(), gen.getWorldGenerator().getConstructor(Map.class, int.class, List.class, Map.class).newInstance(gen.getBlocks(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getBlockCount())).intValue(), matcher, gen.getParams()), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMinHeight())).intValue(), new Double(""+Reference.ENGINE_JAVASCRIPT.eval(gen.getMaxHeight())).intValue());
                                                } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                                                    e.printStackTrace();
                                                }
                                            break;
                                    }
                                }
                        }
                        break;
                }
            }
        } catch (ScriptException e) {
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

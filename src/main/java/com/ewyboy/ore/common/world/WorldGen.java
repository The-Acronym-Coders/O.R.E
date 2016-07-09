package com.ewyboy.ore.common.world;

import com.ewyboy.ore.common.config.ConfigWorldGen;
import com.ewyboy.ore.common.utility.Logger;
import com.ewyboy.ore.common.utility.Reference;
import com.ewyboy.ore.common.utility.helpers.WorldInfoHelper;
import com.google.common.collect.ArrayListMultimap;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldGen implements IWorldGenerator {
    private static List<OreGen> oreSpawnList = new ArrayList<>();
    private static ArrayListMultimap<Integer, ChunkInfo> retrogenChunks = ArrayListMultimap.create();
    private int numChunks = 2;
    private boolean retrogenEnable = true;

    public static OreGen addOreGen(String name, IBlockState block, ConfigWorldGen.OreConfig oreConfig) {
        OreGen oreGen = new OreGen(name, block, Blocks.STONE, oreConfig);
        oreSpawnList.add(oreGen);
        return oreGen;
    }

    public void generateOres(Random random, int chunkX, int chunkZ, World world) {
        for (OreGen gen : oreSpawnList) {
            gen.generate(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void reGenerateOres(Random random, ChunkInfo chunkInfo, World world) {
        for (OreGen gen : oreSpawnList) {
            if (!chunkInfo.getTagCompound().getBoolean(gen.name)) {
                gen.generate(world, random,
                        chunkInfo.getCoordIntPair().chunkXPos * 16,
                        chunkInfo.getCoordIntPair().chunkZPos * 16);
            }
        }
    }

    private boolean retrogenEnabled() {
        return retrogenEnable;
    }

    public void enableRetrogen(boolean enable) {
        retrogenEnable = enable;
    }

    private boolean retroGenRequired(NBTTagCompound modTag) {
        boolean thisChunk = false;
        for (OreGen ore : oreSpawnList) {
            thisChunk |= !modTag.hasKey(ore.name);
            thisChunk |= !modTag.getBoolean(ore.name);
        }
        return thisChunk;
    }

    private void saveGenInfo(NBTTagCompound tag) {
        for (OreGen ore : oreSpawnList) {
            tag.setBoolean(ore.name, ore.oreConfig.Enabled);
        }
    }

    @SubscribeEvent
    public void chunkSave(ChunkDataEvent.Save event) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        event.getData().setTag("ORE", nbtTagCompound);
        nbtTagCompound.setBoolean("DEFAULT", true);
        saveGenInfo(nbtTagCompound);
    }

    @SubscribeEvent
    public void chunkLoad(ChunkDataEvent.Load event) {
        int dimID = event.getWorld().provider.getDimension();
        if (!retrogenEnabled())
            return;

        NBTTagCompound tag = event.getData().getCompoundTag("ORE");
        if ((!tag.hasKey("DEFAULT")) || retroGenRequired(tag)) {
            Logger.info("Chunk " + event.getChunk().getChunkCoordIntPair() + " has been flagged for Ore RetroGen by " + Reference.ModInfo.MOD_NAME);
            retrogenChunks.put(dimID, new ChunkInfo(event.getChunk().getChunkCoordIntPair(), tag));
        }
    }

    @SubscribeEvent
    public void serverWorldTick(TickEvent.WorldTickEvent event) {
        if ((event.side == Side.CLIENT) || (event.phase == TickEvent.Phase.START))
            return;

        int dimID = event.world.provider.getDimension();
        int counter = 0;

        List<ChunkInfo> chunks = retrogenChunks.get(dimID);

        if ((chunks != null) && (!chunks.isEmpty())) {
            if (WorldInfoHelper.getTps() >= 20) {
                numChunks++;
            } else {
                numChunks = Math.max(2, numChunks - 1);
            }

            for (int i = 1; i <= numChunks; i++) {
                int index = chunks.size() - i;
                if (index < 0)
                    return;

                counter++;

                ChunkPos chunkCoordIntPair = chunks.get(index).getCoordIntPair();
                long worldSeed = event.world.getSeed();
                Random fmlRandom = new Random(worldSeed);
                long xSeed = fmlRandom.nextLong() >> 3;
                long zSeed = fmlRandom.nextLong() >> 3;
                fmlRandom.setSeed(xSeed * chunkCoordIntPair.chunkXPos + zSeed * chunkCoordIntPair.chunkZPos ^ worldSeed);
                reGenerateOres(fmlRandom, chunks.get(index), event.world);
                chunks.remove(index);
            }

            if (counter > 0) Logger.info("Retrogen was performed on " + counter + " Chunks, " + Math.max(0, chunks.size()) + " chunks remaining");
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        generateOres(random, chunkX, chunkZ, world);
    }

    public static class OreGen {
        String name;
        WorldGenMinable worldGenMinable;
        ConfigWorldGen.OreConfig oreConfig;

        public OreGen(String name, IBlockState block, Block replaceTarget, ConfigWorldGen.OreConfig oreConfig) {
            this.name = name;
            this.worldGenMinable = new WorldGenMinable(block, oreConfig.VeinSize, BlockMatcher.forBlock(replaceTarget));
            this.oreConfig = oreConfig;
        }

        public void generate(World world, Random random, int x, int z) {
            if (oreConfig.Enabled && oreConfig.isEnabledForDim(world.provider.getDimension())) {
                for (int i = 0; i < oreConfig.ChunkOccurrence; i++) {
                    if (random.nextInt(100) < oreConfig.Weight) {
                        BlockPos blockPos = new BlockPos(x + random.nextInt(16), oreConfig.MinY + random.nextInt(oreConfig.MaxY - oreConfig.MinY), z + random.nextInt(16));
                        this.worldGenMinable.generate(world, random, blockPos);
                    }
                }
            }
        }
    }

    private static class ChunkInfo {
        private ChunkPos coordIntPair;
        private NBTTagCompound tagCompound;

        public ChunkInfo(ChunkPos coordIntPair, NBTTagCompound tagCompound) {
            this.coordIntPair = coordIntPair;
            this.tagCompound = tagCompound;
        }

        public ChunkPos getCoordIntPair() {
            return coordIntPair;
        }

        public NBTTagCompound getTagCompound() {
            return tagCompound;
        }
    }
}

package com.acronym.ore.common.generators.retrogen;

import com.acronym.ore.common.helpers.ChunkHelper;
import com.google.common.collect.Maps;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

import java.util.Map;

public class RetroGenSaveData extends WorldSavedData {

    private Map<ChunkHelper, NBTTagCompound> chunks = Maps.newHashMap();

    private final String NBT_SIZE = "size";
    private final String NBT_X = "CoordX";
    private final String NBT_Z = "CoordZ";
    private final String NBT_TAG = "tag";

    public RetroGenSaveData(String name) {
        super(name);
    }

    public boolean isGenerationNeeded(ChunkHelper coord, String genID) {
        NBTTagCompound nbt = chunks.get(coord);
        return nbt == null || !nbt.hasKey(genID) || !nbt.getBoolean(genID);
    }

    public void markChunkRetroGenerated(ChunkHelper coord, String genID) {
        NBTTagCompound nbt = chunks.get(coord);

        if (nbt == null) nbt = new NBTTagCompound();

        nbt.setBoolean(genID, true);
        chunks.put(coord, nbt);

        markDirty();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        int size = nbt.getInteger(NBT_SIZE);

        for (int i = 0; i < size; ++i) {
            ChunkHelper coord = new ChunkHelper(nbt.getInteger(i + NBT_X), nbt.getInteger(i + NBT_Z));
            chunks.put(coord, nbt.getCompoundTag(i + NBT_TAG));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger(NBT_SIZE, chunks.size());
        int index = 0;

        for (ChunkHelper coord : chunks.keySet()) {
            nbt.setInteger(index + NBT_X, coord.chunkX);
            nbt.setInteger(index + NBT_Z, coord.chunkZ);
            nbt.setTag(index + NBT_TAG, chunks.get(coord));
            index++;
        }

        return nbt;
    }
}

package com.acronym.ore.common.helpers;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

/**
 * Created by EwyBoy
 **/
public class ChunkHelper {

    public int chunkX;
    public int chunkZ;

    public ChunkHelper(Chunk chunk) {
        this.chunkX = chunk.xPosition;
        this.chunkZ = chunk.zPosition;
    }

    public ChunkHelper(int x, int z) {
        this.chunkX = x;
        this.chunkZ = z;
    }

    public ChunkHelper() {
        this(0, 0);
    }

    public ChunkHelper(ChunkHelper cCoord) {
        this.chunkX = cCoord.chunkX;
        this.chunkZ = cCoord.chunkZ;
    }

    public int getCenterX() {
        return (this.chunkX << 4) + 8;
    }

    public int getCenterZ() {
        return (this.chunkZ << 4) + 8;
    }

    public boolean isEqual(ChunkHelper cCoord) {
        return (this.chunkX == cCoord.chunkX) && (this.chunkZ == cCoord.chunkZ);
    }

    public static ChunkHelper getChunkCoordFromBlockCoords(int x, int z) {
        int chunkX = (int) Math.floor(x / 16);
        int chunkZ = (int) Math.floor(z / 16);

        return new ChunkHelper(chunkX, chunkZ);
    }

    public static ChunkHelper getChunkCoordFromBlockCoord(BlockPos pos) {
        return getChunkCoordFromBlockCoords(pos.getX(), pos.getZ());
    }

    @Override
    public Object clone() {
        return new ChunkHelper(this);
    }

    @Override
    public String toString() {
        return "ChunkCoord: [chunkX: " + this.chunkX + ", chunkZ: " + this.chunkZ + "]";
    }

    @Override
    public int hashCode() {
        return ("chunkX:" + this.chunkX + "chunkZ:" + this.chunkZ).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ChunkHelper) && isEqual((ChunkHelper) obj);
    }
}

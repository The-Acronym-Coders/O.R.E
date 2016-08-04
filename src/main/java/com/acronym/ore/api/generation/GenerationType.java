package com.acronym.ore.api.generation;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.WorldGenerator;

/**
 * Created by Jared on 8/4/2016.
 */
public class GenerationType {

    private String key;
    private String block;
    private WorldGenerator generator;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Block getBlock() {
        return Block.REGISTRY.getObject(new ResourceLocation(this.block));
    }

    public void setBlock(Block block) {
        this.block = Block.REGISTRY.getNameForObject(block).toString();
    }

    public WorldGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(WorldGenerator generator) {
        this.generator = generator;
    }

}

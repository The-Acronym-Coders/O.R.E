package com.acronym.ore.api.patterns;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Jared on 8/5/2016.
 */
public class BlocksMatcher implements Predicate<IBlockState> {
    private final List<Block> blocks;

    private BlocksMatcher(List<Block> blocks) {
        this.blocks = blocks;
    }

    public static BlocksMatcher forBlock(List<Block> blocks) {
        return new BlocksMatcher(blocks);
    }

    public boolean apply(@Nullable IBlockState state) {
        return state != null && this.blocks.contains(state.getBlock());
    }
}

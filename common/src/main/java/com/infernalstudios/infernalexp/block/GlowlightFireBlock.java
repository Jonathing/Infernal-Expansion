package com.infernalstudios.infernalexp.block;

import com.infernalstudios.infernalexp.module.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class GlowlightFireBlock extends BaseFireBlock {
    public GlowlightFireBlock(Properties properties, float damage) {
        super(properties, damage);
    }

    @Override
    protected boolean canBurn(BlockState state) {
        return true;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return canSurviveOnBlock(world.getBlockState(pos.below()));
    }

    public static boolean canSurviveOnBlock(BlockState state) {
        return state.is(ModTags.Blocks.GLOW_FIRE_BASE_BLOCKS);
    }
}

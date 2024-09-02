package com.infernalstudios.infernalexp.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HangingPlantBlock extends Block {
    public TagKey<Block> growableOn;
    public VoxelShape shape;


    public HangingPlantBlock(Properties properties, TagKey<Block> growableOn, VoxelShape shape) {
        super(properties);
        this.growableOn = growableOn;
        this.shape = shape;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.shape;
    }

    @Override
    public boolean canSurvive(BlockState floor, LevelReader world, BlockPos pos) {
        BlockPos blockPos = pos.above();
        BlockState ceiling = world.getBlockState(blockPos);
        return ceiling.is(this.growableOn);
    }
}

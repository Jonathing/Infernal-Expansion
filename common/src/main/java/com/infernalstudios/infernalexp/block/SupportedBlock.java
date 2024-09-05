package com.infernalstudios.infernalexp.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class SupportedBlock extends Block {
    public static final DirectionProperty FACING = DirectionProperty.create("facing");
    public static final Map<Direction, VoxelShape> SHAPES = Map.of(
            Direction.DOWN, Block.box(3, 0, 3, 13, 10, 13),
            Direction.UP, Block.box(3, 6, 3, 13, 16, 13),
            Direction.NORTH, Block.box(3, 3, 0, 13, 13, 10),
            Direction.SOUTH, Block.box(3, 3, 6, 13, 13, 16),
            Direction.EAST, Block.box(6, 3, 3, 16, 13, 13),
            Direction.WEST, Block.box(0, 3, 3, 10, 13, 13)
    );

    public SupportedBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.DOWN));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos blockPos = pos.relative(state.getValue(FACING));
        BlockState support = world.getBlockState(blockPos);
        return support.isFaceSturdy(world, blockPos, state.getValue(FACING));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState other, LevelAccessor world,
                                  BlockPos pos, BlockPos otherPos) {
        BlockState result = super.updateShape(state, direction, other, world, pos, otherPos);
        if (!result.is(this)) return result;

        return this.canSurvive(state, world, pos) ? result : Blocks.AIR.defaultBlockState();
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getClickedFace().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }
}

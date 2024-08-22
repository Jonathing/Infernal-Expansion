package com.infernalstudios.infernalexp.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LuminousFungusBlock extends BushBlock {
    public static final BooleanProperty LIT = BooleanProperty.create("is_lit");
    public static final VoxelShape BOX = Block.box(4, 0, 4, 12, 8, 12);

    public LuminousFungusBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public boolean canSurvive(BlockState floor, LevelReader world, BlockPos pos) {
        return floor.isFaceSturdy(world, pos, Direction.UP);
    }


}

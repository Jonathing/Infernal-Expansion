package com.infernalstudios.infernalexp.block.parent;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;

public class NetherPlantBlock extends BushBlock {
    public NetherPlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.isFaceSturdy(world, pos, Direction.UP);
    }
}

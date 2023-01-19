/*
 * Copyright 2022 Infernal Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.infernalstudios.infernalexp.blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundChatPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;

public class DullthornsBlockItem extends BlockItemBase {

    public DullthornsBlockItem(Block block) {
        super(block);
    }

    @Override
    @Nullable
    public BlockPlaceContext updatePlacementContext(BlockPlaceContext context) {
        if (context.getClickedFace() == Direction.UP) {
            return context;
        }

        Level world = context.getLevel();
        Direction placeDirection = context.isInside() ? context.getClickedFace() : context.getClickedFace().getOpposite();
        BlockPos placePos = context.getClickedPos().relative(placeDirection);
        int worldHeight = world.getMaxBuildHeight();
        Block dullthorns = this.getBlock();

        while (world.getBlockState(placePos).getBlock() == dullthorns) {
            placePos = placePos.above();

            // Prevent placing outside world
            if (!world.isClientSide && !world.isInWorldBounds(placePos)) {
                Player player = context.getPlayer();
                if (player instanceof ServerPlayer serverPlayer && placePos.getY() >= worldHeight) {
                    ClientboundChatPacket chatPacket = new ClientboundChatPacket((new TranslatableComponent("build.tooHigh", worldHeight)).withStyle(ChatFormatting.RED), ChatType.GAME_INFO, Util.NIL_UUID);
                    serverPlayer.connection.send(chatPacket);
                }
                return null;
            }
        }

        if (world.isEmptyBlock(placePos)) {
            return BlockPlaceContext.at(context, placePos, placeDirection);
        } else {
            return null;
        }

    }
}

package com.infernalstudios.infernalexp.mixin;

import com.infernalstudios.infernalexp.block.ShroomlightTearBlock;
import com.infernalstudios.infernalexp.module.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoneMealItem.class)
public class BoneMealItemMixin {
    @Inject(method = "growCrop", at = @At("HEAD"), cancellable = true)
    private static void growShroomlight(ItemStack stack, Level world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos).is(Blocks.SHROOMLIGHT) &&
                world.getBlockState(world.getBiome(pos).is(Biomes.WARPED_FOREST) ? pos.above() : pos.below()).isAir()) {
            stack.shrink(1);
            if (world.random.nextBoolean() && !world.isClientSide()) {
                BlockState tear = ModBlocks.SHROOMLIGHT_TEAR.get().defaultBlockState();

                if (world.getBiome(pos).is(Biomes.WARPED_FOREST))
                    world.setBlock(pos.above(), tear.setValue(ShroomlightTearBlock.UP, true), Block.UPDATE_ALL);
                else
                    world.setBlock(pos.below(), tear, Block.UPDATE_ALL);
            }
            cir.setReturnValue(true);
        }
    }
}

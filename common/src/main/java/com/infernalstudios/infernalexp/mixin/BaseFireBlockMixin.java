package com.infernalstudios.infernalexp.mixin;

import com.infernalstudios.infernalexp.block.GlowlightFireBlock;
import com.infernalstudios.infernalexp.module.BlockModule;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BaseFireBlock.class)
public class BaseFireBlockMixin {
    @Inject(method = "getState", at = @At("HEAD"), cancellable = true)
    private static void addGlowFire(BlockGetter world, BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
        if (GlowlightFireBlock.canSurviveOnBlock(world.getBlockState(pos.below())))
            cir.setReturnValue(BlockModule.GLOWLIGHT_FIRE.get().defaultBlockState());
    }
}

package com.infernalstudios.infernalexp.mixin;

import com.infernalstudios.infernalexp.module.BlockModule;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    public void registerCampfires(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (((BlockEntityType) (Object) this) == BlockEntityType.CAMPFIRE && state.is(BlockModule.GLOWLIGHT_CAMPFIRE.get()))
            cir.setReturnValue(true);
    }
}

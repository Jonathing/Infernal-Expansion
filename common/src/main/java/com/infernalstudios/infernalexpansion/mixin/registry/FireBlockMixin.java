package com.infernalstudios.infernalexpansion.mixin.registry;

import com.infernalstudios.infernalexpansion.registration.FlammabilityRegistry;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FireBlock.class)
public class FireBlockMixin {

    @Inject(method = "getIgniteOdds(Lnet/minecraft/world/level/block/state/BlockState;)I", at = @At("RETURN"), cancellable = true)
    private void infernalexpansion$getRegistryIgniteChance(BlockState state, CallbackInfoReturnable<Integer> cir) {
        if (cir.getReturnValue() > 0 ||
                (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED))) {
            return;
        }

        FlammabilityRegistry.Entry entry = FlammabilityRegistry.getRegistry((FireBlock) (Object) this).get(state.getBlock());
        if (entry != null) {
            cir.setReturnValue(entry.igniteChance());
        }
    }

    @Inject(method = "getBurnOdds", at = @At("RETURN"), cancellable = true)
    private void infernalexpansion$getRegistrySpreadChance(BlockState state, CallbackInfoReturnable<Integer> cir) {
        if (cir.getReturnValue() > 0 ||
                (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED))) {
            return;
        }

        FlammabilityRegistry.Entry entry = FlammabilityRegistry.getRegistry((FireBlock) (Object) this).get(state.getBlock());
        if (entry != null) {
            cir.setReturnValue(entry.spreadChance());
        }
    }
}

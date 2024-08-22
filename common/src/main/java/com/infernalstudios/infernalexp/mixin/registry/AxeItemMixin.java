package com.infernalstudios.infernalexp.mixin.registry;

import com.infernalstudios.infernalexp.registration.StrippableRegistry;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(AxeItem.class)
public class AxeItemMixin {

    @Inject(method = "getStripped", at = @At("RETURN"), cancellable = true)
    private void infernalexp$getRegisteredStrippables(BlockState state, CallbackInfoReturnable<Optional<BlockState>> cir) {
        if (cir.getReturnValue().isPresent()) return;

        cir.setReturnValue(Optional.ofNullable(StrippableRegistry.get(state.getBlock()))
                .map(block -> block.defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS))));
    }

}

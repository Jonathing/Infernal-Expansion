package com.infernalstudios.infernalexp.mixin.registry;

import com.infernalstudios.infernalexp.registration.FuelRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {

    @Inject(method = "isFuel", at = @At("RETURN"), cancellable = true)
    private static void infernalexp$checkFuelRegistry(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) return;

        cir.setReturnValue(FuelRegistry.getCookTime(stack.getItem()) > 0);
    }

    @Inject(method = "getBurnDuration", at = @At("RETURN"), cancellable = true)
    private void infernalexp$getRegisteredBurnDuration(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (cir.getReturnValue() > 0) return;

        cir.setReturnValue(FuelRegistry.getCookTime(stack.getItem()));
    }
}

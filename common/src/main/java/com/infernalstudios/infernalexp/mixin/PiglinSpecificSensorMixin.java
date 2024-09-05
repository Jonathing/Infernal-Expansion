package com.infernalstudios.infernalexp.mixin;

import com.infernalstudios.infernalexp.module.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.PiglinSpecificSensor;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiglinSpecificSensor.class)
public class PiglinSpecificSensorMixin {
    @Inject(method = "doTick", at = @At("TAIL"))
    public void avoidWarped(ServerLevel world, LivingEntity piglin, CallbackInfo ci) {
        Player player = world.getNearestPlayer(piglin.getX(), piglin.getY(), piglin.getZ(), 10,
                p -> p instanceof LivingEntity l && l.hasEffect(ModEffects.WARPED.get()));
        if (player != null)
            piglin.getBrain().setMemory(MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED, player);
    }
}

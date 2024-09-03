package com.infernalstudios.infernalexp.module;

import com.infernalstudios.infernalexp.registration.FuelRegistry;
import com.infernalstudios.infernalexp.registration.holders.ItemDataHolder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import java.util.Map;
import java.util.function.Supplier;

public class EffectModuleFabric {
    public static void registerEffects() {
        for (Map.Entry<ResourceLocation, Supplier<MobEffect>> entry : ModEffects.getEffectRegistry().entrySet()) {
            // Register effect
            Registry.register(BuiltInRegistries.MOB_EFFECT, entry.getKey(), entry.getValue().get());
        }
    }
}

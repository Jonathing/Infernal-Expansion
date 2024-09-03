package com.infernalstudios.infernalexp.module;

import com.infernalstudios.infernalexp.IECommon;
import com.infernalstudios.infernalexp.registration.holders.ItemDataHolder;
import net.minecraft.core.Direction;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModEffects {
    /** Map of all Effect Resource Locations to their Suppliers. */
    private static final Map<ResourceLocation, Supplier<MobEffect>> EFFECT_REGISTRY = new HashMap<>();

    public static Supplier<MobEffect> register(String name, Supplier<MobEffect> effect) {
        ResourceLocation id = IECommon.id(name);
        EFFECT_REGISTRY.put(id, effect);
        return effect;
    }

    public static Map<ResourceLocation, Supplier<MobEffect>> getEffectRegistry() {
        return EFFECT_REGISTRY;
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void load() {}


    //public static final Supplier<MobEffect> WARPED = register("warped",
    //        () -> new MobEffect());
}

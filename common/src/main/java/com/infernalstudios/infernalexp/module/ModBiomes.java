package com.infernalstudios.infernalexp.module;

import com.infernalstudios.infernalexp.IECommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

import java.util.HashMap;
import java.util.Map;

public class ModBiomes {
    /** Map of all Biome Resource Keys to their Parameter Point. */
    private static final Map<ResourceKey<Biome>, Climate.ParameterPoint> BIOME_REGISTRY = new HashMap<>();

    public static ResourceKey<Biome> register(String name, Climate.ParameterPoint parameterPoint) {
        return register(ResourceKey.create(Registries.BIOME, IECommon.id(name)), parameterPoint);
    }

    public static ResourceKey<Biome> register(ResourceKey<Biome> id, Climate.ParameterPoint parameterPoint) {
        BIOME_REGISTRY.put(id, parameterPoint);
        return id;
    }

    public static Map<ResourceKey<Biome>, Climate.ParameterPoint> getBiomeRegistry() {
        return BIOME_REGISTRY;
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void load() {}


    public static final ResourceKey<Biome> GLOWSTONE_CANYON = register("glowstone_canyon",
            // temp, humidity, continentalness, erosion, depth, weirdness and offset
            Climate.parameters(0, 0, 0, 0, 0, 0, 0)
    );
}

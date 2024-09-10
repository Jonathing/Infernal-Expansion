package com.infernalstudios.infernalexp.module;

import com.infernalstudios.infernalexp.registration.FuelRegistry;
import com.infernalstudios.infernalexp.registration.holders.ItemDataHolder;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

import java.util.Map;

public class BiomeModuleFabric {
    public static void registerBiomes() {
        for (Map.Entry<ResourceKey<Biome>, Climate.ParameterPoint> entry : ModBiomes.getBiomeRegistry().entrySet()) {
            NetherBiomes.addNetherBiome(entry.getKey(), entry.getValue());
        }
    }
}

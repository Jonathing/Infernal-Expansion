package org.infernalstudios.infernalexp.world.gen.region;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.init.IEBiomes;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.Regions;

import java.util.function.Consumer;

public class TerrablenderBiomeCompat extends Region {
    public static void register() {
        Regions.register(new TerrablenderBiomeCompat(5));
    }

    public TerrablenderBiomeCompat(int weight) {
        super(new ResourceLocation(InfernalExpansion.MOD_ID, "biome_provider"), RegionType.NETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        IEBiomes.getBiomeParameters().forEach(p -> mapper.accept(p.swap()));
    }
}

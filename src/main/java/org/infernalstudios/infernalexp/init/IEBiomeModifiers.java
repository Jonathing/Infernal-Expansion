/*
 * Copyright 2021 Infernal Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.infernalstudios.infernalexp.init;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.world.biome.modifiers.AddConfigurableSpawnsBiomeModifier;
import org.infernalstudios.infernalexp.world.biome.modifiers.AddFeaturesBiomeModifier;

import java.util.function.Supplier;

public class IEBiomeModifiers {

    private static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.BIOME_MODIFIER_SERIALIZERS, InfernalExpansion.MOD_ID);

    public static final Supplier<MapCodec<AddFeaturesBiomeModifier>> ADD_FEATURES = BIOME_MODIFIERS.register("add_features", () -> AddFeaturesBiomeModifier.CODEC);
    public static final Supplier<MapCodec<AddConfigurableSpawnsBiomeModifier>> ADD_CONFIGURABLE_SPAWNS = BIOME_MODIFIERS.register("add_configurable_spawns", () -> AddConfigurableSpawnsBiomeModifier.CODEC);

    public static void register(IEventBus eventBus) {
        BIOME_MODIFIERS.register(eventBus);
        InfernalExpansion.LOGGER.info("Infernal Expansion: Biome Modifiers Registered!");
    }

}

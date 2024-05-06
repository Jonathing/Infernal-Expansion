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
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.SurfaceRules.ConditionSource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.world.gen.surfacerules.ChanceConditionSource;
import org.infernalstudios.infernalexp.world.gen.surfacerules.TerrablenderSurfaceRuleCompat;

public class IESurfaceRules {
    private static final DeferredRegister<MapCodec<? extends ConditionSource>> SURFACE_RULES = DeferredRegister.create(BuiltInRegistries.MATERIAL_CONDITION, InfernalExpansion.MOD_ID);

    public static final MapCodec<? extends ConditionSource> CHANCE = SURFACE_RULES.register("chance", () -> ChanceConditionSource.CODEC);

    public static void register(IEventBus modEventBus) {
        InfernalExpansion.LOGGER.info("Infernal Expansion: Registering Surface Rules");
        SURFACE_RULES.register(modEventBus);
        // TODO: maybe will need to redo terrablender compat?
        TerrablenderSurfaceRuleCompat.addSurfaceRules();
    }

    public static SurfaceRules.ConditionSource chance(String name, float percentageChance) {
        return new ChanceConditionSource(name, percentageChance);
    }

}

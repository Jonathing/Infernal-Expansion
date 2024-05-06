/*
 * Copyright 2022 Infernal Studios
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

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.infernalstudios.infernalexp.InfernalExpansion;

import java.util.function.Supplier;

public class IEPaintings {

    public static DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(BuiltInRegistries.PAINTING_VARIANT, InfernalExpansion.MOD_ID);

    public static Supplier<PaintingVariant> THE_FALLEN_ONES = PAINTING_VARIANTS.register("the_fallen_ones", () -> new PaintingVariant(48, 64));
    public static Supplier<PaintingVariant> CHILLING_ISLES = PAINTING_VARIANTS.register("chilling_isles", () -> new PaintingVariant(48, 48));
    public static Supplier<PaintingVariant> VOLINE = PAINTING_VARIANTS.register("voline", () -> new PaintingVariant(16, 16));
    public static Supplier<PaintingVariant> DA_SALT = PAINTING_VARIANTS.register("da_salt", () -> new PaintingVariant(64, 32));
    public static Supplier<PaintingVariant> GLOWSTONE_CANYON_PAINTING = PAINTING_VARIANTS.register("glowstone_canyon_painting", () -> new PaintingVariant(64, 64));
    public static Supplier<PaintingVariant> SOUL_BY_SOUL = PAINTING_VARIANTS.register("soul_by_soul", () -> new PaintingVariant(80, 48));
    public static Supplier<PaintingVariant> GARRY = PAINTING_VARIANTS.register("pineapple_under_the_lava_sea", () -> new PaintingVariant(64, 64));
    public static Supplier<PaintingVariant> SHROOM_DUALITY = PAINTING_VARIANTS.register("shroom_duality", () -> new PaintingVariant(16, 16));
    public static Supplier<PaintingVariant> PIGS_GREED = PAINTING_VARIANTS.register("pigs_greed", () -> new PaintingVariant(16, 16));
    public static Supplier<PaintingVariant> SOUL_HOLE = PAINTING_VARIANTS.register("soul_hole", () -> new PaintingVariant(16, 16));
    public static Supplier<PaintingVariant> GLOW_LIKE_HELLY = PAINTING_VARIANTS.register("glow_like_helly", () -> new PaintingVariant(16, 16));


    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
        InfernalExpansion.LOGGER.info("Infernal Expansion: Painting Types Registered!");
    }

}

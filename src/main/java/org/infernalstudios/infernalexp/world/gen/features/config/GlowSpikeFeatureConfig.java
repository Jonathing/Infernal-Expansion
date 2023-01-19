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

package org.infernalstudios.infernalexp.world.gen.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record GlowSpikeFeatureConfig(int minDiameter, int maxDiameter, int minHeight, int maxHeight, int maxXOffset, int maxZOffset, float blockDitheringAmount, boolean darkAtTop) implements FeatureConfiguration {

    public static final Codec<GlowSpikeFeatureConfig> CODEC = RecordCodecBuilder.create((builder) -> builder.group(
            Codec.INT.fieldOf("min_diameter").forGetter((config) -> config.minDiameter),
            Codec.INT.fieldOf("max_diameter").forGetter((config) -> config.maxDiameter),
            Codec.INT.fieldOf("min_height").forGetter((config) -> config.minHeight),
            Codec.INT.fieldOf("max_height").forGetter((config) -> config.maxHeight),
            Codec.INT.fieldOf("max_x_offset").forGetter((config) -> config.maxXOffset),
            Codec.INT.fieldOf("max_z_offset").forGetter((config) -> config.maxZOffset),
            Codec.FLOAT.fieldOf("block_dithering_amount").forGetter((config) -> config.blockDitheringAmount),
            Codec.BOOL.fieldOf("dark_at_top").forGetter((config) -> config.darkAtTop))
        .apply(builder, GlowSpikeFeatureConfig::new));

}

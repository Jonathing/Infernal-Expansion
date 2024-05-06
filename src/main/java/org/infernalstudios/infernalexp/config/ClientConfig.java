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

package org.infernalstudios.infernalexp.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.infernalstudios.infernalexp.InfernalExpansion;

public class ClientConfig {

    final ModConfigSpec.IntValue luminousRefreshDelay;

    ClientConfig(final ModConfigSpec.Builder builder) {
        builder.push("general");

        // Luminous Effect
        luminousRefreshDelay = builder
            .comment("Determines how often (in ticks) the luminous effect should update")
            .translation(InfernalExpansion.MOD_ID + ".config.tooltip.luminousRefreshDelay")
            .defineInRange("luminousRefreshDelay", 2, 1, Integer.MAX_VALUE);

        builder.pop();
    }
}

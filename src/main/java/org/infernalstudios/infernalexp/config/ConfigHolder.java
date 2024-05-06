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
import org.apache.commons.lang3.tuple.Pair;

//Don't touch this Class
public class ConfigHolder {

    public static final ModConfigSpec CLIENT_SPEC;
    public static final ModConfigSpec COMMON_SPEC;
    static final ClientConfig CLIENT;
    static final CommonConfig COMMON;

    static {
        {
            final Pair<ClientConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(ClientConfig::new);
            CLIENT = specPair.getLeft();
            CLIENT_SPEC = specPair.getRight();
        }
        {
            final Pair<CommonConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(CommonConfig::new);
            COMMON = specPair.getLeft();
            COMMON_SPEC = specPair.getRight();
        }
    }
}

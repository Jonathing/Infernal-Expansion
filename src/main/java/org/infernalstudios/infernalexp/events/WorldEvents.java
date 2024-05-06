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

package org.infernalstudios.infernalexp.events;

import net.neoforged.eventbus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.registries.ForgeRegistries;
import net.neoforged.registries.RegisterEvent;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.init.IECarvers;
import org.infernalstudios.infernalexp.init.IEFeatures;

@EventBusSubscriber(modid = InfernalExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WorldEvents {

    @SubscribeEvent
    public static void registerWorldGen(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.FEATURES, helper -> IEFeatures.features.forEach(helper::register));
        event.register(ForgeRegistries.Keys.WORLD_CARVERS, helper -> IECarvers.carvers.forEach(helper::register));
    }

}

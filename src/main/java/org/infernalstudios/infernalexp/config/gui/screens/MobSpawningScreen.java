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

package org.infernalstudios.infernalexp.config.gui.screens;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.config.InfernalExpansionConfig;
import org.infernalstudios.infernalexp.config.gui.widgets.TextFieldOption;
import org.infernalstudios.infernalexp.config.gui.widgets.TitleOption;

@OnlyIn(Dist.CLIENT)
public class MobSpawningScreen extends IESettingsScreen {

    public MobSpawningScreen(Screen parentScreen) {
        super(parentScreen, Component.translatable(InfernalExpansion.MOD_ID + ".config.title.mob_spawning"));
    }

    @Override
    public void addSettings() {
        optionsRowList.addBig(TitleOption.create(InfernalExpansion.MOD_ID + ".config.subtitle.spawnable_biomes"));

        for (InfernalExpansionConfig.MobSpawning mobSpawn : InfernalExpansionConfig.MobSpawning.values()) {
            optionsRowList.addBig(TextFieldOption.create("entity." + InfernalExpansion.MOD_ID + "." + mobSpawn.getTranslationName(), InfernalExpansion.MOD_ID + ".config.tooltip." + mobSpawn.getTranslationName(), mobSpawn.getSpawnableBiomes(), mobSpawn::setSpawnableBiomes));
        }
    }
}

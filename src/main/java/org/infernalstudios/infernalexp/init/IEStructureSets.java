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

import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import org.infernalstudios.infernalexp.InfernalExpansion;

@SuppressWarnings("unused")
public class IEStructureSets {

    public static final Holder<StructureSet> GLOWSTONE_CANYON_RUINS = registerSet("glowstone_canyon_ruins", new StructureSet(IEConfiguredStructures.GLOWSTONE_CANYON_RUIN, new RandomSpreadStructurePlacement(8, 4, RandomSpreadType.LINEAR, 20394857)));
    public static final Holder<StructureSet> SOUL_SAND_VALLEY_RUINS = registerSet("soul_sand_valley_ruins", new StructureSet(IEConfiguredStructures.SOUL_SAND_VALLEY_RUIN, new RandomSpreadStructurePlacement(8, 4, RandomSpreadType.LINEAR, 29456392)));
    public static final Holder<StructureSet> BASTION_OUTPOSTS = registerSet("bastion_outposts", new StructureSet(IEConfiguredStructures.BASTION_OUTPOST, new RandomSpreadStructurePlacement(16, 8, RandomSpreadType.LINEAR, 72043594)));
    public static final Holder<StructureSet> BASALT_GIANT_MOAIS = registerSet("basalt_giant_moais", new StructureSet(IEConfiguredStructures.BASALT_GIANT_MOAI, new RandomSpreadStructurePlacement(3, 2, RandomSpreadType.LINEAR, 58327523)));
    public static final Holder<StructureSet> STRIDER_ALTARS = registerSet("strider_altars", new StructureSet(IEConfiguredStructures.STRIDER_ALTAR, new RandomSpreadStructurePlacement(8, 4, RandomSpreadType.LINEAR, 80837592)));

    private static Holder<StructureSet> registerSet(String name, StructureSet pool) {
        ResourceLocation resourceLocation = new ResourceLocation(InfernalExpansion.MOD_ID, name);

        if (BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.keySet().contains(resourceLocation))
            throw new IllegalStateException("Structure Pool ID: \"" + resourceLocation + "\" is already in the registry!");

        return BuiltinRegistries.register(BuiltinRegistries.STRUCTURE_SETS, resourceLocation, pool);
    }

    public static void register() {}

}

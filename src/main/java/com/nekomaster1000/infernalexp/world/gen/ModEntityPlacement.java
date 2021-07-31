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

package com.nekomaster1000.infernalexp.world.gen;

import com.nekomaster1000.infernalexp.entities.BasaltGiantEntity;
import com.nekomaster1000.infernalexp.entities.BlackstoneDwarfEntity;
import com.nekomaster1000.infernalexp.entities.BlindsightEntity;
import com.nekomaster1000.infernalexp.entities.EmbodyEntity;
import com.nekomaster1000.infernalexp.entities.GlowsilkMothEntity;
import com.nekomaster1000.infernalexp.entities.GlowsquitoEntity;
import com.nekomaster1000.infernalexp.entities.ShroomloinEntity;
import com.nekomaster1000.infernalexp.entities.VolineEntity;
import com.nekomaster1000.infernalexp.entities.WarpbeetleEntity;
import com.nekomaster1000.infernalexp.init.IEEntityTypes;

import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.world.gen.Heightmap;

public class ModEntityPlacement {

    public static void spawnPlacement() {
        EntitySpawnPlacementRegistry.register(IEEntityTypes.VOLINE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, VolineEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(IEEntityTypes.SHROOMLOIN.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ShroomloinEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(IEEntityTypes.WARPBEETLE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WarpbeetleEntity::canSpawnOn);
        //EntitySpawnPlacementRegistry.register(IEEntityTypes.CEROBEETLE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CerobeetleEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(IEEntityTypes.EMBODY.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EmbodyEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(IEEntityTypes.BASALT_GIANT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BasaltGiantEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(IEEntityTypes.BLACKSTONE_DWARF.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BlackstoneDwarfEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(IEEntityTypes.GLOWSQUITO.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GlowsquitoEntity::canSpawnOn);
        //EntitySpawnPlacementRegistry.register(IEEntityTypes.PYRNO.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PyrnoEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(IEEntityTypes.BLINDSIGHT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BlindsightEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(IEEntityTypes.GLOWSILK_MOTH.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GlowsilkMothEntity::canSpawnOn);
    }

}

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

package org.infernalstudios.infernalexp.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.infernalstudios.infernalexp.init.IETags;
import org.infernalstudios.infernalexp.mixin.common.WorldGenRegionAccessor;

public abstract class IEFeature<FC extends FeatureConfiguration> extends Feature<FC> {

    public IEFeature(Codec<FC> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<FC> context) {
        if (!shouldPlaceOnStructures())
            return placeFeature(context);

        if (!(context.level() instanceof WorldGenRegion))
            return false;

        Registry<Structure> registry = context.level().registryAccess().registryOrThrow(Registry.STRUCTURE_REGISTRY);
        StructureManager manager = ((WorldGenRegionAccessor) context.level()).getStructureManager();

        for (Holder<Structure> structure : registry.getOrCreateTag(IETags.Structures.NO_INTERSECTING_FEATURES)) {
            if (manager.getStructureAt(context.origin(), structure.value()).isValid())
                return false;
        }

        return placeFeature(context);
    }

    abstract boolean placeFeature(FeaturePlaceContext<FC> context);

    abstract boolean shouldPlaceOnStructures();

}

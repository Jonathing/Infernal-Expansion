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

package org.infernalstudios.infernalexp.mixin.client;

import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.Material;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.ModList;
import org.infernalstudios.infernalexp.api.FireType;
import org.infernalstudios.infernalexp.init.IEFireTypes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Set;

@OnlyIn(Dist.CLIENT)
@Mixin(ModelBakery.class)
public class MixinModelBakery {

    @Shadow
    @Final
    protected static Set<Material> UNREFERENCED_TEXTURES;

    static {
        FireType.getFireTypes().forEach(fireType -> {
            if (fireType != IEFireTypes.FIRE) {
                if (ModList.get().isLoaded(fireType.getName().getNamespace())) {
                    UNREFERENCED_TEXTURES.add(fireType.getSprite0());
                    UNREFERENCED_TEXTURES.add(fireType.getSprite1());
                }
            }
        });
    }

}

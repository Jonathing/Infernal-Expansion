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

package org.infernalstudios.infernalexp.client.entity.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.client.entity.model.GlowsquitoModel;
import org.infernalstudios.infernalexp.entities.GlowsquitoEntity;
import org.jetbrains.annotations.NotNull;

public class GlowsquitoRenderer extends MobRenderer<GlowsquitoEntity, GlowsquitoModel<GlowsquitoEntity>> {
    protected static final ResourceLocation UNBRED_TEXTURE = new ResourceLocation(InfernalExpansion.MOD_ID,
        "textures/entity/glowsquitoid.png");

    protected static final ResourceLocation BRED_TEXTURE = new ResourceLocation(InfernalExpansion.MOD_ID,
        "textures/entity/glowsquitoid_shroomlight.png");

    public GlowsquitoRenderer(EntityRendererProvider.Context context) {
        super(context, new GlowsquitoModel<>(context.bakeLayer(GlowsquitoModel.LAYER_LOCATION)), 0.7f);
        this.addLayer(new GlowsquitoGlowLayer<>(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(GlowsquitoEntity entity) {
        if (entity.getBred()) {
            return BRED_TEXTURE;
        } else {
            return UNBRED_TEXTURE;
        }
    }
}

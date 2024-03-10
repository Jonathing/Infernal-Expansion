package com.helliongames.hellionsmobs.client.model;

import com.helliongames.hellionsmobs.HellionsMobsConstants;
import com.helliongames.hellionsmobs.entity.KitsuneEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class KitsuneModel extends GeoModel<KitsuneEntity> {
    private static final ResourceLocation MODEL = new ResourceLocation(HellionsMobsConstants.MOD_ID, "geo/kitsune.geo.json");
    private static final ResourceLocation TEXTURE = new ResourceLocation(HellionsMobsConstants.MOD_ID, "textures/entity/kitsune.png");
    private static final ResourceLocation ANIMATION = new ResourceLocation(HellionsMobsConstants.MOD_ID, "animations/kitsune.animation.json");

    @Override
    public ResourceLocation getModelResource(KitsuneEntity animatable) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(KitsuneEntity animatable) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(KitsuneEntity animatable) {
        return ANIMATION;
    }
}

package com.helliongames.hellionsmobs.client.renderer;

import com.helliongames.hellionsmobs.client.model.KitsuneModel;
import com.helliongames.hellionsmobs.entity.KitsuneEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class KitsuneRenderer extends GeoEntityRenderer<KitsuneEntity> {
    public KitsuneRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new KitsuneModel());
    }
}

package com.helliongames.hellionsmobs.platform;

import com.helliongames.hellionsmobs.client.renderer.KitsuneRenderer;
import com.helliongames.hellionsmobs.registration.HellionsMobsEntities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class FabricClientHelper implements IClientHelper {
    @Override
    public void registerEntityRenderers() {
        EntityRendererRegistry.register(HellionsMobsEntities.KITSUNE.get(), KitsuneRenderer::new);
    }
}

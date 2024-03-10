package com.helliongames.hellionsmobs.client;

import com.helliongames.hellionsmobs.client.renderer.KitsuneRenderer;
import com.helliongames.hellionsmobs.modules.HellionsMobsEntityTypeModule;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class HellionsMobsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HellionsMobsCommonClient.init();

        EntityRendererRegistry.register(HellionsMobsEntityTypeModule.KITSUNE.get(), KitsuneRenderer::new);
    }
}

package com.helliongames.hellionsmobs.client;

import com.helliongames.hellionsmobs.module.HellionsMobsEntityRendererModule;
import com.helliongames.hellionsmobs.registration.EntityTypeDataHolder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import java.util.Map;

public class HellionsMobsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HellionsMobsCommonClient.init();
        registerEntityRenderers();
    }

    private void registerEntityRenderers() {
        for (Map.Entry<EntityTypeDataHolder, EntityRendererProvider> entry : HellionsMobsEntityRendererModule.getEntityRendererRegistry().entrySet()) {
            // Register entity renderers
            EntityRendererRegistry.register(entry.getKey().get(), entry.getValue());
        }
    }
}

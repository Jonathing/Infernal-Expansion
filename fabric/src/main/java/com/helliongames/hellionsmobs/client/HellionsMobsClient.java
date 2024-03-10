package com.helliongames.hellionsmobs.client;

import com.helliongames.hellionsmobs.module.HellionsMobsEntityTypeModule;
import com.helliongames.hellionsmobs.registration.EntityTypeDataHolder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class HellionsMobsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HellionsMobsCommonClient.init();
        registerEntityRenderers();
    }

    private void registerEntityRenderers() {
        for (Map.Entry<ResourceLocation, EntityTypeDataHolder> entry : HellionsMobsEntityTypeModule.getEntityTypeRegistry().entrySet()) {
            // Register entity renderers
            EntityRendererRegistry.register(entry.getValue().get(), entry.getValue().getRendererProvider());
        }
    }
}

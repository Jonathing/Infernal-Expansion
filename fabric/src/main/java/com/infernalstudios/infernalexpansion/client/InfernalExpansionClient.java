package com.infernalstudios.infernalexpansion.client;

import com.infernalstudios.infernalexpansion.module.EntityRendererModule;
import com.infernalstudios.infernalexpansion.registration.EntityTypeDataHolder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import java.util.Map;

public class InfernalExpansionClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        InfernalExpansionCommonClient.init();
        registerEntityRenderers();
    }

    private void registerEntityRenderers() {
        for (Map.Entry<EntityTypeDataHolder, EntityRendererProvider> entry : EntityRendererModule.getEntityRendererRegistry().entrySet()) {
            // Register entity renderers
            EntityRendererRegistry.register(entry.getKey().get(), entry.getValue());
        }
    }
}

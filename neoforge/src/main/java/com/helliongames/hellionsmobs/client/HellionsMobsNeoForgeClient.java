package com.helliongames.hellionsmobs.client;

import com.helliongames.hellionsmobs.module.HellionsMobsEntityRendererModule;
import com.helliongames.hellionsmobs.registration.EntityTypeDataHolder;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import java.util.Map;

public class HellionsMobsNeoForgeClient {
    public static void init(IEventBus modEventBus) {
        HellionsMobsCommonClient.init();

        modEventBus.addListener(HellionsMobsNeoForgeClient::clientSetup);
        modEventBus.addListener(HellionsMobsNeoForgeClient::registerEntityRenderers);
    }

    private static void clientSetup(final FMLClientSetupEvent event) {
    }

    private static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        for (Map.Entry<EntityTypeDataHolder, EntityRendererProvider> entry : HellionsMobsEntityRendererModule.getEntityRendererRegistry().entrySet()) {
            // Register entity renderers
            event.registerEntityRenderer(entry.getKey().get(), entry.getValue());
        }
    }
}

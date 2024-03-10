package com.helliongames.hellionsmobs.client;

import com.helliongames.hellionsmobs.client.renderer.KitsuneRenderer;
import com.helliongames.hellionsmobs.module.HellionsMobsEntityTypeModule;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class HellionsMobsNeoForgeClient {
    public static void init(IEventBus modEventBus) {
        modEventBus.addListener(HellionsMobsNeoForgeClient::clientSetup);
        modEventBus.addListener(HellionsMobsNeoForgeClient::registerRenderers);
    }

    private static void clientSetup(final FMLClientSetupEvent event) {
    }

    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(HellionsMobsEntityTypeModule.KITSUNE.get(), KitsuneRenderer::new);
    }
}

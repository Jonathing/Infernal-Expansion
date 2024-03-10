package com.helliongames.hellionsmobs.client;

import com.helliongames.hellionsmobs.client.renderer.KitsuneRenderer;
import com.helliongames.hellionsmobs.registration.HellionsMobsEntities;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class HellionsMobsForgeClient {
    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(HellionsMobsForgeClient::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(HellionsMobsForgeClient::registerRenderers);
    }

    private static void clientSetup(final FMLClientSetupEvent event) {
    }

    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(HellionsMobsEntities.KITSUNE.get(), KitsuneRenderer::new);
    }
}

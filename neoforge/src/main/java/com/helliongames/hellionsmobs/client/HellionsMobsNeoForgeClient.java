package com.helliongames.hellionsmobs.client;

import com.helliongames.hellionsmobs.module.HellionsMobsEntityTypeModule;
import com.helliongames.hellionsmobs.registration.EntityTypeDataHolder;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import java.util.Map;

public class HellionsMobsNeoForgeClient {
    public static void init(IEventBus modEventBus) {
        modEventBus.addListener(HellionsMobsNeoForgeClient::clientSetup);
        modEventBus.addListener(HellionsMobsNeoForgeClient::registerEntityRenderers);
    }

    private static void clientSetup(final FMLClientSetupEvent event) {
    }

    private static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        for (Map.Entry<ResourceLocation, EntityTypeDataHolder> entry : HellionsMobsEntityTypeModule.getEntityTypeRegistry().entrySet()) {
            // Register entity renderers
            event.registerEntityRenderer(entry.getValue().get(), entry.getValue().getRendererProvider());
        }
    }
}

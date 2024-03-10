package com.helliongames.hellionsmobs.client;

import com.helliongames.hellionsmobs.module.HellionsMobsEntityTypeModule;
import com.helliongames.hellionsmobs.registration.EntityTypeDataHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Map;

public class HellionsMobsForgeClient {
    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(HellionsMobsForgeClient::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(HellionsMobsForgeClient::registerEntityRenderers);
    }

    private static void clientSetup(final FMLClientSetupEvent event) {
    }

    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        for (Map.Entry<ResourceLocation, EntityTypeDataHolder> entry : HellionsMobsEntityTypeModule.getEntityTypeRegistry().entrySet()) {
            // Register entity renderers
            event.registerEntityRenderer(entry.getValue().get(), entry.getValue().getRendererProvider());
        }
    }
}

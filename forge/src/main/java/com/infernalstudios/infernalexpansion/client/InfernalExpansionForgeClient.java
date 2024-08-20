package com.infernalstudios.infernalexpansion.client;

import com.infernalstudios.infernalexpansion.module.EntityRendererModule;
import com.infernalstudios.infernalexpansion.registration.EntityTypeDataHolder;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Map;

public class InfernalExpansionForgeClient {
    public static void init() {
        InfernalExpansionCommonClient.init();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(InfernalExpansionForgeClient::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(InfernalExpansionForgeClient::registerEntityRenderers);
    }

    private static void clientSetup(final FMLClientSetupEvent event) {
    }

    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        for (Map.Entry<EntityTypeDataHolder, EntityRendererProvider> entry : EntityRendererModule.getEntityRendererRegistry().entrySet()) {
            // Register entity renderers
            event.registerEntityRenderer(entry.getKey().get(), entry.getValue());
        }
    }
}

package com.infernalstudios.infernalexp.client;

import com.infernalstudios.infernalexp.module.ModEntityRenderers;
import com.infernalstudios.infernalexp.registration.holders.BlockDataHolder;
import com.infernalstudios.infernalexp.registration.holders.EntityTypeDataHolder;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Map;

public class InfernalExpansionForgeClient {
    public static void init() {
        IECommonClient.init();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(InfernalExpansionForgeClient::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(InfernalExpansionForgeClient::registerEntityRenderers);
    }

    private static void clientSetup(final FMLClientSetupEvent event) {
        for (BlockDataHolder<?> block : BlockDataHolder.getCutoutBlocks()) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
        }
    }

    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        for (Map.Entry<EntityTypeDataHolder, EntityRendererProvider> entry : ModEntityRenderers.getEntityRendererRegistry().entrySet()) {
            // Register entity renderers
            event.registerEntityRenderer(entry.getKey().get(), entry.getValue());
        }
    }
}

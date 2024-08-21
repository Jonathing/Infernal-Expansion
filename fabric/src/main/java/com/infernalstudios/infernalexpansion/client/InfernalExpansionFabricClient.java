package com.infernalstudios.infernalexpansion.client;

import com.infernalstudios.infernalexpansion.module.EntityRendererModule;
import com.infernalstudios.infernalexpansion.registration.holders.BlockDataHolder;
import com.infernalstudios.infernalexpansion.registration.holders.EntityTypeDataHolder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import java.util.Map;

public class InfernalExpansionFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        InfernalExpansionCommonClient.init();
        registerEntityRenderers();
        registerBlockRenderTypes();
    }

    private void registerEntityRenderers() {
        for (Map.Entry<EntityTypeDataHolder, EntityRendererProvider> entry : EntityRendererModule.getEntityRendererRegistry().entrySet()) {
            // Register entity renderers
            EntityRendererRegistry.register(entry.getKey().get(), entry.getValue());
        }
    }

    private void registerBlockRenderTypes() {
        for (BlockDataHolder<?> block : BlockDataHolder.getCutoutBlocks()) {
            BlockRenderLayerMap.INSTANCE.putBlock(block.get(), RenderType.cutout());
        }
    }
}

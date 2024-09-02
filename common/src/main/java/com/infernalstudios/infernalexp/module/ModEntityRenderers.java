package com.infernalstudios.infernalexp.module;

import com.infernalstudios.infernalexp.registration.holders.EntityTypeDataHolder;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import java.util.HashMap;
import java.util.Map;

public class ModEntityRenderers {
    /** Map of all EntityTypes to their EntityRendererProviders. */
    private static final Map<EntityTypeDataHolder, EntityRendererProvider> ENTITY_RENDERER_REGISTRY = new HashMap<>();

    public static void register(EntityTypeDataHolder entityTypeDataHolder, EntityRendererProvider rendererProvider) {
        ENTITY_RENDERER_REGISTRY.put(entityTypeDataHolder, rendererProvider);
    }

    public static Map<EntityTypeDataHolder, EntityRendererProvider> getEntityRendererRegistry() {
        return ENTITY_RENDERER_REGISTRY;
    }

    // Called in the mod initializer / constructor in order to make sure that renderers are registered
    public static void load() {
//        register(InfernalExpansionEntityTypeModule.KITSUNE, KitsuneRenderer::new);

    }
}

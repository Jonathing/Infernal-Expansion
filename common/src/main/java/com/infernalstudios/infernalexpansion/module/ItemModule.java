package com.infernalstudios.infernalexpansion.module;

import com.infernalstudios.infernalexpansion.InfernalExpansionCommon;
import com.infernalstudios.infernalexpansion.registration.holders.ItemDataHolder;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ItemModule {
    /** Map of all Item Resource Locations to their ItemDataHolders. */
    private static final Map<ResourceLocation, ItemDataHolder<?>> ITEM_REGISTRY = new HashMap<>();



    public static ItemDataHolder<?> register(String name, ItemDataHolder<?> itemDataHolder) {
        ResourceLocation id = InfernalExpansionCommon.id(name);
        ITEM_REGISTRY.put(id, itemDataHolder);
        return itemDataHolder;
    }

    public static Map<ResourceLocation, ItemDataHolder<?>> getItemRegistry() {
        return ITEM_REGISTRY;
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}

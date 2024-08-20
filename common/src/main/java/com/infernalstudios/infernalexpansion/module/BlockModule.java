package com.infernalstudios.infernalexpansion.module;

import com.infernalstudios.infernalexpansion.InfernalExpansionCommon;
import com.infernalstudios.infernalexpansion.registration.BlockDataHolder;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class BlockModule {
    /** Map of all Block Resource Locations to their BlockDataHolders. */
    private static final Map<ResourceLocation, BlockDataHolder<?>> BLOCK_REGISTRY = new HashMap<>();



    public static BlockDataHolder<?> register(String name, BlockDataHolder<?> blockDataHolder) {
        ResourceLocation id = InfernalExpansionCommon.id(name);
        BLOCK_REGISTRY.put(id, blockDataHolder);
        return blockDataHolder;
    }

    public static Map<ResourceLocation, BlockDataHolder<?>> getBlockRegistry() {
        return BLOCK_REGISTRY;
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}

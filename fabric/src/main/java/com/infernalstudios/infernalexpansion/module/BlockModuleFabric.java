package com.infernalstudios.infernalexpansion.module;

import com.infernalstudios.infernalexpansion.registration.BlockDataHolder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class BlockModuleFabric {
    public static void registerBlocks() {
        for (Map.Entry<ResourceLocation, BlockDataHolder<?>> entry : BlockModule.getBlockRegistry().entrySet()) {
            // Register block
            Registry.register(BuiltInRegistries.BLOCK, entry.getKey(), entry.getValue().get());

            // Register the block items
            if (entry.getValue().hasItem()) {
                Registry.register(BuiltInRegistries.ITEM, entry.getKey(), entry.getValue().getBlockItem().get());
            }
        }
    }
}

package com.infernalstudios.infernalexpansion.module;

import com.infernalstudios.infernalexpansion.registration.ItemDataHolder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class ItemModuleFabric {
    public static void registerItems() {
        for (Map.Entry<ResourceLocation, ItemDataHolder<?>> entry : ItemModule.getItemRegistry().entrySet()) {
            // Register item
            Registry.register(BuiltInRegistries.ITEM, entry.getKey(), entry.getValue().get());
        }
    }
}

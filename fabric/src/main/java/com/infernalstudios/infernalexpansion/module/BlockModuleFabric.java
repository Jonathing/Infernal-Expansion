package com.infernalstudios.infernalexpansion.module;

import com.infernalstudios.infernalexpansion.registration.FlammabilityRegistry;
import com.infernalstudios.infernalexpansion.registration.FuelRegistry;
import com.infernalstudios.infernalexpansion.registration.StrippableRegistry;
import com.infernalstudios.infernalexpansion.registration.holders.BlockDataHolder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public class BlockModuleFabric {
    public static void registerBlocks() {
        for (Map.Entry<ResourceLocation, BlockDataHolder<?>> entry : BlockModule.getBlockRegistry().entrySet()) {
            // Register block
            Registry.register(BuiltInRegistries.BLOCK, entry.getKey(), entry.getValue().get());

            // Register the block items
            if (entry.getValue().hasItem()) {
                Registry.register(BuiltInRegistries.ITEM, entry.getKey(), entry.getValue().getBlockItem().get());

                // Register Block Item Fuel
                if (entry.getValue().isFuel()) {
                    FuelRegistry.register(entry.getValue().getBlockItem().get(), entry.getValue().getFuelDuration());
                }
            }

            // Register Block Flammabilities
            for (Map.Entry<Block, FlammabilityRegistry.Entry> flammability : entry.getValue().getFlammabilities().entrySet()) {
                FlammabilityRegistry.getRegistry(flammability.getKey()).register(entry.getValue().get(), flammability.getValue());
            }

            // Register Block Stripping
            if (entry.getValue().hasStrippingResult()) {
                StrippableRegistry.register(entry.getValue().get(), entry.getValue().getStrippingResult());
            }
        }
    }
}

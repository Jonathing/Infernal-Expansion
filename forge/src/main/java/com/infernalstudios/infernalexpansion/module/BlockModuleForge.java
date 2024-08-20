package com.infernalstudios.infernalexpansion.module;

import com.infernalstudios.infernalexpansion.registration.BlockDataHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Map;

@Mod.EventBusSubscriber(modid = "infernalexpansion", bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockModuleForge {
    @SubscribeEvent
    public static void registerBlocks(RegisterEvent event) {
        for (Map.Entry<ResourceLocation, BlockDataHolder<?>> entry : BlockModule.getBlockRegistry().entrySet()) {
            // Register block
            event.register(Registries.BLOCK, blockRegistryHelper ->
                    blockRegistryHelper.register(entry.getKey(), entry.getValue().get())
            );

            // Register the block items
            if (entry.getValue().hasItem()) {
                event.register(Registries.ITEM, itemRegistryHelper ->
                        itemRegistryHelper.register(entry.getKey(), entry.getValue().getBlockItem().get())
                );
            }
        }
    }
}

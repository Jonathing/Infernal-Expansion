package com.infernalstudios.infernalexpansion.module;

import com.infernalstudios.infernalexpansion.registration.FuelRegistry;
import com.infernalstudios.infernalexpansion.registration.holders.ItemDataHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Map;

@Mod.EventBusSubscriber(modid = "infernalexpansion", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemModuleForge {
    @SubscribeEvent
    public static void registerItems(RegisterEvent event) {
        for (Map.Entry<ResourceLocation, ItemDataHolder<?>> entry : ItemModule.getItemRegistry().entrySet()) {
            // Register item
            event.register(Registries.ITEM, itemRegistryHelper ->
                    itemRegistryHelper.register(entry.getKey(), entry.getValue().get())
            );

            // Register Fuel
            if (entry.getValue().isFuel()) {
                FuelRegistry.register(entry.getValue().get(), entry.getValue().getFuelDuration());
            }
        }
    }
}
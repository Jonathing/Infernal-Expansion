package com.helliongames.hellionsmobs.module;

import com.helliongames.hellionsmobs.registration.EntityTypeDataHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.Map;

@Mod.EventBusSubscriber(modid = "hellionsmobs", bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityTypeModuleNeoForge {

    @SubscribeEvent
    public static void registerEntityType(RegisterEvent event) {
        for (Map.Entry<ResourceLocation, EntityTypeDataHolder> entry : HellionsMobsEntityTypeModule.getEntityTypeRegistry().entrySet()) {
            // Register entity type
            event.register(Registries.ENTITY_TYPE, entityTypeRegisterHelper ->
                    entityTypeRegisterHelper.register(entry.getKey(), entry.getValue().get())
            );
        }

    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        for (Map.Entry<ResourceLocation, EntityTypeDataHolder> entry : HellionsMobsEntityTypeModule.getEntityTypeRegistry().entrySet()) {
            // Register entity attributes

            AttributeSupplier.Builder builder = (AttributeSupplier.Builder) entry.getValue().getAttributesSupplier().get();
            // Attach required Forge attributes and register
            builder.add(NeoForgeMod.SWIM_SPEED.value())
                    .add(NeoForgeMod.NAMETAG_DISTANCE.value())
                    .add(NeoForgeMod.ENTITY_GRAVITY.value());

            event.put(entry.getValue().get(), builder.build());
        }
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        for (Map.Entry<ResourceLocation, EntityTypeDataHolder> entry : HellionsMobsEntityTypeModule.getEntityTypeRegistry().entrySet()) {
            // Register entity renderers
            event.registerEntityRenderer(entry.getValue().get(), entry.getValue().getRendererProvider());
        }

    }
}

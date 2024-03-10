package com.helliongames.hellionsmobs.module;

import com.helliongames.hellionsmobs.HellionsMobsConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = "hellionsmobs", bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityTypeModuleNeoForge {

    @SubscribeEvent
    public static void registerEntityType(RegisterEvent event) {
        event.register(Registries.ENTITY_TYPE, entityTypeRegisterHelper -> {
            entityTypeRegisterHelper.register(new ResourceLocation(HellionsMobsConstants.MOD_ID, "kitsune"), HellionsMobsEntityTypeModule.KITSUNE.get());
        });
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        AttributeSupplier.Builder builder = HellionsMobsEntityTypeModule.KITSUNE.getAttributesSupplier().get();
        // Attach required Forge attributes and register
        builder.add(NeoForgeMod.SWIM_SPEED.value())
                .add(NeoForgeMod.NAMETAG_DISTANCE.value())
                .add(NeoForgeMod.ENTITY_GRAVITY.value());

        event.put(HellionsMobsEntityTypeModule.KITSUNE.get(), builder.build());
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(HellionsMobsEntityTypeModule.KITSUNE.get(), HellionsMobsEntityTypeModule.KITSUNE.getRendererProvider());
    }
}

package com.helliongames.hellionsmobs.module;

import com.helliongames.hellionsmobs.HellionsMobsConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = "hellionsmobs", bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityTypeModuleForge {

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
        builder.add(ForgeMod.SWIM_SPEED.get())
                .add(ForgeMod.NAMETAG_DISTANCE.get())
                .add(ForgeMod.ENTITY_GRAVITY.get());

        event.put(HellionsMobsEntityTypeModule.KITSUNE.get(), builder.build());
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(HellionsMobsEntityTypeModule.KITSUNE.get(), HellionsMobsEntityTypeModule.KITSUNE.getRendererProvider());
    }
}

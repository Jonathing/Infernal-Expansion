package com.helliongames.hellionsmobs.module;

import com.helliongames.hellionsmobs.registration.EntityTypeDataHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Map;

@Mod.EventBusSubscriber(modid = "hellionsmobs", bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityTypeModuleForge {

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
            builder.add(ForgeMod.SWIM_SPEED.get())
                    .add(ForgeMod.NAMETAG_DISTANCE.get())
                    .add(ForgeMod.ENTITY_GRAVITY.get());

            event.put(entry.getValue().get(), builder.build());
        }
    }
}

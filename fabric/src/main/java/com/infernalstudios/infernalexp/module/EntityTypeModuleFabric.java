package com.infernalstudios.infernalexp.module;

import com.infernalstudios.infernalexp.registration.holders.EntityTypeDataHolder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.Map;

public class EntityTypeModuleFabric {

    public static void registerEntities() {
        for (Map.Entry<ResourceLocation, EntityTypeDataHolder> entry : ModEntityTypes.getEntityTypeRegistry().entrySet()) {
            // Register entity type
            Registry.register(BuiltInRegistries.ENTITY_TYPE, entry.getKey(), entry.getValue().get());

            // Register entity attributes, if present
            if (entry.getValue().hasAttributes()) {
                AttributeSupplier.Builder attributesBuilder = (AttributeSupplier.Builder) entry.getValue().getAttributesSupplier().get();
                FabricDefaultAttributeRegistry.register(entry.getValue().get(), attributesBuilder);
            }
        }
    }
}

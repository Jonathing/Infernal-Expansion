package com.helliongames.hellionsmobs.module;

import com.helliongames.hellionsmobs.HellionsMobsConstants;
import com.helliongames.hellionsmobs.entity.KitsuneEntity;
import com.helliongames.hellionsmobs.registration.EntityTypeDataHolder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

public class EntityTypeModuleFabric {

    public static void registerEntities() {
        EntityTypeDataHolder<KitsuneEntity> kitsune = HellionsMobsEntityTypeModule.KITSUNE;

        // Register entity type
        Registry.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(HellionsMobsConstants.MOD_ID, "kitsune"), kitsune.get());

        // Register entity attributes, if attached
        if (kitsune.hasAttributes()) {
            AttributeSupplier.Builder attributesBuilder = kitsune.getAttributesSupplier().get();
            FabricDefaultAttributeRegistry.register(kitsune.get(), attributesBuilder);
        }
    }
}

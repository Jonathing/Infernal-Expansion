package com.helliongames.hellionsmobs.modules;

import com.helliongames.hellionsmobs.HellionsMobsConstants;
import com.helliongames.hellionsmobs.entity.KitsuneEntity;
import com.helliongames.hellionsmobs.registration.util.RegistrationProvider;
import com.helliongames.hellionsmobs.registration.util.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class HellionsMobsEntityTypeModule {
    /**
     * The provider for creative tabs
     */
    public static final RegistrationProvider<EntityType<?>> ENTITIES = RegistrationProvider.get(Registries.ENTITY_TYPE, HellionsMobsConstants.MOD_ID);

    public static final RegistryObject<EntityType<KitsuneEntity>> KITSUNE = ENTITIES.register("kitsune", () ->
            EntityType.Builder.of(KitsuneEntity::new, MobCategory.CREATURE).sized(2.0f, 1.75f).build("kitsune"));

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}

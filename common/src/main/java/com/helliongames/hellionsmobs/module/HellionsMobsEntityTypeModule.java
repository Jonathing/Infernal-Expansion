package com.helliongames.hellionsmobs.module;

import com.helliongames.hellionsmobs.client.renderer.KitsuneRenderer;
import com.helliongames.hellionsmobs.entity.KitsuneEntity;
import com.helliongames.hellionsmobs.registration.EntityTypeDataHolder;
import net.minecraft.world.entity.MobCategory;

public class HellionsMobsEntityTypeModule {
    public static final EntityTypeDataHolder<KitsuneEntity> KITSUNE = EntityTypeDataHolder.of(() ->
            EntityTypeDataHolder.Builder.of(KitsuneEntity::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.75f)
                    .build()
            )
            .attributes(KitsuneEntity::createKitsuneAttributes)
            .renderer(KitsuneRenderer::new);
    
    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}

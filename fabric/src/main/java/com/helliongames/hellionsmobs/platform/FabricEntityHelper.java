package com.helliongames.hellionsmobs.platform;

import com.helliongames.hellionsmobs.entity.KitsuneEntity;
import com.helliongames.hellionsmobs.platform.services.IEntityHelper;
import com.helliongames.hellionsmobs.registration.HellionsMobsEntities;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class FabricEntityHelper implements IEntityHelper {
    @Override
    public void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(HellionsMobsEntities.KITSUNE.get(), KitsuneEntity.createKitsuneAttributes());
    }
}

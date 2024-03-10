package com.helliongames.hellionsmobs;

import com.helliongames.hellionsmobs.module.EntityTypeModuleFabric;
import net.fabricmc.api.ModInitializer;

public class HellionsMobs implements ModInitializer {
    
    @Override
    public void onInitialize() {
        HellionsMobsCommon.init();

        EntityTypeModuleFabric.registerEntities();
    }
}

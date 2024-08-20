package com.infernalstudios.infernalexpansion;

import com.infernalstudios.infernalexpansion.module.EntityTypeModuleFabric;
import net.fabricmc.api.ModInitializer;

public class InfernalExpansion implements ModInitializer {
    
    @Override
    public void onInitialize() {
        InfernalExpansionCommon.init();

        EntityTypeModuleFabric.registerEntities();
    }
}

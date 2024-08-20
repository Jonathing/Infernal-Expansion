package com.infernalstudios.infernalexpansion;

import com.infernalstudios.infernalexpansion.module.BlockModuleFabric;
import com.infernalstudios.infernalexpansion.module.EntityTypeModuleFabric;
import com.infernalstudios.infernalexpansion.module.ItemModuleFabric;
import net.fabricmc.api.ModInitializer;

public class InfernalExpansion implements ModInitializer {
    
    @Override
    public void onInitialize() {
        InfernalExpansionCommon.init();

        BlockModuleFabric.registerBlocks();
        ItemModuleFabric.registerItems();
        EntityTypeModuleFabric.registerEntities();
    }
}

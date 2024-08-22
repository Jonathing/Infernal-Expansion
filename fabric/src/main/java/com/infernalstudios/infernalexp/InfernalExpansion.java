package com.infernalstudios.infernalexp;

import com.infernalstudios.infernalexp.module.BlockModuleFabric;
import com.infernalstudios.infernalexp.module.EntityTypeModuleFabric;
import com.infernalstudios.infernalexp.module.ItemModuleFabric;
import net.fabricmc.api.ModInitializer;

public class InfernalExpansion implements ModInitializer {
    
    @Override
    public void onInitialize() {
        IECommon.init();

        BlockModuleFabric.registerBlocks();
        ItemModuleFabric.registerItems();
        EntityTypeModuleFabric.registerEntities();
    }
}

package com.infernalstudios.infernalexpansion;

import com.infernalstudios.infernalexpansion.module.BlockModule;
import com.infernalstudios.infernalexpansion.module.CreativeTabModule;
import com.infernalstudios.infernalexpansion.module.EntityTypeModule;
import net.minecraft.resources.ResourceLocation;

public class InfernalExpansionCommon {

    public static void init() {
        BlockModule.loadClass();
        EntityTypeModule.loadClass();
        CreativeTabModule.loadClass();
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(InfernalExpansionConstants.MOD_ID, name);
    }
}
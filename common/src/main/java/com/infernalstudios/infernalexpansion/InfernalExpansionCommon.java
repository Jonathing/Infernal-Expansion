package com.infernalstudios.infernalexpansion;

import com.infernalstudios.infernalexpansion.module.InfernalExpansionCreativeTabModule;
import com.infernalstudios.infernalexpansion.module.InfernalExpansionEntityTypeModule;
import net.minecraft.resources.ResourceLocation;

public class InfernalExpansionCommon {

    public static void init() {
        InfernalExpansionEntityTypeModule.loadClass();
        InfernalExpansionCreativeTabModule.loadClass();
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(InfernalExpansionConstants.MOD_ID, name);
    }
}
package com.infernalstudios.infernalexp;

import com.infernalstudios.infernalexp.module.*;
import net.minecraft.resources.ResourceLocation;

public class IECommon {

    public static void init() {
        BlockModule.load();
        ItemModule.load();

        FireTypeModule.load();

        EntityTypeModule.load();
        CreativeTabModule.load();
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(IEConstants.MOD_ID, name);
    }
}
package com.infernalstudios.infernalexp;

import com.infernalstudios.infernalexp.module.BlockModule;
import com.infernalstudios.infernalexp.module.CreativeTabModule;
import com.infernalstudios.infernalexp.module.EntityTypeModule;
import com.infernalstudios.infernalexp.module.ItemModule;
import net.minecraft.resources.ResourceLocation;

public class IECommon {

    public static void init() {
        BlockModule.load();
        ItemModule.load();
        EntityTypeModule.load();
        CreativeTabModule.load();
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(IEConstants.MOD_ID, name);
    }
}
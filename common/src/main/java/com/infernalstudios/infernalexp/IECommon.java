package com.infernalstudios.infernalexp;

import com.infernalstudios.infernalexp.module.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.TheEndBiomeSource;

public class IECommon {

    public static void init() {
        ModBlocks.load();
        ModItems.load();

        ModFireTypes.load();

        ModEntityTypes.load();
        ModCreativeTabs.load();

        ModBiomes.load();
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(IEConstants.MOD_ID, name);
    }
}
package com.helliongames.hellionsmobs;

import com.helliongames.hellionsmobs.module.HellionsMobsCreativeTabModule;
import com.helliongames.hellionsmobs.module.HellionsMobsEntityTypeModule;
import net.minecraft.resources.ResourceLocation;

public class HellionsMobsCommon {

    public static void init() {
        HellionsMobsEntityTypeModule.loadClass();
        HellionsMobsCreativeTabModule.loadClass();
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(HellionsMobsConstants.MOD_ID, name);
    }
}
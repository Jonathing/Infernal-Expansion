package com.helliongames.hellionsmobs;

import com.helliongames.hellionsmobs.platform.Services;
import com.helliongames.hellionsmobs.registration.HellionsMobsCreativeTabs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;

public class CommonClass {

    public static void init() {
        HellionsMobsCreativeTabs.loadClass();
    }
}
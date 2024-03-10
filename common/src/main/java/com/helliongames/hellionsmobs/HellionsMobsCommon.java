package com.helliongames.hellionsmobs;

import com.helliongames.hellionsmobs.modules.HellionsMobsEntityTypeModule;
import com.helliongames.hellionsmobs.platform.Services;
import com.helliongames.hellionsmobs.modules.HellionsMobsCreativeTabModule;

public class HellionsMobsCommon {

    public static void init() {
        HellionsMobsEntityTypeModule.loadClass();
        HellionsMobsCreativeTabModule.loadClass();

        Services.ENTITY_HELPER.registerEntityAttributes();
    }
}
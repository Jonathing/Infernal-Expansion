package com.helliongames.hellionsmobs;

import com.helliongames.hellionsmobs.platform.Services;
import com.helliongames.hellionsmobs.registration.HellionsMobsCreativeTabs;
import com.helliongames.hellionsmobs.registration.HellionsMobsEntities;

public class HellionsMobsCommon {

    public static void init() {
        HellionsMobsEntities.loadClass();
        HellionsMobsCreativeTabs.loadClass();

        Services.ENTITY_HELPER.registerEntityAttributes();
    }
}
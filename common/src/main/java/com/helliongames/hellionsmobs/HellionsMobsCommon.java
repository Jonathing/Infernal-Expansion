package com.helliongames.hellionsmobs;

import com.helliongames.hellionsmobs.module.HellionsMobsCreativeTabModule;
import com.helliongames.hellionsmobs.module.HellionsMobsEntityTypeModule;

public class HellionsMobsCommon {

    public static void init() {
        HellionsMobsEntityTypeModule.loadClass();
        HellionsMobsCreativeTabModule.loadClass();
    }
}
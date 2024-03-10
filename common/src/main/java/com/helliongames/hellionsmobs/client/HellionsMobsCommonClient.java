package com.helliongames.hellionsmobs.client;

import com.helliongames.hellionsmobs.module.HellionsMobsEntityRendererModule;

public class HellionsMobsCommonClient {
    public static void init() {
        HellionsMobsEntityRendererModule.registerEntityRenderers();
    }
}

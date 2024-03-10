package com.helliongames.hellionsmobs.client;

import net.fabricmc.api.ClientModInitializer;

public class HellionsMobsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HellionsMobsCommonClient.init();
    }
}

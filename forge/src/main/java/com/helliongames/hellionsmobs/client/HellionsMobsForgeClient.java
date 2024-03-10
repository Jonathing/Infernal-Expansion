package com.helliongames.hellionsmobs.client;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class HellionsMobsForgeClient {
    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(HellionsMobsForgeClient::clientSetup);
    }

    private static void clientSetup(final FMLClientSetupEvent event) {
    }
}

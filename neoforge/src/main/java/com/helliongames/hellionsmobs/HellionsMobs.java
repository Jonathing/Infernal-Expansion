package com.helliongames.hellionsmobs;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class HellionsMobs {

    public HellionsMobs(IEventBus eventBus) {
        CommonClass.init();
    }
}
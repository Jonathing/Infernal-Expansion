package com.helliongames.hellionsmobs;


import com.helliongames.hellionsmobs.client.HellionsMobsNeoForgeClient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(HellionsMobsConstants.MOD_ID)
public class HellionsMobs {

    public HellionsMobs(IEventBus eventBus) {
        HellionsMobsCommon.init();

        if (FMLEnvironment.dist.isClient()) {
            HellionsMobsNeoForgeClient.init(eventBus);
        }
    }
}
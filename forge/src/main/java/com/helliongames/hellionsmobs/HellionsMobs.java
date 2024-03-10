package com.helliongames.hellionsmobs;

import com.helliongames.hellionsmobs.client.HellionsMobsForgeClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(HellionsMobsConstants.MOD_ID)
public class HellionsMobs {
    
    public HellionsMobs() {
        HellionsMobsCommon.init();
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> HellionsMobsForgeClient::init);
    }
}
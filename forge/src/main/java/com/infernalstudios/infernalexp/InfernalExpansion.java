package com.infernalstudios.infernalexp;

import com.infernalstudios.infernalexp.client.InfernalExpansionForgeClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(IEConstants.MOD_ID)
public class InfernalExpansion {
    
    public InfernalExpansion() {
        IECommon.init();
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> InfernalExpansionForgeClient::init);
    }
}
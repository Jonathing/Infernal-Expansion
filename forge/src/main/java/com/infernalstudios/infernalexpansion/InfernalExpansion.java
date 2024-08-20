package com.infernalstudios.infernalexpansion;

import com.infernalstudios.infernalexpansion.client.InfernalExpansionForgeClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(InfernalExpansionConstants.MOD_ID)
public class InfernalExpansion {
    
    public InfernalExpansion() {
        InfernalExpansionCommon.init();
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> InfernalExpansionForgeClient::init);
    }
}
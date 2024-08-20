package com.infernalstudios.infernalexpansion.client;

import com.infernalstudios.infernalexpansion.module.InfernalExpansionEntityRendererModule;

public class InfernalExpansionCommonClient {
    public static void init() {
        InfernalExpansionEntityRendererModule.registerEntityRenderers();
    }
}

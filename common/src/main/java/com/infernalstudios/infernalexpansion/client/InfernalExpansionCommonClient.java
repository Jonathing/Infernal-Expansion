package com.infernalstudios.infernalexpansion.client;

import com.infernalstudios.infernalexpansion.module.EntityRendererModule;

public class InfernalExpansionCommonClient {
    public static void init() {
        EntityRendererModule.registerEntityRenderers();
    }
}

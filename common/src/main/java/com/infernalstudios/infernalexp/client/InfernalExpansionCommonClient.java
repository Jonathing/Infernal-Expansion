package com.infernalstudios.infernalexp.client;

import com.infernalstudios.infernalexp.module.EntityRendererModule;

public class InfernalExpansionCommonClient {
    public static void init() {
        EntityRendererModule.registerEntityRenderers();
    }
}

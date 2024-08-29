package com.infernalstudios.infernalexp.module;

import com.infernalstudios.infernalexp.IECommon;
import com.infernalstudios.infernalexp.IEConstants;
import com.infernalstudios.infernalexp.api.FireType;
import net.minecraft.resources.ResourceLocation;

public class FireTypeModule {
    public static void load() {}

    public static final FireType FIRE = FireType.register(new ResourceLocation("fire"));
    public static final FireType SOUL_FIRE = FireType.register(new ResourceLocation("soul_fire"));
    public static final FireType GLOW_FIRE = FireType.register(new ResourceLocation(IEConstants.MOD_ID, "glowlight_fire"));
    public static final FireType ENDER_FIRE = FireType.register(new ResourceLocation("endergetic", "ender_fire"));
    public static final FireType BORIC_FIRE = FireType.register(new ResourceLocation("byg", "boric_fire"));
    public static final FireType CRYPTIC_FIRE = FireType.register(new ResourceLocation("byg", "cryptic_fire"));
}

package com.infernalstudios.infernalexp.module;

import com.infernalstudios.infernalexp.IECommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static TagKey<Block> create(String name) {
            return TagKey.create(Registries.BLOCK, IECommon.id(name));
        }

        public static final TagKey<Block> GLOW_FIRE_BASE_BLOCKS = create("glowlight_fire_base_blocks");
    }
}

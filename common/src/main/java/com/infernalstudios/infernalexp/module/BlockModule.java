package com.infernalstudios.infernalexp.module;

import com.infernalstudios.infernalexp.IECommon;
import com.infernalstudios.infernalexp.block.LayerBlock;
import com.infernalstudios.infernalexp.registration.holders.BlockDataHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.HashMap;
import java.util.Map;

public class BlockModule {
    /** Map of all Block Resource Locations to their BlockDataHolders. */
    private static final Map<ResourceLocation, BlockDataHolder<?>> BLOCK_REGISTRY = new HashMap<>();

    public static BlockDataHolder<?> register(String name, BlockDataHolder<?> blockDataHolder) {
        ResourceLocation id = IECommon.id(name);
        BLOCK_REGISTRY.put(id, blockDataHolder);
        return blockDataHolder;
    }

    public static Map<ResourceLocation, BlockDataHolder<?>> getBlockRegistry() {
        return BLOCK_REGISTRY;
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void load() {}


    public static final BlockDataHolder<?> SHIMMER_SAND = register("shimmer_sand", BlockDataHolder.of(() ->
                    new SandBlock(0xffffaa, BlockBehaviour.Properties.copy(Blocks.SAND)))
            .withModel(BlockDataHolder.Model.ROTATABLE)
            .withItem()
            .withTags(BlockTags.MINEABLE_WITH_SHOVEL)
            .withTranslation("Shimmer Sand")
    );

    public static final BlockDataHolder<?> SHIMMER_SHEET = register("shimmer_sheet", BlockDataHolder.of(() ->
                    new LayerBlock(BlockBehaviour.Properties.copy(Blocks.SAND)))
            .withItem()
            .withTags(BlockTags.MINEABLE_WITH_SHOVEL)
            .withTranslation("Shimmer Sheet")
    );

    public static final BlockDataHolder<?> GLOWLIGHT_GLASS = register("glowlight_glass", BlockDataHolder.of(() ->
                    new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).noOcclusion()))
            .withModel(BlockDataHolder.Model.CUBE)
            .cutout()
            .withItem()
            .withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Glowlight Glass")
    );


    public static final BlockBehaviour.Properties shimmerstone = BlockBehaviour.Properties.copy(Blocks.STONE)
            .mapColor(MapColor.COLOR_YELLOW);

    public static final BlockDataHolder<?> SHIMMER_STONE = register("shimmer_stone", BlockDataHolder.of(() ->
                    new Block(shimmerstone))
            .withModel(BlockDataHolder.Model.CUBE)
            .withItem()
            .withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Shimmer Stone")
    );

    public static final BlockDataHolder<?> SHIMMER_STONE_BRICKS = register("shimmer_stone_bricks", BlockDataHolder.of(() ->
                    new Block(shimmerstone))
            .withModel(BlockDataHolder.Model.CUBE)
            .withItem()
            .withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Shimmer Stone Bricks")
            .withStairs().withSlab()
    );
}

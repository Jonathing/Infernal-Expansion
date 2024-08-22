package com.infernalstudios.infernalexp.module;

import com.infernalstudios.infernalexp.IECommon;
import com.infernalstudios.infernalexp.block.LayerBlock;
import com.infernalstudios.infernalexp.registration.holders.BlockDataHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.SandBlock;
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
            .dropsSelf()
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
            .glass()
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
            .dropsSelf()
            .withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Shimmer Stone")
    );

    public static final BlockDataHolder<?> SHIMMER_STONE_BRICKS = register("shimmer_stone_bricks", BlockDataHolder.of(() ->
                    new Block(shimmerstone))
            .withModel(BlockDataHolder.Model.CUBE)
            .withItem()
            .dropsSelf()
            .withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Shimmer Stone Bricks")
            .withStairs().withSlab()
    );


    public static final BlockDataHolder<?> POLISHED_GLOWSTONE = register("polished_glowstone", BlockDataHolder.of(() ->
                    new Block(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)))
            .withModel(BlockDataHolder.Model.CUBE)
            .withItem()
            .dropsSelf()
            .withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Polished Glowstone")
    );

    private static final BlockBehaviour.Properties dimstone =
            BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).strength(1).lightLevel(a -> 6)
                    .mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops();

    public static final BlockDataHolder<?> DIMSTONE = register("dimstone", BlockDataHolder.of(() ->
                    new Block(dimstone))
            .withItem()
            .dropsSelf()
            .withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Dimstone")
    );

    public static final BlockDataHolder<?> POLISHED_DIMSTONE = register("polished_dimstone", BlockDataHolder.of(() ->
                    new Block(dimstone))
            .withModel(BlockDataHolder.Model.CUBE)
            .withItem()
            .dropsSelf()
            .withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Polished Dimstone")
    );

    private static final BlockBehaviour.Properties dullstone =
            BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).strength(1.7f).lightLevel(a -> 0)
                    .mapColor(MapColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops();

    public static final BlockDataHolder<?> DULLSTONE = register("dullstone", BlockDataHolder.of(() ->
                    new Block(dullstone))
            .withModel(BlockDataHolder.Model.CUBE)
            .withItem()
            .dropsSelf()
            .withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Dullstone")
    );

    public static final BlockDataHolder<?> POLISHED_DULLSTONE = register("polished_dullstone", BlockDataHolder.of(() ->
                    new Block(dullstone))
            .withModel(BlockDataHolder.Model.CUBE)
            .withItem()
            .dropsSelf()
            .withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Polished Dullstone")
    );
}

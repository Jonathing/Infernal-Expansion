package com.infernalstudios.infernalexp.module;

import com.infernalstudios.infernalexp.IECommon;
import com.infernalstudios.infernalexp.block.DullthornsBlock;
import com.infernalstudios.infernalexp.block.FungusCapBlock;
import com.infernalstudios.infernalexp.block.LayerBlock;
import com.infernalstudios.infernalexp.block.LuminousFungusBlock;
import com.infernalstudios.infernalexp.mixin.accessor.ButtonBlockAccessor;
import com.infernalstudios.infernalexp.mixin.accessor.PressurePlateBlockAccessor;
import com.infernalstudios.infernalexp.registration.holders.BlockDataHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
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
            .withModel(BlockDataHolder.Model.ROTATABLE).withItem().dropsSelf().withTags(BlockTags.MINEABLE_WITH_SHOVEL)
            .withTranslation("Shimmer Sand")
    );

    public static final BlockDataHolder<?> SHIMMER_SHEET = register("shimmer_sheet", BlockDataHolder.of(() ->
                    new LayerBlock(BlockBehaviour.Properties.copy(Blocks.SAND)))
            .withItem().withTags(BlockTags.MINEABLE_WITH_SHOVEL)
            .withTranslation("Shimmer Sheet")
    );

    // TODO: Give it special falling properties
    public static final BlockDataHolder<?> GLIMMER_GRAVEL = register("glimmer_gravel", BlockDataHolder.of(() ->
                    new Block(BlockBehaviour.Properties.copy(Blocks.SAND)))
            .withModel(BlockDataHolder.Model.ROTATABLE).withItem().dropsSelf().withTags(BlockTags.MINEABLE_WITH_SHOVEL)
            .withTranslation("Glimmer Gravel")
    );

    public static final BlockDataHolder<?> GLOWLIGHT_GLASS = register("glowlight_glass", BlockDataHolder.of(() ->
                    new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).noOcclusion()))
            .glass().cutout().withItem()
            .withTranslation("Glowlight Glass")
    );

    public static final BlockBehaviour.Properties shimmerstone = BlockBehaviour.Properties.copy(Blocks.STONE)
            .mapColor(MapColor.COLOR_YELLOW);

    public static final BlockDataHolder<?> SHIMMER_STONE = register("shimmer_stone", BlockDataHolder.of(() ->
                    new Block(shimmerstone))
            .withModel(BlockDataHolder.Model.CUBE).withItem().dropsSelf().withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Shimmer Stone")
    );

    public static final BlockDataHolder<?> SHIMMER_STONE_BRICKS = register("shimmer_stone_bricks", BlockDataHolder.of(() ->
                    new Block(shimmerstone))
            .withStairs().withSlab()
            .withModel(BlockDataHolder.Model.CUBE).withItem().dropsSelf().withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Shimmer Stone Bricks")
    );


    public static final BlockDataHolder<?> POLISHED_GLOWSTONE = register("polished_glowstone", BlockDataHolder.of(() ->
                    new Block(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)))
            .withModel(BlockDataHolder.Model.CUBE).withItem().dropsSelf().withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Polished Glowstone")
    );

    private static final BlockBehaviour.Properties dimstone =
            BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).strength(1).lightLevel(a -> 6)
                    .mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops();

    public static final BlockDataHolder<?> DIMSTONE = register("dimstone", BlockDataHolder.of(() ->
                    new Block(dimstone))
            .withItem().withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Dimstone")
    );

    public static final BlockDataHolder<?> POLISHED_DIMSTONE = register("polished_dimstone", BlockDataHolder.of(() ->
                    new Block(dimstone))
            .withModel(BlockDataHolder.Model.CUBE).withItem().dropsSelf().withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Polished Dimstone")
    );

    private static final BlockBehaviour.Properties dullstone =
            BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).strength(1.7f).lightLevel(a -> 0)
                    .mapColor(MapColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops();

    private static final BlockBehaviour.Properties dullstoneButton =
            BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).strength(1.7f).noCollission()
                    .lightLevel(a -> a.getValue(ButtonBlock.POWERED) ? 15 : 0)
                    .mapColor(MapColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops();

    private static final BlockBehaviour.Properties dullstonePlate =
            BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).strength(1.7f).noCollission()
                    .lightLevel(a -> a.getValue(PressurePlateBlock.POWERED) ? 15 : 0)
                    .mapColor(MapColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops();

    public static final BlockDataHolder<?> DULLSTONE = register("dullstone", BlockDataHolder.of(() ->
                    new Block(dullstone))
            .withModel(BlockDataHolder.Model.CUBE).withItem().withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Dullstone")
    );

    public static final BlockDataHolder<?> POLISHED_DULLSTONE = register("polished_dullstone", BlockDataHolder.of(() ->
                    new Block(dullstone))
            .withModel(BlockDataHolder.Model.CUBE).withItem().dropsSelf().withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Polished Dullstone")
    );

    public static final BlockSetType dullstoneSet = new BlockSetType("dullstone");

    public static final BlockDataHolder<?> DULLSTONE_BUTTON = register("dullstone_button", BlockDataHolder.of(() ->
                    ButtonBlockAccessor.createButtonBlock(dullstoneButton,
                            dullstoneSet, 20, false))
            .withItem().dropsSelf().withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Dullstone Button")
    );

    public static final BlockDataHolder<?> DULLSTONE_PRESSURE_PLATE = register("dullstone_pressure_plate", BlockDataHolder.of(() ->
                    PressurePlateBlockAccessor.createPressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, dullstonePlate,
                            dullstoneSet))
            .withItem().dropsSelf().withTags(BlockTags.MINEABLE_WITH_PICKAXE)
            .withTranslation("Dullstone Pressure Plate")
    );


    private static final BlockBehaviour.Properties cocoon = BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK)
            .strength(2f).lightLevel(a -> 15).mapColor(DyeColor.WHITE).requiresCorrectToolForDrops();

    public static final BlockDataHolder<?> GLOWSILK_COCOON = register("glowsilk_cocoon", BlockDataHolder.of(() ->
                    new RotatedPillarBlock(cocoon))
            .withModel(BlockDataHolder.Model.PILLAR).withItem().dropsSelf()
            .withTags(BlockTags.MINEABLE_WITH_HOE, BlockTags.NEEDS_IRON_TOOL)
            .withTranslation("Glowsilk Cocoon")
    );


    public static final BlockDataHolder<?> LUMINOUS_FUNGUS = register("luminous_fungus", BlockDataHolder.of(() ->
                    new LuminousFungusBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_FUNGUS).mapColor(DyeColor.YELLOW)
                            .lightLevel(a -> a.getValue(LuminousFungusBlock.LIT) ? 15 : 0).randomTicks()))
            .cutout().withItem().dropsSelf()
            .withTranslation("Luminous Fungus")
    );

    public static final BlockDataHolder<?> LUMINOUS_FUNGUS_CAP = register("luminous_fungus_cap", BlockDataHolder.of(() ->
                    new FungusCapBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_WART_BLOCK).mapColor(DyeColor.YELLOW)
                            .lightLevel(a -> 8)))
            .withItem().dropsSelf()
            .withTranslation("Luminous Fungus Cap")
    );

    private static final BlockBehaviour.Properties dullthorns =
            BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(1.1f)
                    .mapColor(MapColor.TERRACOTTA_BROWN).noCollission().noOcclusion();

    public static final BlockDataHolder<?> DULLTHORNS = register("dullthorns", BlockDataHolder.of(() ->
                    new DullthornsBlock(dullthorns))
            .cutout().withItem().dropsSelf()
            .withTags(BlockTags.CLIMBABLE, BlockTags.MINEABLE_WITH_AXE)
            .withTranslation("Dullthorns")
    );

    public static final BlockDataHolder<?> DULLTHORNS_BLOCK = register("dullthorns_block", BlockDataHolder.of(() ->
                    new Block(dullthorns))
            .withItem().dropsSelf()
            .withModel(BlockDataHolder.Model.ROTATABLE)
            .withTags(BlockTags.CLIMBABLE, BlockTags.MINEABLE_WITH_AXE)
            .withTranslation("Dullthorns Block")
    );

}

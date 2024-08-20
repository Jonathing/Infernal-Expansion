package org.infernalstudios.infernalexp.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.block.custom.DullthornsBlock;
import org.infernalstudios.infernalexp.block.custom.FungusCapBlock;
import org.infernalstudios.infernalexp.block.custom.LuminousFungusBlock;
import org.infernalstudios.infernalexp.setup.ModRegistry;

public class ModBlocks {
    public static void register() {
        InfernalExpansion.log("Registering Blocks for " + InfernalExpansion.MOD_ID);


        ModRegistry.registerGlass(GLOWLIGHT_GLASS, GLOWLIGHT_GLASS_PANE);

        ModRegistry.registerStairsAndSlab(SHIMMER_STONE_BRICKS, SHIMMER_STONE_BRICK_STAIRS, SHIMMER_STONE_BRICK_SLAB);
    }

    private static final FabricBlockSettings shimmerStone =
            FabricBlockSettings.copyOf(Blocks.SANDSTONE).mapColor(MapColor.YELLOW).requiresTool();

    private static final FabricBlockSettings dimStone =
            FabricBlockSettings.copyOf(Blocks.GLOWSTONE).strength(1).luminance(6).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool();

    private static final FabricBlockSettings dullStone =
            FabricBlockSettings.copyOf(Blocks.GLOWSTONE).strength(1.7f).luminance(0).mapColor(MapColor.TERRACOTTA_GRAY).requiresTool();
    private static final BlockSetType dullStoneSet =
            new BlockSetTypeBuilder().register(InfernalExpansion.makeID("dullstone"));

    private static final FabricBlockSettings dullthorns =
            FabricBlockSettings.create().mapColor(MapColor.SPRUCE_BROWN).strength(0.4f);


    public static final Block SHIMMER_SAND = ModRegistry.ofBlock("shimmer_sand",
                    new SandBlock(0xffffaa, FabricBlockSettings.copyOf(Blocks.SAND)))
            .model(ModRegistry.Models.ROTATABLE).drop().tool("wood_shovel").build();

    public static final Block SHIMMER_SHEET = ModRegistry.ofBlock("shimmer_sheet",
                    new SnowBlock(FabricBlockSettings.copyOf(Blocks.SAND)))
            .tool("wood_shovel").build();

    public static final Block GLOWLIGHT_GLASS = ModRegistry.ofBlock("glowlight_glass",
                    new GlassBlock(FabricBlockSettings.copyOf(Blocks.GLOWSTONE).nonOpaque()))
            .tool("wood_pickaxe").cutout().build();

    public static final Block GLOWLIGHT_GLASS_PANE = ModRegistry.ofBlock("glowlight_glass_pane",
                    new PaneBlock(FabricBlockSettings.copyOf(Blocks.GLOWSTONE).nonOpaque()))
            .model(ModRegistry.Models.PANE).tool("wood_pickaxe").cutout().build();


    public static final Block SHIMMER_STONE = ModRegistry.ofBlock("shimmer_stone",
                    new Block(shimmerStone))
            .model().drop().tool("wood_pickaxe").build();

    public static final Block SHIMMER_STONE_BRICKS = ModRegistry.ofBlock("shimmer_stone_bricks",
                    new Block(shimmerStone))
            .drop().tool("wood_pickaxe").build();

    public static final Block SHIMMER_STONE_BRICK_STAIRS = ModRegistry.ofBlock("shimmer_stone_brick_stairs",
                    new StairsBlock(SHIMMER_STONE_BRICKS.getDefaultState(), shimmerStone))
            .model(ModRegistry.Models.STAIRS).drop().tool("wood_pickaxe").build();

    public static final Block SHIMMER_STONE_BRICK_SLAB = ModRegistry.ofBlock("shimmer_stone_brick_slab",
                    new SlabBlock(shimmerStone))
            .model(ModRegistry.Models.SLAB).drop().tool("wood_pickaxe").build();


    public static final Block POLISHED_GLOWSTONE = ModRegistry.ofBlock("polished_glowstone",
                    new Block(FabricBlockSettings.copyOf(Blocks.GLOWSTONE)))
            .model().drop().tool("wood_pickaxe").build();

    public static final Block DIMSTONE = ModRegistry.ofBlock("dimstone",
                    new Block(dimStone))
            .drop().tool("wood_pickaxe").build();

    public static final Block POLISHED_DIMSTONE = ModRegistry.ofBlock("polished_dimstone",
                    new Block(dimStone))
            .model().drop().tool("wood_pickaxe").build();

    public static final Block DULLSTONE = ModRegistry.ofBlock("dullstone",
                    new Block(dullStone))
            .model().drop().tool("wood_pickaxe").build();

    public static final Block POLISHED_DULLSTONE = ModRegistry.ofBlock("polished_dullstone",
                    new Block(dullStone))
            .model().drop().tool("wood_pickaxe").build();

    public static final Block DULLSTONE_BUTTON = ModRegistry.ofBlock("dullstone_button",
                    new ButtonBlock(FabricBlockSettings.copyOf(dullStone).collidable(false)
                            .luminance(a -> a.get(ButtonBlock.POWERED) ? 15 : 0), dullStoneSet, 20, false))
            .drop().tool("wood_pickaxe").build();

    public static final Block DULLSTONE_PRESSURE_PLATE = ModRegistry.ofBlock("dullstone_pressure_plate",
                    new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS,
                            FabricBlockSettings.copyOf(dullStone).collidable(false)
                                    .luminance(a -> a.get(PressurePlateBlock.POWERED) ? 15 : 0), dullStoneSet))
            .drop().tool("wood_pickaxe").build();


    public static final Block GLOWSILK_COCOON = ModRegistry.ofBlock("glowsilk_cocoon",
                    new Block(FabricBlockSettings.copyOf(Blocks.MOSS_BLOCK).strength(2f).luminance(15).mapColor(MapColor.YELLOW)))
            .model().drop().tool("stone_hoe").build();


    public static final Block DULLTHORNS = ModRegistry.ofBlock("dullthorns",
                    new DullthornsBlock(Block.createCuboidShape(3, 0, 3, 13, 16, 13),
                            FabricBlockSettings.copyOf(dullthorns).collidable(false).nonOpaque()))
            .model(ModRegistry.Models.CROSS).drop().tool("wood_axe")
            .tag(BlockTags.CLIMBABLE).cutout().build();

    public static final Block DULLTHORNS_BLOCK = ModRegistry.ofBlock("dullthorns_block",
                    new DullthornsBlock(Block.createCuboidShape(0.1, 0, 0.1, 15.9, 16, 15.9), dullthorns))
            .model().drop().tool("wood_axe")
            .tag(BlockTags.CLIMBABLE).build();


    public static final Block LUMINOUS_FUNGUS = ModRegistry.ofBlock("luminous_fungus",
                    new LuminousFungusBlock(FabricBlockSettings.copyOf(Blocks.WARPED_FUNGUS)
                            .luminance(a -> a.get(LuminousFungusBlock.IS_LIT) ? 15 : 0).ticksRandomly()))
            .drop().cutout().build();

    public static final Block LUMINOUS_FUNGUS_CAP = ModRegistry.ofBlock("luminous_fungus_cap",
                    new FungusCapBlock(FabricBlockSettings.copyOf(Blocks.WARPED_FUNGUS)
                            .luminance(8).collidable(true)))
            .tool("wood_hoe").drop().build();
}

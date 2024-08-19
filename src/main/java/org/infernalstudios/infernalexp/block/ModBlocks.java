package org.infernalstudios.infernalexp.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.model.Model;
import net.minecraft.data.client.Models;
import net.minecraft.registry.tag.BlockTags;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.block.custom.LuminousFungusBlock;
import org.infernalstudios.infernalexp.setup.ModRegistry;

public class ModBlocks {
    public static void register() {
        InfernalExpansion.log("Registering Blocks for " + InfernalExpansion.MOD_ID);
    }

    private static final FabricBlockSettings shimmerStone =
            FabricBlockSettings.copyOf(Blocks.SANDSTONE).mapColor(MapColor.YELLOW).requiresTool();

    private static final FabricBlockSettings dimStone =
            FabricBlockSettings.copyOf(Blocks.GLOWSTONE).strength(1).luminance(6).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool();

    private static final FabricBlockSettings dullStone =
            FabricBlockSettings.copyOf(Blocks.GLOWSTONE).strength(1.7f).luminance(0).mapColor(MapColor.TERRACOTTA_GRAY).requiresTool();


    public static final Block SHIMMER_SAND = ModRegistry.ofBlock("shimmer_sand",
            new SandBlock(0xffffaa, FabricBlockSettings.copyOf(Blocks.SAND)))
            .model().drop().tool("wood_shovel").build();

    public static final Block SHIMMER_SHEET = ModRegistry.ofBlock("shimmer_sheet",
            new SnowBlock(FabricBlockSettings.copyOf(Blocks.SAND)))
            .tool("wood_shovel").build();


    public static final Block SHIMMER_STONE = ModRegistry.ofBlock("shimmer_stone",
                    new Block(shimmerStone))
            .model().drop().tool("wood_pickaxe").build();

    public static final Block SHIMMER_STONE_BRICKS = ModRegistry.ofBlock("shimmer_stone_bricks",
                    new Block(shimmerStone))
            .model().drop().tool("wood_pickaxe").build();


    public static final Block POLISHED_GLOWSTONE = ModRegistry.ofBlock("polished_glowstone",
                    new Block(FabricBlockSettings.copyOf(Blocks.GLOWSTONE)))
            .model().drop().tool("wood_pickaxe").build();

    public static final Block DIMSTONE = ModRegistry.ofBlock("dimstone",
                    new Block(dimStone))
            .model().drop().tool("wood_pickaxe").build();

    public static final Block POLISHED_DIMSTONE = ModRegistry.ofBlock("polished_dimstone",
                    new Block(dimStone))
            .model().drop().tool("wood_pickaxe").build();

    public static final Block DULLSTONE = ModRegistry.ofBlock("dullstone",
                    new Block(dullStone))
            .model().drop().tool("wood_pickaxe").build();

    public static final Block POLISHED_DULLSTONE = ModRegistry.ofBlock("polished_dullstone",
                    new Block(dullStone))
            .model().drop().tool("wood_pickaxe").build();


    public static final Block GLOWSILK_COCOON = ModRegistry.ofBlock("glowsilk_cocoon",
                    new Block(FabricBlockSettings.copyOf(Blocks.MOSS_BLOCK).strength(2f).luminance(15).mapColor(MapColor.YELLOW)))
            .model().drop().tool("stone_hoe").build();


    public static final Block LUMINOUS_FUNGUS = ModRegistry.ofBlock("luminous_fungus",
            new LuminousFungusBlock(FabricBlockSettings.copyOf(Blocks.WARPED_FUNGUS).luminance(10).ticksRandomly()))
            .drop().cutout().build();
}

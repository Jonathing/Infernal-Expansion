/*
 * Copyright 2022 Infernal Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.infernalstudios.infernalexp.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.blocks.BasaltIronOreBlock;
import org.infernalstudios.infernalexp.blocks.BasalticMagmaBlock;
import org.infernalstudios.infernalexp.blocks.BuriedBoneBlock;
import org.infernalstudios.infernalexp.blocks.CrumblingBlackstoneBlock;
import org.infernalstudios.infernalexp.blocks.DullthornsBlock;
import org.infernalstudios.infernalexp.blocks.DullthornsBlockBlock;
import org.infernalstudios.infernalexp.blocks.FungusCapBlock;
import org.infernalstudios.infernalexp.blocks.GlowCampfireBlock;
import org.infernalstudios.infernalexp.blocks.GlowFireBlock;
import org.infernalstudios.infernalexp.blocks.GlowSandBlock;
import org.infernalstudios.infernalexp.blocks.GlowTorchBlock;
import org.infernalstudios.infernalexp.blocks.GlowWallTorchBlock;
import org.infernalstudios.infernalexp.blocks.GlowdustBlock;
import org.infernalstudios.infernalexp.blocks.IETransparentBlock;
import org.infernalstudios.infernalexp.blocks.LightUpPressurePlateBlock;
import org.infernalstudios.infernalexp.blocks.LuminousFungusBlock;
import org.infernalstudios.infernalexp.blocks.NetherCarpetBlock;
import org.infernalstudios.infernalexp.blocks.NetherrackPathBlock;
import org.infernalstudios.infernalexp.blocks.PlantedQuartzBlock;
import org.infernalstudios.infernalexp.blocks.ShroomlightFungusBlock;
import org.infernalstudios.infernalexp.blocks.SoulSoilPathBlock;
import org.infernalstudios.infernalexp.blocks.TrappedGlowSandBlock;
import org.infernalstudios.infernalexp.blocks.VerticalSlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import org.infernalstudios.infernalexp.util.legacy.Material;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

@SuppressWarnings("unused")
public class IEBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, InfernalExpansion.MOD_ID);

  /*			single file in /blockstates pulls from (potentially multiple files in)
    			/model/block, which pulls from /textures/blocks all the different ways a block can look.
    			(If a block is animated, pull the name of the animation texture.)
    			/item pulls from the individual json file in /model/block.

    			If the block is a SLAB, STAIR, BUTTON, PRESSURE PLATE, WALL, or WART BLOCK, add it to the corresponding tag file
    			in data/minecraft.tags.

				If the block is a VERTICAL SLAB, add it to the corresponding tag file for quark in data/quark.tags.
				
				Make sure tags for blocks have both .tags.block and .tags.items if they exist in the respective categories.

    			When that's done, make sure they have a LOOT TABLE, found in data/infernalexp/loot_tables.
    			For RECIPES, make sure the block is craftable in some form if not a biome-building block.
    			If it's a type of stone or has bricks, give it a STONE-CUTTER recipe.
    			Furnace Recipes are included in the recipes folder. */

    // Blocks
    public static final Supplier<Block> DIMSTONE = registerBlockWithDefaultItem("dimstone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(1.8F, 2.0F).sound(IESoundEvents.DIMSTONE_TYPE).requiresCorrectToolForDrops().lightLevel(state -> 12)));
    public static final Supplier<Block> DULLSTONE = registerBlockWithDefaultItem("dullstone", () -> new Block(Properties.ofFullCopy(Blocks.GLASS).strength(1.5F, 6.0F).sound(IESoundEvents.DULLSTONE_TYPE).requiresCorrectToolForDrops()));

    public static final Supplier<Block> SMOOTH_GLOWSTONE = registerBlockWithDefaultItem("smooth_glowstone", () -> new Block(Properties.ofFullCopy(Blocks.GLASS).strength(1.5F, 6.0F).sound(SoundType.GLASS).requiresCorrectToolForDrops().lightLevel(state -> 15)));
    public static final Supplier<Block> SMOOTH_DIMSTONE = registerBlockWithDefaultItem("smooth_dimstone", () -> new Block(Properties.ofFullCopy(DIMSTONE.get()).strength(1.5F, 6.0F).lightLevel(state -> 12)));
    public static final Supplier<Block> SMOOTH_DULLSTONE = registerBlockWithDefaultItem("smooth_dullstone", () -> new Block(Properties.ofFullCopy(DULLSTONE.get()).strength(1.5F, 6.0F)));

    public static final Supplier<Block> GLOWSTONE_BRICKS = registerBlockWithDefaultItem("glowstone_bricks", () -> new Block(Properties.ofFullCopy(SMOOTH_GLOWSTONE.get())));
    public static final Supplier<Block> DIMSTONE_BRICKS = registerBlockWithDefaultItem("dimstone_bricks", () -> new Block(Properties.ofFullCopy(SMOOTH_DIMSTONE.get())));
    public static final Supplier<Block> DULLSTONE_BRICKS = registerBlockWithDefaultItem("dullstone_bricks", () -> new Block(Properties.ofFullCopy(SMOOTH_DULLSTONE.get())));

    public static final Supplier<Block> CRACKED_GLOWSTONE_BRICKS = registerBlockWithDefaultItem("cracked_glowstone_bricks", () -> new Block(Properties.ofFullCopy(SMOOTH_GLOWSTONE.get())));
    public static final Supplier<Block> CRACKED_DIMSTONE_BRICKS = registerBlockWithDefaultItem("cracked_dimstone_bricks", () -> new Block(Properties.ofFullCopy(SMOOTH_DIMSTONE.get())));
    public static final Supplier<Block> CRACKED_DULLSTONE_BRICKS = registerBlockWithDefaultItem("cracked_dullstone_bricks", () -> new Block(Properties.ofFullCopy(SMOOTH_DULLSTONE.get())));

    public static final Supplier<Block> CHISELED_GLOWSTONE_BRICKS = registerBlockWithDefaultItem("chiseled_glowstone_bricks", () -> new Block(Properties.ofFullCopy(SMOOTH_GLOWSTONE.get())));
    public static final Supplier<Block> CHISELED_DIMSTONE_BRICKS = registerBlockWithDefaultItem("chiseled_dimstone_bricks", () -> new Block(Properties.ofFullCopy(SMOOTH_DIMSTONE.get())));
    public static final Supplier<Block> CHISELED_DULLSTONE_BRICKS = registerBlockWithDefaultItem("chiseled_dullstone_bricks", () -> new Block(Properties.ofFullCopy(SMOOTH_DULLSTONE.get())));

    public static final Supplier<Block> SMOOTH_GLOWSTONE_SLAB = registerBlockWithDefaultItem("smooth_glowstone_slab", () -> new SlabBlock(Properties.ofFullCopy(SMOOTH_GLOWSTONE.get())));
    public static final Supplier<Block> SMOOTH_GLOWSTONE_VERTICAL_SLAB = registerBlockWithDefaultItem("smooth_glowstone_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(SMOOTH_GLOWSTONE.get())));
    public static final Supplier<Block> SMOOTH_GLOWSTONE_STAIRS = registerBlockWithDefaultItem("smooth_glowstone_stairs", () -> new StairBlock(SMOOTH_GLOWSTONE.get().defaultBlockState(), Properties.ofFullCopy(SMOOTH_GLOWSTONE.get())));
    public static final Supplier<Block> SMOOTH_GLOWSTONE_BUTTON = registerBlockWithDefaultItem("smooth_glowstone_button", () -> new ButtonBlock(IEBlockSetTypes.GLOWSTONE, 20, Properties.ofFullCopy(Blocks.STONE_BUTTON).strength(0.5F, 0.5F).sound(SoundType.GLASS).lightLevel(state -> 15).noCollission()));
    public static final Supplier<Block> SMOOTH_GLOWSTONE_PRESSURE_PLATE = registerBlockWithDefaultItem("smooth_glowstone_pressure_plate", () -> new LightUpPressurePlateBlock(IEBlockSetTypes.GLOWSTONE, Properties.ofFullCopy(SMOOTH_GLOWSTONE.get()).lightLevel(getLightValueLit(15))));

    public static final Supplier<Block> SMOOTH_DIMSTONE_SLAB = registerBlockWithDefaultItem("smooth_dimstone_slab", () -> new SlabBlock(Properties.ofFullCopy(SMOOTH_DIMSTONE.get())));
    public static final Supplier<Block> SMOOTH_DIMSTONE_VERTICAL_SLAB = registerBlockWithDefaultItem("smooth_dimstone_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(SMOOTH_DIMSTONE.get())));
    public static final Supplier<Block> SMOOTH_DIMSTONE_STAIRS = registerBlockWithDefaultItem("smooth_dimstone_stairs", () -> new StairBlock(SMOOTH_DIMSTONE.get().defaultBlockState(), Properties.ofFullCopy(SMOOTH_DIMSTONE.get())));
    public static final Supplier<Block> SMOOTH_DIMSTONE_BUTTON = registerBlockWithDefaultItem("smooth_dimstone_button", () -> new ButtonBlock(IEBlockSetTypes.DIMSTONE, 20, Properties.ofFullCopy(Blocks.STONE_BUTTON).strength(0.5F, 0.5F).sound(IESoundEvents.DIMSTONE_TYPE).lightLevel(state -> 12).noCollission()));

    public static final Supplier<Block> SMOOTH_DULLSTONE_SLAB = registerBlockWithDefaultItem("smooth_dullstone_slab", () -> new SlabBlock(Properties.ofFullCopy(SMOOTH_DULLSTONE.get())));
    public static final Supplier<Block> SMOOTH_DULLSTONE_VERTICAL_SLAB = registerBlockWithDefaultItem("smooth_dullstone_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(SMOOTH_DULLSTONE.get())));
    public static final Supplier<Block> SMOOTH_DULLSTONE_STAIRS = registerBlockWithDefaultItem("smooth_dullstone_stairs", () -> new StairBlock(SMOOTH_DULLSTONE.get().defaultBlockState(), Properties.ofFullCopy(SMOOTH_DULLSTONE.get())));
    public static final Supplier<Block> SMOOTH_DULLSTONE_BUTTON = registerBlockWithDefaultItem("smooth_dullstone_button", () -> new ButtonBlock(IEBlockSetTypes.DULLSTONE, 20, Properties.ofFullCopy(Blocks.STONE_BUTTON).strength(0.5F, 0.5F).sound(IESoundEvents.DULLSTONE_TYPE).noCollission()));

    public static final Supplier<Block> GLOWSTONE_BRICK_SLAB = registerBlockWithDefaultItem("glowstone_brick_slab", () -> new SlabBlock(Properties.ofFullCopy(GLOWSTONE_BRICKS.get())));
    public static final Supplier<Block> GLOWSTONE_BRICK_VERTICAL_SLAB = registerBlockWithDefaultItem("glowstone_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(GLOWSTONE_BRICKS.get())));
    public static final Supplier<Block> GLOWSTONE_BRICK_STAIRS = registerBlockWithDefaultItem("glowstone_brick_stairs", () -> new StairBlock(GLOWSTONE_BRICKS.get().defaultBlockState(), Properties.ofFullCopy(GLOWSTONE_BRICKS.get())));
    public static final Supplier<Block> GLOWSTONE_BRICK_WALL = registerBlockWithDefaultItem("glowstone_brick_wall", () -> new WallBlock(Properties.ofFullCopy(GLOWSTONE_BRICKS.get())));

    public static final Supplier<Block> DIMSTONE_BRICK_SLAB = registerBlockWithDefaultItem("dimstone_brick_slab", () -> new SlabBlock(Properties.ofFullCopy(DIMSTONE_BRICKS.get())));
    public static final Supplier<Block> DIMSTONE_BRICK_VERTICAL_SLAB = registerBlockWithDefaultItem("dimstone_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(DIMSTONE_BRICKS.get())));
    public static final Supplier<Block> DIMSTONE_BRICK_STAIRS = registerBlockWithDefaultItem("dimstone_brick_stairs", () -> new StairBlock(DIMSTONE_BRICKS.get().defaultBlockState(), Properties.ofFullCopy(DIMSTONE_BRICKS.get())));
    public static final Supplier<Block> DIMSTONE_BRICK_WALL = registerBlockWithDefaultItem("dimstone_brick_wall", () -> new WallBlock(Properties.ofFullCopy(DIMSTONE_BRICKS.get())));

    public static final Supplier<Block> DULLSTONE_BRICK_SLAB = registerBlockWithDefaultItem("dullstone_brick_slab", () -> new SlabBlock(Properties.ofFullCopy(DULLSTONE_BRICKS.get())));
    public static final Supplier<Block> DULLSTONE_BRICK_VERTICAL_SLAB = registerBlockWithDefaultItem("dullstone_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(DULLSTONE_BRICKS.get())));
    public static final Supplier<Block> DULLSTONE_BRICK_STAIRS = registerBlockWithDefaultItem("dullstone_brick_stairs", () -> new StairBlock(DULLSTONE_BRICKS.get().defaultBlockState(), Properties.ofFullCopy(DULLSTONE_BRICKS.get())));
    public static final Supplier<Block> DULLSTONE_BRICK_WALL = registerBlockWithDefaultItem("dullstone_brick_wall", () -> new WallBlock(Properties.ofFullCopy(DULLSTONE_BRICKS.get())));

    public static final Supplier<Block> LUMINOUS_WART_BLOCK = registerBlockWithDefaultItem("luminous_wart_block", () -> new Block(Properties.ofFullCopy(Blocks.NETHER_WART_BLOCK).lightLevel(state -> 8)));
    public static final Supplier<RotatedPillarBlock> LUMINOUS_STEM = registerBlockWithDefaultItem("luminous_stem", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.CRIMSON_STEM)));
    public static final Supplier<RotatedPillarBlock> LUMINOUS_HYPHAE = registerBlockWithDefaultItem("luminous_hyphae", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.CRIMSON_HYPHAE)));
    public static final Supplier<RotatedPillarBlock> STRIPPED_LUMINOUS_STEM = registerBlockWithDefaultItem("stripped_luminous_stem", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.STRIPPED_CRIMSON_STEM)));
    public static final Supplier<RotatedPillarBlock> STRIPPED_LUMINOUS_HYPHAE = registerBlockWithDefaultItem("stripped_luminous_hyphae", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.STRIPPED_CRIMSON_HYPHAE)));

    public static final Supplier<Block> GLOWDUST_SAND = registerBlockWithDefaultItem("glowdust_sand", () -> new GlowSandBlock(new ColorRGBA(0xFFC267), getProperties(Material.SNOW).requiresCorrectToolForDrops().strength(0.5F).sound(SoundType.SAND)));
    public static final Supplier<Block> GLOWDUST = registerBlockWithDefaultItem("glowdust", () -> new GlowdustBlock(getProperties(Material.TOP_SNOW).requiresCorrectToolForDrops().strength(0.2f).sound(SoundType.SAND)));
    public static final Supplier<Block> TRAPPED_GLOWDUST_SAND = registerBlockWithDefaultItem("trapped_glowdust_sand", () -> new TrappedGlowSandBlock(0xFFC267, Properties.ofFullCopy(GLOWDUST_SAND.get()).strength(0.2F)));

    public static final Supplier<Block> GLOWDUST_STONE = registerBlockWithDefaultItem("glowdust_stone", () -> new Block(Properties.ofFullCopy(Blocks.SANDSTONE)));
    public static final Supplier<Block> GLOWDUST_STONE_SLAB = registerBlockWithDefaultItem("glowdust_stone_slab", () -> new SlabBlock(Properties.ofFullCopy(GLOWDUST_STONE.get())));
    public static final Supplier<Block> GLOWDUST_STONE_VERTICAL_SLAB = registerBlockWithDefaultItem("glowdust_stone_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(GLOWDUST_STONE.get())));
    public static final Supplier<Block> GLOWDUST_STONE_STAIRS = registerBlockWithDefaultItem("glowdust_stone_stairs", () -> new StairBlock(GLOWDUST_STONE.get().defaultBlockState(), Properties.ofFullCopy(GLOWDUST_STONE.get())));
    public static final Supplier<Block> GLOWDUST_STONE_WALL = registerBlockWithDefaultItem("glowdust_stone_wall", () -> new WallBlock(Properties.ofFullCopy(GLOWDUST_STONE.get())));

    public static final Supplier<Block> GLOWDUST_STONE_BRICKS = registerBlockWithDefaultItem("glowdust_stone_bricks", () -> new Block(Properties.ofFullCopy(Blocks.SANDSTONE)));
    public static final Supplier<Block> GLOWDUST_STONE_BRICK_SLAB = registerBlockWithDefaultItem("glowdust_stone_brick_slab", () -> new SlabBlock(Properties.ofFullCopy(GLOWDUST_STONE_BRICKS.get())));
    public static final Supplier<Block> GLOWDUST_STONE_BRICK_VERTICAL_SLAB = registerBlockWithDefaultItem("glowdust_stone_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(GLOWDUST_STONE_BRICKS.get())));
    public static final Supplier<Block> GLOWDUST_STONE_BRICK_STAIRS = registerBlockWithDefaultItem("glowdust_stone_brick_stairs", () -> new StairBlock(GLOWDUST_STONE_BRICKS.get().defaultBlockState(), Properties.ofFullCopy(GLOWDUST_STONE_BRICKS.get())));
    public static final Supplier<Block> GLOWDUST_STONE_BRICK_WALL = registerBlockWithDefaultItem("glowdust_stone_brick_wall", () -> new WallBlock(Properties.ofFullCopy(GLOWDUST_STONE_BRICKS.get())));
    public static final Supplier<Block> CRACKED_GLOWDUST_STONE_BRICKS = registerBlockWithDefaultItem("cracked_glowdust_stone_bricks", () -> new Block(Properties.ofFullCopy(GLOWDUST_STONE_BRICKS.get())));
    public static final Supplier<Block> CHISELED_GLOWDUST_STONE_BRICKS = registerBlockWithDefaultItem("chiseled_glowdust_stone_bricks", () -> new Block(Properties.ofFullCopy(GLOWDUST_STONE_BRICKS.get())));

    public static final Supplier<Block> CRUMBLING_BLACKSTONE = registerBlockWithDefaultItem("crumbling_blackstone", () -> new CrumblingBlackstoneBlock(Properties.ofFullCopy(Blocks.NETHERRACK)));
    public static final Supplier<Block> RUBBLE = registerBlockWithDefaultItem("rubble", () -> new Block(Properties.ofFullCopy(Blocks.GRAVEL)));
    public static final Supplier<Block> SILT = registerBlockWithDefaultItem("silt", () -> new Block(Properties.ofFullCopy(Blocks.SAND)));

    public static final Supplier<Block> BASALT_COBBLED = registerBlockWithDefaultItem("basalt_cobbled", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.GRAVEL).sound(SoundType.BASALT)));
    public static final Supplier<Block> BASALT_COBBLED_SLAB = registerBlockWithDefaultItem("basalt_cobbled_slab", () -> new SlabBlock(Properties.ofFullCopy(Blocks.GRAVEL).sound(SoundType.BASALT)));
    public static final Supplier<Block> BASALT_COBBLED_VERTICAL_SLAB = registerBlockWithDefaultItem("basalt_cobbled_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(Blocks.GRAVEL).sound(SoundType.BASALT)));

    public static final Supplier<Block> BASALT_SLAB = registerBlockWithDefaultItem("basalt_slab", () -> new SlabBlock(Properties.ofFullCopy(Blocks.BASALT)));
    public static final Supplier<Block> BASALT_VERTICAL_SLAB = registerBlockWithDefaultItem("basalt_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(Blocks.BASALT)));
    public static final Supplier<Block> BASALT_STAIRS = registerBlockWithDefaultItem("basalt_stairs", () -> new StairBlock(Blocks.BASALT.defaultBlockState(), Properties.ofFullCopy(Blocks.BASALT)));
    public static final Supplier<Block> BASALT_WALL = registerBlockWithDefaultItem("basalt_wall", () -> new WallBlock(Properties.ofFullCopy(Blocks.BASALT)));
    public static final Supplier<Block> BASALT_BUTTON = registerBlockWithDefaultItem("basalt_button", () -> new ButtonBlock(IEBlockSetTypes.BASALT, 20, Properties.ofFullCopy(Blocks.BASALT)));

    public static final Supplier<Block> POLISHED_BASALT_PRESSURE_PLATE = registerBlockWithDefaultItem("polished_basalt_pressure_plate", () -> new PressurePlateBlock(IEBlockSetTypes.BASALT, Properties.ofFullCopy(Blocks.POLISHED_BASALT)));
    public static final Supplier<Block> POLISHED_BASALT_SLAB = registerBlockWithDefaultItem("polished_basalt_slab", () -> new SlabBlock(Properties.ofFullCopy(Blocks.POLISHED_BASALT)));
    public static final Supplier<Block> POLISHED_BASALT_VERTICAL_SLAB = registerBlockWithDefaultItem("polished_basalt_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(Blocks.POLISHED_BASALT)));
    public static final Supplier<Block> POLISHED_BASALT_TILES = registerBlockWithDefaultItem("polished_basalt_tiles", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.POLISHED_BASALT)));
    public static final Supplier<Block> POLISHED_BASALT_TILES_SLAB = registerBlockWithDefaultItem("polished_basalt_tiles_slab", () -> new SlabBlock(Properties.ofFullCopy(Blocks.POLISHED_BASALT)));
    public static final Supplier<Block> POLISHED_BASALT_TILES_VERTICAL_SLAB = registerBlockWithDefaultItem("polished_basalt_tiles_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(Blocks.POLISHED_BASALT)));

    public static final Supplier<Block> BASALT_BRICKS = registerBlockWithDefaultItem("basalt_bricks", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.BASALT)));
    public static final Supplier<Block> BASALT_BRICK_SLAB = registerBlockWithDefaultItem("basalt_brick_slab", () -> new SlabBlock(Properties.ofFullCopy(BASALT_BRICKS.get())));
    public static final Supplier<Block> BASALT_BRICK_VERTICAL_SLAB = registerBlockWithDefaultItem("basalt_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(BASALT_BRICKS.get())));
    public static final Supplier<Block> BASALT_BRICK_STAIRS = registerBlockWithDefaultItem("basalt_brick_stairs", () -> new StairBlock(BASALT_BRICKS.get().defaultBlockState(), Properties.ofFullCopy(BASALT_BRICKS.get())));
    public static final Supplier<Block> BASALT_BRICK_WALL = registerBlockWithDefaultItem("basalt_brick_wall", () -> new WallBlock(Properties.ofFullCopy(BASALT_BRICKS.get())));
    public static final Supplier<Block> CRACKED_BASALT_BRICKS = registerBlockWithDefaultItem("cracked_basalt_bricks", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.BASALT)));
    public static final Supplier<Block> CHISELED_BASALT_BRICKS = registerBlockWithDefaultItem("chiseled_basalt_bricks", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.BASALT)));
    public static final Supplier<Block> MAGMATIC_CHISELED_BASALT_BRICKS = registerBlockWithDefaultItem("magmatic_chiseled_basalt_bricks", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.BASALT)));

    public static final Supplier<Block> BASALT_IRON_ORE = registerBlockWithDefaultItem("basalt_iron_ore", () -> new BasaltIronOreBlock(Properties.ofFullCopy(Blocks.NETHER_GOLD_ORE)));
    public static final Supplier<Block> BASALTIC_MAGMA = registerBlockWithDefaultItem("basaltic_magma", () -> new BasalticMagmaBlock(Properties.ofFullCopy(Blocks.MAGMA_BLOCK).lightLevel(state -> 2)));

    public static final Supplier<Block> SOUL_SAND_SLAB = registerBlockWithDefaultItem("soul_sand_slab", () -> new SlabBlock(Properties.ofFullCopy(Blocks.SOUL_SAND)));
    public static final Supplier<Block> SOUL_SAND_VERTICAL_SLAB = registerBlockWithDefaultItem("soul_sand_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(Blocks.SOUL_SAND)));
    public static final Supplier<Block> SOUL_SAND_STAIRS = registerBlockWithDefaultItem("soul_sand_stairs", () -> new StairBlock(Blocks.SOUL_SAND.defaultBlockState(), Properties.ofFullCopy((Blocks.SOUL_SAND))));

    public static final Supplier<Block> SOUL_SOIL_SLAB = registerBlockWithDefaultItem("soul_soil_slab", () -> new SlabBlock(Properties.ofFullCopy(Blocks.SOUL_SOIL)));
    public static final Supplier<Block> SOUL_SOIL_VERTICAL_SLAB = registerBlockWithDefaultItem("soul_soil_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(Blocks.SOUL_SOIL)));
    public static final Supplier<Block> SOUL_SOIL_STAIRS = registerBlockWithDefaultItem("soul_soil_stairs", () -> new StairBlock(Blocks.SOUL_SOIL.defaultBlockState(), Properties.ofFullCopy(Blocks.SOUL_SOIL)));

    public static final Supplier<Block> SOUL_STONE = registerBlockWithDefaultItem("soul_stone", () -> new Block(Properties.ofFullCopy(Blocks.COBBLESTONE).sound(IESoundEvents.SOUL_STONE_TYPE)));
    public static final Supplier<Block> SOUL_STONE_SLAB = registerBlockWithDefaultItem("soul_stone_slab", () -> new SlabBlock(Properties.ofFullCopy(Blocks.COBBLESTONE).sound(SoundType.SOUL_SOIL)));
    public static final Supplier<Block> SOUL_STONE_VERTICAL_SLAB = registerBlockWithDefaultItem("soul_stone_vertical_slab", () -> new VerticalSlabBlock((Properties.ofFullCopy(Blocks.COBBLESTONE).sound(SoundType.SOUL_SOIL))));
    public static final Supplier<Block> SOUL_STONE_STAIRS = registerBlockWithDefaultItem("soul_stone_stairs", () -> new StairBlock(Blocks.COBBLESTONE.defaultBlockState(), Properties.ofFullCopy(Blocks.SOUL_SOIL)));
    public static final Supplier<Block> SOUL_STONE_WALL = registerBlockWithDefaultItem("soul_stone_wall", () -> new WallBlock(Properties.ofFullCopy(Blocks.COBBLESTONE_WALL)));

    public static final Supplier<Block> SOUL_SLATE = registerBlockWithDefaultItem("soul_slate", () -> new Block(Properties.ofFullCopy(Blocks.STONE_BRICKS).sound(IESoundEvents.SOUL_STONE_TYPE)));
    public static final Supplier<Block> SOUL_SLATE_SLAB = registerBlockWithDefaultItem("soul_slate_slab", () -> new SlabBlock(Properties.ofFullCopy(SOUL_SLATE.get())));
    public static final Supplier<Block> SOUL_SLATE_VERTICAL_SLAB = registerBlockWithDefaultItem("soul_slate_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(SOUL_SLATE.get())));
    public static final Supplier<Block> SOUL_SLATE_STAIRS = registerBlockWithDefaultItem("soul_slate_stairs", () -> new StairBlock(SOUL_SLATE.get().defaultBlockState(), Properties.ofFullCopy(SOUL_SLATE.get())));
    public static final Supplier<Block> SOUL_SLATE_BUTTON = registerBlockWithDefaultItem("soul_slate_button", () -> new ButtonBlock(IEBlockSetTypes.SOUL_SLATE, 20, Properties.ofFullCopy(SOUL_SLATE.get())));
    public static final Supplier<Block> SOUL_SLATE_PRESSURE_PLATE = registerBlockWithDefaultItem("soul_slate_pressure_plate", () -> new LightUpPressurePlateBlock(IEBlockSetTypes.SOUL_SLATE, Properties.ofFullCopy(SOUL_SLATE.get()).lightLevel(getLightValueLit(15))));

    public static final Supplier<Block> SOUL_STONE_BRICKS = registerBlockWithDefaultItem("soul_stone_bricks", () -> new Block(Properties.ofFullCopy(SOUL_STONE.get())));
    public static final Supplier<Block> SOUL_STONE_BRICK_SLAB = registerBlockWithDefaultItem("soul_stone_brick_slab", () -> new SlabBlock(Properties.ofFullCopy(SOUL_STONE_BRICKS.get())));
    public static final Supplier<Block> SOUL_STONE_BRICK_VERTICAL_SLAB = registerBlockWithDefaultItem("soul_stone_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(SOUL_STONE_BRICKS.get())));
    public static final Supplier<Block> SOUL_STONE_BRICK_STAIRS = registerBlockWithDefaultItem("soul_stone_brick_stairs", () -> new StairBlock(SOUL_STONE_BRICKS.get().defaultBlockState(), Properties.ofFullCopy(SOUL_STONE_BRICKS.get())));
    public static final Supplier<Block> SOUL_STONE_BRICK_WALL = registerBlockWithDefaultItem("soul_stone_brick_wall", () -> new WallBlock(Properties.ofFullCopy(SOUL_STONE_BRICKS.get())));
    public static final Supplier<Block> CRACKED_SOUL_STONE_BRICKS = registerBlockWithDefaultItem("cracked_soul_stone_bricks", () -> new Block(Properties.ofFullCopy(SOUL_STONE.get())));
    public static final Supplier<Block> CHISELED_SOUL_STONE_BRICKS = registerBlockWithDefaultItem("chiseled_soul_stone_bricks", () -> new RotatedPillarBlock(Properties.ofFullCopy(SOUL_STONE.get())));
    public static final Supplier<Block> CHARGED_CHISELED_SOUL_STONE_BRICKS = registerBlockWithDefaultItem("charged_chiseled_soul_stone_bricks", () -> new RotatedPillarBlock(Properties.ofFullCopy(SOUL_SLATE.get())));

    public static final Supplier<Block> SOUL_SLATE_BRICKS = registerBlockWithDefaultItem("soul_slate_bricks", () -> new Block(Properties.ofFullCopy(SOUL_SLATE.get())));
    public static final Supplier<Block> SOUL_SLATE_BRICK_SLAB = registerBlockWithDefaultItem("soul_slate_brick_slab", () -> new SlabBlock(Properties.ofFullCopy(SOUL_SLATE_BRICKS.get())));
    public static final Supplier<Block> SOUL_SLATE_BRICK_VERTICAL_SLAB = registerBlockWithDefaultItem("soul_slate_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.ofFullCopy(SOUL_SLATE_BRICKS.get())));
    public static final Supplier<Block> SOUL_SLATE_BRICK_STAIRS = registerBlockWithDefaultItem("soul_slate_brick_stairs", () -> new StairBlock(SOUL_SLATE_BRICKS.get().defaultBlockState(), Properties.ofFullCopy(SOUL_SLATE_BRICKS.get())));
    public static final Supplier<Block> SOUL_SLATE_BRICK_WALL = registerBlockWithDefaultItem("soul_slate_brick_wall", () -> new WallBlock(Properties.ofFullCopy(SOUL_SLATE_BRICKS.get())));
    public static final Supplier<Block> CRACKED_SOUL_SLATE_BRICKS = registerBlockWithDefaultItem("cracked_soul_slate_bricks", () -> new Block(Properties.ofFullCopy(SOUL_SLATE.get())));
    public static final Supplier<Block> CHISELED_SOUL_SLATE_BRICKS = registerBlockWithDefaultItem("chiseled_soul_slate_bricks", () -> new RotatedPillarBlock(Properties.ofFullCopy(SOUL_SLATE.get())));
    public static final Supplier<Block> CHARGED_CHISELED_SOUL_SLATE_BRICKS = registerBlockWithDefaultItem("charged_chiseled_soul_slate_bricks", () -> new RotatedPillarBlock(Properties.ofFullCopy(SOUL_SLATE.get())));

    public static final Supplier<Block> CRIMSON_FUNGUS_CAP = registerBlockWithDefaultItem("crimson_fungus_cap", () -> new FungusCapBlock(Properties.ofFullCopy(Blocks.NETHER_WART_BLOCK)));
    public static final Supplier<Block> WARPED_FUNGUS_CAP = registerBlockWithDefaultItem("warped_fungus_cap", () -> new FungusCapBlock(Properties.ofFullCopy(Blocks.WARPED_WART_BLOCK)));
    public static final Supplier<Block> LUMINOUS_FUNGUS_CAP = registerBlockWithDefaultItem("luminous_fungus_cap", () -> new FungusCapBlock(Properties.ofFullCopy(Blocks.NETHER_WART_BLOCK).lightLevel(state -> 14)));

    public static final Supplier<Block> GLOW_LANTERN = registerBlockWithDefaultItem("glow_lantern", () -> new LanternBlock(Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Supplier<Block> GLOW_TORCH = registerBlock("glow_torch", () -> new GlowTorchBlock(Properties.ofFullCopy(Blocks.TORCH)));
    @SuppressWarnings("deprecation")
    public static final Supplier<Block> GLOW_TORCH_WALL = registerBlock("glow_torch_wall", () -> new GlowWallTorchBlock(Properties.ofFullCopy(IEBlocks.GLOW_TORCH.get()).dropsLike(GLOW_TORCH.get())));
    public static final Supplier<Block> GLOW_CAMPFIRE = registerBlockWithDefaultItem("glow_campfire", () -> new GlowCampfireBlock(true, 2, Properties.ofFullCopy(Blocks.CAMPFIRE)));
    public static final Supplier<Block> GLOW_FIRE = registerBlock("glow_fire", () -> new GlowFireBlock(Properties.ofFullCopy(Blocks.FIRE)));

    public static final Supplier<Block> GLOWSILK_COCOON = registerBlockWithDefaultItem("glowsilk_cocoon", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.GRASS_BLOCK).sound(SoundType.WOOL).requiresCorrectToolForDrops().strength(5.0F, 1200.0F).lightLevel(state -> 5)));
    // Foliage
    public static final DeferredHolder<Block, Block> LUMINOUS_FUNGUS = registerBlockWithDefaultItem("luminous_fungus", () -> new LuminousFungusBlock(Properties.ofFullCopy(Blocks.BROWN_MUSHROOM).mapColor(MapColor.PLANT).lightLevel(getLightValueLit(15)).noCollission().sound(SoundType.GRASS).offsetType(OffsetType.XZ)));
    public static final DeferredHolder<Block, Block> DULLTHORNS = registerBlock("dullthorns", () -> new DullthornsBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY).lightLevel(state -> 3).noCollission().randomTicks().strength(0.1F).sound(SoundType.GRASS).offsetType(OffsetType.XZ)));

    public static final Supplier<Block> DULLTHORNS_BLOCK = registerBlockWithDefaultItem("dullthorns_block", () -> new DullthornsBlockBlock(Properties.ofFullCopy(Blocks.CACTUS).strength(1.0F).sound(SoundType.WART_BLOCK)));

    public static final Supplier<FlowerPotBlock> POTTED_LUMINOUS_FUNGUS = registerBlock("potted_luminous_fungus", () -> new FlowerPotBlock(LUMINOUS_FUNGUS.get(), Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final Supplier<FlowerPotBlock> POTTED_DULLTHORNS = registerBlock("potted_dullthorns", () -> new FlowerPotBlock(DULLTHORNS.get(), Properties.ofFullCopy(Blocks.FLOWER_POT)));

    public static final DeferredHolder<Block, Block> SHROOMLIGHT_FUNGUS = registerBlockWithDefaultItem("shroomlight_fungus", () -> new ShroomlightFungusBlock(Properties.ofFullCopy(Blocks.BROWN_MUSHROOM).mapColor(MapColor.PLANT).lightLevel(state -> 13).noCollission().sound(SoundType.GRASS).offsetType(OffsetType.XZ)));
    public static final Supplier<FlowerPotBlock> POTTED_SHROOMLIGHT_FUNGUS = registerBlock("potted_shroomlight_fungus", () -> new FlowerPotBlock(SHROOMLIGHT_FUNGUS.get(), Properties.ofFullCopy(Blocks.FLOWER_POT)));

    public static final Supplier<BuriedBoneBlock> BURIED_BONE = registerBlock("buried_bone", () -> new BuriedBoneBlock(Properties.ofFullCopy(Blocks.BROWN_MUSHROOM).mapColor(MapColor.TERRACOTTA_WHITE).noCollission().sound(SoundType.BONE_BLOCK).offsetType(OffsetType.XZ)));
    public static final Supplier<FlowerPotBlock> POTTED_BURIED_BONE = registerBlock("potted_buried_bone", () -> new FlowerPotBlock(BURIED_BONE.get(), Properties.ofFullCopy(Blocks.FLOWER_POT)));

    public static final Supplier<PlantedQuartzBlock> PLANTED_QUARTZ = registerBlock("planted_quartz", () -> new PlantedQuartzBlock(Properties.ofFullCopy(Blocks.STONE).strength(1.5F).requiresCorrectToolForDrops().noCollission().sound(SoundType.NETHER_ORE)));

    public static final Supplier<Block> CRIMSON_NYLIUM_PATH = registerBlockWithDefaultItem("crimson_nylium_path", () -> new NetherrackPathBlock(Properties.ofFullCopy(Blocks.NETHERRACK)));
    public static final Supplier<Block> WARPED_NYLIUM_PATH = registerBlockWithDefaultItem("warped_nylium_path", () -> new NetherrackPathBlock(Properties.ofFullCopy(Blocks.NETHERRACK)));
    public static final Supplier<Block> CRIMSON_NYLIUM_CARPET = registerBlockWithDefaultItem("crimson_nylium_carpet", () -> new NetherCarpetBlock(Properties.ofFullCopy(Blocks.NETHERRACK).strength(0.1F).sound(SoundType.NYLIUM)));
    public static final Supplier<Block> WARPED_NYLIUM_CARPET = registerBlockWithDefaultItem("warped_nylium_carpet", () -> new NetherCarpetBlock(Properties.ofFullCopy(Blocks.NETHERRACK).strength(0.1F).sound(SoundType.NYLIUM)));
    public static final Supplier<Block> SOUL_SOIL_PATH = registerBlockWithDefaultItem("soul_soil_path", () -> new SoulSoilPathBlock(Properties.ofFullCopy(Blocks.SOUL_SOIL)));

    public static final Supplier<IETransparentBlock> QUARTZ_GLASS = registerBlockWithDefaultItem("quartz_glass", () -> new IETransparentBlock(Properties.ofFullCopy(Blocks.GLASS).strength(2.0F, 6.0F)));
    public static final Supplier<IronBarsBlock> QUARTZ_GLASS_PANE = registerBlockWithDefaultItem("quartz_glass_pane", () -> new IronBarsBlock(Properties.ofFullCopy(Blocks.GLASS)));

    public static final Supplier<IETransparentBlock> GLOW_GLASS = registerBlockWithDefaultItem("glow_glass", () -> new IETransparentBlock(Properties.ofFullCopy(Blocks.GLASS).lightLevel(state -> 10)));
    public static final Supplier<IronBarsBlock> GLOW_GLASS_PANE = registerBlockWithDefaultItem("glow_glass_pane", () -> new IronBarsBlock(Properties.ofFullCopy(Blocks.GLASS).lightLevel(state -> 10)));

    private static boolean isntSolid(BlockState state, BlockGetter reader, BlockPos pos) {
        return false;
    }

    private static Boolean neverAllowSpawn(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
        return (boolean) false;
    }

    public static BlockBehaviour.Properties getProperties(BlockBehaviour.Properties materialIn, float hardnessAndResistanceIn) {
        return getProperties(materialIn, hardnessAndResistanceIn, hardnessAndResistanceIn);
    }

    public static BlockBehaviour.Properties getProperties(BlockBehaviour.Properties materialIn, float hardnessIn, float resistanceIn) {
        return materialIn.strength(hardnessIn, resistanceIn);
    }

    public static BlockBehaviour.Properties getProperties(BlockBehaviour.Properties materialIn) {
        return materialIn.instabreak();
    }

    public static BlockBehaviour.Properties getProperties(Block block) {
        return BlockBehaviour.Properties.ofFullCopy(block);
    }

    private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        InfernalExpansion.LOGGER.info("Infernal Expansion: Blocks Registered!");
    }

    public static <T extends Block> DeferredHolder<Block, T> registerBlockWithDefaultItem(String name, Supplier<? extends T> blockSupplier) {
        DeferredHolder<Block, T> block = BLOCKS.register(name, blockSupplier);
        IEItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    public static <T extends Block> DeferredHolder<Block, T> registerBlockWithDefaultItemConditioned(String name, Supplier<? extends T> blockSupplier, String modID) {
        if (ModList.get().isLoaded(modID)) {
            DeferredHolder<Block, T> block = BLOCKS.register(name, blockSupplier);
            IEItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
            return block;
        } else {
            DeferredHolder<Block, T> block = BLOCKS.register(name, blockSupplier);
            IEItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
            return block;
        }
    }

    public static <T extends Block> DeferredHolder<Block, T> registerBlock(String name, Supplier<? extends T> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

}

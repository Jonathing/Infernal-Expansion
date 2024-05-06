package org.infernalstudios.infernalexp.util.legacy;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

// Replacement properties for the Material class
// TODO: Replace all usage in the IEBlocks class with these properties
@Deprecated(forRemoval = true)
public class Material {
    public static final BlockBehaviour.Properties AIR = BlockBehaviour.Properties.of().replaceable();
    public static final BlockBehaviour.Properties STRUCTURAL_AIR = BlockBehaviour.Properties.of().replaceable();
    public static final BlockBehaviour.Properties PORTAL = BlockBehaviour.Properties.of().pushReaction(PushReaction.BLOCK);
    public static final BlockBehaviour.Properties CLOTH_DECORATION = BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).ignitedByLava();
    public static final BlockBehaviour.Properties PLANT = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties WATER_PLANT = BlockBehaviour.Properties.of().mapColor(MapColor.WATER).instrument(NoteBlockInstrument.BASEDRUM);
    public static final BlockBehaviour.Properties REPLACEABLE_PLANT = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().ignitedByLava().pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties REPLACEABLE_FIREPROOF_PLANT = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties REPLACEABLE_WATER_PLANT = BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties WATER = BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().pushReaction(PushReaction.DESTROY).liquid();
    public static final BlockBehaviour.Properties BUBBLE_COLUMN = BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().pushReaction(PushReaction.DESTROY).liquid();
    public static final BlockBehaviour.Properties LAVA = BlockBehaviour.Properties.of().mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid();
    public static final BlockBehaviour.Properties TOP_SNOW = BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).replaceable().pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties FIRE = BlockBehaviour.Properties.of().mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties DECORATION = BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties WEB = BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties SCULK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK);
    public static final BlockBehaviour.Properties BUILDABLE_GRASS = BlockBehaviour.Properties.of();
    public static final BlockBehaviour.Properties CLAY = BlockBehaviour.Properties.of().mapColor(MapColor.CLAY);
    public static final BlockBehaviour.Properties DIRT = BlockBehaviour.Properties.of().mapColor(MapColor.DIRT);
    public static final BlockBehaviour.Properties GRASS = BlockBehaviour.Properties.of().mapColor(MapColor.GRASS);
    public static final BlockBehaviour.Properties ICE_SOLID = BlockBehaviour.Properties.of().mapColor(MapColor.ICE);
    public static final BlockBehaviour.Properties SAND = BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.SNARE);
    public static final BlockBehaviour.Properties SPONGE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW);
    public static final BlockBehaviour.Properties SHULKER_SHELL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE);
    public static final BlockBehaviour.Properties WOOD = BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().instrument(NoteBlockInstrument.BASS);
    public static final BlockBehaviour.Properties NETHER_WOOD = BlockBehaviour.Properties.of().mapColor(MapColor.WOOD);
    public static final BlockBehaviour.Properties BAMBOO_SAPLING = BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties BAMBOO = BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties WOOL = BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).ignitedByLava();
    public static final BlockBehaviour.Properties EXPLOSIVE = BlockBehaviour.Properties.of().mapColor(MapColor.FIRE).ignitedByLava();
    public static final BlockBehaviour.Properties LEAVES = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).ignitedByLava().pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties GLASS = BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.HAT);
    public static final BlockBehaviour.Properties ICE = BlockBehaviour.Properties.of().mapColor(MapColor.ICE);
    public static final BlockBehaviour.Properties CACTUS = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties STONE = BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM);
    public static final BlockBehaviour.Properties METAL = BlockBehaviour.Properties.of().mapColor(MapColor.METAL);
    public static final BlockBehaviour.Properties SNOW = BlockBehaviour.Properties.of().mapColor(MapColor.SNOW);
    public static final BlockBehaviour.Properties HEAVY_METAL = BlockBehaviour.Properties.of().mapColor(MapColor.METAL).pushReaction(PushReaction.BLOCK);
    public static final BlockBehaviour.Properties BARRIER = BlockBehaviour.Properties.of().pushReaction(PushReaction.BLOCK);
    public static final BlockBehaviour.Properties PISTON = BlockBehaviour.Properties.of().mapColor(MapColor.STONE).pushReaction(PushReaction.BLOCK);
    public static final BlockBehaviour.Properties MOSS = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties VEGETABLE = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties EGG = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties CAKE = BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties AMETHYST = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE);
    public static final BlockBehaviour.Properties POWDER_SNOW = BlockBehaviour.Properties.of().mapColor(MapColor.SNOW);
    public static final BlockBehaviour.Properties FROGSPAWN = BlockBehaviour.Properties.of().mapColor(MapColor.WATER).pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties FLOGLIGHT = BlockBehaviour.Properties.of();
    public static final BlockBehaviour.Properties DECORATED_POT = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).pushReaction(PushReaction.DESTROY);
}

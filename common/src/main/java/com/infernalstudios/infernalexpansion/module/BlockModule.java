package com.infernalstudios.infernalexpansion.module;

import com.infernalstudios.infernalexpansion.InfernalExpansionCommon;
import com.infernalstudios.infernalexpansion.registration.holders.BlockDataHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.HashMap;
import java.util.Map;

public class BlockModule {
    /** Map of all Block Resource Locations to their BlockDataHolders. */
    private static final Map<ResourceLocation, BlockDataHolder<?>> BLOCK_REGISTRY = new HashMap<>();

    public static final BlockDataHolder<?> SHIMMER_SAND = register("shimmer_sand", BlockDataHolder.of(() ->
       new Block(BlockBehaviour.Properties.copy(Blocks.SAND)))
            .withModel(BlockDataHolder.Model.ROTATABLE)
            .withItem()
            .withTranslation("Shimmer Sand")
    );

    public static BlockDataHolder<?> register(String name, BlockDataHolder<?> blockDataHolder) {
        ResourceLocation id = InfernalExpansionCommon.id(name);
        BLOCK_REGISTRY.put(id, blockDataHolder);
        return blockDataHolder;
    }

    public static Map<ResourceLocation, BlockDataHolder<?>> getBlockRegistry() {
        return BLOCK_REGISTRY;
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}

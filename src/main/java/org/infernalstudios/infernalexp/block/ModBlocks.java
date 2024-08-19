package org.infernalstudios.infernalexp.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.model.Model;
import net.minecraft.data.client.Models;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.setup.ModRegistry;

public class ModBlocks {
    public static void register() {
        InfernalExpansion.log("Registering Blocks for " + InfernalExpansion.MOD_ID);
    }


    public static final Block SHIMMER_SAND = ModRegistry.ofBlock("shimmer_sand",
            new SandBlock(0xffffaa, FabricBlockSettings.copyOf(Blocks.SAND)))
            .model().drop().tool("wood_shovel").build();

    public static final Block SHIMMER_SHEET = ModRegistry.ofBlock("shimmer_sheet",
            new SnowBlock(FabricBlockSettings.copyOf(Blocks.SAND)))
            .tool("wood_shovel").build();


    public static final Block LUMINOUS_FUNGUS = ModRegistry.ofBlock("luminous_fungus",
            new PlantBlock(FabricBlockSettings.copyOf(Blocks.WARPED_FUNGUS).luminance(10)))
            .drop().model(ModRegistry.Models.CROSS).cutout().build();
}

package org.infernalstudios.infernalexp.setup.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import org.infernalstudios.infernalexp.setup.ModRegistry;

import java.util.List;
import java.util.Map;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        for (Map.Entry<Block, ItemConvertible> entry : ModRegistry.BLOCK_DROPS.entrySet()) {
            if (ModRegistry.BLOCK_MODELS.getOrDefault(ModRegistry.Models.SLAB, List.of()).contains(entry.getKey()))
                addDrop(entry.getKey(), slabDrops(entry.getKey()));
            else if (ModRegistry.BLOCK_MODELS.getOrDefault(ModRegistry.Models.DOOR, List.of()).contains(entry.getKey()))
                addDrop(entry.getKey(), doorDrops(entry.getKey()));
            else
                addDrop(entry.getKey(), entry.getValue());
        }
    }
}

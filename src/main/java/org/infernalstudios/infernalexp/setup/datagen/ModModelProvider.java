package org.infernalstudios.infernalexp.setup.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.item.Item;
import org.infernalstudios.infernalexp.setup.ModRegistry;

import java.util.Map;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        BlockStateModelGenerator.BlockTexturePool pool;

        for (Block block : ModRegistry.getModelList(ModRegistry.Models.CUBE))
            generator.registerSimpleCubeAll(block);
        for (Block block : ModRegistry.getModelList(ModRegistry.Models.PILLAR))
            generator.registerLog(block).log(block);
        for (Block block : ModRegistry.getModelList(ModRegistry.Models.CROSS))
            generator.registerTintableCross(block, BlockStateModelGenerator.TintType.NOT_TINTED);
        for (Block block : ModRegistry.getModelList(ModRegistry.Models.DOOR))
            generator.registerDoor(block);
        for (Block block : ModRegistry.getModelList(ModRegistry.Models.TRAPDOOR))
            generator.registerTrapdoor(block);

        for (Block block : ModRegistry.BLOCK_SETS.keySet()) {
            pool = generator.registerCubeAllModelTexturePool(block);
            for (Map.Entry<ModRegistry.Models, Block> entry : ModRegistry.BLOCK_SETS.get(block).entrySet()) {
                if (entry.getKey() == ModRegistry.Models.STAIRS)
                    pool.stairs(entry.getValue());
                if (entry.getKey() == ModRegistry.Models.SLAB)
                    pool.slab(entry.getValue());
                if (entry.getKey() == ModRegistry.Models.BUTTON)
                    pool.button(entry.getValue());
                if (entry.getKey() == ModRegistry.Models.PRESSURE_PLATE)
                    pool.pressurePlate(entry.getValue());
                if (entry.getKey() == ModRegistry.Models.FENCE)
                    pool.fence(entry.getValue());
                if (entry.getKey() == ModRegistry.Models.FENCE_GATE)
                    pool.fenceGate(entry.getValue());
            }
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        for (Map.Entry<Item, Model> entry : ModRegistry.ITEM_MODELS.entrySet())
            generator.register(entry.getKey(), entry.getValue());
    }
}

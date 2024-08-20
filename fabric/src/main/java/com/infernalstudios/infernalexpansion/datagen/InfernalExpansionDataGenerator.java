package com.infernalstudios.infernalexpansion.datagen;

import com.infernalstudios.infernalexpansion.module.BlockModule;
import com.infernalstudios.infernalexpansion.module.ItemModule;
import com.infernalstudios.infernalexpansion.registration.BlockDataHolder;
import com.infernalstudios.infernalexpansion.registration.ItemDataHolder;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;

import java.util.concurrent.CompletableFuture;

public class InfernalExpansionDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(IEBlockTagProvider::new);
        pack.addProvider(IEItemTagProvider::new);
        pack.addProvider(IEBlockLootTableProvider::new);
        pack.addProvider(IEBlockModelProvider::new);
    }

    private static class IEBlockTagProvider extends FabricTagProvider.BlockTagProvider {

        public IEBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {

        }
    }

    private static class IEItemTagProvider extends FabricTagProvider.ItemTagProvider {
        public IEItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {

        }
    }

    private static class IEBlockLootTableProvider extends FabricBlockLootTableProvider {

        protected IEBlockLootTableProvider(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generate() {
            for (BlockDataHolder<?> blockDataHolder : BlockModule.getBlockRegistry().values()) {
                if (blockDataHolder.hasModel()) {
                    switch (blockDataHolder.getModel()) {
                        case SLAB -> {
                            add(blockDataHolder.get(), createSlabItemTable(blockDataHolder.get()));
                            continue;
                        }
                        case DOOR -> {
                            add(blockDataHolder.get(), createDoorTable(blockDataHolder.get()));
                            continue;
                        }
                    }
                }

                add(blockDataHolder.get(), createSingleItemTable(blockDataHolder.get()));
            }
        }
    }

    private static class IEBlockModelProvider extends FabricModelProvider {

        public IEBlockModelProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockModelGenerators generator) {
            for (BlockDataHolder<?> blockDataHolder : BlockModule.getBlockRegistry().values()) {
                if (blockDataHolder.hasModel()) {
                    switch (blockDataHolder.getModel()) {
                        case CUBE -> generator.createTrivialCube(blockDataHolder.get());
                        case PILLAR -> generator.woodProvider(blockDataHolder.get());
                        case CROSS -> generator.createCrossBlockWithDefaultItem(blockDataHolder.get(), BlockModelGenerators.TintState.NOT_TINTED);
                        case DOOR -> generator.createDoor(blockDataHolder.get());
                        case TRAPDOOR -> generator.createTrapdoor(blockDataHolder.get());
                    }
                }

            }
        }

        @Override
        public void generateItemModels(ItemModelGenerators generator) {
            for (ItemDataHolder<?> itemDataHolder : ItemModule.getItemRegistry().values()) {
                if (itemDataHolder.hasModel()) {
                    generator.generateFlatItem(itemDataHolder.get(), itemDataHolder.getModel());
                }
            }
        }
    }
}

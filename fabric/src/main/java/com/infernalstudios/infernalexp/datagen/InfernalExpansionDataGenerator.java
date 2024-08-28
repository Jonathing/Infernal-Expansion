package com.infernalstudios.infernalexp.datagen;

import com.infernalstudios.infernalexp.IECommon;
import com.infernalstudios.infernalexp.module.BlockModule;
import com.infernalstudios.infernalexp.module.CreativeTabModule;
import com.infernalstudios.infernalexp.module.ItemModule;
import com.infernalstudios.infernalexp.registration.holders.BlockDataHolder;
import com.infernalstudios.infernalexp.registration.holders.ItemDataHolder;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.LootNumberProviderType;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class InfernalExpansionDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(IEBlockTagProvider::new);
        pack.addProvider(IEItemTagProvider::new);
        pack.addProvider(IEBlockLootTableProvider::new);
        pack.addProvider(IEModelProvider::new);
        pack.addProvider(IELangProvider::new);
        pack.addProvider(IERecipeProvider::new);
    }

    private static class IERecipeProvider extends FabricRecipeProvider {
        public IERecipeProvider(FabricDataOutput output) {
            super(output);
        }

        public static void tilesRecipe(Consumer<FinishedRecipe> exporter, ItemLike result,
                                        ItemLike a, ItemLike b, String name) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result)
                    .pattern("AB")
                    .pattern("BA")
                    .define('A', a)
                    .define('B', b)
                    .unlockedBy(getHasName(a), has(a))
                    .unlockedBy(getHasName(b), has(b))
                    .group(name).save(exporter, IECommon.id(name));
        }

        @Override
        public void buildRecipes(Consumer<FinishedRecipe> exporter) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockModule.SHIMMER_SHEET.get(), 6)
                    .pattern("SSS")
                    .define('S', BlockModule.SHIMMER_SAND.get())
                    .unlockedBy(getHasName(BlockModule.SHIMMER_SAND.get()), has(BlockModule.SHIMMER_SAND.get()))
                    .group("shimmer_sheet").save(exporter, IECommon.id("shimmer_sheet"));

            twoByTwoPacker(exporter, RecipeCategory.BUILDING_BLOCKS, ItemModule.DULLROCKS.get(), BlockModule.DULLSTONE.get());

            tilesRecipe(exporter, BlockModule.DIMSTONE.get(), ItemModule.DULLROCKS.get(), Items.GLOWSTONE_DUST, "dimstone");
        }
    }

    private static class IELangProvider extends FabricLanguageProvider {
        protected IELangProvider(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generateTranslations(TranslationBuilder builder) {
            // Put manually added entries here
            builder.add(CreativeTabModule.INFERNAL_EXPANSION_TAB.getResourceKey(), "Infernal Expansion");

            // This handles all supplied block and item entries automatically
            for (BlockDataHolder<?> blockDataHolder : BlockModule.getBlockRegistry().values()) {
                if (blockDataHolder.hasTranslation()) {
                    builder.add(blockDataHolder.get(), blockDataHolder.getTranslation());
                }

                if (blockDataHolder.isGlass()) {
                    builder.add(blockDataHolder.getPaneBlock().get(), blockDataHolder.getTranslation() + " Pane");
                }

                for (Map.Entry<BlockDataHolder.Model, BlockDataHolder<?>> blocksetEntry : blockDataHolder.getBlocksets().entrySet()) {
                    if (blockDataHolder.hasTranslation()) {
                        builder.add(blocksetEntry.getValue().get(), blockDataHolder.getTranslation() + " " + blocksetEntry.getKey().getLang());
                    }
                }
            }

            for (ItemDataHolder<?> itemDataHolder : ItemModule.getItemRegistry().values()) {
                if (itemDataHolder.hasTranslation()) {
                    builder.add(itemDataHolder.get(), itemDataHolder.getTranslation());
                }
            }
        }
    }

    private static class IEBlockTagProvider extends FabricTagProvider.BlockTagProvider {
        public IEBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            for (Map.Entry<TagKey<Block>, List<BlockDataHolder<?>>> entry : BlockDataHolder.getBlockTags().entrySet()) {
                FabricTagProvider<Block>.FabricTagBuilder tagBuilder = getOrCreateTagBuilder(entry.getKey());

                entry.getValue().forEach(b -> tagBuilder.add(b.get()));
            }
        }
    }

    private static class IEItemTagProvider extends FabricTagProvider.ItemTagProvider {
        public IEItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            for (Map.Entry<TagKey<Item>, List<ItemDataHolder<?>>> entry : ItemDataHolder.getItemTags().entrySet()) {
                FabricTagProvider<Item>.FabricTagBuilder tagBuilder = getOrCreateTagBuilder(entry.getKey());

                entry.getValue().forEach(b -> tagBuilder.add(b.get()));
            }
        }
    }

    private static class IEBlockLootTableProvider extends FabricBlockLootTableProvider {
        protected IEBlockLootTableProvider(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generate() {
            for (BlockDataHolder<?> blockDataHolder : BlockModule.getBlockRegistry().values()) {
                for (BlockDataHolder<?> blocksetHolder : blockDataHolder.getBlocksets().values()) {
                    if (blocksetHolder.hasModel()) {
                        if (Objects.requireNonNull(blockDataHolder.getModel()) == BlockDataHolder.Model.SLAB) {
                            add(blockDataHolder.get(), createSlabItemTable(blockDataHolder.get()));
                        }
                    }
                }

                if (blockDataHolder.isGlass()) {
                    add(blockDataHolder.get(), createSilkTouchOnlyTable(blockDataHolder.get()));
                    add(blockDataHolder.getPaneBlock().get(), createSilkTouchOnlyTable(blockDataHolder.getPaneBlock().get()));
                } else if (blockDataHolder.hasModel()) {
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

                if (blockDataHolder.hasDrop()) {
                    if (blockDataHolder.getDropCount() == null)
                        add(blockDataHolder.get(), createSilkTouchOnlyTable(blockDataHolder.getDrop().get()));
                    else
                        add(blockDataHolder.get(), createSingleItemTable(blockDataHolder.getDrop().get(), blockDataHolder.getDropCount()));
                }

                for (Map.Entry<BlockDataHolder.Model, BlockDataHolder<?>> entry : blockDataHolder.getBlocksets().entrySet()) {
                    switch (entry.getKey()) {
                        case SLAB -> {
                            add(entry.getValue().get(), createSlabItemTable(entry.getValue().get()));
                        }
                        case DOOR -> {
                            add(entry.getValue().get(), createDoorTable(entry.getValue().get()));
                        }
                        default -> {
                            add(entry.getValue().get(), createSingleItemTable(entry.getValue().get()));
                        }
                    }
                }
            }
        }
    }

    private static class IEModelProvider extends FabricModelProvider {
        public IEModelProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockModelGenerators generator) {
            for (BlockDataHolder<?> blockDataHolder : BlockModule.getBlockRegistry().values()) {
                if (blockDataHolder.getBlocksets().isEmpty()) {

                    if (blockDataHolder.isGlass()) {
                        generator.createGlassBlocks(blockDataHolder.get(), blockDataHolder.getPaneBlock().get());
                    } else if (blockDataHolder.hasModel()) {
                        switch (blockDataHolder.getModel()) {
                            case CUBE -> generator.createTrivialCube(blockDataHolder.get());
                            case PILLAR -> {
                                var pillar = generator.woodProvider(blockDataHolder.get());
                                pillar.log(blockDataHolder.get());
                            }
                            case ROTATABLE -> generator.createRotatedVariantBlock(blockDataHolder.get());
                            case CROSS -> generator.createCrossBlockWithDefaultItem(blockDataHolder.get(), BlockModelGenerators.TintState.NOT_TINTED);
                            case DOOR -> generator.createDoor(blockDataHolder.get());
                            case TRAPDOOR -> generator.createTrapdoor(blockDataHolder.get());
                        }
                    }
                } else {
                    BlockModelGenerators.BlockFamilyProvider familyProvider = generator.family(blockDataHolder.get());
                    for (Map.Entry<BlockDataHolder.Model, BlockDataHolder<?>> entry : blockDataHolder.getBlocksets().entrySet()) {
                        switch (entry.getKey()) {
                            case STAIRS -> familyProvider.stairs(entry.getValue().get());
                            case SLAB -> familyProvider.slab(entry.getValue().get());
                            case PRESSURE_PLATE -> familyProvider.pressurePlate(entry.getValue().get());
                            case BUTTON -> familyProvider.button(entry.getValue().get());
                            case FENCE -> familyProvider.fence(entry.getValue().get());
                            case FENCE_GATE -> familyProvider.fenceGate(entry.getValue().get());
                        }
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

package com.infernalstudios.infernalexp.datagen;

import com.infernalstudios.infernalexp.IECommon;
import com.infernalstudios.infernalexp.module.ModBlocks;
import com.infernalstudios.infernalexp.module.ModCreativeTabs;
import com.infernalstudios.infernalexp.module.ModItems;
import com.infernalstudios.infernalexp.module.ModTags;
import com.infernalstudios.infernalexp.registration.holders.BlockDataHolder;
import com.infernalstudios.infernalexp.registration.holders.ItemDataHolder;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

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

        private static String getName(ItemLike item) {
            return BuiltInRegistries.ITEM.getKey(item.asItem()).getPath();
        }

        private static void offerTilesRecipe(Consumer<FinishedRecipe> exporter, ItemLike result, int count,
                                            ItemLike a, ItemLike b) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, count)
                    .pattern("AB")
                    .pattern("BA")
                    .define('A', a)
                    .define('B', b)
                    .unlockedBy(getHasName(a), has(a))
                    .unlockedBy(getHasName(b), has(b))
                    .group(getName(result))
                    .save(exporter, IECommon.id(getName(result)));
        }

        private static void offer2x2Recipe(Consumer<FinishedRecipe> exporter, ItemLike to, int count,
                                            ItemLike from) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, to, count)
                    .pattern("##")
                    .pattern("##")
                    .define('#', from)
                    .unlockedBy(getHasName(from), has(from))
                    .group(getName(to))
                    .save(exporter, IECommon.id(getName(to)));
        }

        private static void offer3x3Recipe(Consumer<FinishedRecipe> exporter, ItemLike to, int count,
                                           ItemLike from) {
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, to, count)
                    .requires(from, 9)
                    .unlockedBy(getHasName(from), has(from))
                    .group(getName(to))
                    .save(exporter, IECommon.id(getName(to)));
        }

        private static void offerUnpackRecipe(Consumer<FinishedRecipe> exporter, ItemLike to, ItemLike from) {
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, to, 9)
                    .requires(from)
                    .unlockedBy(getHasName(from), has(from))
                    .group(getName(to))
                    .save(exporter, IECommon.id(getName(to)));
        }

        @Override
        public void buildRecipes(Consumer<FinishedRecipe> exporter) {
            for (BlockDataHolder<?> block : ModBlocks.getBlockRegistry().values()) {
                if (block.getStairs() != null) {
                    stairBuilder(block.getStairs().get(), Ingredient.of(block.get()))
                            .group(getName(block.getStairs().get()))
                            .unlockedBy(getHasName(block.get()), has(block.get()))
                            .save(exporter, IECommon.id(getName(block.getStairs().get())));
                }
                if (block.getSlab() != null) {
                    slabBuilder(RecipeCategory.BUILDING_BLOCKS, block.getSlab().get(), Ingredient.of(block.get()))
                            .group(getName(block.getSlab().get()))
                            .unlockedBy(getHasName(block.get()), has(block.get()))
                            .save(exporter, IECommon.id(getName(block.getSlab().get())));
                }
                if (block.getPaneBlock() != null) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block.getPaneBlock().get(), 16)
                            .pattern("###")
                            .pattern("###")
                            .define('#', block.get())
                            .group(getName(block.getPaneBlock().get()))
                            .unlockedBy("has_glass", has(block.get()))
                            .save(exporter, IECommon.id(getName(block.getPaneBlock().get())));
                }
            }


            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHIMMER_SHEET.get(), 6)
                    .pattern("###")
                    .define('#', ModBlocks.SHIMMER_SAND.get())
                    .unlockedBy(getHasName(ModBlocks.SHIMMER_SAND.get()), has(ModBlocks.SHIMMER_SAND.get()))
                    .group("shimmer_sheet").save(exporter, IECommon.id("shimmer_sheet"));

            offer2x2Recipe(exporter, ModBlocks.SHIMMER_STONE_BRICKS.get(), 4, ModBlocks.SHIMMER_SAND.get());

            buttonBuilder(ModBlocks.DULLSTONE_BUTTON.get(), Ingredient.of(ModBlocks.POLISHED_DULLSTONE.get()))
                    .group(getName(ModBlocks.DULLSTONE_BUTTON.get()))
                    .unlockedBy(getHasName(ModBlocks.POLISHED_DULLSTONE.get()), has(ModBlocks.POLISHED_DULLSTONE.get()))
                    .save(exporter, IECommon.id(getName(ModBlocks.DULLSTONE_BUTTON.get())));
            pressurePlateBuilder(RecipeCategory.REDSTONE, ModBlocks.DULLSTONE_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.POLISHED_DULLSTONE.get()))
                    .group(getName(ModBlocks.DULLSTONE_PRESSURE_PLATE.get()))
                    .unlockedBy(getHasName(ModBlocks.POLISHED_DULLSTONE.get()), has(ModBlocks.POLISHED_DULLSTONE.get()))
                    .save(exporter, IECommon.id(getName(ModBlocks.DULLSTONE_PRESSURE_PLATE.get())));

            offer2x2Recipe(exporter, ModBlocks.POLISHED_GLOWSTONE.get(), 4, Blocks.GLOWSTONE);

            offerTilesRecipe(exporter, ModBlocks.DIMSTONE.get(), 1, ModItems.DULLROCKS.get(), Items.GLOWSTONE_DUST);
            offer2x2Recipe(exporter, ModBlocks.POLISHED_DIMSTONE.get(), 4, ModBlocks.DIMSTONE.get());

            offer2x2Recipe(exporter, ModBlocks.DULLSTONE.get(), 1, ModItems.DULLROCKS.get());
            offer2x2Recipe(exporter, ModBlocks.POLISHED_DULLSTONE.get(), 4, ModBlocks.DULLSTONE.get());

            offer2x2Recipe(exporter, ModBlocks.DULLTHORNS_BLOCK.get(), 1, ModBlocks.DULLTHORNS.get());

            offer3x3Recipe(exporter, ModBlocks.LUMINOUS_FUNGUS_CAP.get(), 1, ModBlocks.LUMINOUS_FUNGUS.get());
            offerUnpackRecipe(exporter, ModBlocks.LUMINOUS_FUNGUS.get(), ModBlocks.LUMINOUS_FUNGUS_CAP.get());
            offer3x3Recipe(exporter, ModBlocks.CRIMSON_FUNGUS_CAP.get(), 1, Items.CRIMSON_FUNGUS);
            offerUnpackRecipe(exporter, Items.CRIMSON_FUNGUS, ModBlocks.CRIMSON_FUNGUS_CAP.get());
            offer3x3Recipe(exporter, ModBlocks.WARPED_FUNGUS_CAP.get(), 1, Items.WARPED_FUNGUS);
            offerUnpackRecipe(exporter, Items.WARPED_FUNGUS, ModBlocks.WARPED_FUNGUS_CAP.get());

            oreSmelting(exporter, List.of(ModBlocks.BASALT_IRON_ORE.get()), RecipeCategory.MISC, Items.IRON_ORE,
                    5, 200, "basalt_iron_ore");
            oreBlasting(exporter, List.of(ModBlocks.BASALT_IRON_ORE.get()), RecipeCategory.MISC, Items.IRON_ORE,
                    5, 100, "basalt_iron_ore");
        }
    }

    private static class IELangProvider extends FabricLanguageProvider {
        protected IELangProvider(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generateTranslations(TranslationBuilder builder) {
            // Put manually added entries here
            builder.add(ModCreativeTabs.INFERNAL_EXPANSION_TAB.getResourceKey(), "Infernal Expansion");

            // This handles all supplied block and item entries automatically
            for (BlockDataHolder<?> blockDataHolder : ModBlocks.getBlockRegistry().values()) {
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

            for (ItemDataHolder<?> itemDataHolder : ModItems.getItemRegistry().values()) {
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

            getOrCreateTagBuilder(ModTags.Blocks.SHROOMLIGHT_TEARS_GROWABLE)
                    .add(Blocks.SHROOMLIGHT);

            getOrCreateTagBuilder(ModTags.Blocks.GLOW_FIRE_BASE_BLOCKS)
                    .add(Blocks.GLOWSTONE);
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
            for (BlockDataHolder<?> blockDataHolder : ModBlocks.getBlockRegistry().values()) {
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
            for (BlockDataHolder<?> blockDataHolder : ModBlocks.getBlockRegistry().values()) {
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
            for (ItemDataHolder<?> itemDataHolder : ModItems.getItemRegistry().values()) {
                if (itemDataHolder.hasModel()) {
                    generator.generateFlatItem(itemDataHolder.get(), itemDataHolder.getModel());
                }
            }
        }
    }
}

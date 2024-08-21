package com.infernalstudios.infernalexpansion.registration.holders;

import com.infernalstudios.infernalexpansion.registration.FlammabilityRegistry;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BlockDataHolder<T extends Block> {
    private static final Map<TagKey<Block>, List<BlockDataHolder<?>>> BLOCK_TAGS = new HashMap<>();

    private T cachedEntry;
    private final Supplier<T> entrySupplier;

    private ItemDataHolder<? extends Item> blockItem;
    private Model model;
    private String defaultTranslation;
    private final Map<Block, FlammabilityRegistry.Entry> FLAMMABILITIES = new HashMap<>();
    private Block strippingResult;
    private int fuelDuration;

    public BlockDataHolder(Supplier<T> entrySupplier) {
        this.entrySupplier = entrySupplier;
    }

    public static BlockDataHolder<? extends Block> of(Supplier<?> blockSupplier) {
        return new BlockDataHolder(blockSupplier);
    }

    /**
     * Retrieves the cached entry if it exists, otherwise calls the supplier to create a new entry.
     * @return The cached entry, or a new entry if the cached entry does not exist.
     */
    public T get() {
        if (this.cachedEntry != null) return cachedEntry;

        T entry = entrySupplier.get();
        this.cachedEntry = entry;

        return entry;
    }

    public BlockDataHolder<? extends Block> withItem() {
        this.blockItem = ItemDataHolder.of(() -> new BlockItem(this.get(), new Item.Properties())).withModel(ModelTemplates.FLAT_ITEM);
        return this;
    }

    public BlockDataHolder<?> withStripping(Block stripResult) {
        this.strippingResult = stripResult;
        return this;
    }

    public boolean hasStrippingResult() {
        return this.strippingResult != null;
    }

    public Block getStrippingResult() {
        return this.strippingResult;
    }

    public BlockDataHolder<?> withFlammableDefault(FlammabilityRegistry.Entry flammabilityEntry) {
        return this.withFlammable(Blocks.FIRE, flammabilityEntry);
    }

    public BlockDataHolder<?> withFlammable(Block fireBlock, FlammabilityRegistry.Entry flammabilityEntry) {
        this.FLAMMABILITIES.put(fireBlock, flammabilityEntry);
        return this;
    }

    public Map<Block, FlammabilityRegistry.Entry> getFlammabilities() {
        return this.FLAMMABILITIES;
    }

    /**
     * Registers the block item as a fuel source. Does nothing if the block has no item.
     */
    public BlockDataHolder<?> withFuel(int fuelDuration) {
        this.fuelDuration = fuelDuration;
        return this;
    }

    public boolean isFuel() {
        return this.fuelDuration > 0;
    }

    public int getFuelDuration() {
        return this.fuelDuration;
    }

    @SafeVarargs
    public final BlockDataHolder<?> withTags(TagKey<Block>... tags) {
        for (TagKey<Block> tag : tags) {
            BLOCK_TAGS.putIfAbsent(tag, new ArrayList<>());
            BLOCK_TAGS.get(tag).add(this);
        }

        return this;
    }

    public static Map<TagKey<Block>, List<BlockDataHolder<?>>> getBlockTags() {
        return BLOCK_TAGS;
    }

    public boolean hasItem() {
        return this.blockItem != null;
    }

    public ItemDataHolder<?> getBlockItem() {
        return this.blockItem;
    }

    public BlockDataHolder<?> withModel(Model model) {
        this.model = model;
        return this;
    }

    public boolean hasModel() {
        return this.model != null;
    }

    public Model getModel() {
        return this.model;
    }

    public BlockDataHolder<?> withTranslation(String translation) {
        this.defaultTranslation = translation;
        return this;
    }

    public boolean hasTranslation() {
        return this.defaultTranslation != null;
    }

    public String getTranslation() {
        return this.defaultTranslation;
    }

    public enum Model {
        CUBE,
        PILLAR,
        CROSS,
        DOOR,
        TRAPDOOR,
        STAIRS,
        SLAB,
        BUTTON,
        PRESSURE_PLATE,
        FENCE,
        FENCE_GATE;
    }
}
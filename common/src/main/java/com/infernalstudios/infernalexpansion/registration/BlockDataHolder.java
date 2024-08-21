package com.infernalstudios.infernalexpansion.registration;

import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class BlockDataHolder<T extends Block> {
    private T cachedEntry;
    private final Supplier<T> entrySupplier;

    private ItemDataHolder<? extends Item> blockItem;
    private Model model;
    private String defaultTranslation;

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
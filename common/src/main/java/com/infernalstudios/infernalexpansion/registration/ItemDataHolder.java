package com.infernalstudios.infernalexpansion.registration;

import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ItemDataHolder<T extends Item> {
    private T cachedEntry;
    private final Supplier<T> entrySupplier;

    private ModelTemplate model;
    private String defaultTranslation;

    public ItemDataHolder(Supplier<T> entrySupplier) {
        this.entrySupplier = entrySupplier;
    }

    public static ItemDataHolder<? extends Item> of(Supplier<? extends Item> itemSupplier) {
        return new ItemDataHolder(itemSupplier);
    }

    public ItemDataHolder<?> withModel(ModelTemplate model) {
        this.model = model;
        return this;
    }

    public boolean hasModel() {
        return this.model != null;
    }

    public ModelTemplate getModel() {
        return this.model;
    }

    public ItemDataHolder<?> withTranslation(String translation) {
        this.defaultTranslation = translation;
        return this;
    }

    public boolean hasTranslation() {
        return this.defaultTranslation != null;
    }

    public String getTranslation() {
        return this.defaultTranslation;
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
}
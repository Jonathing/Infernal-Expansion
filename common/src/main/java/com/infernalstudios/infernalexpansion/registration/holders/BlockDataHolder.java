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
    private static final List<BlockDataHolder<?>> CUTOUT_BLOCKS = new ArrayList<>();

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

    /**
     * Creates a default BlockItem for this block
     */
    public BlockDataHolder<? extends Block> withItem() {
        this.blockItem = ItemDataHolder.of(() -> new BlockItem(this.get(), new Item.Properties())).withModel(ModelTemplates.FLAT_ITEM);
        return this;
    }

    /**
     * Adds the ability to strip this block with an axe
     * @param stripResult the block to set it to when it gets stripped
     */
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

    /**
     * Registers flammability with Vanilla Fire
     * @param flammabilityEntry an Entry of the ignite and spread chances
     */
    public BlockDataHolder<?> withFlammableDefault(FlammabilityRegistry.Entry flammabilityEntry) {
        return this.withFlammable(Blocks.FIRE, flammabilityEntry);
    }


    /**
     * Registers flammability with the supplied fire block
     * @param fireBlock must extend FireBlock
     * @param flammabilityEntry an Entry of the ignite and spread chances
     */
    public BlockDataHolder<?> withFlammable(Block fireBlock, FlammabilityRegistry.Entry flammabilityEntry) {
        this.FLAMMABILITIES.put(fireBlock, flammabilityEntry);
        return this;
    }

    public Map<Block, FlammabilityRegistry.Entry> getFlammabilities() {
        return this.FLAMMABILITIES;
    }

    /**
     * Registers the block item as a fuel source. Does nothing if the block has no item.
     * @param fuelDuration the length in ticks this fuel source burns
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

    /**
     * Registers this block to the supplied tags
     * @param tags the tag keys to register the block to
     */
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

    /**
     * The model type of this block for datagen
     */
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

    /**
     * Sets the rendertype of this block to cutout, allowing for transparency
     */
    public BlockDataHolder<?> cutout() {
        CUTOUT_BLOCKS.add(this);
        return this;
    }

    public static List<BlockDataHolder<?>> getCutoutBlocks() {
        return CUTOUT_BLOCKS;
    }

    /**
     * Sets the default EN_US translation for this block
     * @param translation the name for this block
     */
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
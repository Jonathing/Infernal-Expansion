package org.infernalstudios.infernalexp.setup;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.data.client.Model;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import org.infernalstudios.infernalexp.InfernalExpansion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModRegistry {
    /**
     * This class is responsible for everything registry wise for blocks and items, handling the datagen along the way
     * To register custom datagen behavior, go to the dedicated Provider in setup.datagen and add it there
     *
     * For examples on how to use this helper class, take a look at End's Phantasm's source, all blocks and items are registered this way
     * https://github.com/Lyof429/EndsPhantasm
     */

    /**
     * Use this method to register a new block. It returns a BlockBuilder for chaining, so don't forget to call .build() at the end
     * It auto generates a BlockItem to go with it, so call it with false as a third argument to prevent that
     */
    public static BlockBuilder ofBlock(String id, Block block) {
        return ModRegistry.ofBlock(id, block, true);
    }

    public static BlockBuilder ofBlock(String id, Block block, boolean item) {
        return new BlockBuilder(InfernalExpansion.makeID(id), block, item);
    }

    /**
     * Similarly, this method should be used to register a new item
     */
    public static ItemBuilder ofItem(String id, Item item) {
        return new ItemBuilder(InfernalExpansion.makeID(id), item);
    }


    public static class BlockBuilder {
        /** Use ofBlock() instead */
        protected BlockBuilder(Identifier id, Block block, boolean item) {
            this.id = id;
            this.block = Registry.register(Registries.BLOCK, id, block);
            if (item)
                ModRegistry.ofItem(id.getPath(), new BlockItem(block, new FabricItemSettings())).build();
        }

        /** Call a the end of a block registry chain to validate and get the block itself */
        public Block build() {
            BLOCKS.add(this.block);
            return this.block;
        }

        protected Identifier id;
        protected Block block;

        /** Adds the block to the list of self dropping ones. Slabs and Doors should use that too, they handle the extra
         *  behavior themselves */
        public BlockBuilder drop() {
            return this.drop(this.block);
        }

        /** Adds a drop to the block that is not itself. This will make the block *always* drop this, silk touch behaviors
         * should be registered in ModLootTableProvider */
        public BlockBuilder drop(ItemConvertible loot) {
            BLOCK_DROPS.putIfAbsent(this.block, loot);
            return this;
        }

        /** Tags the block with the given TagKey */
        public BlockBuilder tag(TagKey<Block> tagname) {
            BLOCK_TAGS.putIfAbsent(tagname, new ArrayList<>());
            BLOCK_TAGS.get(tagname).add(this.block);
            return this;
        }

        /** Tags the blocks with the given TagKeys. Use it instead of the above for several tags at once */
        @SafeVarargs
        public final BlockBuilder tag(TagKey<Block>... tags) {
            for (TagKey<Block> tagname : tags) {
                this.tag(tagname);
            }
            return this;
        }

        /** Tags the corresponding block item with the given TagKey */
        public BlockBuilder tagitem(TagKey<Item> tagname) {
            ITEM_TAGS.putIfAbsent(tagname, new ArrayList<>());
            ITEM_TAGS.get(tagname).add(this.block.asItem());
            return this;
        }

        /** Tags the corresponding block item with the given TagKeys. Use it instead of the above for several tags at once */
        @SafeVarargs
        public final BlockBuilder tagitem(TagKey<Item>... tags) {
            for (TagKey<Item> tagname : tags) {
                this.tagitem(tagname);
            }
            return this;
        }

        /** Sets tool needed to mine this block
         *  Tool should be of the form "material_tooltype". For example: "diamond_pickaxe" or "stone_axe"
         *  For blocks needing a tool but no specific material (or vice versa) don't put anything on one side of the _
         *  ("_pickaxe" for any pickaxe or "stone_" for anything of stone tier) */
        public BlockBuilder tool(String tool_material) {
            String[] needed = tool_material.split("_");

            if (needed[0].equals("stone")) this.tag(BlockTags.NEEDS_STONE_TOOL);
            if (needed[0].equals("iron")) this.tag(BlockTags.NEEDS_IRON_TOOL);
            if (needed[0].equals("diamond")) this.tag(BlockTags.NEEDS_DIAMOND_TOOL);

            if (needed[1].equals("pickaxe")) this.tag(BlockTags.PICKAXE_MINEABLE);
            if (needed[1].equals("axe")) this.tag(BlockTags.AXE_MINEABLE);
            if (needed[1].equals("shovel")) this.tag(BlockTags.SHOVEL_MINEABLE);
            if (needed[1].equals("hoe")) this.tag(BlockTags.HOE_MINEABLE);
            if (needed[1].equals("sword")) this.tag(BlockTags.SWORD_EFFICIENT);

            return this;
        }

        /** Sets the block to have a default cube model */
        public BlockBuilder model() {
            return this.model(Models.CUBE);
        }

        /** Sets model for the block. Use one of the Models enum below
         *  For custom models, either put the files by hand or add the option and create it in ModModelProvider */
        public BlockBuilder model(Models model) {
            BLOCK_MODELS.putIfAbsent(model, new ArrayList<>());
            BLOCK_MODELS.get(model).add(this.block);
            return this;
        }

        /** Sets item model for the corresponding block item. model is of the vanilla class.
         *  If nothing is set, it will default to the base block's model.
         *  (for example, Model.GENERATED) */
        public BlockBuilder model(Model model) {
            ITEM_MODELS.put(this.block.asItem(), model);
            return this;
        }

        /** Makes the block have cutout rendering */
        public BlockBuilder cutout() {
            BLOCK_CUTOUT.add(this.block);
            return this;
        }

        /** Makes the associated block item be usable as fuel */
        public BlockBuilder fuel(int duration) {
            ITEM_BURNABLE.put(this.block, duration);
            return this;
        }

        /** Makes the block be able to burn and catch fire from fire or lava */
        public BlockBuilder flammable(int duration, int spread) {
            BLOCK_FLAMMABLE.put(this.block, new Pair<>(duration, spread));
            return this;
        }

        /** Makes the block strippable into the given one */
        public BlockBuilder strip(Block stripped) {
            BLOCK_STRIPPED.putIfAbsent(this.block, stripped);
            return this;
        }
    }


    public static class ItemBuilder {
        /** Use ofItem() instead */
        protected ItemBuilder(Identifier id, Item item) {
            this.id = id;
            this.item = Registry.register(Registries.ITEM, id, item);
        }

        /** Call a the end of an item registry chain to validate and get the item itself */
        public Item build() {
            ITEMS.add(this.item);
            return this.item;
        }

        protected Identifier id;
        protected Item item;

        /** Tags the item with the given TagKey */
        public ItemBuilder tag(TagKey<Item> tagname) {
            ITEM_TAGS.putIfAbsent(tagname, new ArrayList<>());
            ITEM_TAGS.get(tagname).add(this.item);
            return this;
        }

        /** Tags the item with the given TagKeys. Use it instead of the above for several tags at once */
        @SafeVarargs
        public final ItemBuilder tag(TagKey<Item>... tags) {
            for (TagKey<Item> tagname : tags) {
                this.tag(tagname);
            }
            return this;
        }

        /** Sets item model for the item. model is of the vanilla class.
         *  (for example, Model.GENERATED) */
        public ItemBuilder model(Model model) {
            ITEM_MODELS.put(this.item, model);
            return this;
        }

        /** Makes the item be usable as fuel */
        public ItemBuilder fuel(int duration) {
            ITEM_BURNABLE.put(this.item, duration);
            return this;
        }
    }


    public enum Models {
        CUBE,
        ROTATABLE,
        CROSS,
        PILLAR,
        STAIRS,
        SLAB,
        BUTTON,
        PRESSURE_PLATE,
        FENCE,
        FENCE_GATE,
        DOOR,
        TRAPDOOR,
        PANE
    }

    public static class Foods {
    }


    public static List<Block> getModelList(Models key) {
        return BLOCK_MODELS.getOrDefault(key, new ArrayList<>());
    }

    /** Registers stairs and slab for the given parent block. Call it in ModBlocks.register() (and so once the blocks are already registered) */
    public static void registerStairsAndSlab(Block parent, Block stairs, Block slab) {
        registerSet(parent, Map.of(
                Models.STAIRS, stairs,
                Models.SLAB, slab
        ));
    }

    /** Registers a glass pane for the given parent block. Works the same as the above */
    public static void registerGlass(Block parent, Block pane) {
        registerSet(parent, Map.of(
                Models.PANE, pane
        ));
    }

    public static void registerSet(Block parent, Map<Models, Block> set) {
        BLOCK_SETS.putIfAbsent(parent, set);
        for (Models model : set.keySet()) {
            BLOCK_MODELS.putIfAbsent(model, new ArrayList<>());
            BLOCK_MODELS.get(model).add(set.get(model));
        }
    }


    public static List<Block> BLOCKS = new ArrayList<>();
    public static Map<TagKey<Block>, List<Block>> BLOCK_TAGS = new HashMap<>();

    public static Map<Block, ItemConvertible> BLOCK_DROPS = new HashMap<>();

    public static Map<Block, Block> BLOCK_STRIPPED = new HashMap<>();
    public static Map<Block, Map<Models, Block>> BLOCK_SETS = new HashMap<>();

    public static Map<Models, List<Block>> BLOCK_MODELS = new HashMap<>();
    public static List<Block> BLOCK_CUTOUT = new ArrayList<>();

    public static Map<Block, Pair<Integer, Integer>> BLOCK_FLAMMABLE = new HashMap<>();


    public static List<Item> ITEMS = new ArrayList<>();
    public static Map<TagKey<Item>, List<Item>> ITEM_TAGS = new HashMap<>();
    public static Map<Item, Model> ITEM_MODELS = new HashMap<>();
    public static Map<ItemConvertible, Integer> ITEM_BURNABLE = new HashMap<>();
}

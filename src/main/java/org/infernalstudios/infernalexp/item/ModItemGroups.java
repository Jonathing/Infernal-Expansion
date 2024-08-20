package org.infernalstudios.infernalexp.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup INFERNALEXP = register(InfernalExpansion.MOD_ID,
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.infernalexp"))
                    .icon(() -> new ItemStack(ModItems.TAB_ICON))
                    .entries((displayContext, entries) -> {

                        entries.add(ModBlocks.SHIMMER_SAND);
                        entries.add(ModBlocks.SHIMMER_SHEET);

                        entries.add(ModBlocks.SHIMMER_STONE);
                        entries.add(ModBlocks.SHIMMER_STONE_BRICKS);

                        entries.add(ModBlocks.POLISHED_GLOWSTONE);
                        entries.add(ModBlocks.DIMSTONE);
                        entries.add(ModBlocks.POLISHED_DIMSTONE);
                        entries.add(ModBlocks.DULLSTONE);
                        entries.add(ModBlocks.POLISHED_DULLSTONE);
                        entries.add(ModBlocks.DULLSTONE_BUTTON);
                        entries.add(ModBlocks.DULLSTONE_PRESSURE_PLATE);
                        entries.add(ModItems.DULLROCKS);

                        entries.add(ModBlocks.GLOWSILK_COCOON);

                        entries.add(ModBlocks.LUMINOUS_FUNGUS);

                    })
                    .build());


    public static ItemGroup register(String id, ItemGroup tab) {
        return Registry.register(Registries.ITEM_GROUP, InfernalExpansion.makeID(id), tab);
    }

    public static void register() {
        InfernalExpansion.log("Registering ItemGroups for " + InfernalExpansion.MOD_ID);

        /** Use this kind of syntax to add items to the vanilla tabs */
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            //entries.add(ModBlocks.THING);
        });
    }
}

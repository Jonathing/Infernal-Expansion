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

public class ModItemGroups {
    public static final ItemGroup INFERNALEXP = register(InfernalExpansion.MOD_ID,
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.infernalexp"))
                    .icon(() -> new ItemStack(Blocks.NETHERRACK)) // TODO: change the tab's icon
                    .entries((displayContext, entries) -> {
                        //entries.add(ModItems.OBJECT);
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

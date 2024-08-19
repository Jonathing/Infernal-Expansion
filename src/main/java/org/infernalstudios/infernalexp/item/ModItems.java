package org.infernalstudios.infernalexp.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.setup.ModRegistry;

public class ModItems {
    public static void register() {
        InfernalExpansion.log("Registering Items for " + InfernalExpansion.MOD_ID);
    }


    public static final Item TAB_ICON = ModRegistry.ofItem("tab_icon",
            new Item(new FabricItemSettings()))
            .model(Models.GENERATED).build();


    public static final Item DULLROCKS = ModRegistry.ofItem("dullrocks",
            new Item(new FabricItemSettings()))
            .model(Models.GENERATED).build();
}

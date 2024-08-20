package com.infernalstudios.infernalexpansion.module;

import com.infernalstudios.infernalexpansion.InfernalExpansionConstants;
import com.infernalstudios.infernalexpansion.registration.util.RegistrationProvider;
import com.infernalstudios.infernalexpansion.registration.util.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CreativeTabModule {
    /**
     * The provider for creative tabs
     */
    public static final RegistrationProvider<CreativeModeTab> TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, InfernalExpansionConstants.MOD_ID);

    public static final RegistryObject<CreativeModeTab> INFERNAL_EXPANSION_TAB = TABS.register("infernalexpansion", () -> CreativeModeTab
            .builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("itemGroup.infernalexpansion_tab"))
            .icon(() -> new ItemStack(Items.NETHER_BRICK))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(Items.NETHER_BRICK);
            }).build());

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}

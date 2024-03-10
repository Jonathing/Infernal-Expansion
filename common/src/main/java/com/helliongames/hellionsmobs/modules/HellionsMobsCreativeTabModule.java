package com.helliongames.hellionsmobs.modules;

import com.helliongames.hellionsmobs.HellionsMobsConstants;
import com.helliongames.hellionsmobs.registration.util.RegistrationProvider;
import com.helliongames.hellionsmobs.registration.util.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class HellionsMobsCreativeTabModule {
    /**
     * The provider for creative tabs
     */
    public static final RegistrationProvider<CreativeModeTab> TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, HellionsMobsConstants.MOD_ID);

    public static final RegistryObject<CreativeModeTab> HELLIONS_MOBS_TAB = TABS.register("hellionsmobs", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("itemGroup.hellionsmobs_tab")).icon(() -> new ItemStack(Items.NETHER_BRICK)).displayItems((itemDisplayParameters, output) -> {
        output.accept(Items.NETHER_BRICK);
    }).build());

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}

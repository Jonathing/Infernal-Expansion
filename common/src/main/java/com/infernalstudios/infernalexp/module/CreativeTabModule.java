package com.infernalstudios.infernalexp.module;

import com.infernalstudios.infernalexp.IEConstants;
import com.infernalstudios.infernalexp.registration.util.RegistrationProvider;
import com.infernalstudios.infernalexp.registration.util.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CreativeTabModule {
    /**
     * The provider for creative tabs
     */
    public static final RegistrationProvider<CreativeModeTab> TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, IEConstants.MOD_ID);

    public static final RegistryObject<CreativeModeTab> INFERNAL_EXPANSION_TAB = TABS.register(IEConstants.MOD_ID, () -> CreativeModeTab
            .builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("itemgroup.infernalexp"))
            .icon(() -> new ItemStack(ItemModule.TAB_ICON.get()))
            .displayItems((itemDisplayParameters, entries) -> {

                entries.accept(BlockModule.SHIMMER_SAND.get());
                entries.accept(BlockModule.SHIMMER_SHEET.get());

                entries.accept(BlockModule.SHIMMER_STONE.get());
                entries.accept(BlockModule.SHIMMER_STONE_BRICKS.get());
                entries.accept(BlockModule.SHIMMER_STONE_BRICKS.getStairs().get());
                entries.accept(BlockModule.SHIMMER_STONE_BRICKS.getSlab().get());

            })
            .build());

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void load() {}
}

package org.infernalstudios.infernalexp.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.infernalstudios.infernalexp.InfernalExpansion;

import java.util.function.Supplier;

public class IECreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, InfernalExpansion.MOD_ID);

    public static final Supplier<CreativeModeTab> TAB = CREATIVE_TABS.register("InfernalTab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(IEItems.TAB_ITEM.get())).displayItems(IECreativeTabs::displayItems).build());

    private static void displayItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        // TODO
    }

    public static void register(IEventBus modEventBus) {
        CREATIVE_TABS.register(modEventBus);
        InfernalExpansion.LOGGER.info("Infernal Expansion: Creative Tabs Registered!");
    }
}

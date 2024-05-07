package org.infernalstudios.infernalexp.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.CreativeModeTabRegistry;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.infernalstudios.infernalexp.InfernalExpansion;

import java.util.function.Supplier;

public class IECreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, InfernalExpansion.MOD_ID);

    public static final Supplier<CreativeModeTab> TAB = CREATIVE_TABS.register("InfernalTab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(IEItems.TAB_ITEM.get())).displayItems(IECreativeTabs::displayItems).build());

    private static void displayItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        output.accept(IEItems.GLOWCOAL.get());
        output.accept(IEItems.DULLROCKS.get());
        output.accept(IEItems.MOTH_DUST.get());
        output.accept(IEItems.MOLTEN_GOLD_CLUSTER.get());
        output.accept(IEItems.SOUL_SALT_CLUMP.get());

        output.accept(IEItems.BLINDSIGHT_TONGUE.get());
        output.accept(IEItems.BLINDSIGHT_TONGUE_STEW.get());
        output.accept(IEItems.CURED_JERKY.get());
        output.accept(IEItems.SPIRIT_EYE.get());

        output.accept(IEItems.RAW_HOGCHOP.get());
        output.accept(IEItems.COOKED_HOGCHOP.get());

        output.accept(IEItems.VOLINE_SPAWN_EGG.get());
        output.accept(IEItems.SHROOMLOIN_SPAWN_EGG.get());
        output.accept(IEItems.WARPBEETLE_SPAWN_EGG.get());
        output.accept(IEItems.EMBODY_SPAWN_EGG.get());
        output.accept(IEItems.BASALT_GIANT_SPAWN_EGG.get());
        output.accept(IEItems.GLOWSQUITO_SPAWN_EGG.get());
        output.accept(IEItems.BLACKSTONE_DWARF_SPAWN_EGG.get());
        output.accept(IEItems.BLINDSIGHT_SPAWN_EGG.get());
        output.accept(IEItems.GLOWSILK_MOTH_SPAWN_EGG.get());

        output.accept(IEItems.VOLINE_BUCKET.get());
        output.accept(IEItems.MAGMA_CUBE_BUCKET.get());
        output.accept(IEItems.STRIDER_BUCKET.get());

        output.accept(IEItems.GLOW_TORCH.get());
        output.accept(IEItems.DULLTHORNS.get());
        output.accept(IEItems.ASCUS_BOMB.get());
        output.accept(IEItems.INFERNAL_PAINTING.get());

        output.accept(IEItems.MUSIC_DISC_SOUL_SPUNK.get());
        output.accept(IEItems.MUSIC_DISC_FLUSH.get());

        output.accept(IEItems.GLOWSILK_BOW.get());
        output.accept(IEItems.BLINDSIGHT_TONGUE_WHIP.get());
        output.accept(IEItems.KINETIC_TONGUE_WHIP.get());
    }

    public static void register(IEventBus modEventBus) {
        CREATIVE_TABS.register(modEventBus);
        InfernalExpansion.LOGGER.info("Infernal Expansion: Creative Tabs Registered!");
    }
}

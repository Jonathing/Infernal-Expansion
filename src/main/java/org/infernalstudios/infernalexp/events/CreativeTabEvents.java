package org.infernalstudios.infernalexp.events;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.init.IEItems;

public class CreativeTabEvents {
    @SubscribeEvent
    public static void modifyVanillaTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(IEItems.GLOWCOAL.get());
            event.accept(IEItems.DULLROCKS.get());
            event.accept(IEItems.MOTH_DUST.get());
            event.accept(IEItems.MOLTEN_GOLD_CLUSTER.get());
            event.accept(IEItems.SOUL_SALT_CLUMP.get());

            event.accept(IEItems.MUSIC_DISC_SOUL_SPUNK.get());
            event.accept(IEItems.MUSIC_DISC_FLUSH.get());
        }

        else if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(IEItems.BLINDSIGHT_TONGUE.get());
            event.accept(IEItems.BLINDSIGHT_TONGUE_STEW.get());
            event.accept(IEItems.CURED_JERKY.get());
            event.accept(IEItems.SPIRIT_EYE.get());

            event.accept(IEItems.RAW_HOGCHOP.get());
            event.accept(IEItems.COOKED_HOGCHOP.get());
        }

        else if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(IEItems.VOLINE_SPAWN_EGG.get());
            event.accept(IEItems.SHROOMLOIN_SPAWN_EGG.get());
            event.accept(IEItems.WARPBEETLE_SPAWN_EGG.get());
            event.accept(IEItems.EMBODY_SPAWN_EGG.get());
            event.accept(IEItems.BASALT_GIANT_SPAWN_EGG.get());
            event.accept(IEItems.GLOWSQUITO_SPAWN_EGG.get());
            event.accept(IEItems.BLACKSTONE_DWARF_SPAWN_EGG.get());
            event.accept(IEItems.BLINDSIGHT_SPAWN_EGG.get());
            event.accept(IEItems.GLOWSILK_MOTH_SPAWN_EGG.get());
        }
        
        else if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(IEItems.GLOWSILK_BOW.get());
            event.accept(IEItems.BLINDSIGHT_TONGUE_WHIP.get());
            event.accept(IEItems.KINETIC_TONGUE_WHIP.get());
        }
    }
}

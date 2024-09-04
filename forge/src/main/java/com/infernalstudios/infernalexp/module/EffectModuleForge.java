package com.infernalstudios.infernalexp.module;

import com.infernalstudios.infernalexp.IECommon;
import com.infernalstudios.infernalexp.potion.PotionRecipe;
import com.infernalstudios.infernalexp.registration.FuelRegistry;
import com.infernalstudios.infernalexp.registration.holders.ItemDataHolder;
import com.infernalstudios.infernalexp.registration.holders.MobEffectDataHolder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.VanillaBrewingRecipe;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Map;

@Mod.EventBusSubscriber(modid = "infernalexp", bus = Mod.EventBusSubscriber.Bus.MOD)
public class EffectModuleForge {
    @SubscribeEvent
    public static void registerEffects(RegisterEvent event) {
        for (Map.Entry<ResourceLocation, MobEffectDataHolder<?>> entry : ModEffects.getEffectRegistry().entrySet()) {
            // Register effect
            event.register(ForgeRegistries.MOB_EFFECTS.getRegistryKey(), entry.getKey(), entry.getValue()::get);

            if (entry.getValue().hasPotion()) {
                String id = entry.getKey().getPath();

                Potion basep = new Potion(new MobEffectInstance(entry.getValue().get(), 3600));
                Potion longp = new Potion(id, new MobEffectInstance(entry.getValue().get(), 9600));
                Potion strongp = new Potion(id, new MobEffectInstance(entry.getValue().get(), 1800, 1));

                event.register(ForgeRegistries.POTIONS.getRegistryKey(), entry.getKey(), () -> basep);
                event.register(ForgeRegistries.POTIONS.getRegistryKey(), IECommon.id("long_" + id), () -> longp);
                event.register(ForgeRegistries.POTIONS.getRegistryKey(), IECommon.id("strong_" + id), () -> strongp);

                BrewingRecipeRegistry.addRecipe(new PotionRecipe(Potions.AWKWARD, entry.getValue().getPotionIngredient().get(), basep));
                BrewingRecipeRegistry.addRecipe(new PotionRecipe(basep, Items.REDSTONE, longp));
                BrewingRecipeRegistry.addRecipe(new PotionRecipe(basep, Items.GLOWSTONE_DUST, strongp));
            }
        }
    }
}
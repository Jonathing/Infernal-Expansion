/*
 * Copyright 2022 Infernal Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.infernalstudios.infernalexp.init;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.blocks.DullthornsBlockItem;
import org.infernalstudios.infernalexp.items.AscusBombItem;
import org.infernalstudios.infernalexp.items.EntityBucketItem;
import org.infernalstudios.infernalexp.items.GlowcoalItem;
import org.infernalstudios.infernalexp.items.GlowsilkBowItem;
import org.infernalstudios.infernalexp.items.InfernalPaintingItem;
import org.infernalstudios.infernalexp.items.ItemBase;
import org.infernalstudios.infernalexp.items.SlurpItem;
import org.infernalstudios.infernalexp.items.SlurpSoupItem;
import org.infernalstudios.infernalexp.items.WhipItem;

import java.util.function.Supplier;

public class IEItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, InfernalExpansion.MOD_ID);

    // Items
    public static final Supplier<Item> GLOWCOAL = ITEMS.register("glowcoal", GlowcoalItem::new);
    public static final Supplier<Item> DULLROCKS = ITEMS.register("glownuggets", ItemBase::new);
    public static final Supplier<Item> MOTH_DUST = ITEMS.register("moth_dust", ItemBase::new);
    public static final Supplier<Item> MOLTEN_GOLD_CLUSTER = ITEMS.register("molten_gold_cluster", ItemBase::new);
    public static final Supplier<Item> GLOWSILK = ITEMS.register("glowsilk", ItemBase::new);
    public static final Supplier<Item> SOUL_SALT_CLUMP = ITEMS.register("soul_salt_clump", ItemBase::new);


    // Foods
    public static final Supplier<Item> BLINDSIGHT_TONGUE = ITEMS.register("blindsight_tongue", () -> new SlurpItem(new Item.Properties()
        .food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.5F)
                                          .effect(() ->
                                              new MobEffectInstance(MobEffects.JUMP, 100, 1), 1.0F)
                                          .build())));

    public static final Supplier<BowlFoodItem> BLINDSIGHT_TONGUE_STEW = registerItem("blindsight_tongue_stew", () -> new SlurpSoupItem(new Item.Properties().stacksTo(1)
                                                                                                                                                            .food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.9F)
                                                                                                                                                                                              .effect(() ->
                                                                                                                                                                                                  new MobEffectInstance(MobEffects.JUMP, 1200, 1), 1.0F)
                                                                                                                                                                                              .build())));

    public static final Supplier<Item> CURED_JERKY = ITEMS.register("cured_jerky", () -> new Item(new Item.Properties()
        .food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.6F)
                                          .build())));

    public static final Supplier<Item> SPIRIT_EYE = ITEMS.register("spirit_eye", () -> new Item(new Item.Properties()
        .food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.8F)
                                          .effect(() ->
                                              new MobEffectInstance(MobEffects.GLOWING, 200, 0), 1.0F)
                                          .build())));

    // TODO: 1.20.5 has replaced the .meat() method on the FoodProperties.Builder with the respective entity food tag (e.g. "minecraft:wolf_food")

    public static final Supplier<Item> RAW_HOGCHOP = ITEMS.register("raw_hogchop", () -> new Item(new Item.Properties()
        .food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F)
                                          .effect(() ->
                                              new MobEffectInstance(IEEffects.INFECTION, 200, 1), 1.0F)
                                          .build())));

    public static final Supplier<Item> COOKED_HOGCHOP = ITEMS.register("cooked_hogchop", () -> new Item(new Item.Properties()
        .food(new FoodProperties.Builder().nutrition(10).saturationModifier(0.8F)
                                          .effect(() ->
                                              new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1), 1.0F)
                                          .build())));


    // Spawn Eggs
    public static final Supplier<DeferredSpawnEggItem> VOLINE_SPAWN_EGG = registerItem("voline_spawn_egg", () -> new DeferredSpawnEggItem(IEEntityTypes.VOLINE, 0x2E2631, 0x652833, new Item.Properties()));
    public static final Supplier<DeferredSpawnEggItem> SHROOMLOIN_SPAWN_EGG = registerItem("shroomloin_spawn_egg", () -> new DeferredSpawnEggItem(IEEntityTypes.SHROOMLOIN, 0x854242, 0xFF6500, new Item.Properties()));
    public static final Supplier<DeferredSpawnEggItem> WARPBEETLE_SPAWN_EGG = registerItem("warpbeetle_spawn_egg", () -> new DeferredSpawnEggItem(IEEntityTypes.WARPBEETLE, 0x72EA95, 0x2D3860, new Item.Properties()));
    // public static final Supplier<ModSpawnEggItem> CEROBEETLE_SPAWN_EGG = ITEMS.register("cerobeetle_spawn_egg",() -> new ModSpawnEggItem(ModEntityType.CEROBEETLE, 0x73EB96, 0x409089, new Item.Properties()));
    public static final Supplier<DeferredSpawnEggItem> EMBODY_SPAWN_EGG = registerItem("embody_spawn_egg", () -> new DeferredSpawnEggItem(IEEntityTypes.EMBODY, 0x796152, 0x6DEDF1, new Item.Properties()));
    public static final Supplier<DeferredSpawnEggItem> BASALT_GIANT_SPAWN_EGG = registerItem("basalt_giant_spawn_egg", () -> new DeferredSpawnEggItem(IEEntityTypes.BASALT_GIANT, 0x545454, 0xe36412, new Item.Properties()));
    public static final Supplier<DeferredSpawnEggItem> GLOWSQUITO_SPAWN_EGG = registerItem("glowsquito_spawn_egg", () -> new DeferredSpawnEggItem(IEEntityTypes.GLOWSQUITO, 0x383948, 0xe5c092, new Item.Properties()));
    public static final Supplier<DeferredSpawnEggItem> BLACKSTONE_DWARF_SPAWN_EGG = registerItem("blackstone_dwarf_spawn_egg", () -> new DeferredSpawnEggItem(IEEntityTypes.BLACKSTONE_DWARF, 0x1a1a1c, 0x36313f, new Item.Properties()));
    public static final Supplier<DeferredSpawnEggItem> BLINDSIGHT_SPAWN_EGG = registerItem("blindsight_spawn_egg", () -> new DeferredSpawnEggItem(IEEntityTypes.BLINDSIGHT, 0x312c36, 0xfbda74, new Item.Properties()));
    public static final Supplier<DeferredSpawnEggItem> GLOWSILK_MOTH_SPAWN_EGG = registerItem("glowsilk_moth_spawn_egg", () -> new DeferredSpawnEggItem(IEEntityTypes.GLOWSILK_MOTH, 0x724423, 0xe3b064, new Item.Properties()));

    //  public static final Supplier<ModSpawnEggItem> PYRNO_SPAWN_EGG = ITEMS.register("pyrno_spawn_egg", () -> new ModSpawnEggItem(ModEntityType.PYRNO, 0x5D514B, 0xFEE15E, new Item.Properties().group(InfernalExpansion.TAB)));

    // Bucket items
    public static final Supplier<EntityBucketItem> VOLINE_BUCKET = registerItem("voline_bucket", () -> new EntityBucketItem(IEEntityTypes.VOLINE::get, Fluids.LAVA, () -> SoundEvents.BUCKET_EMPTY_LAVA, new Item.Properties().stacksTo(1)));
    public static final Supplier<EntityBucketItem> MAGMA_CUBE_BUCKET = registerItem("magma_cube_bucket", () -> new EntityBucketItem(() -> EntityType.MAGMA_CUBE, Fluids.LAVA, () -> SoundEvents.MAGMA_CUBE_SQUISH, new Item.Properties().stacksTo(1)));
    public static final Supplier<EntityBucketItem> STRIDER_BUCKET = registerItem("strider_bucket", () -> new EntityBucketItem(() -> EntityType.STRIDER, Fluids.LAVA, () -> SoundEvents.BUCKET_EMPTY_LAVA, new Item.Properties().stacksTo(1)));

    // Block items
    public static final Supplier<Item> GLOW_TORCH = registerItem("glow_torch", () -> new StandingAndWallBlockItem(IEBlocks.GLOW_TORCH.get(), IEBlocks.GLOW_TORCH_WALL.get(), new Item.Properties(), Direction.DOWN));

    public static final Supplier<Item> DULLTHORNS = registerItem("dullthorns", () -> new DullthornsBlockItem(IEBlocks.DULLTHORNS.get()));

    public static final Supplier<Item> ASCUS_BOMB = registerItem("ascus_bomb", AscusBombItem::new);
    public static final Supplier<Item> INFERNAL_PAINTING = registerItem("infernal_painting", () -> new InfernalPaintingItem(new Item.Properties()));

    // Record Discs
    public static final Supplier<Item> MUSIC_DISC_SOUL_SPUNK = registerItem("music_disc_soul_spunk", () -> new RecordItem(8, () -> IESoundEvents.MUSIC_DISC_SOUL_SPUNK.get(), new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 234));
    public static final Supplier<Item> MUSIC_DISC_FLUSH = registerItem("music_disc_flush", () -> new RecordItem(7, () -> IESoundEvents.MUSIC_DISC_FLUSH.get(), new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 262));

    // Tools
    public static final Supplier<BowItem> GLOWSILK_BOW = registerItem("glowsilk_bow", () -> new GlowsilkBowItem(new Item.Properties().durability(384)));
    public static final Supplier<WhipItem> BLINDSIGHT_TONGUE_WHIP = registerItem("blindsight_tongue_whip", () -> new WhipItem(IEItemTiers.BLINDSIGHT_TONGUE, 4.0F, -3.4F, new Item.Properties()));
    public static final Supplier<WhipItem> KINETIC_TONGUE_WHIP = registerItem("kinetic_tongue_whip", () -> new WhipItem(IEItemTiers.KINETIC_OPAL, 6.0F, -3.4F, ModList.get().isLoaded("miningmaster") ? new Item.Properties() : new Item.Properties()));

    public static final Supplier<Item> TAB_ITEM = registerItem("tab_icon", () -> new Item(new Item.Properties()));

    /*
    public static final Supplier<SwordItem> FROSTBITTEN_SWORD = ITEMS.register("frostbitten_sword", () -> new SwordItem(ModItemTier.FROSTBITTEN_NETHERITE, 2, -2.4F, new Item.Properties().group(InfernalExpansion.TAB)));
    public static final Supplier<PickaxeItem> FROSTBITTEN_PICKAXE = ITEMS.register("frostbitten_pickaxe", () -> new PickaxeItem(ModItemTier.FROSTBITTEN_NETHERITE, 1, -2.4F, new Item.Properties().group(InfernalExpansion.TAB)));
    public static final Supplier<AxeItem> FROSTBITTEN_AXE = ITEMS.register("frostbitten_axe", () -> new AxeItem(ModItemTier.FROSTBITTEN_NETHERITE, 4, -3.1F, new Item.Properties().group(InfernalExpansion.TAB)));
    public static final Supplier<ShovelItem> FROSTBITTEN_SHOVEL = ITEMS.register("frostbitten_shovel", () -> new ShovelItem(ModItemTier.FROSTBITTEN_NETHERITE, -1, -2.4F, new Item.Properties().group(InfernalExpansion.TAB)));
    public static final Supplier<HoeItem> FROSTBITTEN_HOE = ITEMS.register("frostbitten_hoe", () -> new HoeItem(ModItemTier.FROSTBITTEN_NETHERITE, -2, -1.0F, new Item.Properties().group(InfernalExpansion.TAB)));
    */

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        InfernalExpansion.LOGGER.info("Infernal Expansion: Items Registered!");
    }

    public static <T extends Item> Supplier<T> registerItem(String name, Supplier<? extends T> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
    }
}

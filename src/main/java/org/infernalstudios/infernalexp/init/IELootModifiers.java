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

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.config.InfernalExpansionConfig;

import javax.annotation.Nonnull;
import java.util.List;

public class IELootModifiers {

    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, InfernalExpansion.MOD_ID);

    public static final RegistryObject<GlobalLootModifierSerializer<HoglinLootModifier>> HOGLIN_LOOT_MODIFIER = LOOT_MODIFIERS.register("hoglin_loot_modifier", HoglinLootSerializer::new);

    private static class HoglinLootModifier extends LootModifier {

        /**
         * Constructs a LootModifier.
         *
         * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
         */

        protected HoglinLootModifier(LootItemCondition[] conditionsIn) {
            super(conditionsIn);
        }

        @Nonnull
        @Override
        protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
            if (!InfernalExpansionConfig.MobInteractions.USE_HOGCHOPS.getBoolean()) {
                return generatedLoot;
            }

            int numChops = 0;
            int numCookedChops = 0;

            for (ItemStack item : generatedLoot) {
                if (item.sameItem(Items.PORKCHOP.getDefaultInstance())) {
                    numChops += item.getCount();
                } else if (item.sameItem(Items.COOKED_PORKCHOP.getDefaultInstance())) {
                    numCookedChops += item.getCount();
                }
            }

            generatedLoot.removeIf(x -> x.sameItem(Items.PORKCHOP.getDefaultInstance()));
            generatedLoot.removeIf(x -> x.sameItem(Items.COOKED_PORKCHOP.getDefaultInstance()));
            generatedLoot.add(new ItemStack(IEItems.COOKED_HOGCHOP.get(), numCookedChops));
            generatedLoot.add(new ItemStack(IEItems.RAW_HOGCHOP.get(), numChops));

            return generatedLoot;
        }
    }

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIERS.register(eventBus);
        InfernalExpansion.LOGGER.info("Infernal Expansion: Loot Modifiers Registered!");
    }

    private static class HoglinLootSerializer extends GlobalLootModifierSerializer<HoglinLootModifier> {

        @Override
        public HoglinLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditionsIn) {
            return new HoglinLootModifier(conditionsIn);
        }

        @Override
        public JsonObject write(HoglinLootModifier instance) {
            return null;
        }
    }
}

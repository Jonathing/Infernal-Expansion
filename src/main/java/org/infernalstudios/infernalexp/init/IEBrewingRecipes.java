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

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;

public class IEBrewingRecipes {

    public static void register(PotionBrewing.Builder builder) {
        registerBrewingRecipe(builder, IEItems.MOTH_DUST.get(), IEPotions.LUMINOUS.getDelegate(), IEPotions.LONG_LUMINOUS.getDelegate(), IEPotions.STRONG_LUMINOUS.getDelegate());
        registerBrewingRecipe(builder, IEItems.ASCUS_BOMB.get(), IEPotions.INFECTION.getDelegate(), IEPotions.LONG_INFECTION.getDelegate(), IEPotions.STRONG_INFECTION.getDelegate());
    }

    private static void registerBrewingRecipe(PotionBrewing.Builder builder, Item ingredient, Holder<Potion> normalPotion, Holder<Potion> longPotion, Holder<Potion> strongPotion) {
        // Allow reagent to be used to make mundane potions
        builder.addStartMix(ingredient, normalPotion);

        // Add strong and long variants
        builder.addMix(normalPotion, Items.REDSTONE, longPotion);
        builder.addMix(normalPotion, Items.GLOWSTONE_DUST, strongPotion);
    }

}

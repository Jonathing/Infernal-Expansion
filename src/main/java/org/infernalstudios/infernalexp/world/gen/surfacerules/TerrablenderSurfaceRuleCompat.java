/*
 * Copyright 2021 Infernal Studios
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

package org.infernalstudios.infernalexp.world.gen.surfacerules;

import net.minecraft.world.level.levelgen.SurfaceRules;
import net.neoforged.fml.ModList;
import org.infernalstudios.infernalexp.InfernalExpansion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TerrablenderSurfaceRuleCompat {

    public static void addSurfaceRules() {
        if (!ModList.get().isLoaded("terrablender"))
            return;

        try {
            Class<?> surfaceRuleManagerClass = Class.forName("terrablender.api.SurfaceRuleManager");
            Class<?> ruleCategoryClass = Class.forName("terrablender.api.SurfaceRuleManager$RuleCategory");

            Method addSurfaceRulesMethod = surfaceRuleManagerClass.getMethod("addSurfaceRules", ruleCategoryClass, String.class, SurfaceRules.RuleSource.class);

            for (Object constant : ruleCategoryClass.getEnumConstants()) {
                Method nameMethod = constant.getClass().getMethod("name");

                if (nameMethod.invoke(constant).equals("NETHER"))
                    addSurfaceRulesMethod.invoke(surfaceRuleManagerClass, constant, InfernalExpansion.MOD_ID, NetherSurfaceRules.addNetherSurfaceRulesWithBedrock());
            }

        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException exception) {
            throw new RuntimeException(exception);
        }
    }

}

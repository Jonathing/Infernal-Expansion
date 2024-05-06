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

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.client.particle.GlowstoneSparkleParticle;
import org.infernalstudios.infernalexp.client.particle.InfectionParticle;

@EventBusSubscriber(modid = InfernalExpansion.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class IEParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, InfernalExpansion.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GLOWSTONE_SPARKLE = PARTICLES.register("glowstone_sparkle", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> INFECTION = PARTICLES.register("infection", () -> new SimpleParticleType(false));

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(IEParticleTypes.GLOWSTONE_SPARKLE.get(), GlowstoneSparkleParticle.Factory::new);
        event.registerSpriteSet(IEParticleTypes.INFECTION.get(), InfectionParticle.Factory::new);

        InfernalExpansion.LOGGER.info("Infernal Expansion: Particles Registered!");
    }
}

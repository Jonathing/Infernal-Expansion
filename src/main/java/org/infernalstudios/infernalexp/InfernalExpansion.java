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

package org.infernalstudios.infernalexp;

import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.infernalstudios.infernalexp.client.InfernalExpansionClient;
import org.infernalstudios.infernalexp.config.ConfigHolder;
import org.infernalstudios.infernalexp.data.SpawnrateManager;
import org.infernalstudios.infernalexp.events.MiscEvents;
import org.infernalstudios.infernalexp.events.MobEvents;
import org.infernalstudios.infernalexp.events.RegistryEvents;
import org.infernalstudios.infernalexp.events.WorldEvents;
import org.infernalstudios.infernalexp.init.IEBiomeModifiers;
import org.infernalstudios.infernalexp.init.IEBiomes;
import org.infernalstudios.infernalexp.init.IEBlockEntityTypes;
import org.infernalstudios.infernalexp.init.IEBlocks;
import org.infernalstudios.infernalexp.init.IECapabilities;
import org.infernalstudios.infernalexp.init.IECommands;
import org.infernalstudios.infernalexp.init.IECreativeTabs;
import org.infernalstudios.infernalexp.init.IEEffects;
import org.infernalstudios.infernalexp.init.IEEntityClassifications;
import org.infernalstudios.infernalexp.init.IEEntityTypes;
import org.infernalstudios.infernalexp.init.IEFireTypes;
import org.infernalstudios.infernalexp.init.IEItems;
import org.infernalstudios.infernalexp.init.IELootModifiers;
import org.infernalstudios.infernalexp.init.IEPaintings;
import org.infernalstudios.infernalexp.init.IEParticleTypes;
import org.infernalstudios.infernalexp.init.IEPotions;
import org.infernalstudios.infernalexp.init.IEProcessors;
import org.infernalstudios.infernalexp.init.IEShroomloinTypes;
import org.infernalstudios.infernalexp.init.IESoundEvents;
import org.infernalstudios.infernalexp.init.IEStructureSets;
import org.infernalstudios.infernalexp.init.IEStructureTypes;
import org.infernalstudios.infernalexp.init.IEStructures;
import org.infernalstudios.infernalexp.init.IESurfaceRules;
import org.infernalstudios.infernalexp.network.IENetworkHandler;
import org.infernalstudios.infernalexp.world.gen.ModEntityPlacement;

@Mod(InfernalExpansion.MOD_ID)
public class InfernalExpansion {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "infernalexp";

    public InfernalExpansion(FMLModContainer modContainer) {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        final IEventBus modEventBus = modContainer.getEventBus();

        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::commonSetup);

        // Registering deferred registers to the mod bus
        IEParticleTypes.PARTICLES.register(modEventBus);
        IESoundEvents.register(modEventBus);
        IEBlocks.register(modEventBus);
        IEItems.register(modEventBus);
        IEEffects.register(modEventBus);
        IEPotions.register(modEventBus);
        IEEntityTypes.register(modEventBus);
        IEPaintings.register(modEventBus);
        IEBlockEntityTypes.register(modEventBus);
        IEBiomes.register(modEventBus);
        IELootModifiers.register(modEventBus);
        IEBiomeModifiers.register(modEventBus);
        IECreativeTabs.register(modEventBus);
        IESurfaceRules.register(modEventBus);

        IEShroomloinTypes.registerAll();
        IEEntityClassifications.register();

        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(new MiscEvents());
        NeoForge.EVENT_BUS.register(new MobEvents());
        NeoForge.EVENT_BUS.register(new WorldEvents());
        NeoForge.EVENT_BUS.register(new IECapabilities());
        NeoForge.EVENT_BUS.register(new RegistryEvents());

        // Registering Configs
        modContainer.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
        modContainer.registerConfig(ModConfig.Type.COMMON, ConfigHolder.COMMON_SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Setup and register structures, processors, packets, capabilities and brewing recipes
        event.enqueueWork(IEProcessors::bootstrap); // If we fully switched over to using just code for structures, this line wouldn't be necessary
        event.enqueueWork(IEStructureTypes::register);
        event.enqueueWork(IEStructures::register);
        event.enqueueWork(IEStructureSets::register);
        event.enqueueWork(IENetworkHandler::register);
        event.enqueueWork(IEFireTypes::register);

        // Create mob spawnrate config files, they get created on game load instead of world load
        // just in case someone only launches the games once then goes and looks at the config files.
        event.enqueueWork(SpawnrateManager::createResources);

        // Register for Quark Compatibility in recipe
        // TODO: figure this one out
//        CraftingHelper.register(new CompatibilityQuark.Serializer());

        // Places entity spawn locations on the ground
        ModEntityPlacement.spawnPlacement();

        // Register New Flowers to be Able to Place in Pots
        FlowerPotBlock flowerPot = (FlowerPotBlock) Blocks.FLOWER_POT;
        flowerPot.addPlant(IEBlocks.DULLTHORNS.getId(), IEBlocks.POTTED_DULLTHORNS);
        flowerPot.addPlant(IEBlocks.LUMINOUS_FUNGUS.getId(), IEBlocks.POTTED_LUMINOUS_FUNGUS);
        flowerPot.addPlant(IEBlocks.SHROOMLIGHT_FUNGUS.getId(), IEBlocks.POTTED_SHROOMLIGHT_FUNGUS);

        // Custom Dispenser Behavior
        DispenserBlock.registerBehavior(Items.GLOWSTONE_DUST, new DefaultDispenseItemBehavior() {
            @Override
            protected ItemStack execute(BlockSource source, ItemStack stack) {
                Level world = source.level();
                BlockPos blockpos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
                ItemStack itemstack = stack.split(1);
                if (world.getBlockState(blockpos).getBlock() == IEBlocks.DIMSTONE.get()) {
                    world.setBlockAndUpdate(blockpos, Blocks.GLOWSTONE.defaultBlockState());
                } else if (world.getBlockState(blockpos).getBlock() == IEBlocks.DULLSTONE.get()) {
                    world.setBlockAndUpdate(blockpos, IEBlocks.DIMSTONE.get().defaultBlockState());
                } else {
                    spawnItem(world, itemstack, 6, source.state().getValue(DispenserBlock.FACING), DispenserBlock.getDispensePosition(source));
                }

                return stack;
            }
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            InfernalExpansionClient.init(event::enqueueWork);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        IECommands.registerCommands(event.getServer().getCommands().getDispatcher());
    }
}

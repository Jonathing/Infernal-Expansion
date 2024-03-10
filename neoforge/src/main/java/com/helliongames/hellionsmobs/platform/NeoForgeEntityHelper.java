package com.helliongames.hellionsmobs.platform;

import com.helliongames.hellionsmobs.entity.KitsuneEntity;
import com.helliongames.hellionsmobs.modules.HellionsMobsEntityTypeModule;
import com.helliongames.hellionsmobs.platform.services.IEntityHelper;
import com.helliongames.hellionsmobs.registration.util.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = "hellionsmobs", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NeoForgeEntityHelper implements IEntityHelper {
    private static final Map<RegistryObject<?>, Supplier<AttributeSupplier.Builder>> entityAttributes = new HashMap<>();

    @Override
    public void registerEntityAttributes() {
        entityAttributes.put(HellionsMobsEntityTypeModule.KITSUNE, KitsuneEntity::createKitsuneAttributes);
    }

    @SubscribeEvent
    public static void registerEntityAttributesListener(EntityAttributeCreationEvent event) {
        for (Map.Entry<RegistryObject<?>, Supplier<AttributeSupplier.Builder>> entry : entityAttributes.entrySet()) {
            AttributeSupplier.Builder builder = entry.getValue().get();
            // Attach required Forge attributes and register
            builder.add(NeoForgeMod.SWIM_SPEED.value())
                    .add(NeoForgeMod.NAMETAG_DISTANCE.value())
                    .add(NeoForgeMod.ENTITY_GRAVITY.value());

            event.put((EntityType<? extends LivingEntity>) entry.getKey().get(), builder.build());
        }
    }
}

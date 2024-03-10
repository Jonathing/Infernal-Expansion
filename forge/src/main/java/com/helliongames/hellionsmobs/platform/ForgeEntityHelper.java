package com.helliongames.hellionsmobs.platform;

import com.helliongames.hellionsmobs.entity.KitsuneEntity;
import com.helliongames.hellionsmobs.platform.services.IEntityHelper;
import com.helliongames.hellionsmobs.registration.HellionsMobsEntities;
import com.helliongames.hellionsmobs.registration.util.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = "hellionsmobs", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeEntityHelper implements IEntityHelper {
    private static final Map<RegistryObject<?>, Supplier<AttributeSupplier.Builder>> entityAttributes = new HashMap<>();

    @Override
    public void registerEntityAttributes() {
        entityAttributes.put(HellionsMobsEntities.KITSUNE, KitsuneEntity::createKitsuneAttributes);
    }

    @SubscribeEvent
    public static void registerEntityAttributesListener(EntityAttributeCreationEvent event) {
        for (Map.Entry<RegistryObject<?>, Supplier<AttributeSupplier.Builder>> entry : entityAttributes.entrySet()) {
            AttributeSupplier.Builder builder = entry.getValue().get();
            // Attach required Forge attributes and register
            builder.add(ForgeMod.SWIM_SPEED.get())
                    .add(ForgeMod.NAMETAG_DISTANCE.get())
                    .add(ForgeMod.ENTITY_GRAVITY.get());

            event.put((EntityType<? extends LivingEntity>) entry.getKey().get(), builder.build());
        }
    }
}

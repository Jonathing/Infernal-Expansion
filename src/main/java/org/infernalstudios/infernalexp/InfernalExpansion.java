package org.infernalstudios.infernalexp;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.infernalstudios.infernalexp.block.ModBlocks;
import org.infernalstudios.infernalexp.item.ModItemGroups;
import org.infernalstudios.infernalexp.item.ModItems;
import org.infernalstudios.infernalexp.setup.ModDataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class InfernalExpansion implements ModInitializer {
	public static final String MOD_ID = "infernalexp";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.register();
		ModBlocks.register();

		ModItemGroups.register();


		/** The rest of this method's body should be left as called last to prevent datagen issues, only add stuff before this comment */
		ModDataGenerator.registerBurnable();
		ModDataGenerator.registerFuels();
		ModDataGenerator.registerStripped();

		GeckoLib.initialize();
	}

	public static Identifier makeID(String name) {
		return Identifier.of(MOD_ID, name);
	}

	public static <T> T log(T message) {
		LOGGER.info(String.valueOf(message));
		return message;
	}
}
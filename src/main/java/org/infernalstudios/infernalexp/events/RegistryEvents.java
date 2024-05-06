package org.infernalstudios.infernalexp.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import org.infernalstudios.infernalexp.init.IEBrewingRecipes;

public class RegistryEvents {
    @SubscribeEvent
    public void onRegisterBrewingRecipes(RegisterBrewingRecipesEvent event) {
        IEBrewingRecipes.register(event.getBuilder());
    }
}

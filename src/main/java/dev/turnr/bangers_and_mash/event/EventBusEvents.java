package dev.turnr.bangers_and_mash.event;

import dev.turnr.bangers_and_mash.BangersAndMash;
import dev.turnr.bangers_and_mash.recipe.food_processor.GeneralFoodProcessorRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BangersAndMash.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventBusEvents {
  @SubscribeEvent
  public static void onRegisterRecipeSerializers(final RegistryEvent.Register<RecipeSerializer<?>> event) {
    Registry.register(Registry.RECIPE_TYPE, GeneralFoodProcessorRecipe.Type.ID, GeneralFoodProcessorRecipe.Type.INSTANCE);
  }
}

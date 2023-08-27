package dev.turnr.farmlore.recipe;

import dev.turnr.farmlore.Farmlore;
import dev.turnr.farmlore.recipe.food_processor.FoodProcessorRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeRegistry {
  public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
      .create(ForgeRegistries.RECIPE_SERIALIZERS, Farmlore.MOD_ID);

  public static final RegistryObject<RecipeSerializer<FoodProcessorRecipe>> FOOD_PROCESSOR = SERIALIZERS
      .register("food_processor", () -> FoodProcessorRecipe.Serializer.INSTANCE);

  public static void register(IEventBus eventBus) {
    SERIALIZERS.register(eventBus);
  }

}

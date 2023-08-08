package dev.turnr.bangers_and_mash.recipe;

import dev.turnr.bangers_and_mash.BangersAndMash;
import dev.turnr.bangers_and_mash.recipe.food_processor.GeneralFoodProcessorRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Recipes {
  public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
      .create(ForgeRegistries.RECIPE_SERIALIZERS, BangersAndMash.MOD_ID);

  public static final RegistryObject<RecipeSerializer<GeneralFoodProcessorRecipe>> FOOD_PROCESSOR = SERIALIZERS
      .register("food_processor", () -> GeneralFoodProcessorRecipe.Serializer.INSTANCE);

  public static void register(IEventBus eventBus) {
    SERIALIZERS.register(eventBus);
  }

}

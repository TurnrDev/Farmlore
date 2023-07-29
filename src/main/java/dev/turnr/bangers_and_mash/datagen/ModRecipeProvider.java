package dev.turnr.bangers_and_mash.datagen;

import static dev.turnr.bangers_and_mash.item.ModItems.RAW_SAUSAGES;

import com.mojang.logging.LogUtils;
import dev.turnr.bangers_and_mash.BangersAndMash;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

  private static final Logger LOGGER = LogUtils.getLogger();

  public ModRecipeProvider(DataGenerator pGenerator) {
    super(pGenerator);
    LOGGER.info("HELLO from ModRecipeProvider constructor");
  }

  @Nullable
  private static Item getCookedSausage(Item rawSausage) {
    ResourceLocation rawSausageLocation = rawSausage.getRegistryName();
    String cookedSausagePath = "cooked_" + rawSausageLocation.getPath();
    ResourceLocation cookedSausageResourcePath = new ResourceLocation(
        rawSausageLocation.getNamespace(), cookedSausagePath);
    return ForgeRegistries.ITEMS.getValue(cookedSausageResourcePath);
  }

  private void addSmokingRecipe(Consumer<FinishedRecipe> finishedRecipeConsumer, Item input,
      Item output, float pExperience, int pCookingTime) {
    Ingredient inputIngredient = Ingredient.of(input);
    ResourceLocation recipeId = new ResourceLocation(BangersAndMash.MOD_ID,
        "smoking/" + output.getRegistryName().getPath());
    SimpleCookingRecipeBuilder.smoking(inputIngredient, output, pExperience, pCookingTime)
        .unlockedBy("has_item", has(input)).save(finishedRecipeConsumer, recipeId);
  }

  private void addCampfireRecipe(Consumer<FinishedRecipe> finishedRecipeConsumer, Item input,
      Item output, float pExperience, int pCookingTime) {
    Ingredient inputIngredient = Ingredient.of(input);
    ResourceLocation recipeId = new ResourceLocation(BangersAndMash.MOD_ID,
        "campfire_cooking/" + output.getRegistryName().getPath());
    SimpleCookingRecipeBuilder.campfireCooking(inputIngredient, output, pExperience, pCookingTime)
        .unlockedBy("has_item", has(input)).save(finishedRecipeConsumer, recipeId);
  }

  private void addSmeltingRecipe(Consumer<FinishedRecipe> finishedRecipeConsumer, Item input,
      Item output, float pExperience, int pCookingTime) {
    Ingredient inputIngredient = Ingredient.of(input);
    ResourceLocation recipeId = new ResourceLocation(BangersAndMash.MOD_ID,
        "smelting/" + output.getRegistryName().getPath());
    SimpleCookingRecipeBuilder.smelting(inputIngredient, output, pExperience, pCookingTime)
        .unlockedBy("has_item", has(input)).save(finishedRecipeConsumer, recipeId);
  }

  private void buildSausageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
    LOGGER.info("HELLO from buildSausageRecipes in ModRecipeProvider");

    // Loop through all the items in the raw sausage tag
    for (RegistryObject<Item> pRawSausage : RAW_SAUSAGES) {
      Item rawSausage = pRawSausage.get();
      Item cookedSausage = getCookedSausage(rawSausage);
      if (cookedSausage == null) {
        LOGGER.info("No cooked sausage found for {}", rawSausage.getRegistryName());
        continue;
      }
      LOGGER.info("Adding sausage recipes for {}", rawSausage.getRegistryName());
      addCampfireRecipe(pFinishedRecipeConsumer, rawSausage, cookedSausage, 0.0F, 600);
      addSmokingRecipe(pFinishedRecipeConsumer, rawSausage, cookedSausage, 0.0F, 200);
      addSmeltingRecipe(pFinishedRecipeConsumer, rawSausage, cookedSausage, 0.0F, 200);
    }
  }


  @Override
  protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
    LOGGER.error("HELLO from buildCraftingRecipes in ModRecipeProvider");
    buildSausageRecipes(pFinishedRecipeConsumer);
  }
}

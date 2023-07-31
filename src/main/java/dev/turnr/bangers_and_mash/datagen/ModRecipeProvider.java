package dev.turnr.bangers_and_mash.datagen;

import static dev.turnr.bangers_and_mash.item.ModItems.RAW_SAUSAGES;

import com.mojang.logging.LogUtils;
import dev.turnr.bangers_and_mash.BangersAndMash;
import dev.turnr.bangers_and_mash.item.ModItems;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Ingredient.ItemValue;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

  private static final Logger LOGGER = LogUtils.getLogger();

  public ModRecipeProvider(DataGenerator pGenerator) {
    super(pGenerator);
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
    LOGGER.debug("HELLO from buildSausageRecipes in ModRecipeProvider");

    // Loop through all the items in the raw sausage tag
    for (RegistryObject<Item> pRawSausage : RAW_SAUSAGES) {
      Item rawSausage = pRawSausage.get();
      Item cookedSausage = getCookedSausage(rawSausage);
      if (cookedSausage == null) {
        LOGGER.debug("No cooked sausage found for {}", rawSausage.getRegistryName());
        continue;
      }
      LOGGER.debug("Adding sausage recipes for {}", rawSausage.getRegistryName());
      addCampfireRecipe(pFinishedRecipeConsumer, rawSausage, cookedSausage, 0.0F, 600);
      addSmokingRecipe(pFinishedRecipeConsumer, rawSausage, cookedSausage, 0.0F, 200);
      addSmeltingRecipe(pFinishedRecipeConsumer, rawSausage, cookedSausage, 0.0F, 200);
    }
  }

  private void buildMiscRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
    LOGGER.debug("HELLO from buildMiscRecipes in ModRecipeProvider");

    // Potatoes -> Potato Quarters
    ShapelessRecipeBuilder.shapeless(ModItems.POTATO_QUARTER.get(), 4)
        .requires(Items.POTATO)
        .unlockedBy("has_item", has(Items.POTATO))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "crafting/potato_quarter_x4"));
    SingleItemRecipeBuilder.stonecutting(Ingredient.of(Items.POTATO), ModItems.POTATO_QUARTER.get(),
            4)
        .unlockedBy("has_item", has(Items.POTATO))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "stonecutting/potato_quarter_x4"));

    // Tinplate or forge:plates/tin -> Tin Can
    ShapedRecipeBuilder.shaped(ModItems.TIN_CAN.get(), 2).pattern("T T").pattern(" T ")
        .define('T', Ingredient.fromValues(Stream.of(
            new ItemValue(ModItems.TINPLATE.get().getDefaultInstance()),
            new Ingredient.TagValue(ItemTags.create(new ResourceLocation("forge", "plates/tin"))))))
        .unlockedBy("has_item", has(ModItems.TINPLATE.get()))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "crafting/tin_can_x2"));

    // Tin + Steel Plates -> Tinplate
    ShapedRecipeBuilder.shaped(ModItems.TINPLATE.get(), 3).pattern("T").pattern("S").pattern("S")
        .define('T', Ingredient.of(ItemTags.create(new ResourceLocation("forge", "plates/tin"))))
        .define('S', Ingredient.of(ItemTags.create(new ResourceLocation("forge", "plates/steel"))))
        .unlockedBy("has_tin", has(ItemTags.create(new ResourceLocation("forge", "plates/tin")))
        )
        .unlockedBy("has_steel",
            has(ItemTags.create(new ResourceLocation("forge", "plates/steel"))))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "crafting/tinplate_x2"));


  }


  @Override
  protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
    LOGGER.debug("HELLO from buildCraftingRecipes in ModRecipeProvider");
    buildSausageRecipes(pFinishedRecipeConsumer);
    buildMiscRecipes(pFinishedRecipeConsumer);
  }
}

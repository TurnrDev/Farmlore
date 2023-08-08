package dev.turnr.bangers_and_mash.datagen;

import com.mojang.logging.LogUtils;
import dev.turnr.bangers_and_mash.BangersAndMash;
import dev.turnr.bangers_and_mash.items.GenericItems;
import dev.turnr.bangers_and_mash.items.Food;
import dev.turnr.bangers_and_mash.items.Ingredients;
import dev.turnr.bangers_and_mash.recipe.food_processor.FoodProcessorRecipe;
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
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
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
    for (RegistryObject<Item> pRawSausage : Food.Tags.RAW_SAUSAGES) {
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
    ShapelessRecipeBuilder.shapeless(Food.Items.POTATO_QUARTER.get(), 4)
        .requires(Items.POTATO)
        .unlockedBy("has_item", has(Items.POTATO))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "crafting/potato_quarter_x4"));
    SingleItemRecipeBuilder.stonecutting(Ingredient.of(Items.POTATO), Food.Items.POTATO_QUARTER.get(),
            4)
        .unlockedBy("has_item", has(Items.POTATO))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "stonecutting/potato_quarter_x4"));

    // forge:plates/tin / steel / aluminum -> Tin Can
    ShapedRecipeBuilder.shaped(GenericItems.METAL_CAN.get(), 2).pattern("# #").pattern(" # ")
        .define('#', Ingredient.fromValues(Stream.of(
            new Ingredient.TagValue(ItemTags.create(new ResourceLocation("forge", "plates/tin"))),
            new Ingredient.TagValue(ItemTags.create(new ResourceLocation("forge", "plates/steel"))),
            new Ingredient.TagValue(
                ItemTags.create(new ResourceLocation("forge", "plates/aluminum"))))))
        .unlockedBy("has_plates", has(ItemTags.create(new ResourceLocation("forge", "plates"))))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "crafting/metal_can_x2"));

    ShapedRecipeBuilder.shaped(GenericItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get(), 1)
        .pattern(" n ")
        .pattern("nin")
        .pattern(" n ")
        .define('i', Ingredient.of(Items.IRON_INGOT))
        .define('n', Ingredient.of(Items.IRON_NUGGET))
        .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
        .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "crafting/food_processor_attachment_steel_blade"));

    ShapedRecipeBuilder.shaped(GenericItems.FOOD_PROCESSOR_ATTACHMENT_DOUGH_HOOK.get(), 1)
        .pattern(" n ")
        .pattern("n n")
        .pattern("  n")
        .define('n', Ingredient.of(Items.IRON_NUGGET))
        .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "crafting/food_processor_attachment_dough_hook"));

    ShapedRecipeBuilder.shaped(GenericItems.FOOD_PROCESSOR_ATTACHMENT_DOUGH_BLADE.get(), 1)
        .pattern("n")
        .pattern("i")
        .pattern("n")
        .define('i', Ingredient.of(Items.IRON_INGOT))
        .define('n', Ingredient.of(Items.IRON_NUGGET))
        .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
        .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "crafting/food_processor_attachment_dough_blade"));

    ShapedRecipeBuilder.shaped(GenericItems.FOOD_PROCESSOR_ATTACHMENT_SHREDDING_DISC.get(), 1)
        .pattern("s")
        .pattern("n")
        .define('n', Ingredient.of(Items.IRON_NUGGET))
        .define('s', ItemTags.create(new ResourceLocation("forge", "plates/iron")))
        .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
        .unlockedBy("has_iron_plate", has(ItemTags.create(new ResourceLocation("forge", "plates/iron"))))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "crafting/food_processor_attachment_shredding_disc"));

    ShapedRecipeBuilder.shaped(GenericItems.FOOD_PROCESSOR_ATTACHMENT_JUICER.get(), 1)
        .pattern("n")
        .pattern("s")
        .define('n', Ingredient.of(Items.IRON_NUGGET))
        .define('s', ItemTags.create(new ResourceLocation("forge", "plates/iron")))
        .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
        .unlockedBy("has_iron_plate", has(ItemTags.create(new ResourceLocation("forge", "plates/iron"))))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "crafting/food_processor_attachment_juicer"));

    ShapedRecipeBuilder.shaped(GenericItems.FOOD_PROCESSOR_ATTACHMENT_MILL.get(), 1)
        .pattern(" n ")
        .pattern("n n")
        .pattern(" n ")
        .define('n', Ingredient.of(Items.IRON_NUGGET))
        .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "crafting/food_processor_attachment_mill"));

    ShapedRecipeBuilder.shaped(GenericItems.FOOD_PROCESSOR_ATTACHMENT_WHISK.get(), 1)
        .pattern(" n ")
        .pattern("sns")
        .pattern("sss")
        .define('n', Ingredient.of(Items.IRON_NUGGET))
        .define('s', ItemTags.create(new ResourceLocation("forge", "plates/iron")))
        .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
        .unlockedBy("has_iron_plate", has(ItemTags.create(new ResourceLocation("forge", "plates/iron"))))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "crafting/food_processor_attachment_whisk"));

    FoodProcessorRecipeBuilder.foodProcessor(Ingredients.RAW_MINCED_PORK.get(), 1)
        .setAttachment(GenericItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get())
        .requires(Items.PORKCHOP, 1)
        .unlockedBy("has_porkchop", has(Items.PORKCHOP))
        .unlockedBy("has_food_processor_attachment_steel_blade",
            has(GenericItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get()))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "food_processor/minced_pork"));

    FoodProcessorRecipeBuilder.foodProcessor(Ingredients.RAW_MINCED_BEEF.get(), 1)
        .setAttachment(GenericItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get())
        .requires(Items.BEEF, 1)
        .unlockedBy("has_beef", has(Items.BEEF))
        .unlockedBy("has_food_processor_attachment_steel_blade",
            has(GenericItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get()))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "food_processor/minced_beef"));

    FoodProcessorRecipeBuilder.foodProcessor(Food.Items.SOSIG.get(), 1)
        .setAttachment(Items.DRAGON_EGG)
        .requires(Items.PORKCHOP, 1)
        .unlockedBy("has_porkchop", has(Items.PORKCHOP))
        .unlockedBy("has_dragon_egg", has(Items.DRAGON_EGG))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(BangersAndMash.MOD_ID, "food_processor/sosig"));

  }


  @Override
  protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
    LOGGER.debug("HELLO from buildCraftingRecipes in ModRecipeProvider");
    buildSausageRecipes(pFinishedRecipeConsumer);
    buildMiscRecipes(pFinishedRecipeConsumer);
  }
}

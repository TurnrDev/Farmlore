package dev.turnr.farmlore.datagen;

import static dev.turnr.farmlore.items.EdibleItems.SAUSAGE_MAPPING;

import com.google.common.collect.ImmutableList;
import com.mojang.logging.LogUtils;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.turnr.farmlore.Farmlore;
import dev.turnr.farmlore.blocks.AllBlocks;
import dev.turnr.farmlore.datagen.buiders.FoodProcessorRecipeBuilder;
import dev.turnr.farmlore.items.EdibleItems;
import dev.turnr.farmlore.items.OtherItems;
import dev.turnr.farmlore.items.ToolItems;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

public class FarmloreRecipeProvider extends RecipeProvider implements IConditionBuilder {

  public static final ImmutableList<ItemLike> ELDORITE_SMELTABLES = ImmutableList.of(
      Item.byBlock(AllBlocks.ELDORITE_ORE.get()),
      Item.byBlock(AllBlocks.DEEPSLATE_ELDORITE_ORE.get()),
      OtherItems.RAW_ELDORITE.get()
  );
  private static final Logger LOGGER = LogUtils.getLogger();

  public FarmloreRecipeProvider(PackOutput pOutput) {
    super(pOutput);
  }


  private static void storageRecipes(
      Consumer<FinishedRecipe> pFinishedRecipeConsumer,
      RecipeCategory pUnpackedCategory,
      ItemLike pUnpacked,
      RecipeCategory pPackedCategory,
      int pDensity,
      ItemLike pPacked,
      String pPackedName,
      @Nullable String pPackedGroup,
      String pUnpackedName,
      @Nullable String pUnpackedGroup
  ) {
    ShapelessRecipeBuilder.shapeless(pUnpackedCategory, pUnpacked, pDensity).requires(pPacked)
        .group(pUnpackedGroup).unlockedBy(getHasName(pPacked), has(pPacked))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(Farmlore.MOD_ID, "crafting/" + pUnpackedName));

    ShapelessRecipeBuilder.shapeless(pPackedCategory, pPacked).requires(pUnpacked, pDensity)
        .group(pPackedGroup).unlockedBy(getHasName(pUnpacked), has(pUnpacked))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(Farmlore.MOD_ID, "crafting/" + pPackedName));
  }

  private void addSmokingRecipe(Consumer<FinishedRecipe> finishedRecipeConsumer, Item input,
      Item output, float pExperience, int pCookingTime,
      @Nullable String pGroup) {
    Ingredient inputIngredient = Ingredient.of(input);
    ResourceLocation recipeId = new ResourceLocation(Farmlore.MOD_ID,
        "smoking/" + ForgeRegistries.ITEMS.getKey(output).getPath() + "_from_"
            + ForgeRegistries.ITEMS.getKey(input.asItem()).getPath());
    SimpleCookingRecipeBuilder.smoking(inputIngredient, RecipeCategory.FOOD, output, pExperience,
            pCookingTime).group(pGroup)
        .unlockedBy(getHasName(input), has(input)).save(finishedRecipeConsumer, recipeId);
  }

  private void addCampfireRecipe(Consumer<FinishedRecipe> pWriter, Item input,
      Item output, float pExperience, int pCookingTime,
      @Nullable String pGroup) {
    Ingredient inputIngredient = Ingredient.of(input);
    ResourceLocation recipeId = new ResourceLocation(Farmlore.MOD_ID,
        "campfire_cooking/" + ForgeRegistries.ITEMS.getKey(output).getPath() + "_from_"
            + ForgeRegistries.ITEMS.getKey(input.asItem()).getPath());
    SimpleCookingRecipeBuilder.campfireCooking(inputIngredient, RecipeCategory.FOOD, output,
            pExperience,
            pCookingTime).group(pGroup)
        .unlockedBy(getHasName(input), has(input)).save(pWriter, recipeId);
  }

  private void addSmeltingRecipe(Consumer<FinishedRecipe> pWriter, ItemLike input,
      RecipeCategory pCategory,
      Item output, float pExperience, int pCookingTime,
      @Nullable String pGroup) {
    Ingredient inputIngredient = Ingredient.of(input);
    ResourceLocation recipeId = new ResourceLocation(Farmlore.MOD_ID,
        "smelting/" + ForgeRegistries.ITEMS.getKey(output).getPath() + "_from_"
            + ForgeRegistries.ITEMS.getKey(input.asItem()).getPath());
    SimpleCookingRecipeBuilder.smelting(inputIngredient, pCategory, output, pExperience,
            pCookingTime).group(pGroup)
        .unlockedBy(getHasName(input), has(input)).save(pWriter, recipeId);
  }

  private void addBlastingRecipe(Consumer<FinishedRecipe> pWriter, ItemLike input,
      RecipeCategory pCategory,
      Item output, float pExperience, int pCookingTime,
      @Nullable String pGroup) {
    Ingredient inputIngredient = Ingredient.of(input);
    ResourceLocation recipeId = new ResourceLocation(Farmlore.MOD_ID,
        "blasting/" + ForgeRegistries.ITEMS.getKey(output).getPath() + "_from_"
            + ForgeRegistries.ITEMS.getKey(input.asItem()).getPath());
    SimpleCookingRecipeBuilder.blasting(inputIngredient, pCategory, output, pExperience,
            pCookingTime).group(pGroup)
        .unlockedBy(getHasName(input), has(input)).save(pWriter, recipeId);
  }

  private void buildSausageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
    LOGGER.debug("HELLO from buildSausageRecipes in FarmloreRecipeProvider");

    // Loop through all the items in the raw sausage tag
    for (Map.Entry<RegistryEntry<Item>, RegistryEntry<Item>> entry : SAUSAGE_MAPPING.entrySet()) {
      Item rawSausage = entry.getKey().get();
      Item cookedSausage = entry.getValue().get();
      addCampfireRecipe(pFinishedRecipeConsumer, rawSausage, cookedSausage,
          0.0F, 600, "cooked_sausage");
      addSmokingRecipe(pFinishedRecipeConsumer, rawSausage, cookedSausage,
          0.0F, 200, "cooked_sausage");
      addSmeltingRecipe(pFinishedRecipeConsumer, rawSausage, RecipeCategory.FOOD, cookedSausage,
          0.0F, 200, "cooked_sausage");
    }

    // Cumberland Sausage is made with chopped pork instead. Made with black pepper.

    FoodProcessorRecipeBuilder.foodProcessor(EdibleItems.GLOUCESTER_SAUSAGE.get(), 1)
        .setAttachment(ToolItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get())
        .requires(OtherItems.MINCED_PORK.get())
        .requires(OtherItems.SAGE.get())
        .unlockedBy("has_minced_pork", has(OtherItems.MINCED_PORK.get()))
        .unlockedBy("has_sage", has(OtherItems.SAGE.get()))
        .unlockedBy("has_food_processor_attachment_steel_blade",
            has(ToolItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get()))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(Farmlore.MOD_ID, "food_processor/gloucester_sausage"));

    FoodProcessorRecipeBuilder.foodProcessor(EdibleItems.LINCOLNSHIRE_SAUSAGE.get(), 1)
        .setAttachment(ToolItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get())
        .requires(OtherItems.MINCED_PORK.get())
        .requires(OtherItems.THYME.get())
        .unlockedBy("has_minced_pork", has(OtherItems.MINCED_PORK.get()))
        .unlockedBy("has_thyme", has(OtherItems.THYME.get()))
        .unlockedBy("has_food_processor_attachment_steel_blade",
            has(ToolItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get()))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(Farmlore.MOD_ID, "food_processor/lincolnshire_sausage"));

    // Manchester Sausage has Cloves, Ginger, Nutmeg, Mace and White Pepper. TODO: Add these

    // Marylebone Sausage has Mace, Sage and Ginger.

    // Oxford Sausage has Sage, Marjoram(?), Lemon, Pork and Veal.

    FoodProcessorRecipeBuilder.foodProcessor(EdibleItems.PORK_APPLE_SAUSAGE.get(), 1)
        .setAttachment(ToolItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get())
        .requires(OtherItems.MINCED_PORK.get())
        .requires(Items.APPLE)
        .unlockedBy("has_minced_pork", has(OtherItems.MINCED_PORK.get()))
        .unlockedBy("has_apple", has(Items.APPLE))
        .unlockedBy("has_food_processor_attachment_steel_blade",
            has(ToolItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get()))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(Farmlore.MOD_ID, "food_processor/pork_apple_sausage"));

    FoodProcessorRecipeBuilder.foodProcessor(EdibleItems.TOMATO_SAUSAGE.get(), 1)
        .setAttachment(ToolItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get())
        .requires(OtherItems.MINCED_PORK.get())
        .requires(ItemTags.create(new ResourceLocation("forge", "vegetables/tomato")))
        .unlockedBy("has_minced_pork", has(OtherItems.MINCED_PORK.get()))
        .unlockedBy("has_tomato",
            has(ItemTags.create(new ResourceLocation("forge", "vegetables/tomato"))))
        .unlockedBy("has_food_processor_attachment_steel_blade",
            has(ToolItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get()))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(Farmlore.MOD_ID, "food_processor/tomato_sausage"));

    // Yorkshire Sausage has Cayenne, Nutmeg, White Pepper and Mace.

  }

  private void buildMiscRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
    LOGGER.debug("HELLO from buildMiscRecipes in FarmloreRecipeProvider");

    // forge:plates/tin / steel / aluminum -> Tin Can
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
            ToolItems.METAL_CAN.get(), 2).pattern("# #").pattern(" # ")
        .define('#', Ingredient.fromValues(Stream.of(
            new Ingredient.TagValue(ItemTags.create(new ResourceLocation("forge", "plates/tin"))),
            new Ingredient.TagValue(ItemTags.create(new ResourceLocation("forge", "plates/steel"))),
            new Ingredient.TagValue(
                ItemTags.create(new ResourceLocation("forge", "plates/aluminum"))))))
        .unlockedBy("has_plates", has(ItemTags.create(new ResourceLocation("forge", "plates"))))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(Farmlore.MOD_ID, "crafting/metal_can_x2"));

    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,
            ToolItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get(), 1)
        .pattern(" n ")
        .pattern("nin")
        .pattern(" n ")
        .define('i', Ingredient.of(Items.IRON_INGOT))
        .define('n', Ingredient.of(Items.IRON_NUGGET))
        .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
        .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(Farmlore.MOD_ID,
                "crafting/food_processor_attachment_steel_blade"));

    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,
            ToolItems.FOOD_PROCESSOR_ATTACHMENT_DOUGH_HOOK.get(), 1)
        .pattern(" n ")
        .pattern("n n")
        .pattern("  n")
        .define('n', Ingredient.of(Items.IRON_NUGGET))
        .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(Farmlore.MOD_ID,
                "crafting/food_processor_attachment_dough_hook"));

    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,
            ToolItems.FOOD_PROCESSOR_ATTACHMENT_SHREDDING_DISC.get(), 1)
        .pattern("s")
        .pattern("n")
        .define('n', Ingredient.of(Items.IRON_NUGGET))
        .define('s', ItemTags.create(new ResourceLocation("forge", "plates/iron")))
        .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
        .unlockedBy("has_iron_plate",
            has(ItemTags.create(new ResourceLocation("forge", "plates/iron"))))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(Farmlore.MOD_ID,
                "crafting/food_processor_attachment_shredding_disc"));

    FoodProcessorRecipeBuilder.foodProcessor(OtherItems.MINCED_PORK.get(), 1)
        .setAttachment(ToolItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get())
        .requires(Items.PORKCHOP, 1)
        .unlockedBy("has_porkchop", has(Items.PORKCHOP))
        .unlockedBy("has_food_processor_attachment_steel_blade",
            has(ToolItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get()))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(Farmlore.MOD_ID, "food_processor/minced_pork"));

    FoodProcessorRecipeBuilder.foodProcessor(OtherItems.MINCED_BEEF.get(), 1)
        .setAttachment(ToolItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get())
        .requires(Items.BEEF, 1)
        .unlockedBy("has_beef", has(Items.BEEF))
        .unlockedBy("has_food_processor_attachment_steel_blade",
            has(ToolItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get()))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(Farmlore.MOD_ID, "food_processor/minced_beef"));

    SimpleCookingRecipeBuilder.smelting(Ingredient.of(OtherItems.MINCED_PORK.get()),
            RecipeCategory.FOOD,
            OtherItems.COOKED_MINCED_PORK.get(), 0.0f, 200)
        .unlockedBy("has_minced_pork", has(OtherItems.MINCED_PORK.get()))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(Farmlore.MOD_ID, "smelting/cooked_minced_pork"));

    SimpleCookingRecipeBuilder.smelting(Ingredient.of(OtherItems.MINCED_BEEF.get()),
            RecipeCategory.FOOD,
            OtherItems.COOKED_MINCED_BEEF.get(), 0.0f, 200)
        .unlockedBy("has_minced_beef", has(OtherItems.MINCED_BEEF.get()))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(Farmlore.MOD_ID, "smelting/cooked_minced_beef"));

    // Wheat -> Burlap

    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, OtherItems.BURLAP.get(), 3)
        .pattern("###")
        .define('#', Ingredient.of(Items.WHEAT))
        .unlockedBy("has_wheat", has(Items.WHEAT))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(Farmlore.MOD_ID, "crafting/burlap"));

    // Burlap -> Burlap Sack
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, OtherItems.BURLAP_SACK.get(), 1)
        .pattern("# #")
        .pattern(" # ")
        .define('#', Ingredient.of(OtherItems.BURLAP.get()))
        .unlockedBy("has_burlap", has(OtherItems.BURLAP.get()))
        .save(pFinishedRecipeConsumer,
            new ResourceLocation(Farmlore.MOD_ID, "crafting/burlap_sack"));

    // Eldorite
    for (ItemLike itemlike : ELDORITE_SMELTABLES) {
      Item item = itemlike.asItem();
      addSmeltingRecipe(pFinishedRecipeConsumer, item, RecipeCategory.MISC,
          OtherItems.ELDORITE_INGOT.get(), 0.7F, 200, "eldorite_ingot");
      addBlastingRecipe(pFinishedRecipeConsumer, item, RecipeCategory.MISC,
          OtherItems.ELDORITE_INGOT.get(), 0.7F, 100, "eldorite_ingot");
    }

    storageRecipes(pFinishedRecipeConsumer, RecipeCategory.MISC,
        OtherItems.ELDORITE_NUGGET.get(), RecipeCategory.MISC,
        9, OtherItems.ELDORITE_INGOT.get(), "eldorite_ingot_from_nuggets", "eldorite_ingot",
        "eldorite_nugget", "eldorite_nugget");

    storageRecipes(pFinishedRecipeConsumer, RecipeCategory.MISC,
        OtherItems.ELDORITE_INGOT.get(), RecipeCategory.MISC,
        9, AllBlocks.ELDORITE_BLOCK.get(), "eldorite_block", "eldorite_block", "eldorite_ingot",
        "eldorite_ingot");

    storageRecipes(pFinishedRecipeConsumer, RecipeCategory.MISC,
        OtherItems.RAW_ELDORITE.get(), RecipeCategory.MISC,
        9, AllBlocks.RAW_ELDORITE_BLOCK.get(), "raw_eldorite_block", "raw_eldorite_block",
        "raw_eldorite",
        "raw_eldorite");

  }


  @Override
  protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
    LOGGER.debug("HELLO from buildCraftingRecipes in FarmloreRecipeProvider");
    buildSausageRecipes(pWriter);
    buildMiscRecipes(pWriter);
  }
}

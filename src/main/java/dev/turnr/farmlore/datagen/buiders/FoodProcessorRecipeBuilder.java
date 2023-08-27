package dev.turnr.farmlore.datagen.buiders;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.turnr.farmlore.recipe.food_processor.FoodProcessorRecipe;
import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

public class FoodProcessorRecipeBuilder implements RecipeBuilder {

  private final Item output;
  private final int count;
  private final List<Ingredient> ingredients = Lists.newArrayList();
  private final Advancement.Builder advancement = Advancement.Builder.advancement();
  private Ingredient attachment;


  public FoodProcessorRecipeBuilder(Item output, int count) {
    this.output = output;
    this.count = count;
  }

  public static FoodProcessorRecipeBuilder foodProcessor(Item result) {
    return new FoodProcessorRecipeBuilder(result, 1);
  }

  public static FoodProcessorRecipeBuilder foodProcessor(Item result, int count) {
    return new FoodProcessorRecipeBuilder(result, count);
  }

  public FoodProcessorRecipeBuilder requires(TagKey<Item> pTag) {
    return this.requires(Ingredient.of(pTag));
  }

  public FoodProcessorRecipeBuilder requires(ItemLike pItem) {
    return this.requires(pItem, 1);
  }

  public FoodProcessorRecipeBuilder requires(ItemLike pItem, int pQuantity) {
    for (int i = 0; i < pQuantity; ++i) {
      this.requires(Ingredient.of(pItem));
    }

    return this;
  }

  public FoodProcessorRecipeBuilder requires(Ingredient pIngredient) {
    return this.requires(pIngredient, 1);
  }

  public FoodProcessorRecipeBuilder requires(Ingredient pIngredient, int pQuantity) {
    for (int i = 0; i < pQuantity; ++i) {
      this.ingredients.add(pIngredient);
    }

    return this;
  }

  public FoodProcessorRecipeBuilder setAttachment(TagKey<Item> pTag) {
    return this.setAttachment(Ingredient.of(pTag));
  }

  public FoodProcessorRecipeBuilder setAttachment(ItemLike pItem) {
    return this.setAttachment(Ingredient.of(pItem));
  }

  public FoodProcessorRecipeBuilder setAttachment(Ingredient pIngredient) {
    this.attachment = pIngredient;
    return this;
  }

  public FoodProcessorRecipeBuilder unlockedBy(String pCriterionName,
      CriterionTriggerInstance pCriterionTrigger) {
    this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
    return this;
  }

  public FoodProcessorRecipeBuilder group(@Nullable String pGroupName) {
    throw new UnsupportedOperationException("this recipe does not support recipe groups");
  }

  public Item getResult() {
    return this.output;
  }

  public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
    this.ensureValid(pRecipeId);
    this.advancement.parent(new ResourceLocation("recipes/root"))
        .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId)).rewards(
            AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
    pFinishedRecipeConsumer.accept(
        new FoodProcessorRecipeBuilder.Result(pRecipeId, this.output, this.count, this.attachment,
            this.ingredients, this.advancement, new ResourceLocation(pRecipeId.getNamespace(),
            "recipes/" + pRecipeId.getPath())));
  }

  private void ensureValid(ResourceLocation pId) {
    if (this.attachment == null) {
      throw new IllegalStateException("No attachment defined for recipe " + pId + "!");
    }

    if (this.advancement.getCriteria().isEmpty()) {
      throw new IllegalStateException("No way of obtaining recipe " + pId);
    }
  }

  public static class Result implements FinishedRecipe {

    private final ResourceLocation id;
    private final Item output;
    private final int count;
    private final Ingredient attachment;
    private final List<Ingredient> ingredients;
    private final Advancement.Builder advancement;
    private final ResourceLocation advancementId;

    public Result(ResourceLocation pId, Item pOutput, int pCount, Ingredient pAttachment,
        List<Ingredient> pIngredients, Advancement.Builder pAdvancement,
        ResourceLocation pAdvancementId) {
      this.id = pId;
      this.output = pOutput;
      this.count = pCount;
      this.attachment = pAttachment;
      this.ingredients = pIngredients;
      this.advancement = pAdvancement;
      this.advancementId = pAdvancementId;
    }

    public void serializeRecipeData(JsonObject pJson) {
      pJson.add("attachment", this.attachment.toJson());

      JsonArray ingredientsArray = new JsonArray();

      for (Ingredient ingredient : this.ingredients) {
        ingredientsArray.add(ingredient.toJson());
      }

      pJson.add("ingredients", ingredientsArray);
      JsonObject outputObject = new JsonObject();
      outputObject.addProperty("item", ForgeRegistries.ITEMS.getKey(this.output).toString());
      if (this.count > 1) {
        outputObject.addProperty("count", this.count);
      }

      pJson.add("output", outputObject);
    }

    public RecipeSerializer<?> getType() {
      return FoodProcessorRecipe.Serializer.INSTANCE;
    }

    public ResourceLocation getId() {
      return this.id;
    }


    @javax.annotation.Nullable
    public JsonObject serializeAdvancement() {
      return this.advancement.serializeToJson();
    }

    @Nullable
    public ResourceLocation getAdvancementId() {
      return this.advancementId;
    }
  }
}

package dev.turnr.farmlore.recipe.food_processor;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.turnr.farmlore.Farmlore;
import dev.turnr.farmlore.blockentities.FoodProcessorEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

public class FoodProcessorRecipe implements Recipe<SimpleContainer> {
  private final ResourceLocation id;
  private final ItemStack output;
  private final NonNullList<Ingredient> ingredients;
  private final Ingredient attachment;

  public FoodProcessorRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> ingredients, Ingredient attachment) {
    this.id = id;
    this.output = output;
    this.ingredients = ingredients;
    this.attachment = attachment;
  }


  /**
   * Used to check if a recipe matches current crafting inventory
   *
   * @param pContainer
   * @param pLevel
   */
  @Override
  public boolean matches(SimpleContainer pContainer, Level pLevel) {
    boolean hasCorrectAttachment = this.attachment.test(pContainer.getItem(FoodProcessorEntity.ATTACHMENT_SLOT_ID));

    if (!hasCorrectAttachment) {
      return false;
    }

    for (int i = 0; i < this.ingredients.size(); i++) {
      if (!this.ingredients.get(i).test(pContainer.getItem(i + 1))) {
        return false;
      }
    }


    return true;
  }

  @Override
  public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
    return assemble(pContainer);
  }

  public ItemStack assemble(SimpleContainer pContainer) {
    return this.output;
  }

  /**
   * Used to determine if this recipe can fit in a grid of the given width/height
   *
   * @param pWidth
   * @param pHeight
   */
  @Override
  public boolean canCraftInDimensions(int pWidth, int pHeight) {
    return true;
  }

  @Override
  public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
    return getResultItem();
  }

  /**
   * Get the result of this recipe, usually for display purposes (e.g. recipe book). If your recipe
   * has more than one possible result (e.g. it's dynamic and depends on its inputs), then return an
   * empty stack.
   */
  public ItemStack getResultItem() {
    return this.output.copy();
  }

  @Override
  public ResourceLocation getId() {
    return this.id;
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return Serializer.INSTANCE;
  }

  @Override
  public RecipeType<?> getType() {
    return Type.INSTANCE;
  }

  public static class Type implements RecipeType<FoodProcessorRecipe> {
    private Type() {
    }

    public static final Type INSTANCE = new Type();
    public static final String ID = new ResourceLocation(Farmlore.MOD_ID, "food_processor").toString();
  }

  public static class Serializer implements RecipeSerializer<FoodProcessorRecipe> {
    public static final Serializer INSTANCE = new Serializer();
    public static final ResourceLocation ID = new ResourceLocation(Farmlore.MOD_ID, "food_processor");

    @Override
    public FoodProcessorRecipe fromJson(ResourceLocation pId, JsonObject pJson) {
      ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "output"));

      Ingredient attachment = Ingredient.fromJson(GsonHelper.getAsJsonObject(pJson, "attachment"));

      JsonArray ingredientsJson = GsonHelper.getAsJsonArray(pJson, "ingredients");
      NonNullList<Ingredient> ingredients = NonNullList.withSize(ingredientsJson.size(), Ingredient.EMPTY);

      for (int i = 0; i < ingredientsJson.size(); i++) {
        ingredients.set(i, Ingredient.fromJson(ingredientsJson.get(i)));
      }

      return new FoodProcessorRecipe(pId, output, ingredients, attachment);
    }

    @Override
    public FoodProcessorRecipe fromNetwork(ResourceLocation pId, FriendlyByteBuf buf) {
      Ingredient attachment = Ingredient.fromNetwork(buf);
      NonNullList<Ingredient> ingredients = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

      for (int i = 0; i < ingredients.size(); i++) {
        ingredients.set(i, Ingredient.fromNetwork(buf));
      }

      ItemStack output = buf.readItem();
      return new FoodProcessorRecipe(pId, output, ingredients, attachment);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, FoodProcessorRecipe recipe) {
      buf.writeItem(recipe.attachment.getItems()[0]);
      buf.writeInt(recipe.ingredients.size());

      for (Ingredient ingredient : recipe.ingredients) {
        ingredient.toNetwork(buf);
      }

      buf.writeItemStack(recipe.output, false);
    }


  }

}

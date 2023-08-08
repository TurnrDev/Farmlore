package dev.turnr.bangers_and_mash.recipe.food_processor;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.turnr.bangers_and_mash.BangersAndMash;
import dev.turnr.bangers_and_mash.blocks.custom.FoodProcessor;
import dev.turnr.bangers_and_mash.blocks.entities.FoodProcessorEntity;
import dev.turnr.bangers_and_mash.items.GenericItems;
import javax.annotation.Nullable;
import net.minecraft.core.NonNullList;
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

public class GeneralFoodProcessorRecipe implements Recipe<SimpleContainer> {
  private final ResourceLocation id;
  private final ItemStack output;
  private final NonNullList<Ingredient> ingredients;

  public GeneralFoodProcessorRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> ingredients) {
    this.id = id;
    this.output = output;
    this.ingredients = ingredients;
  }


  /**
   * Used to check if a recipe matches current crafting inventory
   *
   * @param pContainer
   * @param pLevel
   */
  @Override
  public boolean matches(SimpleContainer pContainer, Level pLevel) {
    boolean hasCorrectAttachment = Ingredient.of(
            GenericItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get())
        .test(pContainer.getItem(FoodProcessorEntity.ATTACHMENT_SLOT_ID));

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

  /**
   * Returns an Item that is the result of this recipe
   *
   * @param pContainer
   */
  @Override
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

  /**
   * Get the result of this recipe, usually for display purposes (e.g. recipe book). If your recipe
   * has more than one possible result (e.g. it's dynamic and depends on its inputs), then return an
   * empty stack.
   */
  @Override
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

  public static class Type implements RecipeType<GeneralFoodProcessorRecipe> {
    private Type() {
    }

    public static final Type INSTANCE = new Type();
    public static final String ID = new ResourceLocation(BangersAndMash.MOD_ID, "food_processor").toString();
  }

  public static class Serializer implements RecipeSerializer<GeneralFoodProcessorRecipe> {
    public static final Serializer INSTANCE = new Serializer();
    public static final ResourceLocation ID = new ResourceLocation(BangersAndMash.MOD_ID, "food_processor");

    @Override
    public GeneralFoodProcessorRecipe fromJson(ResourceLocation pId, JsonObject pJson) {
      ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "output"));

      JsonArray ingredientsJson = GsonHelper.getAsJsonArray(pJson, "ingredients");
      NonNullList<Ingredient> ingredients = NonNullList.withSize(ingredientsJson.size(), Ingredient.EMPTY);

      for (int i = 0; i < ingredientsJson.size(); i++) {
        ingredients.set(i, Ingredient.fromJson(ingredientsJson.get(i)));
      }

      return new GeneralFoodProcessorRecipe(pId, output, ingredients);
    }

    @Override
    public GeneralFoodProcessorRecipe fromNetwork(ResourceLocation pId, FriendlyByteBuf buf) {
      NonNullList<Ingredient> ingredients = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

      for (int i = 0; i < ingredients.size(); i++) {
        ingredients.set(i, Ingredient.fromNetwork(buf));
      }

      ItemStack output = buf.readItem();
      return new GeneralFoodProcessorRecipe(pId, output, ingredients);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, GeneralFoodProcessorRecipe recipe) {
      buf.writeInt(recipe.ingredients.size());

      for (Ingredient ingredient : recipe.ingredients) {
        ingredient.toNetwork(buf);
      }

      buf.writeItemStack(recipe.output, false);
    }

    @Override
    public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
      return INSTANCE;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
      return ID;
    }

    @Override
    public Class<RecipeSerializer<?>> getRegistryType() {
      return Serializer.castClass(RecipeSerializer.class);
    }

    @SuppressWarnings("unchecked")
    private static <G> Class<G> castClass(Class<?> cls) {
      return (Class<G>)cls;
    }


  }

}

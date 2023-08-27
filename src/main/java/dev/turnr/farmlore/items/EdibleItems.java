package dev.turnr.farmlore.items;

import dev.turnr.farmlore.Farmlore;
import java.util.Arrays;
import java.util.List;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EdibleItems {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
      ForgeRegistries.ITEMS,
      Farmlore.MOD_ID);
  public static final RegistryObject<Item> CANNED_BAKED_BEANS = createFood(
      "canned_baked_beans",
      new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).build());
  /**
   * Potato Quarters are an intermediate step in the process of making many potato dishes. They are
   * made by cutting a potato into quarters. This can be achieved by using any blade or axe on a
   * potato, or by using the Stone-cutter, or the Create Mod's Mechanical Saw.
   */
  public static final RegistryObject<Item> POTATO_QUARTER = createFood(
      "potato_quarter", EdibleItems.Properties.POTATO_QUARTER);

  /* Potatoes */
  /**
   * Boiled Potatoes Can be made by putting a potato in a bubble stream, or by putting a potato in a
   * cauldron of water and heating it with a fire underneath.
   * TODO: Add game mechanic for heating water in a cauldron
   * TODO: Add game mechanic for bubble streams
   */
  public static final RegistryObject<Item> BOILED_POTATOES = createFood(
      "boiled_potatoes", EdibleItems.Properties.BOILED_POTATOES);
  /**
   * Mashed Potatoes, known in the UK as just "mash", is a dish prepared by mashing boiled potatoes.
   * Milk and butter are frequently used in preparation, and it is frequently whipped at the end.
   * The ideal recipe for this boiled potatoes, butter and milk. The butter and milk are added to
   * the potatoes after they have been boiled and drained.
   */
  public static final RegistryObject<Item> MASHED_POTATOES = createFood(
      "mashed_potato", EdibleItems.Properties.MASHED_POTATOES);
  /**
   * Cheesy Mash is a variant of mashed potatoes, with cheese added to the mix.
   */
  public static final RegistryObject<Item> CHEESY_MASH = createFood("cheesy_mash",
      EdibleItems.Properties.CHEESY_MASH);
  /**
   * Chips, known in the US as "French Fries", are a dish prepared by cutting potatoes into strips
   * or wedges, then deep-frying them.
   */
  public static final RegistryObject<Item> CHIPS = createFood("chips",
      EdibleItems.Properties.CHIPS);
  /**
   * The sosig is a god tier sausage. Do not mock its artwork.
   */
  public static final RegistryObject<Item> SOSIG = ITEMS.register("sosig", () -> new Item(
      new Item.Properties().food(Foods.ENCHANTED_GOLDEN_APPLE)
          .rarity(Rarity.EPIC)));
  /**
   * This is a hefty, chunky sausage that's easily identified, as it comes in a continuous spiral
   * that is usually bought by length, not by weight. Spiced with pepper, this a flavorsome sausage,
   * and an excellent all-around choice.
   */
  public static final RegistryObject<Item> COOKED_CUMBERLAND_SAUSAGE = createFood(
      "cooked_cumberland_sausage", EdibleItems.Properties.COOKED_CUMBERLAND_SAUSAGE);

  /* Cooked Sausages */
  /**
   * As the name implies, it is made with Gloucester Old Spot Pork, nicely flavored with sage.
   */
  public static final RegistryObject<Item> COOKED_GLOUCESTER_SAUSAGE = createFood(
      "cooked_gloucester_sausage", EdibleItems.Properties.COOKED_GLOUCESTER_SAUSAGE);
  /**
   * It's all herby and meaty, often heady with sage and sometimes a little thyme.
   */
  public static final RegistryObject<Item> COOKED_LINCOLNSHIRE_SAUSAGE = createFood(
      "cooked_lincolnshire_sausage", EdibleItems.Properties.COOKED_LINCOLNSHIRE_SAUSAGE);
  /**
   * This herby sausage contains cloves, ginger, nutmeg, mace, and white pepper.
   */
  public static final RegistryObject<Item> COOKED_MANCHESTER_SAUSAGE = createFood(
      "cooked_manchester_sausage", EdibleItems.Properties.COOKED_MANCHESTER_SAUSAGE);
  /**
   * Expect mace, sage, and ginger in this traditional London butcher's sausage.
   */
  public static final RegistryObject<Item> COOKED_MARYLEBONE_SAUSAGE = createFood(
      "cooked_marylebone_sausage", EdibleItems.Properties.COOKED_MARYLEBONE_SAUSAGE);
  /**
   * Savory with sage, a touch of marjoram, lemon, pork, and veal, this is a nicely refined
   * sausage.
   */
  public static final RegistryObject<Item> COOKED_OXFORD_SAUSAGE = createFood(
      "cooked_oxford_sausage", EdibleItems.Properties.COOKED_OXFORD_SAUSAGE);
  /**
   * The apple in this pork sausage opens itself up to using cider in the mix, thus creating a
   * lovely moist sausage much loved in the West Country.
   */
  public static final RegistryObject<Item> COOKED_PORK_APPLE_SAUSAGE = createFood(
      "cooked_pork_apple_sausage", EdibleItems.Properties.COOKED_PORK_APPLE_SAUSAGE);
  /**
   * Also known as Lorne, it's made from a mixture of pork and beef. Conveniently, this sausage sits
   * very well in a sandwich and is often found on the breakfast plate.
   */
  public static final RegistryObject<Item> COOKED_SQUARE_SAUSAGE = createFood(
      "cooked_square_sausage", EdibleItems.Properties.COOKED_SQUARE_SAUSAGE);
  /**
   * This coarse sausage is similar to Lincolnshire.
   */
  public static final RegistryObject<Item> COOKED_SUFFOLK_SAUSAGE = createFood(
      "cooked_suffolk_sausage", EdibleItems.Properties.COOKED_SUFFOLK_SAUSAGE);
  /**
   * with its distinctive red color and light tomato flavor, it's always a favorite with children.
   */
  public static final RegistryObject<Item> COOKED_TOMATO_SAUSAGE = createFood(
      "cooked_tomato_sausage", EdibleItems.Properties.COOKED_TOMATO_SAUSAGE);
  /**
   * Expect a sausage spiced with cayenne, a pinch of nutmeg, white pepper, and mace.
   */
  public static final RegistryObject<Item> COOKED_YORKSHIRE_SAUSAGE = createFood(
      "cooked_yorkshire_sausage", EdibleItems.Properties.COOKED_YORKSHIRE_SAUSAGE);
  /* Raw Sausages */
  public static final RegistryObject<Item> CUMBERLAND_SAUSAGE = createFood(
      "cumberland_sausage", EdibleItems.Properties.CUMBERLAND_SAUSAGE);
  public static final RegistryObject<Item> GLOUCESTER_SAUSAGE = createFood(
      "gloucester_sausage", EdibleItems.Properties.GLOUCESTER_SAUSAGE);
  public static final RegistryObject<Item> LINCOLNSHIRE_SAUSAGE = createFood(
      "lincolnshire_sausage", EdibleItems.Properties.LINCOLNSHIRE_SAUSAGE);
  public static final RegistryObject<Item> MANCHESTER_SAUSAGE = createFood(
      "manchester_sausage", EdibleItems.Properties.MANCHESTER_SAUSAGE);
  public static final RegistryObject<Item> MARYLEBONE_SAUSAGE = createFood(
      "marylebone_sausage", EdibleItems.Properties.MARYLEBONE_SAUSAGE);
  public static final RegistryObject<Item> OXFORD_SAUSAGE = createFood("oxford_sausage",
      EdibleItems.Properties.OXFORD_SAUSAGE);
  public static final RegistryObject<Item> PORK_APPLE_SAUSAGE = createFood(
      "pork_apple_sausage", EdibleItems.Properties.PORK_APPLE_SAUSAGE);
  public static final RegistryObject<Item> SQUARE_SAUSAGE = createFood("square_sausage",
      EdibleItems.Properties.SQUARE_SAUSAGE);
  public static final RegistryObject<Item> SUFFOLK_SAUSAGE = createFood(
      "suffolk_sausage", EdibleItems.Properties.SUFFOLK_SAUSAGE);
  public static final RegistryObject<Item> TOMATO_SAUSAGE = createFood("tomato_sausage",
      Properties.TOMATO_SAUSAGE);
  public static final RegistryObject<Item> YORKSHIRE_SAUSAGE = createFood(
      "yorkshire_sausage", EdibleItems.Properties.YORKSHIRE_SAUSAGE);

  public static RegistryObject<Item> createFood(String name, FoodProperties food) {
    return ITEMS.register(name,
        () -> new Item(new Item.Properties().food(food)));
  }

  public static void register() {
    ItemRegistry.register(ITEMS);
  }

  public class Properties {


    public static final FoodProperties POTATO_QUARTER = (new FoodProperties.Builder()).nutrition(1)
        .saturationMod(0.075F).build();
    public static final FoodProperties BOILED_POTATOES = (new FoodProperties.Builder()).nutrition(4)
        .saturationMod(0.6F).build();
    public static final FoodProperties MASHED_POTATOES = (new FoodProperties.Builder()).nutrition(6)
        .saturationMod(0.6F).build();
    public static final FoodProperties CHEESY_MASH = (new FoodProperties.Builder()).nutrition(7)
        .saturationMod(0.6F).build();
    public static final FoodProperties CHIPS = (new FoodProperties.Builder()).nutrition(6)
        .saturationMod(0.6F).build();
    public static final FoodProperties COOKED_CUMBERLAND_SAUSAGE = (new FoodProperties.Builder()).nutrition(
        8).saturationMod(0.8F).meat().build();
    public static final FoodProperties COOKED_GLOUCESTER_SAUSAGE = (new FoodProperties.Builder()).nutrition(
        8).saturationMod(0.8F).meat().build();
    public static final FoodProperties COOKED_LINCOLNSHIRE_SAUSAGE = (new FoodProperties.Builder()).nutrition(
        8).saturationMod(0.8F).meat().build();
    public static final FoodProperties COOKED_MANCHESTER_SAUSAGE = (new FoodProperties.Builder()).nutrition(
        8).saturationMod(0.8F).meat().build();
    public static final FoodProperties COOKED_MARYLEBONE_SAUSAGE = (new FoodProperties.Builder()).nutrition(
        8).saturationMod(0.8F).meat().build();
    public static final FoodProperties COOKED_OXFORD_SAUSAGE = (new FoodProperties.Builder()).nutrition(
        8).saturationMod(0.8F).meat().build();
    public static final FoodProperties COOKED_PORK_APPLE_SAUSAGE = (new FoodProperties.Builder()).nutrition(
        7).saturationMod(0.7F).meat().build();
    public static final FoodProperties COOKED_SQUARE_SAUSAGE = (new FoodProperties.Builder()).nutrition(
        8).saturationMod(0.8F).meat().build();
    public static final FoodProperties COOKED_SUFFOLK_SAUSAGE = (new FoodProperties.Builder()).nutrition(
        8).saturationMod(0.8F).meat().build();
    public static final FoodProperties COOKED_TOMATO_SAUSAGE = (new FoodProperties.Builder()).nutrition(
        7).saturationMod(0.7F).meat().build();
    public static final FoodProperties COOKED_YORKSHIRE_SAUSAGE = (new FoodProperties.Builder()).nutrition(
        8).saturationMod(0.8F).meat().build();
    public static final FoodProperties CUMBERLAND_SAUSAGE = (new FoodProperties.Builder()).nutrition(
        3).saturationMod(0.3F).meat().build();
    public static final FoodProperties GLOUCESTER_SAUSAGE = (new FoodProperties.Builder()).nutrition(
        3).saturationMod(0.3F).meat().build();
    public static final FoodProperties LINCOLNSHIRE_SAUSAGE = (new FoodProperties.Builder()).nutrition(
        3).saturationMod(0.3F).meat().build();
    public static final FoodProperties MANCHESTER_SAUSAGE = (new FoodProperties.Builder()).nutrition(
        3).saturationMod(0.3F).meat().build();
    public static final FoodProperties MARYLEBONE_SAUSAGE = (new FoodProperties.Builder()).nutrition(
            3)
        .saturationMod(0.3F).meat().build();
    public static final FoodProperties OXFORD_SAUSAGE = (new FoodProperties.Builder()).nutrition(3)
        .saturationMod(0.3F).meat().build();
    public static final FoodProperties PORK_APPLE_SAUSAGE = (new FoodProperties.Builder()).nutrition(
        2).saturationMod(0.2F).meat().build();
    public static final FoodProperties SQUARE_SAUSAGE = (new FoodProperties.Builder()).nutrition(3)
        .saturationMod(0.3F).meat().build();
    public static final FoodProperties SUFFOLK_SAUSAGE = (new FoodProperties.Builder()).nutrition(3)
        .saturationMod(0.3F).meat().build();
    public static final FoodProperties TOMATO_SAUSAGE = (new FoodProperties.Builder()).nutrition(2)
        .saturationMod(0.2F).meat().build();
    public static final FoodProperties YORKSHIRE_SAUSAGE = (new FoodProperties.Builder()).nutrition(
            3)
        .saturationMod(0.3F).meat().build();

  }

  public class Tags {

    public static final List<RegistryObject<Item>> COOKED_SAUSAGES = Arrays.asList(
        EdibleItems.COOKED_CUMBERLAND_SAUSAGE,
        EdibleItems.COOKED_GLOUCESTER_SAUSAGE,
        EdibleItems.COOKED_LINCOLNSHIRE_SAUSAGE,
        EdibleItems.COOKED_MANCHESTER_SAUSAGE,
        EdibleItems.COOKED_MARYLEBONE_SAUSAGE,
        EdibleItems.COOKED_OXFORD_SAUSAGE,
        EdibleItems.COOKED_PORK_APPLE_SAUSAGE,
        EdibleItems.COOKED_SQUARE_SAUSAGE,
        EdibleItems.COOKED_SUFFOLK_SAUSAGE,
        EdibleItems.COOKED_TOMATO_SAUSAGE,
        EdibleItems.COOKED_YORKSHIRE_SAUSAGE);
    public static final List<RegistryObject<Item>> RAW_SAUSAGES = Arrays.asList(
        EdibleItems.CUMBERLAND_SAUSAGE,
        EdibleItems.GLOUCESTER_SAUSAGE,
        EdibleItems.LINCOLNSHIRE_SAUSAGE,
        EdibleItems.MANCHESTER_SAUSAGE,
        EdibleItems.MARYLEBONE_SAUSAGE,
        EdibleItems.OXFORD_SAUSAGE,
        EdibleItems.PORK_APPLE_SAUSAGE,
        EdibleItems.SQUARE_SAUSAGE,
        EdibleItems.SUFFOLK_SAUSAGE,
        EdibleItems.TOMATO_SAUSAGE,
        EdibleItems.YORKSHIRE_SAUSAGE);
  }
}

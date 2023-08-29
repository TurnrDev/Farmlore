package dev.turnr.farmlore.items;

import static dev.turnr.farmlore.Farmlore.REGISTRATE;

import com.tterrag.registrate.util.entry.RegistryEntry;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class EdibleItems {

  public static final RegistryEntry<Item> CANNED_BAKED_BEANS = REGISTRATE.item("canned_baked_beans",
          Item::new)
      .properties(
          p -> p.food(new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).build()))
      .lang("Canned Baked Beans")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();

  /**
   * The sosig is a god tier sausage. Do not mock its artwork.
   */
  public static final RegistryEntry<Item> SOSIG = REGISTRATE.item("sosig", Item::new)
      .properties(p -> p.food(Foods.ENCHANTED_GOLDEN_APPLE))
      .properties(p -> p.rarity(Rarity.EPIC))
      .lang("Sosig")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  /**
   * This is a hefty, chunky sausage that's easily identified, as it comes in a continuous spiral
   * that is usually bought by length, not by weight. Spiced with pepper, this a flavorsome sausage,
   * and an excellent all-around choice.
   */
  public static final RegistryEntry<Item> COOKED_CUMBERLAND_SAUSAGE = REGISTRATE.item(
          "cooked_cumberland_sausage", Item::new)
      .properties(p -> p.food(EdibleItems.Properties.COOKED_CUMBERLAND_SAUSAGE))
      .lang("Cooked Cumberland Sausage")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  /**
   * As the name implies, it is made with Gloucester Old Spot Pork, nicely flavored with sage.
   */
  public static final RegistryEntry<Item> COOKED_GLOUCESTER_SAUSAGE = REGISTRATE.item(
          "cooked_gloucester_sausage", Item::new)
      .properties(p -> p.food(EdibleItems.Properties.COOKED_GLOUCESTER_SAUSAGE))
      .lang("Cooked Gloucester Sausage")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  /**
   * It's all herby and meaty, often heady with sage and sometimes a little thyme.
   */
  public static final RegistryEntry<Item> COOKED_LINCOLNSHIRE_SAUSAGE = REGISTRATE.item(
          "cooked_lincolnshire_sausage", Item::new)
      .properties(p -> p.food(EdibleItems.Properties.COOKED_LINCOLNSHIRE_SAUSAGE))
      .lang("Cooked Lincolnshire Sausage")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  /**
   * This herby sausage contains cloves, ginger, nutmeg, mace, and white pepper.
   */
  public static final RegistryEntry<Item> COOKED_MANCHESTER_SAUSAGE = REGISTRATE.item(
          "cooked_manchester_sausage", Item::new)
      .properties(p -> p.food(EdibleItems.Properties.COOKED_MANCHESTER_SAUSAGE))
      .lang("Cooked Manchester Sausage")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  /**
   * Expect mace, sage, and ginger in this traditional London butcher's sausage.
   */
  public static final RegistryEntry<Item> COOKED_MARYLEBONE_SAUSAGE = REGISTRATE.item(
          "cooked_marylebone_sausage", Item::new)
      .properties(p -> p.food(EdibleItems.Properties.COOKED_MARYLEBONE_SAUSAGE))
      .lang("Cooked Marylebone Sausage")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  /**
   * Savory with sage, a touch of marjoram, lemon, pork, and veal, this is a nicely refined
   * sausage.
   */
  public static final RegistryEntry<Item> COOKED_OXFORD_SAUSAGE = REGISTRATE.item(
          "cooked_oxford_sausage", Item::new)
      .properties(p -> p.food(EdibleItems.Properties.COOKED_OXFORD_SAUSAGE))
      .lang("Cooked Oxford Sausage")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  /**
   * The apple in this pork sausage opens itself up to using cider in the mix, thus creating a
   * lovely moist sausage much loved in the West Country.
   */
  public static final RegistryEntry<Item> COOKED_PORK_APPLE_SAUSAGE = REGISTRATE.item(
          "cooked_pork_apple_sausage", Item::new)
      .properties(p -> p.food(EdibleItems.Properties.COOKED_PORK_APPLE_SAUSAGE))
      .lang("Cooked Pork & Apple Sausage")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  /**
   * This coarse sausage is similar to Lincolnshire.
   */
  public static final RegistryEntry<Item> COOKED_SUFFOLK_SAUSAGE = REGISTRATE.item(
          "cooked_suffolk_sausage", Item::new)
      .properties(p -> p.food(EdibleItems.Properties.COOKED_SUFFOLK_SAUSAGE))
      .lang("Cooked Suffolk Sausage")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  /**
   * with its distinctive red color and light tomato flavor, it's always a favorite with children.
   */
  public static final RegistryEntry<Item> COOKED_TOMATO_SAUSAGE = REGISTRATE.item(
          "cooked_tomato_sausage", Item::new)
      .properties(p -> p.food(EdibleItems.Properties.COOKED_TOMATO_SAUSAGE))
      .lang("Cooked Tomato Sausage")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  /**
   * Expect a sausage spiced with cayenne, a pinch of nutmeg, white pepper, and mace.
   */
  public static final RegistryEntry<Item> COOKED_YORKSHIRE_SAUSAGE = REGISTRATE.item(
          "cooked_yorkshire_sausage", Item::new)
      .properties(p -> p.food(EdibleItems.Properties.COOKED_YORKSHIRE_SAUSAGE))
      .lang("Cooked Yorkshire Sausage")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  public static final RegistryEntry<Item> CUMBERLAND_SAUSAGE = REGISTRATE.item("cumberland_sausage",
          Item::new)
      .properties(p -> p.food(EdibleItems.Properties.CUMBERLAND_SAUSAGE))
      .lang("Cumberland Sausage")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  public static final RegistryEntry<Item> GLOUCESTER_SAUSAGE = REGISTRATE.item("gloucester_sausage",
          Item::new)
      .properties(p -> p.food(EdibleItems.Properties.GLOUCESTER_SAUSAGE))
      .lang("Gloucester Sausage")

      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  public static final RegistryEntry<Item> LINCOLNSHIRE_SAUSAGE = REGISTRATE.item(
          "lincolnshire_sausage", Item::new)
      .properties(p -> p.food(EdibleItems.Properties.LINCOLNSHIRE_SAUSAGE))
      .lang("Lincolnshire Sausage")

      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  public static final RegistryEntry<Item> MANCHESTER_SAUSAGE = REGISTRATE.item("manchester_sausage",
          Item::new)
      .properties(p -> p.food(EdibleItems.Properties.MANCHESTER_SAUSAGE))
      .lang("Manchester Sausage")

      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  public static final RegistryEntry<Item> MARYLEBONE_SAUSAGE = REGISTRATE.item("marylebone_sausage",
          Item::new)
      .properties(p -> p.food(EdibleItems.Properties.MARYLEBONE_SAUSAGE))
      .lang("Marylebone Sausage")

      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  public static final RegistryEntry<Item> OXFORD_SAUSAGE = REGISTRATE.item("oxford_sausage",
          Item::new)
      .properties(p -> p.food(EdibleItems.Properties.OXFORD_SAUSAGE))
      .lang("Oxford Sausage")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  public static final RegistryEntry<Item> PORK_APPLE_SAUSAGE = REGISTRATE.item("pork_apple_sausage",
          Item::new)
      .properties(p -> p.food(EdibleItems.Properties.PORK_APPLE_SAUSAGE))
      .lang("Pork & Apple Sausage")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  public static final RegistryEntry<Item> SUFFOLK_SAUSAGE = REGISTRATE.item("suffolk_sausage",
          Item::new)
      .properties(p -> p.food(EdibleItems.Properties.SUFFOLK_SAUSAGE))
      .lang("Suffolk Sausage")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)

      .register();
  public static final RegistryEntry<Item> TOMATO_SAUSAGE = REGISTRATE.item("tomato_sausage",
          Item::new)
      .properties(p -> p.food(EdibleItems.Properties.TOMATO_SAUSAGE))
      .lang("Tomato Sausage")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();
  public static final RegistryEntry<Item> YORKSHIRE_SAUSAGE = REGISTRATE.item("yorkshire_sausage",
          Item::new)
      .properties(p -> p.food(EdibleItems.Properties.YORKSHIRE_SAUSAGE))
      .lang("Yorkshire Sausage")
      .tab(CreativeModeTabs.FOOD_AND_DRINKS)
      .register();

  // Create a mapping or dictionary of raw sausages to cooked sausages
  public static final Map<RegistryEntry<Item>, RegistryEntry<Item>> SAUSAGE_MAPPING = new HashMap<>();

  static {
    SAUSAGE_MAPPING.put(CUMBERLAND_SAUSAGE, COOKED_CUMBERLAND_SAUSAGE);
    SAUSAGE_MAPPING.put(GLOUCESTER_SAUSAGE, COOKED_GLOUCESTER_SAUSAGE);
    SAUSAGE_MAPPING.put(LINCOLNSHIRE_SAUSAGE, COOKED_LINCOLNSHIRE_SAUSAGE);
    SAUSAGE_MAPPING.put(MANCHESTER_SAUSAGE, COOKED_MANCHESTER_SAUSAGE);
    SAUSAGE_MAPPING.put(MARYLEBONE_SAUSAGE, COOKED_MARYLEBONE_SAUSAGE);
    SAUSAGE_MAPPING.put(OXFORD_SAUSAGE, COOKED_OXFORD_SAUSAGE);
    SAUSAGE_MAPPING.put(PORK_APPLE_SAUSAGE, COOKED_PORK_APPLE_SAUSAGE);
    SAUSAGE_MAPPING.put(SUFFOLK_SAUSAGE, COOKED_SUFFOLK_SAUSAGE);
    SAUSAGE_MAPPING.put(TOMATO_SAUSAGE, COOKED_TOMATO_SAUSAGE);
    SAUSAGE_MAPPING.put(YORKSHIRE_SAUSAGE, COOKED_YORKSHIRE_SAUSAGE);
  }

  public static void register() {
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
}

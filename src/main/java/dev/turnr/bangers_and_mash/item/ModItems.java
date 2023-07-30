package dev.turnr.bangers_and_mash.item;

import dev.turnr.bangers_and_mash.BangersAndMash;
import java.util.Arrays;
import java.util.List;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
      BangersAndMash.MOD_ID);

  /* Items */
  public static final RegistryObject<Item> TIN_CAN = ITEMS.register("tin_can",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<Item> TINPLATE = ITEMS.register("tinplate",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


  /* Food */
  public static final RegistryObject<Item> CANNED_BAKED_BEANS = ModItems.registerFood(
      "canned_baked_beans", new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).build());

  /* Potatoes */

  /**
   * Potato Quarters are an intermediate step in the process of making many potato dishes. They are
   * made by cutting a potato into quarters. This can be achieved by using any blade or axe on a
   * potato, or by using the Stone-cutter, or the Create Mod's Mechanical Saw.
   */
  public static final RegistryObject<Item> POTATO_QUARTER = ModItems.registerFood(
      "potato_quarter", ModFoods.POTATO_QUARTER);


  /**
   * Boiled Potatoes Can be made by putting a potato in a bubble stream, or by putting a potato in a
   * cauldron of water and heating it with a fire underneath.
   * TODO: Add game mechanic for heating water in a cauldron
   * TODO: Add game mechanic for bubble streams
   */
  public static final RegistryObject<Item> BOILED_POTATOES = ModItems.registerFood(
      "boiled_potatoes", ModFoods.BOILED_POTATOES);

  /**
   * Mashed Potatoes, known in the UK as just "mash", is a dish prepared by mashing boiled potatoes.
   * Milk and butter are frequently used in preparation, and it is frequently whipped at the end.
   * The ideal recipe for this boiled potatoes, butter and milk. The butter and milk are added to
   * the potatoes after they have been boiled and drained.
   */
  public static final RegistryObject<Item> MASHED_POTATOES = ModItems.registerFood(
      "mashed_potato", ModFoods.MASHED_POTATOES);

  /**
   * Cheesy Mash is a variant of mashed potatoes, with cheese added to the mix.
   */
  public static final RegistryObject<Item> CHEESY_MASH = ModItems.registerFood("cheesy_mash",
      ModFoods.CHEESY_MASH);

  /**
   * Chips, known in the US as "French Fries", are a dish prepared by cutting potatoes into strips
   * or wedges, then deep-frying them.
   */
  public static final RegistryObject<Item> CHIPS = ModItems.registerFood("chips",
      ModFoods.CHIPS);

  /**
   * The sosig is a god tier sausage. Do not mock its artwork.
   */
  public static final RegistryObject<Item> SOSIG = ITEMS.register("sosig", () -> new Item(
      new Item.Properties().food(Foods.ENCHANTED_GOLDEN_APPLE).tab(CreativeModeTab.TAB_FOOD)
          .rarity(Rarity.EPIC)));

  /* Cooked Sausages */

  /**
   * This is a hefty, chunky sausage that's easily identified, as it comes in a continuous spiral
   * that is usually bought by length, not by weight. Spiced with pepper, this a flavorsome sausage,
   * and an excellent all-around choice.
   */
  public static final RegistryObject<Item> COOKED_CUMBERLAND_SAUSAGE = ModItems.registerFood(
      "cooked_cumberland_sausage", ModFoods.COOKED_CUMBERLAND_SAUSAGE);
  /**
   * As the name implies, it is made with Gloucester Old Spot Pork, nicely flavored with sage.
   */
  public static final RegistryObject<Item> COOKED_GLOUCESTER_SAUSAGE = ModItems.registerFood(
      "cooked_gloucester_sausage", ModFoods.COOKED_GLOUCESTER_SAUSAGE);
  /**
   * It's all herby and meaty, often heady with sage and sometimes a little thyme.
   */
  public static final RegistryObject<Item> COOKED_LINCOLNSHIRE_SAUSAGE = ModItems.registerFood(
      "cooked_lincolnshire_sausage", ModFoods.COOKED_LINCOLNSHIRE_SAUSAGE);
  /**
   * This herby sausage contains cloves, ginger, nutmeg, mace, and white pepper.
   */
  public static final RegistryObject<Item> COOKED_MANCHESTER_SAUSAGE = ModItems.registerFood(
      "cooked_manchester_sausage", ModFoods.COOKED_MANCHESTER_SAUSAGE);
  /**
   * Expect mace, sage, and ginger in this traditional London butcher's sausage.
   */
  public static final RegistryObject<Item> COOKED_MARYLEBONE_SAUSAGE = ModItems.registerFood(
      "cooked_marylebone_sausage", ModFoods.COOKED_MARYLEBONE_SAUSAGE);
  /**
   * Savory with sage, a touch of marjoram, lemon, pork, and veal, this is a nicely refined
   * sausage.
   */
  public static final RegistryObject<Item> COOKED_OXFORD_SAUSAGE = ModItems.registerFood(
      "cooked_oxford_sausage", ModFoods.COOKED_OXFORD_SAUSAGE);
  /**
   * The apple in this pork sausage opens itself up to using cider in the mix, thus creating a
   * lovely moist sausage much loved in the West Country.
   */
  public static final RegistryObject<Item> COOKED_PORK_APPLE_SAUSAGE = ModItems.registerFood(
      "cooked_pork_apple_sausage", ModFoods.COOKED_PORK_APPLE_SAUSAGE);
  /**
   * Also known as Lorne, it's made from a mixture of pork and beef. Conveniently, this sausage sits
   * very well in a sandwich and is often found on the breakfast plate.
   */
  public static final RegistryObject<Item> COOKED_SQUARE_SAUSAGE = ModItems.registerFood(
      "cooked_square_sausage", ModFoods.COOKED_SQUARE_SAUSAGE);
  /**
   * This coarse sausage is similar to Lincolnshire.
   */
  public static final RegistryObject<Item> COOKED_SUFFOLK_SAUSAGE = ModItems.registerFood(
      "cooked_suffolk_sausage", ModFoods.COOKED_SUFFOLK_SAUSAGE);
  /**
   * with its distinctive red color and light tomato flavor, it's always a favorite with children.
   */
  public static final RegistryObject<Item> COOKED_TOMATO_SAUSAGE = ModItems.registerFood(
      "cooked_tomato_sausage", ModFoods.COOKED_TOMATO_SAUSAGE);
  /**
   * Expect a sausage spiced with cayenne, a pinch of nutmeg, white pepper, and mace.
   */
  public static final RegistryObject<Item> COOKED_YORKSHIRE_SAUSAGE = ModItems.registerFood(
      "cooked_yorkshire_sausage", ModFoods.COOKED_YORKSHIRE_SAUSAGE);

  /* Raw Sausages */
  public static final RegistryObject<Item> CUMBERLAND_SAUSAGE = ModItems.registerFood(
      "cumberland_sausage", ModFoods.CUMBERLAND_SAUSAGE);
  public static final RegistryObject<Item> GLOUCESTER_SAUSAGE = ModItems.registerFood(
      "gloucester_sausage", ModFoods.GLOUCESTER_SAUSAGE);
  public static final RegistryObject<Item> LINCOLNSHIRE_SAUSAGE = ModItems.registerFood(
      "lincolnshire_sausage", ModFoods.LINCOLNSHIRE_SAUSAGE);
  public static final RegistryObject<Item> MANCHESTER_SAUSAGE = ModItems.registerFood(
      "manchester_sausage", ModFoods.MANCHESTER_SAUSAGE);
  public static final RegistryObject<Item> MARYLEBONE_SAUSAGE = ModItems.registerFood(
      "marylebone_sausage", ModFoods.MARYLEBONE_SAUSAGE);
  public static final RegistryObject<Item> OXFORD_SAUSAGE = ModItems.registerFood("oxford_sausage",
      ModFoods.OXFORD_SAUSAGE);
  public static final RegistryObject<Item> PORK_APPLE_SAUSAGE = ModItems.registerFood(
      "pork_apple_sausage", ModFoods.PORK_APPLE_SAUSAGE);
  public static final RegistryObject<Item> SQUARE_SAUSAGE = ModItems.registerFood("square_sausage",
      ModFoods.SQUARE_SAUSAGE);
  public static final RegistryObject<Item> SUFFOLK_SAUSAGE = ModItems.registerFood(
      "suffolk_sausage", ModFoods.SUFFOLK_SAUSAGE);
  public static final RegistryObject<Item> TOMATO_SAUSAGE = ModItems.registerFood("tomato_sausage",
      ModFoods.TOMATO_SAUSAGE);
  public static final RegistryObject<Item> YORKSHIRE_SAUSAGE = ModItems.registerFood(
      "yorkshire_sausage", ModFoods.YORKSHIRE_SAUSAGE);

  public static final List<RegistryObject<Item>> RAW_SAUSAGES = Arrays.asList(CUMBERLAND_SAUSAGE,
      GLOUCESTER_SAUSAGE, LINCOLNSHIRE_SAUSAGE, MANCHESTER_SAUSAGE, MARYLEBONE_SAUSAGE,
      OXFORD_SAUSAGE, PORK_APPLE_SAUSAGE, SQUARE_SAUSAGE, SUFFOLK_SAUSAGE, TOMATO_SAUSAGE,
      YORKSHIRE_SAUSAGE);

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }

  public static RegistryObject<Item> registerFood(String name, FoodProperties food) {
    return ITEMS.register(name,
        () -> new Item(new Item.Properties().food(food).tab(CreativeModeTab.TAB_FOOD)));
  }
}

package dev.turnr.bangers_and_mash.item;

import dev.turnr.bangers_and_mash.BangersAndMash;
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

  /**
   * The sosig is a god tier sausage. Do not mock its artwork.
   */
  public static final RegistryObject<Item> SOSIG = ITEMS.register("sosig", () -> new Item(
      new Item.Properties().food(Foods.ENCHANTED_GOLDEN_APPLE).tab(CreativeModeTab.TAB_FOOD)
          .rarity(Rarity.EPIC)));

  /* Cooked Sausages */
  public static final RegistryObject<Item> COOKED_CUMBERLAND_SAUSAGE = ModItems.registerFood(
      "cooked_cumberland_sausage", ModFoods.COOKED_CUMBERLAND_SAUSAGE);
  public static final RegistryObject<Item> COOKED_GLOUCESTER_SAUSAGE = ModItems.registerFood(
      "cooked_gloucester_sausage", ModFoods.COOKED_GLOUCESTER_SAUSAGE);
  public static final RegistryObject<Item> COOKED_LINCOLNSHIRE_SAUSAGE = ModItems.registerFood(
      "cooked_lincolnshire_sausage", ModFoods.COOKED_LINCOLNSHIRE_SAUSAGE);
  public static final RegistryObject<Item> COOKED_MANCHESTER_SAUSAGE = ModItems.registerFood(
      "cooked_manchester_sausage", ModFoods.COOKED_MANCHESTER_SAUSAGE);
  public static final RegistryObject<Item> COOKED_MARLEBONE_SAUSAGE = ModItems.registerFood(
      "cooked_marlebone_sausage", ModFoods.COOKED_MARLEBONE_SAUSAGE);
  public static final RegistryObject<Item> COOKED_OXFORD_SAUSAGE = ModItems.registerFood(
      "cooked_oxford_sausage", ModFoods.COOKED_OXFORD_SAUSAGE);
  public static final RegistryObject<Item> COOKED_PORK_APPLE_SAUSAGE = ModItems.registerFood(
      "cooked_pork_apple_sausage", ModFoods.COOKED_PORK_APPLE_SAUSAGE);
  public static final RegistryObject<Item> COOKED_SQUARE_SAUSAGE = ModItems.registerFood(
      "cooked_square_sausage", ModFoods.COOKED_SQUARE_SAUSAGE);
  public static final RegistryObject<Item> COOKED_SUFFOLK_SAUSAGE = ModItems.registerFood(
      "cooked_suffolk_sausage", ModFoods.COOKED_SUFFOLK_SAUSAGE);
  public static final RegistryObject<Item> COOKED_TOMATO_SAUSAGE = ModItems.registerFood(
      "cooked_tomato_sausage", ModFoods.COOKED_TOMATO_SAUSAGE);
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
  public static final RegistryObject<Item> MARLEBONE_SAUSAGE = ModItems.registerFood(
      "marlebone_sausage", ModFoods.MARLEBONE_SAUSAGE);
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

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }

  public static RegistryObject<Item> registerFood(String name, FoodProperties food) {
    return ITEMS.register(name,
        () -> new Item(new Item.Properties().food(food).tab(CreativeModeTab.TAB_FOOD)));
  }
}

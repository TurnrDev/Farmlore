package dev.turnr.bangers_and_mash.item;

import dev.turnr.bangers_and_mash.BangersAndMash;
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

  public static final RegistryObject<Item> CANNED_BAKED_BEANS = ITEMS.register("canned_baked_beans",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));

  public static final RegistryObject<Item> TIN_CAN = ITEMS.register("tin_can",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<Item> TINPLATE = ITEMS.register("tinplate",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<Item> SOSIG = ITEMS.register("sosig", () -> new Item(
      new Item.Properties().food(Foods.ENCHANTED_GOLDEN_APPLE).tab(CreativeModeTab.TAB_FOOD)
          .rarity(Rarity.EPIC)));

  // Cooked Sausages
  public static final RegistryObject<Item> COOKED_CUMBERLAND_SAUSAGE = ITEMS.register(
      "cooked_cumberland_sausage", () -> new Item(
          new Item.Properties().food(ModFoods.COOKED_CUMBERLAND_SAUSAGE)
              .tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> COOKED_GLOUCESTER_SAUSAGE = ITEMS.register(
      "cooked_gloucester_sausage", () -> new Item(
          new Item.Properties().food(ModFoods.COOKED_GLOUCESTER_SAUSAGE)
              .tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> COOKED_LINCOLNSHIRE_SAUSAGE = ITEMS.register(
      "cooked_lincolnshire_sausage", () -> new Item(
          new Item.Properties().food(ModFoods.COOKED_LINCOLNSHIRE_SAUSAGE)
              .tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> COOKED_MANCHESTER_SAUSAGE = ITEMS.register(
      "cooked_manchester_sausage", () -> new Item(
          new Item.Properties().food(ModFoods.COOKED_MANCHESTER_SAUSAGE)
              .tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> COOKED_MARLEBONE_SAUSAGE = ITEMS.register(
      "cooked_marlebone_sausage", () -> new Item(
          new Item.Properties().food(ModFoods.COOKED_MARLEBONE_SAUSAGE)
              .tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> COOKED_OXFORD_SAUSAGE = ITEMS.register(
      "cooked_oxford_sausage", () -> new Item(
          new Item.Properties().food(ModFoods.COOKED_OXFORD_SAUSAGE)
              .tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> COOKED_PORK_APPLE_SAUSAGE = ITEMS.register(
      "cooked_pork_apple_sausage", () -> new Item(
          new Item.Properties().food(ModFoods.COOKED_PORK_APPLE_SAUSAGE)
              .tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> COOKED_SQUARE_SAUSAGE = ITEMS.register(
      "cooked_square_sausage", () -> new Item(
          new Item.Properties().food(ModFoods.COOKED_SQUARE_SAUSAGE)
              .tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> COOKED_SUFFOLK_SAUSAGE = ITEMS.register(
      "cooked_suffolk_sausage", () -> new Item(
          new Item.Properties().food(ModFoods.COOKED_SUFFOLK_SAUSAGE)
              .tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> COOKED_TOMATO_SAUSAGE = ITEMS.register(
      "cooked_tomato_sausage", () -> new Item(
          new Item.Properties().food(ModFoods.COOKED_TOMATO_SAUSAGE)
              .tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> COOKED_YORKSHIRE_SAUSAGE = ITEMS.register(
      "cooked_yorkshire_sausage", () -> new Item(
          new Item.Properties().food(ModFoods.COOKED_YORKSHIRE_SAUSAGE)
              .tab(CreativeModeTab.TAB_FOOD)));

  // Raw Sausages
  public static final RegistryObject<Item> CUMBERLAND_SAUSAGE = ITEMS.register("cumberland_sausage",
      () -> new Item(
          new Item.Properties().food(ModFoods.CUMBERLAND_SAUSAGE).tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> GLOUCESTER_SAUSAGE = ITEMS.register("gloucester_sausage",
      () -> new Item(
          new Item.Properties().food(ModFoods.GLOUCESTER_SAUSAGE).tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> LINCOLNSHIRE_SAUSAGE = ITEMS.register(
      "lincolnshire_sausage", () -> new Item(
          new Item.Properties().food(ModFoods.LINCOLNSHIRE_SAUSAGE).tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> MANCHESTER_SAUSAGE = ITEMS.register("manchester_sausage",
      () -> new Item(
          new Item.Properties().food(ModFoods.MANCHESTER_SAUSAGE).tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> MARLEBONE_SAUSAGE = ITEMS.register("marlebone_sausage",
      () -> new Item(
          new Item.Properties().food(ModFoods.MARLEBONE_SAUSAGE).tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> OXFORD_SAUSAGE = ITEMS.register("oxford_sausage",
      () -> new Item(
          new Item.Properties().food(ModFoods.OXFORD_SAUSAGE).tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> PORK_APPLE_SAUSAGE = ITEMS.register("pork_apple_sausage",
      () -> new Item(
          new Item.Properties().food(ModFoods.PORK_APPLE_SAUSAGE).tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> SQUARE_SAUSAGE = ITEMS.register("square_sausage",
      () -> new Item(
          new Item.Properties().food(ModFoods.SQUARE_SAUSAGE).tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> SUFFOLK_SAUSAGE = ITEMS.register("suffolk_sausage",
      () -> new Item(
          new Item.Properties().food(ModFoods.SUFFOLK_SAUSAGE).tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> TOMATO_SAUSAGE = ITEMS.register("tomato_sausage",
      () -> new Item(
          new Item.Properties().food(ModFoods.TOMATO_SAUSAGE).tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> YORKSHIRE_SAUSAGE = ITEMS.register("yorkshire_sausage",
      () -> new Item(
          new Item.Properties().food(ModFoods.YORKSHIRE_SAUSAGE).tab(CreativeModeTab.TAB_FOOD)));

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}

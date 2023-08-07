package dev.turnr.bangers_and_mash.items;

import dev.turnr.bangers_and_mash.BangersAndMash;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GenericItems {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
      BangersAndMash.MOD_ID);

  public static final RegistryObject<Item> METAL_CAN = ITEMS.register("metal_can",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  /**
   * A standard attachment that's included with all food processors. The knife blade is used for
   * mixing, mincing, mashing and puréeing as well as chopping. It's sometimes called a
   * multipurpose blade.
   */
  public static final RegistryObject<Item> FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE = ITEMS.register(
      "food_processor_attachment_steel_blade",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  /**
   * This is for kneading recipes that use yeast, such as sweet or savoury bread doughs.
   */
  public static final RegistryObject<Item> FOOD_PROCESSOR_ATTACHMENT_DOUGH_HOOK = ITEMS.register(
      "food_processor_attachment_dough_hook",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  /**
   * This is for kneading recipes that use yeast, such as sweet or savoury bread doughs.
   */
  public static final RegistryObject<Item> FOOD_PROCESSOR_ATTACHMENT_DOUGH_BLADE = ITEMS.register(
      "food_processor_attachment_dough_blade",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  /**
   * This is for shredding or grating vegetables, cheese and other foods.
   */
  public static final RegistryObject<Item> FOOD_PROCESSOR_ATTACHMENT_SHREDDING_DISC = ITEMS.register(
      "food_processor_attachment_shredding_disc",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<Item> FOOD_PROCESSOR_ATTACHMENT_JUICER = ITEMS.register(
      "food_processor_attachment_juicer",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<Item> FOOD_PROCESSOR_ATTACHMENT_MILL = ITEMS.register(
      "food_processor_attachment_mill",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<Item> FOOD_PROCESSOR_ATTACHMENT_WHISK = ITEMS.register(
      "food_processor_attachment_whisk",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static void register() {
    ItemRegistry.register(ITEMS);
  }
}

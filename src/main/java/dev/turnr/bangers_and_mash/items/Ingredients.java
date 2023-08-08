package dev.turnr.bangers_and_mash.items;

import dev.turnr.bangers_and_mash.BangersAndMash;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Ingredients This class will hold items which serve purely as ingredients for food items. The
 * items will have no nutritional value, and will not be edible on their own. They will be used in
 * recipes to create food items.
 */
public class Ingredients {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
      BangersAndMash.MOD_ID);

  public static final RegistryObject<Item> PARSLEY = ITEMS.register("parsley",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> BASIL = ITEMS.register("basil",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> CORIANDER = ITEMS.register("coriander",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> MINT = ITEMS.register("mint",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> ROSEMARY = ITEMS.register("rosemary",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
  public static final RegistryObject<Item> THYME = ITEMS.register("thyme",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));

  public static final RegistryObject<Item> RAW_MINCED_PORK = ITEMS.register("minced_pork",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));

  public static final RegistryObject<Item> RAW_MINCED_BEEF = ITEMS.register("minced_beef",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));

  public static void register() {
    ItemRegistry.register(ITEMS);
  }
}

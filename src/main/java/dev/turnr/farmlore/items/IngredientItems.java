package dev.turnr.farmlore.items;

import dev.turnr.farmlore.Farmlore;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * This class will hold items which serve purely as ingredients for other recipes.
 */
public class IngredientItems {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
      Farmlore.MOD_ID);

  public static final RegistryObject<Item> PARSLEY = ITEMS.register("parsley",
      () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> BASIL = ITEMS.register("basil",
      () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> CORIANDER = ITEMS.register("coriander",
      () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> MINT = ITEMS.register("mint",
      () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> ROSEMARY = ITEMS.register("rosemary",
      () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> THYME = ITEMS.register("thyme",
      () -> new Item(new Item.Properties()));

  public static final RegistryObject<Item> SAGE = ITEMS.register("sage",
      () -> new Item(new Item.Properties()));

  public static final RegistryObject<Item> MINCED_PORK = ITEMS.register("minced_pork",
      () -> new Item(new Item.Properties()));

  public static final RegistryObject<Item> MINCED_BEEF = ITEMS.register("minced_beef",
      () -> new Item(new Item.Properties()));

  public static final RegistryObject<Item> COOKED_MINCED_PORK = ITEMS.register("cooked_minced_pork",
      () -> new Item(new Item.Properties()));

  public static final RegistryObject<Item> COOKED_MINCED_BEEF = ITEMS.register("cooked_minced_beef",
      () -> new Item(new Item.Properties()));

  public static final RegistryObject<Item> BURLAP = ITEMS.register("burlap",
      () -> new Item(new Item.Properties()));

  public static final RegistryObject<Item> RAW_ELDORITE = ITEMS.register("raw_eldorite",
      () -> new Item(new Item.Properties()));

  public static final RegistryObject<Item> ELDORITE_INGOT = ITEMS.register("eldorite_ingot",
      () -> new Item(new Item.Properties()));

  public static final RegistryObject<Item> ELDORITE_NUGGET = ITEMS.register("eldorite_nugget",
      () -> new Item(new Item.Properties()));

  public static void register() {
    ItemRegistry.register(ITEMS);
  }
}

package dev.turnr.bangers_and_mash.items;

import dev.turnr.bangers_and_mash.BangersAndMash;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Ingredients
 * This class will hold items which serve purely as ingredients for food items.
 * The items will have no nutritional value, and will not be edible on their own.
 * They will be used in recipes to create food items.
 */
public class Ingredients {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
      BangersAndMash.MOD_ID);

  public static void register() {
    ItemRegistry.register(ITEMS);
  }
}

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
   * The food processor is a block, but it is also an item. I guess I will need to implement this in ModBlocks.java instead.
   * I will leave this here for now, but I will need to remove it later.
   * It would be nice to make this tiered, much like the vanilla tools in game. I will need to look into that.
   * It might make sense for the food_processor to not be tiered, but infact, holds a tiered item inside of it,
   * such as "Gold Blade" or "Diamond Blade". This would allow for the food processor to be upgraded, and for the blade to break over time.
   * I will need to look into this. For now, it's just an item.
   */
  public static final RegistryObject<Item> FOOD_PROCESSOR = ITEMS.register("food_processor",
      () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static void register() {
    ItemRegistry.register(ITEMS);
  }
}

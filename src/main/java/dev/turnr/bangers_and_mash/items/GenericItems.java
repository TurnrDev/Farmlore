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

  public static void register() {
    ItemRegistry.register(ITEMS);
  }
}

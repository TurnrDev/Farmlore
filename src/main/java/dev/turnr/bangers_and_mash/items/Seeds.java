package dev.turnr.bangers_and_mash.items;

import dev.turnr.bangers_and_mash.BangersAndMash;
import dev.turnr.bangers_and_mash.blocks.GenericBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Seeds {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
      BangersAndMash.MOD_ID);

  public static final RegistryObject<Item> THYME = ITEMS.register("thyme_seeds",
      () -> new ItemNameBlockItem(GenericBlocks.THYME.get(), new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

  public static void register() {
    ItemRegistry.register(ITEMS);
  }

}
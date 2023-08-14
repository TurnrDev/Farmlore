package dev.turnr.bangers_and_mash.items;

import dev.turnr.bangers_and_mash.BangersAndMash;
import dev.turnr.bangers_and_mash.blocks.PlantBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PlantableItems {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
      BangersAndMash.MOD_ID);

  public static final RegistryObject<Item> PARSLEY = ITEMS.register("parsley_seeds",
      () -> new ItemNameBlockItem(PlantBlocks.PARSLEY.get(),
          new Item.Properties()));

  public static final RegistryObject<Item> BASIL = ITEMS.register("basil_seeds",
      () -> new ItemNameBlockItem(PlantBlocks.BASIL.get(),
          new Item.Properties()));

  public static final RegistryObject<Item> CORIANDER = ITEMS.register("coriander_seeds",
      () -> new ItemNameBlockItem(PlantBlocks.CORIANDER.get(),
          new Item.Properties()));

  public static final RegistryObject<Item> MINT = ITEMS.register("mint_seeds",
      () -> new ItemNameBlockItem(PlantBlocks.MINT.get(),
          new Item.Properties()));

  public static final RegistryObject<Item> ROSEMARY = ITEMS.register("rosemary_seeds",
      () -> new ItemNameBlockItem(PlantBlocks.ROSEMARY.get(),
          new Item.Properties()));

  public static final RegistryObject<Item> THYME = ITEMS.register("thyme_seeds",
      () -> new ItemNameBlockItem(PlantBlocks.THYME.get(),
          new Item.Properties()));

  public static final RegistryObject<Item> SAGE = ITEMS.register("sage_seeds",
      () -> new ItemNameBlockItem(PlantBlocks.SAGE.get(),
          new Item.Properties()));

  public static void register() {
    ItemRegistry.register(ITEMS);
  }

}
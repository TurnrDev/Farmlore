package dev.turnr.farmlore.items;

import dev.turnr.farmlore.Farmlore;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ClothingItems {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
      Farmlore.MOD_ID);

  public static final RegistryObject<Item> BURLAP_SACK = ITEMS.register("burlap_sack",
      () -> new ArmorItem(ArmorMaterials.BURLAP, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

  public static void register() {
    ItemRegistry.register(ITEMS);
  }
}

package dev.turnr.bangers_and_mash.blocks;

import dev.turnr.bangers_and_mash.BangersAndMash;
import dev.turnr.bangers_and_mash.items.ItemRegistry;
import java.util.function.Supplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GenericBlocks {

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
      ForgeRegistries.BLOCKS,
      BangersAndMash.MOD_ID);
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
      ForgeRegistries.ITEMS,
      BangersAndMash.MOD_ID);

  /**
   * It would be nice to make this tiered, much like the vanilla tools in game. I will need to look
   * into that. It might make sense for the food_processor to not be tiered, but infact, holds a
   * tiered item inside of it, such as "Gold Blade" or "Diamond Blade". This would allow for the
   * food processor to be upgraded, and for the blade to break over time. I will need to look into
   * this. For now, it's just this.
   */
  public static final RegistryObject<Block> FOOD_PROCESSOR = registerBlock("food_processor",
      () -> new Block(
          BlockBehaviour.Properties.of(Material.METAL)), CreativeModeTab.TAB_MISC);


  private static <T extends Block> RegistryObject<T> registerBlock(String name,
      Supplier<T> block, CreativeModeTab tab) {
    RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
    registerBlockItem(name, registeredBlock, tab);
    return registeredBlock;
  }

  private static <T extends Block> RegistryObject<Item> registerBlockItem(String name,
      RegistryObject<T> block, CreativeModeTab tab) {
    return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
  }

  public static void register() {
    BlockRegistry.register(BLOCKS);
    ItemRegistry.register(ITEMS);
  }
}
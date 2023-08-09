package dev.turnr.bangers_and_mash.blocks;

import dev.turnr.bangers_and_mash.BangersAndMash;
import dev.turnr.bangers_and_mash.blocks.herbs.HerbBlock;
import dev.turnr.bangers_and_mash.items.ItemRegistry;
import dev.turnr.bangers_and_mash.items.Seeds;
import java.util.function.Supplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PlantBlocks {

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
      ForgeRegistries.BLOCKS,
      BangersAndMash.MOD_ID);
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
      ForgeRegistries.ITEMS,
      BangersAndMash.MOD_ID);

  private static final BlockBehaviour.Properties HERB_PROPERTIES = BlockBehaviour.Properties.of(
      Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS);
  public static final RegistryObject<Block> PARSLEY = registerBlockWithoutBlockItem("parsley",
      () -> new HerbBlock(HERB_PROPERTIES));
  public static final RegistryObject<Block> BASIL = registerBlockWithoutBlockItem("basil",
      () -> new HerbBlock(HERB_PROPERTIES));
  public static final RegistryObject<Block> CORIANDER = registerBlockWithoutBlockItem("coriander",
      () -> new HerbBlock(HERB_PROPERTIES));
  public static final RegistryObject<Block> MINT = registerBlockWithoutBlockItem("mint",
      () -> new HerbBlock(HERB_PROPERTIES));
  public static final RegistryObject<Block> ROSEMARY = registerBlockWithoutBlockItem("rosemary",
      () -> new HerbBlock(HERB_PROPERTIES));
  public static final RegistryObject<Block> THYME = registerBlockWithoutBlockItem("thyme",
      () -> new HerbBlock(HERB_PROPERTIES));

  public static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name,
      Supplier<T> block) {
    return BLOCKS.register(name, block);
  }

  public static void register() {
    BlockRegistry.register(BLOCKS);
    ItemRegistry.register(ITEMS);
  }
}

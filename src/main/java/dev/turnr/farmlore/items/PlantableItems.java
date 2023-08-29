package dev.turnr.farmlore.items;

import static dev.turnr.farmlore.Farmlore.REGISTRATE;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.turnr.farmlore.blocks.AllBlocks;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.common.Tags.Items;

public class PlantableItems {

  public static final RegistryEntry<ItemNameBlockItem> PARSLEY_SEEDS = REGISTRATE.item(
          "parsley_seeds",
          p -> new ItemNameBlockItem(AllBlocks.PARSLEY.get(), p)).tab(CreativeModeTabs.NATURAL_BLOCKS)
      .tag(Items.SEEDS).register();

  public static final RegistryEntry<ItemNameBlockItem> BASIL_SEEDS = REGISTRATE.item("basil_seeds",
      p -> new ItemNameBlockItem(AllBlocks.BASIL.get(),
          p)).tab(CreativeModeTabs.NATURAL_BLOCKS).tag(Items.SEEDS).register();

  public static final RegistryEntry<ItemNameBlockItem> CORIANDER_SEEDS = REGISTRATE.item(
      "coriander_seeds",
      p -> new ItemNameBlockItem(AllBlocks.CORIANDER.get(),
          p)).tab(CreativeModeTabs.NATURAL_BLOCKS).tag(Items.SEEDS).register();

  public static final RegistryEntry<ItemNameBlockItem> MINT_SEEDS = REGISTRATE.item("mint_seeds",
      p -> new ItemNameBlockItem(AllBlocks.MINT.get(),
          p)).tab(CreativeModeTabs.NATURAL_BLOCKS).tag(Items.SEEDS).register();

  public static final RegistryEntry<ItemNameBlockItem> ROSEMARY_SEEDS = REGISTRATE.item(
      "rosemary_seeds",
      p -> new ItemNameBlockItem(AllBlocks.ROSEMARY.get(),
          p)).tab(CreativeModeTabs.NATURAL_BLOCKS).tag(Items.SEEDS).register();

  public static final RegistryEntry<ItemNameBlockItem> THYME_SEEDS = REGISTRATE.item("thyme_seeds",
      p -> new ItemNameBlockItem(AllBlocks.THYME.get(),
          p)).tab(CreativeModeTabs.NATURAL_BLOCKS).tag(Items.SEEDS).register();

  public static final RegistryEntry<ItemNameBlockItem> SAGE_SEEDS = REGISTRATE.item("sage_seeds",
      p -> new ItemNameBlockItem(AllBlocks.SAGE.get(),
          p)).tab(CreativeModeTabs.NATURAL_BLOCKS).tag(Items.SEEDS).register();

  public static void register() {

  }

}
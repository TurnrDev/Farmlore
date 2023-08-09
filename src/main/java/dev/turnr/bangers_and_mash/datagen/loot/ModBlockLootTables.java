package dev.turnr.bangers_and_mash.datagen.loot;

import dev.turnr.bangers_and_mash.blocks.BlockRegistry;
import dev.turnr.bangers_and_mash.blocks.GenericBlocks;
import dev.turnr.bangers_and_mash.blocks.PlantBlocks;
import dev.turnr.bangers_and_mash.blocks.herbs.HerbBlock;
import dev.turnr.bangers_and_mash.items.Ingredients;
import dev.turnr.bangers_and_mash.items.Seeds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ModBlockLootTables extends BlockLoot {

  @Override
  protected void addTables() {
    this.dropSelf(GenericBlocks.FOOD_PROCESSOR.get());

    this.addCrop(PlantBlocks.PARSLEY.get(), Ingredients.PARSLEY.get(), Seeds.PARSLEY.get());
    this.addCrop(PlantBlocks.BASIL.get(), Ingredients.BASIL.get(), Seeds.BASIL.get());
    this.addCrop(PlantBlocks.CORIANDER.get(), Ingredients.CORIANDER.get(), Seeds.CORIANDER.get());
    this.addCrop(PlantBlocks.MINT.get(), Ingredients.MINT.get(), Seeds.MINT.get());
    this.addCrop(PlantBlocks.ROSEMARY.get(), Ingredients.ROSEMARY.get(), Seeds.ROSEMARY.get());
    this.addCrop(PlantBlocks.THYME.get(), Ingredients.THYME.get(), Seeds.THYME.get());

  }

  private void addCrop(Block plant, Item crop, Item seed) {
    this.add(plant, createCropDrops(plant, crop, seed, LootItemBlockStatePropertyCondition
        .hasBlockStateProperties(plant)
        .setProperties(
            StatePropertiesPredicate.Builder.properties()
                .hasProperty(HerbBlock.AGE, HerbBlock.MAX_AGE))));
  }

  @Override
  protected @NotNull Iterable<Block> getKnownBlocks() {
    return BlockRegistry.getEntries().map(RegistryObject::get)::iterator;
  }
}

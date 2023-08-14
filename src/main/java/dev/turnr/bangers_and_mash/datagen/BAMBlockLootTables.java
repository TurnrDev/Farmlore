package dev.turnr.bangers_and_mash.datagen;

import dev.turnr.bangers_and_mash.blocks.BlockRegistry;
import dev.turnr.bangers_and_mash.blocks.GenericBlocks;
import dev.turnr.bangers_and_mash.blocks.PlantBlocks;
import dev.turnr.bangers_and_mash.blocks.herbs.HerbBlock;
import dev.turnr.bangers_and_mash.items.IngredientItems;
import dev.turnr.bangers_and_mash.items.PlantableItems;
import java.util.Set;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class BAMBlockLootTables extends BlockLootSubProvider {

  public BAMBlockLootTables() {
    super(Set.of(), FeatureFlags.REGISTRY.allFlags());
  }

  @Override
  protected void generate() {
    this.dropSelf(GenericBlocks.FOOD_PROCESSOR.get());

    this.addCrop(PlantBlocks.PARSLEY.get(), IngredientItems.PARSLEY.get(), PlantableItems.PARSLEY.get());
    this.addCrop(PlantBlocks.BASIL.get(), IngredientItems.BASIL.get(), PlantableItems.BASIL.get());
    this.addCrop(PlantBlocks.CORIANDER.get(), IngredientItems.CORIANDER.get(), PlantableItems.CORIANDER.get());
    this.addCrop(PlantBlocks.MINT.get(), IngredientItems.MINT.get(), PlantableItems.MINT.get());
    this.addCrop(PlantBlocks.ROSEMARY.get(), IngredientItems.ROSEMARY.get(), PlantableItems.ROSEMARY.get());
    this.addCrop(PlantBlocks.THYME.get(), IngredientItems.THYME.get(), PlantableItems.THYME.get());
    this.addCrop(PlantBlocks.SAGE.get(), IngredientItems.SAGE.get(), PlantableItems.SAGE.get());

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

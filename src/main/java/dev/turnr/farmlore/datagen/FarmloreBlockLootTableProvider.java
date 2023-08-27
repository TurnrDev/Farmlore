package dev.turnr.farmlore.datagen;

import dev.turnr.farmlore.blocks.BlockRegistry;
import dev.turnr.farmlore.blocks.GenericBlocks;
import dev.turnr.farmlore.blocks.PlantBlocks;
import dev.turnr.farmlore.blocks.herbs.HerbBlock;
import dev.turnr.farmlore.items.IngredientItems;
import dev.turnr.farmlore.items.PlantableItems;
import java.util.Set;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class FarmloreBlockLootTableProvider extends BlockLootSubProvider {

  public FarmloreBlockLootTableProvider() {
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

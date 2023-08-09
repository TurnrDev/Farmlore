package dev.turnr.bangers_and_mash.datagen.loot;

import dev.turnr.bangers_and_mash.blocks.BlockRegistry;
import dev.turnr.bangers_and_mash.blocks.GenericBlocks;
import dev.turnr.bangers_and_mash.blocks.herbs.HerbBlock;
import dev.turnr.bangers_and_mash.items.Ingredients;
import dev.turnr.bangers_and_mash.items.Seeds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ModBlockLootTables extends BlockLoot {

  @Override
  protected void addTables() {
    this.dropSelf(GenericBlocks.FOOD_PROCESSOR.get());

    this.add(GenericBlocks.THYME.get(),
        createCropDrops(GenericBlocks.THYME.get(), Ingredients.THYME.get(),
            Seeds.THYME.get(), LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(GenericBlocks.THYME.get())
                .setProperties(
                    StatePropertiesPredicate.Builder.properties()
                        .hasProperty(HerbBlock.AGE, HerbBlock.MAX_AGE))));

  }

  @Override
  protected @NotNull Iterable<Block> getKnownBlocks() {
    return BlockRegistry.getEntries().map(RegistryObject::get)::iterator;
  }
}

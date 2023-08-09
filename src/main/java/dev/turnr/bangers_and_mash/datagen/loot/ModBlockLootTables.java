package dev.turnr.bangers_and_mash.datagen.loot;

import dev.turnr.bangers_and_mash.blocks.BlockRegistry;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends BlockLoot {

  @Override
  protected void addTables() {
    for (Block block : getKnownBlocks()) {
      dropSelf(block);  // Lazy loop for dropping self on everything for now.
    }
  }

  @Override
  protected Iterable<Block> getKnownBlocks() {
    return BlockRegistry.getEntries().map(RegistryObject::get)::iterator;
  }
}

package dev.turnr.bangers_and_mash.datagen.loot;

import java.util.function.BiConsumer;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable.Builder;

public class ModChestLootTables extends ChestLoot {

  @Override
  public void accept(BiConsumer<ResourceLocation, Builder> biConsumer) {
    // https://youtu.be/rGURjv3jH7Y?list=PLKGarocXCE1Hut51TKKqZKqVZtKLZC48x&t=2006
    throw new UnsupportedOperationException("TODO: ModChestLootTables#accept");
  }
}

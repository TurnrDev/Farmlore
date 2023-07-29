package dev.turnr.bangers_and_mash.datagen;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import org.jetbrains.annotations.NotNull;

public class ModLootTableProvider extends LootTableProvider {

  public ModLootTableProvider(DataGenerator pGenerator) {
    super(pGenerator);
  }

  @Override
  protected void validate(Map<ResourceLocation, LootTable> map,
      @NotNull ValidationContext validationTracker) {
    map.forEach((id, table) -> LootTables.validate(validationTracker, id, table));
  }

  @Override
  protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootContextParamSet>> getTables() {
    // https://youtu.be/rGURjv3jH7Y?list=PLKGarocXCE1Hut51TKKqZKqVZtKLZC48x&t=1222
    throw new UnsupportedOperationException("TODO: ModLootTableProvider#getTables");
  }
}

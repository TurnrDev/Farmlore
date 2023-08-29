package dev.turnr.farmlore.blockentities;

import static dev.turnr.farmlore.Farmlore.REGISTRATE;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import dev.turnr.farmlore.blocks.AllBlocks;

public class AllBlockEntities {

  public static final BlockEntityEntry<FoodProcessorEntity> FOOD_PROCESSOR = REGISTRATE.blockEntity("food_processor", FoodProcessorEntity::new)
      .validBlock(AllBlocks.FOOD_PROCESSOR)
      .register();

  public static void register() {
  }

}

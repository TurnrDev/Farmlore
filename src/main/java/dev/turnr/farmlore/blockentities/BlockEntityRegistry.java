package dev.turnr.farmlore.blockentities;

import dev.turnr.farmlore.Farmlore;
import dev.turnr.farmlore.blocks.GenericBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {

  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
      ForgeRegistries.BLOCK_ENTITY_TYPES, Farmlore.MOD_ID);

  public static final RegistryObject<BlockEntityType<FoodProcessorEntity>> FOOD_PROCESSOR = BLOCK_ENTITIES.register(
      "food_processor",
      () -> BlockEntityType.Builder.of(FoodProcessorEntity::new, GenericBlocks.FOOD_PROCESSOR.get())
          .build(null));

  public static void register(IEventBus eventBus) {
    BLOCK_ENTITIES.register(eventBus);
  }

}

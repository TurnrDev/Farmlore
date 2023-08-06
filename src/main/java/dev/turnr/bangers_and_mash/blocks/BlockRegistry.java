package dev.turnr.bangers_and_mash.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {

  private static final List<DeferredRegister<Block>> REGISTERS = new ArrayList<>();

  public static void register(DeferredRegister<Block> registry) {
    REGISTERS.add(registry);
  }

  public static void register(IEventBus bus) {
    for (DeferredRegister<Block> register : REGISTERS) {
      register.register(bus);
    }
  }

  public static Stream<RegistryObject<Block>> getItems() {
    return REGISTERS.stream().flatMap(registry -> registry.getEntries().stream());
  }

}

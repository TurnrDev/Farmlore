package dev.turnr.farmlore.items;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {

  private static final List<DeferredRegister<Item>> REGISTERS = new ArrayList<>();

  public static void register(DeferredRegister<Item> registry) {
    REGISTERS.add(registry);
  }

  public static void register(IEventBus bus) {
    for (DeferredRegister<Item> register : REGISTERS) {
      register.register(bus);
    }
  }

  public static Stream<RegistryObject<Item>> getEntries() {
    return REGISTERS.stream().flatMap(registry -> registry.getEntries().stream());
  }

}

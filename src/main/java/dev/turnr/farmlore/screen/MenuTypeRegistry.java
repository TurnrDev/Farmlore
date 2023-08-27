package dev.turnr.farmlore.screen;

import dev.turnr.farmlore.Farmlore;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypeRegistry {

  public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(
      ForgeRegistries.MENU_TYPES, Farmlore.MOD_ID);

  public static final RegistryObject<MenuType<FoodProcessorMenu>> FOOD_PROCESSOR_MENU =
      registerMenuType(FoodProcessorMenu::new, "food_processor_menu");

  private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(
      IContainerFactory<T> factory, String name) {
    return MENUS.register(name, () -> IForgeMenuType.create(factory));
  }

  public static void register(IEventBus eventBus) {
    MENUS.register(eventBus);
  }
}

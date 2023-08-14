package dev.turnr.bangers_and_mash;

import com.mojang.logging.LogUtils;
import dev.turnr.bangers_and_mash.blocks.BlockRegistry;
import dev.turnr.bangers_and_mash.blocks.GenericBlocks;
import dev.turnr.bangers_and_mash.blocks.PlantBlocks;
import dev.turnr.bangers_and_mash.blocks.entities.GenericBlockEntities;
import dev.turnr.bangers_and_mash.items.EdibleItems;
import dev.turnr.bangers_and_mash.items.ToolItems;
import dev.turnr.bangers_and_mash.items.IngredientItems;
import dev.turnr.bangers_and_mash.items.ItemRegistry;
import dev.turnr.bangers_and_mash.items.PlantableItems;
import dev.turnr.bangers_and_mash.recipe.RecipeRegistry;
import dev.turnr.bangers_and_mash.screen.FoodProcessorScreen;
import dev.turnr.bangers_and_mash.screen.MenuTypeRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(BangersAndMash.MOD_ID)
public class BangersAndMash {

  public static final String MOD_ID = "bangers_and_mash";
  private static final Logger LOGGER = LogUtils.getLogger();

  public BangersAndMash() {
    IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

    PlantableItems.register();
    PlantBlocks.register();
    IngredientItems.register();
    EdibleItems.register();
    ToolItems.register();
    GenericBlocks.register();

    ItemRegistry.register(eventBus);
    BlockRegistry.register(eventBus);

    GenericBlockEntities.register(eventBus);
    MenuTypeRegistry.register(eventBus);
    RecipeRegistry.register(eventBus);

    eventBus.addListener(this::commonSetup);

    MinecraftForge.EVENT_BUS.register(this);
    eventBus.addListener(this::addCreative);
  }

  public static ResourceLocation getId(String path) {
    return new ResourceLocation(MOD_ID, path);
  }

  private void commonSetup(final FMLCommonSetupEvent event) {
    // Some common setup code
    LOGGER.info("HELLO FROM COMMON SETUP");
  }

  private void addCreative(BuildCreativeModeTabContentsEvent event) {
    if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
      for (RegistryObject<Item> item : EdibleItems.ITEMS.getEntries()) {
        event.accept(item.get());
      }
    }

    if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
      for (RegistryObject<Item> item : PlantableItems.ITEMS.getEntries()) {
        event.accept(item.get());
      }
    }

    if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
      for (RegistryObject<Item> item : ToolItems.ITEMS.getEntries()) {
        event.accept(item.get());
      }
    }
  }

  // You can use SubscribeEvent and let the Event Bus discover methods to call
  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {
    // Do something when the server starts
    LOGGER.info("HELLO from server starting");
  }

  // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
  @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
  public static class ClientModEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
      LOGGER.debug("HELLO FROM CLIENT SETUP");
      ItemBlockRenderTypes.setRenderLayer(GenericBlocks.FOOD_PROCESSOR.get(),
          RenderType.translucent());

      for (RegistryObject<Block> block : PlantBlocks.BLOCKS.getEntries()) {
        ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
      }

      MenuScreens.register(MenuTypeRegistry.FOOD_PROCESSOR_MENU.get(),
          FoodProcessorScreen::new);
    }
  }

}

package dev.turnr.farmlore;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.turnr.farmlore.blockentities.AllBlockEntities;
import dev.turnr.farmlore.blocks.AllBlocks;
import dev.turnr.farmlore.blocks.herbs.HerbBlock;
import dev.turnr.farmlore.items.OtherItems;
import dev.turnr.farmlore.items.EdibleItems;
import dev.turnr.farmlore.items.PlantableItems;
import dev.turnr.farmlore.items.ToolItems;
import dev.turnr.farmlore.recipe.RecipeRegistry;
import dev.turnr.farmlore.screen.FoodProcessorScreen;
import dev.turnr.farmlore.screen.MenuTypeRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

@Mod(Farmlore.MOD_ID)
public class Farmlore {

  public static final String MOD_ID = "turnr_farmlore";
  public static final Registrate REGISTRATE = Registrate.create(MOD_ID);
  private static final Logger LOGGER = LogUtils.getLogger();

  public Farmlore() {
    IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

    OtherItems.register();
    PlantableItems.register();
    EdibleItems.register();
    ToolItems.register();
    AllBlocks.register();

    AllBlockEntities.register();
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
  }

  private void addCreative(BuildCreativeModeTabContentsEvent event) {

  }

  // You can use SubscribeEvent and let the Event Bus discover methods to call
  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {
  }

  // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
  @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
  public static class ClientModEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
      ItemBlockRenderTypes.setRenderLayer(AllBlocks.FOOD_PROCESSOR.get(),
          RenderType.translucent());

      for (RegistryEntry<Block> block : REGISTRATE.getAll(ForgeRegistries.BLOCKS.getRegistryKey())) {
        if (block.get() instanceof HerbBlock) {
          ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
        }
      }

      MenuScreens.register(MenuTypeRegistry.FOOD_PROCESSOR_MENU.get(),
          FoodProcessorScreen::new);
    }
  }

}

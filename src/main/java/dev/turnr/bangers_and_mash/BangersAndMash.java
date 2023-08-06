package dev.turnr.bangers_and_mash;

import com.mojang.logging.LogUtils;
import dev.turnr.bangers_and_mash.blocks.BlockRegistry;
import dev.turnr.bangers_and_mash.blocks.GenericBlocks;
import dev.turnr.bangers_and_mash.items.Food;
import dev.turnr.bangers_and_mash.items.GenericItems;
import dev.turnr.bangers_and_mash.items.Ingredients;
import dev.turnr.bangers_and_mash.items.ItemRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(BangersAndMash.MOD_ID)
public class BangersAndMash {

  public static final String MOD_ID = "bangers_and_mash";
  private static final Logger LOGGER = LogUtils.getLogger();

  public BangersAndMash() {
    IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

    Food.Items.register();
    Ingredients.register();
    GenericItems.register();
    GenericBlocks.register();

    ItemRegistry.register(eventBus);
    BlockRegistry.register(eventBus);

    eventBus.addListener(this::setup);
    eventBus.addListener(this::clientSetup);

    // Register ourselves for server and other game events we are interested in
    MinecraftForge.EVENT_BUS.register(this);
  }

  public static ResourceLocation getId(String path) {
    return new ResourceLocation(MOD_ID, path);
  }

  private void clientSetup(final FMLCommonSetupEvent event) {
    // do something that can only be done on the client
    LOGGER.debug("HELLO FROM CLIENT SETUP");
    ItemBlockRenderTypes.setRenderLayer(GenericBlocks.FOOD_PROCESSOR.get(),
        RenderType.translucent());
  }

  private void setup(final FMLCommonSetupEvent event) {

    // some preinit code
    LOGGER.debug("HELLO FROM PREINIT");
  }
}

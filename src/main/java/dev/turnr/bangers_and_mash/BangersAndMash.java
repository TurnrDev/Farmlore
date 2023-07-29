package dev.turnr.bangers_and_mash;

import com.mojang.logging.LogUtils;
import dev.turnr.bangers_and_mash.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.slf4j.Logger;

@Mod(BangersAndMash.MOD_ID)
public class BangersAndMash {

  public static final String MOD_ID = "bangers_and_mash";
  private static final Logger LOGGER = LogUtils.getLogger();

  public BangersAndMash() {
    IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    ModItems.register(eventBus);
    eventBus.addListener(this::setup);

    // Register ourselves for server and other game events we are interested in
    MinecraftForge.EVENT_BUS.register(this);
  }

  public static ResourceLocation getId(String path) {
    return new ResourceLocation(MOD_ID, path);
  }

  private void setup(final FMLCommonSetupEvent event) {

    // some preinit code
    LOGGER.debug("HELLO FROM PREINIT");
  }
}

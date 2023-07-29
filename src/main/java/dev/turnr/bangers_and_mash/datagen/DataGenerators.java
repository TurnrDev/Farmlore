package dev.turnr.bangers_and_mash.datagen;

import com.mojang.logging.LogUtils;
import dev.turnr.bangers_and_mash.BangersAndMash;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.slf4j.Logger;

@EventBusSubscriber(modid = BangersAndMash.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

  private static final Logger LOGGER = LogUtils.getLogger();

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    LOGGER.info("HELLO from gatherData in DataGenerators");
    DataGenerator generator = event.getGenerator();
    ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

    generator.addProvider(new ModRecipeProvider(generator));
  }
}

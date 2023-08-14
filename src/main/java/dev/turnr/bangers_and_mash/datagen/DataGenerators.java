package dev.turnr.bangers_and_mash.datagen;

import dev.turnr.bangers_and_mash.BangersAndMash;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = BangersAndMash.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    PackOutput packOutput = generator.getPackOutput();
    ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
    CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

    generator.addProvider(event.includeServer(), new BAMRecipeProvider(packOutput));
    generator.addProvider(event.includeServer(), BAMLootTableProvider.create(packOutput));
    generator.addProvider(event.includeClient(), new BAMBlockStateProvider(packOutput, existingFileHelper));
    generator.addProvider(event.includeClient(), new BAMItemModelProvider(packOutput, existingFileHelper));

    BAMBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(), new BAMBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));
    generator.addProvider(event.includeServer(), new BAMItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));
  }
}

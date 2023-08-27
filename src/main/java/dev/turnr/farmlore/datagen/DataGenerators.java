package dev.turnr.farmlore.datagen;

import dev.turnr.farmlore.Farmlore;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = Farmlore.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    PackOutput packOutput = generator.getPackOutput();
    ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
    CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

    generator.addProvider(event.includeServer(), new FarmloreRecipeProvider(packOutput));
    generator.addProvider(event.includeServer(), FarmloreLootTableProvider.create(packOutput));
    generator.addProvider(event.includeClient(), new FarmloreBlockStateProvider(packOutput, existingFileHelper));
    generator.addProvider(event.includeClient(), new FarmloreItemModelProvider(packOutput, existingFileHelper));

    FarmloreBlockTagProvider blockTagGenerator = generator.addProvider(event.includeServer(), new FarmloreBlockTagProvider(packOutput, lookupProvider, existingFileHelper));
    generator.addProvider(event.includeServer(), new FarmloreItemTagsProvider(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));
  }
}

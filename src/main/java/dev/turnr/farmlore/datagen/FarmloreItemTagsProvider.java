package dev.turnr.farmlore.datagen;

import static dev.turnr.farmlore.Farmlore.MOD_ID;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class FarmloreItemTagsProvider extends ItemTagsProvider {

  public FarmloreItemTagsProvider(PackOutput output,
      CompletableFuture<HolderLookup.Provider> lookupProvider,
      CompletableFuture<TagLookup<Block>> blockTagLookup, ExistingFileHelper existingFileHelper) {
    super(output, lookupProvider, blockTagLookup, MOD_ID, existingFileHelper);
  }

  /**
   *
   */
  @Override
  protected void addTags(HolderLookup.Provider pProvider) {}

  /**
   * Gets a name for this provider, to use in logging.
   */
  @Override
  public String getName() {
    return "FarmloreItemTagsProvider";
  }
}

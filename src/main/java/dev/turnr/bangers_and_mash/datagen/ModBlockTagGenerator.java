package dev.turnr.bangers_and_mash.datagen;

import dev.turnr.bangers_and_mash.BangersAndMash;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ModBlockTagGenerator extends BlockTagsProvider {

  public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
    super(output, lookupProvider, BangersAndMash.MOD_ID, existingFileHelper);
  }

  @Override
  protected void addTags(HolderLookup.Provider pProvider) {
    this.tag(BlockTags.MINEABLE_WITH_PICKAXE);
    this.tag(BlockTags.MINEABLE_WITH_AXE);
    this.tag(BlockTags.MINEABLE_WITH_HOE);
    this.tag(BlockTags.MINEABLE_WITH_SHOVEL);
  }
}

package dev.turnr.bangers_and_mash.datagen.tags;

import static dev.turnr.bangers_and_mash.BangersAndMash.MOD_ID;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ModBlockTagsProvider extends BlockTagsProvider {

  public ModBlockTagsProvider(DataGenerator pGenerator,
      @Nullable ExistingFileHelper existingFileHelper) {
    super(pGenerator, MOD_ID, existingFileHelper);
  }

  @Override
  protected void addTags() {
    this.tag(BlockTags.MINEABLE_WITH_PICKAXE);
    this.tag(BlockTags.MINEABLE_WITH_AXE);
    this.tag(BlockTags.MINEABLE_WITH_HOE);
    this.tag(BlockTags.MINEABLE_WITH_SHOVEL);
  }
}

package dev.turnr.farmlore.datagen;

import dev.turnr.farmlore.Farmlore;
import dev.turnr.farmlore.FarmloreTags;
import dev.turnr.farmlore.blocks.GenericBlocks;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class FarmloreBlockTagProvider extends BlockTagsProvider {

  public FarmloreBlockTagProvider(PackOutput output,
      CompletableFuture<HolderLookup.Provider> lookupProvider,
      @Nullable ExistingFileHelper existingFileHelper) {
    super(output, lookupProvider, Farmlore.MOD_ID, existingFileHelper);
  }

  @Override
  protected void addTags(HolderLookup.Provider pProvider) {
    this.tag(FarmloreTags.Blocks.forgeTag("ores/eldorite"))
        .add(GenericBlocks.ELDORITE_ORE.get())
        .add(GenericBlocks.DEEPSLATE_ELDORITE_ORE.get());

    this.tag(FarmloreTags.Blocks.forgeTag("ores_in_ground/stone"))
        .add(GenericBlocks.ELDORITE_ORE.get());

    this.tag(FarmloreTags.Blocks.forgeTag("ores_in_ground/deepslate"))
        .add(GenericBlocks.DEEPSLATE_ELDORITE_ORE.get());

    this.tag(Tags.Blocks.ORES)
        .addTag(FarmloreTags.Blocks.forgeTag("ores/eldorite"));

    this.tag(BlockTags.NEEDS_IRON_TOOL)
        .addTag(FarmloreTags.Blocks.forgeTag("ores/eldorite"))
        .addTag(FarmloreTags.Blocks.forgeTag("storage_blocks/eldorite"));
    this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .addTag(FarmloreTags.Blocks.forgeTag("ores/eldorite"));
    this.tag(BlockTags.MINEABLE_WITH_AXE);
    this.tag(BlockTags.MINEABLE_WITH_HOE);
    this.tag(BlockTags.MINEABLE_WITH_SHOVEL);

    this.tag(FarmloreTags.Blocks.forgeTag("storage_blocks/raw_eldorite"))
        .add(GenericBlocks.RAW_ELDORITE_BLOCK.get());

    this.tag(Tags.Blocks.STORAGE_BLOCKS)
        .addTag(FarmloreTags.Blocks.forgeTag("storage_blocks/raw_eldorite"));

    this.tag(FarmloreTags.Blocks.forgeTag("storage_blocks/eldorite"))
        .add(GenericBlocks.ELDORITE_BLOCK.get());

    this.tag(Tags.Blocks.STORAGE_BLOCKS)
        .addTag(FarmloreTags.Blocks.forgeTag("storage_blocks/eldorite"));

    this.tag(BlockTags.BEACON_BASE_BLOCKS)
        .addTag(FarmloreTags.Blocks.forgeTag("storage_blocks/eldorite"));
  }
}

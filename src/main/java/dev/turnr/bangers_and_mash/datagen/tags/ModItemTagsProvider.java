package dev.turnr.bangers_and_mash.datagen.tags;

import static dev.turnr.bangers_and_mash.BangersAndMash.MOD_ID;
import static dev.turnr.bangers_and_mash.ModTags.Items.FOOD;
import static dev.turnr.bangers_and_mash.ModTags.Items.Food.SAUSAGES;

import dev.turnr.bangers_and_mash.ModTags.Items.Food.Sausages;
import dev.turnr.bangers_and_mash.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class ModItemTagsProvider extends ItemTagsProvider {

  public ModItemTagsProvider(DataGenerator pGenerator,
      BlockTagsProvider pBlockTagsProvider,
      @Nullable ExistingFileHelper existingFileHelper) {
    super(pGenerator, pBlockTagsProvider, MOD_ID, existingFileHelper);
  }

  /**
   *
   */
  @Override
  protected void addTags() {
    TagAppender<Item> rawSausagesTagProvider = this.tag(Sausages.RAW);

    for (RegistryObject<Item> item : ModItems.RAW_SAUSAGES) {
      rawSausagesTagProvider.add(item.get());
    }

    TagAppender<Item> cookedSausagesTagProvider = this.tag(Sausages.COOKED);

    for (RegistryObject<Item> item : ModItems.COOKED_SAUSAGES) {
      cookedSausagesTagProvider.add(item.get());
    }

    this.tag(SAUSAGES).addTags(Sausages.RAW, Sausages.COOKED);
    this.tag(FOOD).addTag(SAUSAGES);


  }

  /**
   * Gets a name for this provider, to use in logging.
   */
  @Override
  public String getName() {
    return "ModItemTagsProvider";
  }
}

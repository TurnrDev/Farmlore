package dev.turnr.bangers_and_mash.datagen.tags;

import static dev.turnr.bangers_and_mash.BangersAndMash.MOD_ID;

import dev.turnr.bangers_and_mash.ModTags;
import dev.turnr.bangers_and_mash.items.Food;
import dev.turnr.bangers_and_mash.items.GenericItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
    TagAppender<Item> rawSausagesTagProvider = this.tag(ModTags.Items.FORGE_SAUSAGES_RAW);

    for (RegistryObject<Item> item : Food.Tags.RAW_SAUSAGES) {
      rawSausagesTagProvider.add(item.get());
    }

    TagAppender<Item> cookedSausagesTagProvider = this.tag(ModTags.Items.FORGE_SAUSAGES_COOKED);

    for (RegistryObject<Item> item : Food.Tags.COOKED_SAUSAGES) {
      cookedSausagesTagProvider.add(item.get());
    }

    cookedSausagesTagProvider.addOptional(
        new ResourceLocation("pamhc2foodextended", "sausageitem"));

    this.tag(ModTags.Items.FORGE_SAUSAGES)
        .addTag(ModTags.Items.FORGE_SAUSAGES_RAW).addTag(ModTags.Items.FORGE_SAUSAGES_COOKED);

    this.tag(ModTags.Items.CONTAINERS).add(GenericItems.METAL_CAN.get()).add(Items.BUCKET)
        .addOptional(
            new ResourceLocation("tconstruct", "copper_can"));

    this.tag(ModTags.Items.FOOD_PROCESSOR_ATTACHMENT_WHISK)
        .add(GenericItems.FOOD_PROCESSOR_ATTACHMENT_WHISK.get()).addOptional(
            new ResourceLocation("create", "whisk"));


  }

  /**
   * Gets a name for this provider, to use in logging.
   */
  @Override
  public String getName() {
    return "ModItemTagsProvider";
  }
}

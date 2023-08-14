package dev.turnr.bangers_and_mash.datagen;

import static dev.turnr.bangers_and_mash.BangersAndMash.MOD_ID;

import dev.turnr.bangers_and_mash.Tags;
import dev.turnr.bangers_and_mash.items.Food;
import dev.turnr.bangers_and_mash.items.GenericItems;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class BAMItemTagGenerator extends ItemTagsProvider {

  public BAMItemTagGenerator(PackOutput output,
      CompletableFuture<HolderLookup.Provider> lookupProvider,
      CompletableFuture<TagLookup<Block>> blockTagLookup, ExistingFileHelper existingFileHelper) {
    super(output, lookupProvider, blockTagLookup, MOD_ID, existingFileHelper);
  }

  /**
   *
   */
  @Override
  protected void addTags(HolderLookup.Provider pProvider) {
    TagAppender<Item> rawSausagesTagProvider = this.tag(Tags.Items.FORGE_SAUSAGES_RAW);

    for (RegistryObject<Item> item : Food.Tags.RAW_SAUSAGES) {
      rawSausagesTagProvider.add(item.getKey());
    }

    TagAppender<Item> cookedSausagesTagProvider = this.tag(Tags.Items.FORGE_SAUSAGES_COOKED);

    for (RegistryObject<Item> item : Food.Tags.COOKED_SAUSAGES) {
      cookedSausagesTagProvider.add(item.getKey());
    }

    cookedSausagesTagProvider.addOptional(
        new ResourceLocation("pamhc2foodextended", "sausageitem"));

    this.tag(Tags.Items.FORGE_SAUSAGES)
        .addTag(Tags.Items.FORGE_SAUSAGES_RAW).addTag(Tags.Items.FORGE_SAUSAGES_COOKED);

    this.tag(Tags.Items.CONTAINERS).add(GenericItems.METAL_CAN.get()).add(Items.BUCKET)
        .addOptional(
            new ResourceLocation("tconstruct", "copper_can"));

    this.tag(Tags.Items.FOOD_PROCESSOR_ATTACHMENT_WHISK)
        .add(GenericItems.FOOD_PROCESSOR_ATTACHMENT_WHISK.get()).addOptional(
            new ResourceLocation("create", "whisk"));

    this.tag(Tags.Items.FOOD_PROCESSOR_ATTACHMENTS)
        .add(GenericItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get())
        .add(GenericItems.FOOD_PROCESSOR_ATTACHMENT_DOUGH_HOOK.get())
        .add(GenericItems.FOOD_PROCESSOR_ATTACHMENT_DOUGH_BLADE.get())
        .add(GenericItems.FOOD_PROCESSOR_ATTACHMENT_SHREDDING_DISC.get())
        .add(GenericItems.FOOD_PROCESSOR_ATTACHMENT_JUICER.get())
        .add(GenericItems.FOOD_PROCESSOR_ATTACHMENT_MILL.get())
        .addTag(Tags.Items.FOOD_PROCESSOR_ATTACHMENT_WHISK);


  }

  /**
   * Gets a name for this provider, to use in logging.
   */
  @Override
  public String getName() {
    return "BAMItemTagGenerator";
  }
}

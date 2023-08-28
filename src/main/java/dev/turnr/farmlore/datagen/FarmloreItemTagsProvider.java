package dev.turnr.farmlore.datagen;

import static dev.turnr.farmlore.Farmlore.MOD_ID;

import dev.turnr.farmlore.FarmloreTags;
import dev.turnr.farmlore.blocks.GenericBlocks;
import dev.turnr.farmlore.items.EdibleItems;
import dev.turnr.farmlore.items.IngredientItems;
import dev.turnr.farmlore.items.ToolItems;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

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
  protected void addTags(HolderLookup.Provider pProvider) {
    TagAppender<Item> rawSausagesTagProvider = this.tag(FarmloreTags.Items.FORGE_SAUSAGES_RAW);

    for (RegistryObject<Item> item : EdibleItems.Tags.RAW_SAUSAGES) {
      rawSausagesTagProvider.add(item.getKey());
    }

    TagAppender<Item> cookedSausagesTagProvider = this.tag(
        FarmloreTags.Items.FORGE_SAUSAGES_COOKED);

    for (RegistryObject<Item> item : EdibleItems.Tags.COOKED_SAUSAGES) {
      cookedSausagesTagProvider.add(item.getKey());
    }

    cookedSausagesTagProvider.addOptional(
        new ResourceLocation("pamhc2foodextended", "sausageitem"));

    this.tag(FarmloreTags.Items.FORGE_SAUSAGES)
        .addTag(FarmloreTags.Items.FORGE_SAUSAGES_RAW)
        .addTag(FarmloreTags.Items.FORGE_SAUSAGES_COOKED);

    this.tag(FarmloreTags.Items.CONTAINERS).add(ToolItems.METAL_CAN.get()).add(Items.BUCKET)
        .addOptional(
            new ResourceLocation("tconstruct", "copper_can"));

    this.tag(FarmloreTags.Items.FOOD_PROCESSOR_ATTACHMENT_WHISK)
        .add(ToolItems.FOOD_PROCESSOR_ATTACHMENT_WHISK.get()).addOptional(
            new ResourceLocation("create", "whisk"));

    this.tag(FarmloreTags.Items.FOOD_PROCESSOR_ATTACHMENTS)
        .add(ToolItems.FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE.get())
        .add(ToolItems.FOOD_PROCESSOR_ATTACHMENT_DOUGH_HOOK.get())
        .add(ToolItems.FOOD_PROCESSOR_ATTACHMENT_DOUGH_BLADE.get())
        .add(ToolItems.FOOD_PROCESSOR_ATTACHMENT_SHREDDING_DISC.get())
        .add(ToolItems.FOOD_PROCESSOR_ATTACHMENT_JUICER.get())
        .add(ToolItems.FOOD_PROCESSOR_ATTACHMENT_MILL.get())
        .addTag(FarmloreTags.Items.FOOD_PROCESSOR_ATTACHMENT_WHISK);

    this.tag(FarmloreTags.Items.forgeTag("ores/eldorite"))
        .add(Item.byBlock(GenericBlocks.ELDORITE_ORE.get()))
        .add(Item.byBlock(GenericBlocks.DEEPSLATE_ELDORITE_ORE.get()));

    this.tag(FarmloreTags.Items.forgeTag("raw_materials/eldorite"))
        .add(IngredientItems.RAW_ELDORITE.get());

    this.tag(FarmloreTags.Items.forgeTag("ingots/eldorite"))
        .add(IngredientItems.ELDORITE_INGOT.get());

    this.tag(FarmloreTags.Items.forgeTag("nuggets/eldorite"))
        .add(IngredientItems.ELDORITE_NUGGET.get());

    this.tag(FarmloreTags.Items.forgeTag("ores_in_ground/stone"))
        .add(Item.byBlock(GenericBlocks.ELDORITE_ORE.get()));

    this.tag(FarmloreTags.Items.forgeTag("ores_in_ground/deepslate"))
        .add(Item.byBlock(GenericBlocks.DEEPSLATE_ELDORITE_ORE.get()));

    this.tag(Tags.Items.ORES).addTag(FarmloreTags.Items.forgeTag("ores/eldorite"));
    this.tag(Tags.Items.RAW_MATERIALS)
        .addTag(FarmloreTags.Items.forgeTag("raw_materials/eldorite"));
    this.tag(Tags.Items.INGOTS).addTag(FarmloreTags.Items.forgeTag("ingots/eldorite"));
    this.tag(Tags.Items.NUGGETS).addTag(FarmloreTags.Items.forgeTag("nuggets/eldorite"));

    this.tag(FarmloreTags.Items.forgeTag("storage_blocks/raw_eldorite"))
        .add(Item.byBlock(GenericBlocks.ELDORITE_BLOCK.get()));

    this.tag(Tags.Items.STORAGE_BLOCKS)
        .addTag(FarmloreTags.Items.forgeTag("storage_blocks/raw_eldorite"));

  }

  /**
   * Gets a name for this provider, to use in logging.
   */
  @Override
  public String getName() {
    return "FarmloreItemTagsProvider";
  }
}

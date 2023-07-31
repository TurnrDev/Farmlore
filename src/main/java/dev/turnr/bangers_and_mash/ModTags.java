package dev.turnr.bangers_and_mash;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

  public static class Blocks {

    private static TagKey<Block> tag(String name) {
      return BlockTags.create(BangersAndMash.getId(name));
    }

    private static TagKey<Block> forgeTag(String name) {
      return BlockTags.create(new ResourceLocation("forge", name));
    }
  }

  public static class Items {

    public static final TagKey<Item> FORGE_SAUSAGES = forgeTag("sausages");
    public static final TagKey<Item> FORGE_SAUSAGES_RAW = forgeTag("sausages/raw");
    public static final TagKey<Item> FORGE_SAUSAGES_COOKED = forgeTag("sausages/cooked");

    public static final TagKey<Item> CONTAINERS = tag("containers");

    private static TagKey<Item> tag(String name) {
      return ItemTags.create(BangersAndMash.getId(name));
    }

    private static TagKey<Item> forgeTag(String name) {
      return ItemTags.create(new ResourceLocation("forge", name));
    }

  }


}

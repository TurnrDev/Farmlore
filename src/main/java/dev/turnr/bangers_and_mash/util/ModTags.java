package dev.turnr.bangers_and_mash.util;

import dev.turnr.bangers_and_mash.BangersAndMash;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
  public static class Blocks {

    private static TagKey<Block> tag(String name)
    {
      return BlockTags.create(BangersAndMash.getId(name));
    }

    private static TagKey<Item> forgeTag(String name)
    {
      return ItemTags.create(new ResourceLocation("forge", name));
    }
  }
  public static class Items {

    public static class Food {
      public static class Sausages {
        public static final TagKey<Item> RAW = forgeTag("food/sausages/raw");
        public static final TagKey<Item> COOKED = forgeTag("food/sausages/cooked");
      }
    }


    private static TagKey<Item> tag(String name)
    {
      return ItemTags.create(BangersAndMash.getId(name));
    }

    private static TagKey<Item> forgeTag(String name)
    {
      return ItemTags.create(new ResourceLocation("forge", name));
    }
  }


}

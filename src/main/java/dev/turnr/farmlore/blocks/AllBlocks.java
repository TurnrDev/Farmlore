package dev.turnr.farmlore.blocks;

import static dev.turnr.farmlore.Farmlore.REGISTRATE;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.turnr.farmlore.FarmloreTags;
import dev.turnr.farmlore.blocks.herbs.HerbBlock;
import dev.turnr.farmlore.blocks.machines.FoodProcessorBlock;
import dev.turnr.farmlore.items.OtherItems;
import dev.turnr.farmlore.items.PlantableItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraftforge.common.Tags;

public class AllBlocks {

  /**
   * It would be nice to make this tiered, much like the vanilla tools in game. I will need to look
   * into that. It might make sense for the food_processor to not be tiered, but infact, holds a
   * tiered item inside of it, such as "Gold Blade" or "Diamond Blade". This would allow for the
   * food processor to be upgraded, and for the blade to break over time. I will need to look into
   * this. For now, it's just this.
   */
  public static final BlockEntry<FoodProcessorBlock> FOOD_PROCESSOR = REGISTRATE.block(
          "food_processor", FoodProcessorBlock::new)
      .properties(p -> p.mapColor(MapColor.METAL).strength(0.8F).sound(SoundType.GLASS))
      .blockstate((ctx, prov) -> prov.horizontalBlock(ctx.get(), ctx.get().getModelFile(prov)))
      .lang("Food Processor")
      .simpleItem()
      .register();

  public static final BlockEntry<DropExperienceBlock> ELDORITE_ORE = REGISTRATE.block("eldorite_ore", DropExperienceBlock::new)
      .properties(p -> p.mapColor(MapColor.STONE))
      .properties(p -> p.sound(SoundType.STONE))
      .properties(p -> p.requiresCorrectToolForDrops().strength(3.0F, 3.0F))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .loot((p, b) -> p.add(b, p.createOreDrop(b, OtherItems.RAW_ELDORITE.get())))
      .tag(BlockTags.NEEDS_IRON_TOOL)
      .tag(Tags.Blocks.ORES)
      .tag(FarmloreTags.Blocks.forgeTag("ores/eldorite"))
      .tag(FarmloreTags.Blocks.forgeTag("ores_in_ground/stone"))
      .item()
      .tag(Tags.Items.ORES)
      .tag(FarmloreTags.Items.forgeTag("ores/eldorite"))
      .tag(FarmloreTags.Items.forgeTag("ores_in_ground/stone"))
      .tab(CreativeModeTabs.NATURAL_BLOCKS)
      .build()
      .lang("Eldorite Ore")
      .register();

  public static final BlockEntry<DropExperienceBlock> DEEPSLATE_ELDORITE_ORE = REGISTRATE.block("deepslate_eldorite_ore", DropExperienceBlock::new)
      .properties(p -> p.mapColor(MapColor.STONE))
      .properties(p -> p.sound(SoundType.STONE))
      .properties(p -> p.requiresCorrectToolForDrops().strength(4.5F, 3.0F))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .loot((p, b) -> p.add(b, p.createOreDrop(b, OtherItems.RAW_ELDORITE.get())))
      .tag(BlockTags.NEEDS_IRON_TOOL)
      .tag(Tags.Blocks.ORES)
      .tag(FarmloreTags.Blocks.forgeTag("ores/eldorite"))
      .tag(FarmloreTags.Blocks.forgeTag("ores_in_ground/deepslate"))
      .item()
      .tag(Tags.Items.ORES)
      .tag(FarmloreTags.Items.forgeTag("ores/eldorite"))
      .tag(FarmloreTags.Items.forgeTag("ores_in_ground/deepslate"))
      .tab(CreativeModeTabs.NATURAL_BLOCKS)
      .build()
      .lang("Deepslate Eldorite Ore")
      .register();

  public static final RegistryEntry<Block> RAW_ELDORITE_BLOCK = REGISTRATE.block("raw_eldorite_block", Block::new)
      .properties(p -> p.mapColor(MapColor.GLOW_LICHEN))
      .properties(p -> p.requiresCorrectToolForDrops().strength(5.2F, 6.0F))
      .tag(BlockTags.NEEDS_IRON_TOOL)
      .tag(Tags.Blocks.STORAGE_BLOCKS)
      .tag(FarmloreTags.Blocks.forgeTag("storage_blocks/raw_eldorite"))
      .item()
      .tag(Tags.Items.STORAGE_BLOCKS)
      .tag(FarmloreTags.Items.forgeTag("storage_blocks/raw_eldorite"))
      .tab(CreativeModeTabs.BUILDING_BLOCKS)
      .build()
      .lang("Block of Raw Eldorite")
      .register();

  public static final RegistryEntry<Block> ELDORITE_BLOCK = REGISTRATE.block("eldorite_block", Block::new)
      .properties(p -> p.mapColor(MapColor.GLOW_LICHEN))
      .properties(p -> p.requiresCorrectToolForDrops().strength(5.2F, 6.0F))
      .tag(BlockTags.NEEDS_IRON_TOOL)
      .tag(Tags.Blocks.STORAGE_BLOCKS)
      .tag(FarmloreTags.Blocks.forgeTag("storage_blocks/eldorite"))
      .tag(BlockTags.BEACON_BASE_BLOCKS)
      .item()
      .tag(Tags.Items.STORAGE_BLOCKS)
      .tag(FarmloreTags.Items.forgeTag("storage_blocks/eldorite"))
      .tab(CreativeModeTabs.BUILDING_BLOCKS)
      .build()
      .lang("Block of Eldorite")
      .register();

  public static final BlockEntry<HerbBlock>
      PARSLEY = herb("parsley", "Parsley", OtherItems.PARSLEY, PlantableItems.PARSLEY_SEEDS),
      BASIL = herb("basil", "Basil", OtherItems.BASIL, PlantableItems.BASIL_SEEDS),
      CORIANDER = herb("coriander", "Coriander", OtherItems.CORIANDER, PlantableItems.CORIANDER_SEEDS),
      MINT = herb("mint", "Mint", OtherItems.MINT, PlantableItems.MINT_SEEDS),
      ROSEMARY = herb("rosemary", "Rosemary", OtherItems.ROSEMARY, PlantableItems.ROSEMARY_SEEDS),
      THYME = herb("thyme", "Thyme", OtherItems.THYME, PlantableItems.THYME_SEEDS),
      SAGE = herb("sage", "Sage", OtherItems.SAGE, PlantableItems.SAGE_SEEDS);

  public static BlockEntry<HerbBlock> herb(String name, String verboseName, RegistryEntry<Item> crop, RegistryEntry<ItemNameBlockItem> seed) {
    return REGISTRATE.block(name, HerbBlock::new)
        .properties(p -> p.mapColor(MapColor.PLANT))
        .properties(p -> p.noCollission())
        .properties(p -> p.randomTicks())
        .properties(p -> p.instabreak())
        .properties(p -> p.sound(SoundType.CROP))
        .properties(p -> p.pushReaction(PushReaction.DESTROY))
        .loot(
            (p, b) -> p.add(b, p.createCropDrops(b, crop.get(), seed.get(), LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(b)
                .setProperties(
                    StatePropertiesPredicate.Builder.properties()
                        .hasProperty(HerbBlock.AGE, HerbBlock.MAX_AGE)))))
        .blockstate((ctx, prov) -> ctx.get().getBlockState(prov))
        .lang(verboseName)
        .register();
  }

  public static void register() {
  }
}

package dev.turnr.bangers_and_mash.blocks.herbs;

import dev.turnr.bangers_and_mash.items.Seeds;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class HerbBlock extends CropBlock {

  public static final int MAX_AGE = BlockStateProperties.MAX_AGE_3;
  public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

  public HerbBlock(Properties pProperties) {
    super(pProperties);
  }

  public int getMaxAge() {
    return MAX_AGE;
  }

  public IntegerProperty getAgeProperty() {
    return AGE;
  }

  @Override
  protected Item getBaseSeedId() {
    // Get the seed item from the block's item
    // Get the block's id, append "_seeds", and get the item with that id
    String block_id = this.asBlock().getRegistryName().getPath();

    // Get the item with the id "block_id + "_seeds""
    String seed_id = block_id + "_seeds";

    // Get the item with the id "seed_id"
    return Seeds.ITEMS.getEntries().stream()
        .filter(entry -> entry.get().getRegistryName().getPath().equals(seed_id))
        .findFirst().get().get();
  }

  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
    pBuilder.add(AGE);
  }
}

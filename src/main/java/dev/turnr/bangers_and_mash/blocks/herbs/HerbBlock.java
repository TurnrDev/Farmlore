package dev.turnr.bangers_and_mash.blocks.herbs;

import dev.turnr.bangers_and_mash.items.Seeds;
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
  protected ItemLike getBaseSeedId() {
    return Seeds.THYME.get();
  }

  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
    pBuilder.add(AGE);
  }
}

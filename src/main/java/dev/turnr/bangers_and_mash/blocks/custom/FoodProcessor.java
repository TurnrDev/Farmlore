package dev.turnr.bangers_and_mash.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;

public class FoodProcessor extends Block {

  public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

  public FoodProcessor(Properties pProperties) {
    super(pProperties);
  }

  @Override
  public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos,
      CollisionContext pContext) {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.1875, 0.9375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.125, 0.1875, 0.125, 0.875, 0.9375, 0.875),
        BooleanOp.OR);

    return shape;
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext pContext) {
    return this.defaultBlockState()
        .setValue(FACING, pContext.getHorizontalDirection().getOpposite());
  }

  @Override
  public BlockState rotate(BlockState pState, Rotation pRotation) {
    return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
  }

  @Override
  public BlockState mirror(BlockState pState, Mirror pMirror) {
    return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
    pBuilder.add(FACING);
  }

  public static ModelFile model(Block block, BlockStateProvider pGenerator) {
    return ((FoodProcessor) block).model(pGenerator);
  }

  public ModelFile model(BlockStateProvider pGenerator) {
    ModelFile model = pGenerator.models()
        .getExistingFile(pGenerator.modLoc(this.getRegistryName().getPath()));
    VariantBlockStateBuilder builder = pGenerator.getVariantBuilder(this);

    // Block state variant for facing=north
    builder.partialState()
        .with(FACING, Direction.NORTH)
        .addModels(new ConfiguredModel(model));

    // Block state variant for facing=east
    builder.partialState()
        .with(FACING, Direction.EAST)
        .addModels(new ConfiguredModel(model, 0, 90, false));

    // Block state variant for facing=south
    builder.partialState()
        .with(FACING, Direction.SOUTH)
        .addModels(new ConfiguredModel(model, 0, 180, false));

    // Block state variant for facing=west
    builder.partialState()
        .with(FACING, Direction.WEST)
        .addModels(new ConfiguredModel(model, 0, 270, false));

    return model;
  }
}


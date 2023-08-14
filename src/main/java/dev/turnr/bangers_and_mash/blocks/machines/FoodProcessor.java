package dev.turnr.bangers_and_mash.blocks.machines;

import dev.turnr.bangers_and_mash.blocks.entities.FoodProcessorEntity;
import dev.turnr.bangers_and_mash.blocks.entities.GenericBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class FoodProcessor extends BaseEntityBlock {

  public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

  public FoodProcessor(Properties pProperties) {
    super(pProperties);
  }

  public static ModelFile model(Block block, BlockStateProvider pGenerator) {
    return ((FoodProcessor) block).model(pGenerator);
  }

  @Override
  public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos,
      CollisionContext pContext) {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.3125, 0.9375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.1875, 0.3125, 0.1875, 0.8125, 0.9375, 0.8125),
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

  public ModelFile model(BlockStateProvider pGenerator) {
    ModelFile model = pGenerator.models()
        .getExistingFile(pGenerator.modLoc(ForgeRegistries.BLOCKS.getKey(this).getPath()));
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

  @Override
  public RenderShape getRenderShape(BlockState pState) {
    return RenderShape.MODEL;
  }

  @Override
  public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState,
      boolean pIsMoving) {
    if (pState.getBlock() != pNewState.getBlock()) {
      BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
      if (blockEntity instanceof FoodProcessorEntity) {
        ((FoodProcessorEntity) blockEntity).drops();
      }
    }
    super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
  }

  @Override
  public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
      Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
    if (!pLevel.isClientSide()) {
      BlockEntity entity = pLevel.getBlockEntity(pPos);
      if (entity instanceof FoodProcessorEntity) {
        NetworkHooks.openScreen(((ServerPlayer) pPlayer), (FoodProcessorEntity) entity, pPos);
      } else {
        throw new IllegalStateException("Our Container provider is missing!");
      }
    }

    return InteractionResult.sidedSuccess(pLevel.isClientSide());
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
    return new FoodProcessorEntity(pPos, pState);
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState,
      BlockEntityType<T> pBlockEntityType) {
    return createTickerHelper(pBlockEntityType, GenericBlockEntities.FOOD_PROCESSOR.get(),
        FoodProcessorEntity::tick);
  }
}


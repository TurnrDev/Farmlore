package dev.turnr.bangers_and_mash.blocks.machines;

import dev.turnr.bangers_and_mash.blockentities.FoodProcessorEntity;
import dev.turnr.bangers_and_mash.blockentities.BlockEntityRegistry;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
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
import org.jetbrains.annotations.NotNull;


public class FoodProcessorBlock extends HorizontalDirectionalBlock implements EntityBlock {

  public FoodProcessorBlock(Properties pProperties) {
    super(pProperties);
  }

  /**
   * Called on server when {@link net.minecraft.world.level.Level#blockEvent} is called. If server returns true, then
   * also called on the client. On the Server, this may perform additional changes to the world, like pistons replacing
   * the block with an extended base. On the client, the update may involve replacing block entities or effects such as
   * sounds or particles
   * @deprecated call via {@link net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#triggerEvent}
   * whenever possible. Implementing/overriding is fine.
   */
  public boolean triggerEvent(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, int pId, int pParam) {
    super.triggerEvent(pState, pLevel, pPos, pId, pParam);
    BlockEntity blockentity = pLevel.getBlockEntity(pPos);
    return blockentity != null && blockentity.triggerEvent(pId, pParam);
  }

  @Nullable
  public MenuProvider getMenuProvider(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos) {
    BlockEntity blockentity = pLevel.getBlockEntity(pPos);
    return blockentity instanceof MenuProvider ? (MenuProvider)blockentity : null;
  }

  @Nullable
  protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> pServerType, BlockEntityType<E> pClientType, BlockEntityTicker<? super E> pTicker) {
    return pClientType == pServerType ? (BlockEntityTicker<A>)pTicker : null;
  }

  public static ModelFile model(Block block, BlockStateProvider pGenerator) {
    return ((FoodProcessorBlock) block).model(pGenerator);
  }

  @Override
  public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos,
      @NotNull CollisionContext pContext) {
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
  protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> pBuilder) {
    super.createBlockStateDefinition(pBuilder);
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

  public @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
    return RenderShape.MODEL;
  }

  @Override
  public void onRemove(BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, BlockState pNewState,
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
  public @NotNull InteractionResult use(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos,
      @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
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
  public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
    return new FoodProcessorEntity(pPos, pState);
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level pLevel, @NotNull BlockState pState,
      @NotNull BlockEntityType<T> pBlockEntityType) {
    return createTickerHelper(pBlockEntityType, BlockEntityRegistry.FOOD_PROCESSOR.get(),
        FoodProcessorEntity::tick);
  }
}


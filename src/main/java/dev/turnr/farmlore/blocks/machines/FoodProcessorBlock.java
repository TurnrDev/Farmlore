package dev.turnr.farmlore.blocks.machines;

import dev.turnr.farmlore.blockentities.BlockEntityRegistry;
import dev.turnr.farmlore.blockentities.FoodProcessorEntity;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
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
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;


public class FoodProcessorBlock extends HorizontalDirectionalBlock implements EntityBlock {

  public FoodProcessorBlock(Properties pProperties) {
    super(pProperties);
  }

  @Nullable
  protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(
      BlockEntityType<A> pServerType, BlockEntityType<E> pClientType,
      BlockEntityTicker<? super E> pTicker) {
    return pClientType == pServerType ? (BlockEntityTicker<A>) pTicker : null;
  }

  public boolean triggerEvent(@NotNull BlockState pState, @NotNull Level pLevel,
      @NotNull BlockPos pPos, int pId, int pParam) {
    super.triggerEvent(pState, pLevel, pPos, pId, pParam);
    BlockEntity blockentity = pLevel.getBlockEntity(pPos);
    return blockentity != null && blockentity.triggerEvent(pId, pParam);
  }

  @Nullable
  public MenuProvider getMenuProvider(@NotNull BlockState pState, Level pLevel,
      @NotNull BlockPos pPos) {
    BlockEntity blockentity = pLevel.getBlockEntity(pPos);
    return blockentity instanceof MenuProvider ? (MenuProvider) blockentity : null;
  }

  @Override
  public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel,
      @NotNull BlockPos pPos,
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
  protected void createBlockStateDefinition(
      StateDefinition.@NotNull Builder<Block, BlockState> pBuilder) {
    super.createBlockStateDefinition(pBuilder);
    pBuilder.add(FACING);
  }

  public ModelFile getModelFile(BlockStateProvider pGenerator) {
    return pGenerator.models()
        .getExistingFile(pGenerator.modLoc(
            Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(this)).getPath()));
  }

  public @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
    return RenderShape.MODEL;
  }

  @Override
  public void onRemove(BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos,
      BlockState pNewState,
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
  public @NotNull InteractionResult use(@NotNull BlockState pState, Level pLevel,
      @NotNull BlockPos pPos,
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
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level pLevel,
      @NotNull BlockState pState,
      @NotNull BlockEntityType<T> pBlockEntityType) {
    return createTickerHelper(pBlockEntityType, BlockEntityRegistry.FOOD_PROCESSOR.get(),
        FoodProcessorEntity::tick);
  }
}


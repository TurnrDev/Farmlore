package dev.turnr.bangers_and_mash.blocks.entities;

import dev.turnr.bangers_and_mash.Tags;
import dev.turnr.bangers_and_mash.recipe.food_processor.FoodProcessorRecipe;
import dev.turnr.bangers_and_mash.screen.FoodProcessorMenu;
import java.util.Optional;
import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.commons.lang3.Range;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class FoodProcessorEntity extends BlockEntity implements MenuProvider {

  public static final int ATTACHMENT_SLOT_ID = 0;
  public static final Range<Integer> INPUT_SLOTS_ID_RANGE = Range.between(1, 9);
  public static final int OUTPUT_SLOT_ID = 10;
  protected final ContainerData data;
  protected final ItemStackHandler inventory = new ItemStackHandler(11) {
    @Override
    protected void onContentsChanged(int slot) {
      setChanged();
    }
  };
  private int progress = 0;
  private int maxProgress = 72;
  private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

  public FoodProcessorEntity(BlockPos pPos,
      BlockState pBlockState) {
    super(GenericBlockEntities.FOOD_PROCESSOR.get(), pPos, pBlockState);
    this.data = new ContainerData() {
      public int get(int index) {
        switch (index) {
          case 0:
            return FoodProcessorEntity.this.progress;
          case 1:
            return FoodProcessorEntity.this.maxProgress;
          default:
            return 0;
        }
      }

      public void set(int index, int value) {
        switch (index) {
          case 0:
            FoodProcessorEntity.this.progress = value;
            break;
          case 1:
            FoodProcessorEntity.this.maxProgress = value;
            break;
        }
      }

      public int getCount() {
        return 2;
      }
    };
  }

  public static void tick(Level pLevel, BlockPos pPos, BlockState pState,
      FoodProcessorEntity pBlockEntity) {
    if (hasRecipe(pBlockEntity)) {
      pBlockEntity.progress++;
      setChanged(pLevel, pPos, pState);
      if (pBlockEntity.progress > pBlockEntity.maxProgress) {
        craftItem(pBlockEntity);
      }
    } else {
      pBlockEntity.resetProgress();
      setChanged(pLevel, pPos, pState);
    }
  }

  private static boolean hasRecipe(FoodProcessorEntity entity) {
    Level level = entity.level;
    SimpleContainer inventory = new SimpleContainer(entity.inventory.getSlots());
    for (int i = 0; i < entity.inventory.getSlots(); i++) {
      inventory.setItem(i, entity.inventory.getStackInSlot(i));
    }

    Optional<FoodProcessorRecipe> match = level.getRecipeManager()
        .getRecipeFor(FoodProcessorRecipe.Type.INSTANCE, inventory, level);

    return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
        && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())
        && hasAttachmentInAttachmentSlot(entity);
  }

  private static boolean hasAttachmentInAttachmentSlot(FoodProcessorEntity entity) {
    return entity.inventory.getStackInSlot(FoodProcessorEntity.ATTACHMENT_SLOT_ID)
        .is(Tags.Items.FOOD_PROCESSOR_ATTACHMENTS);
  }

  private static void craftItem(FoodProcessorEntity entity) {
    Level level = entity.level;
    SimpleContainer inventory = new SimpleContainer(entity.inventory.getSlots());
    for (int i = 0; i < entity.inventory.getSlots(); i++) {
      inventory.setItem(i, entity.inventory.getStackInSlot(i));
    }

    Optional<FoodProcessorRecipe> match = level.getRecipeManager()
        .getRecipeFor(FoodProcessorRecipe.Type.INSTANCE, inventory, level);

    if (match.isPresent()) {
      entity.inventory.getStackInSlot(FoodProcessorEntity.ATTACHMENT_SLOT_ID)
          .hurt(1, RandomSource.create(), null);
      int minSlot = FoodProcessorEntity.INPUT_SLOTS_ID_RANGE.getMinimum();
      int maxSlot = FoodProcessorEntity.INPUT_SLOTS_ID_RANGE.getMaximum();

      for (int i = minSlot; i <= maxSlot; i++) {
        entity.inventory.extractItem(i, 1, false);
      }

      entity.inventory.setStackInSlot(FoodProcessorEntity.OUTPUT_SLOT_ID,
          new ItemStack(match.get().getResultItem().getItem(),
              entity.inventory.getStackInSlot(FoodProcessorEntity.OUTPUT_SLOT_ID).getCount() + 1));

      entity.resetProgress();
    }
  }

  private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
    return inventory.getItem(FoodProcessorEntity.OUTPUT_SLOT_ID).getItem() == output.getItem()
        || inventory.getItem(FoodProcessorEntity.OUTPUT_SLOT_ID).isEmpty();
  }

  private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
    return inventory.getItem(FoodProcessorEntity.OUTPUT_SLOT_ID).getMaxStackSize()
        > inventory.getItem(FoodProcessorEntity.OUTPUT_SLOT_ID).getCount();
  }

  public final int getInventorySize() {
    return this.inventory.getSlots();
  }

  @Nullable
  @Override
  public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory,
      Player pPlayer) {
    return new FoodProcessorMenu(pContainerId, pPlayerInventory, this, this.data);
  }

  @Nonnull
  @Override
  public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
    if (cap == ForgeCapabilities.ITEM_HANDLER) {
      return lazyItemHandler.cast();
    }
    return super.getCapability(cap, side);
  }

  @Override
  public void onLoad() {
    super.onLoad();
    lazyItemHandler = LazyOptional.of(() -> inventory);
  }

  @Override
  protected void saveAdditional(@NotNull CompoundTag tag) {
    tag.put("inventory", inventory.serializeNBT());
    tag.putInt("progress", progress);
    super.saveAdditional(tag);
  }

  @Override
  public void load(CompoundTag tag) {
    super.load(tag);
    inventory.deserializeNBT(tag.getCompound("inventory"));
    this.progress = tag.getInt("progress");
  }

  public void drops() {
    SimpleContainer container = new SimpleContainer(inventory.getSlots());
    for (int i = 0; i < inventory.getSlots(); i++) {
      container.setItem(i, inventory.getStackInSlot(i));
    }

    Containers.dropContents(this.level, this.worldPosition, container);
  }

  private void resetProgress() {
    this.progress = 0;
  }

  /**
   * @return
   */
  @Override
  public Component getDisplayName() {
    return null;
  }
}

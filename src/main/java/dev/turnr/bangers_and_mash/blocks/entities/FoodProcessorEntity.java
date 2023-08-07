package dev.turnr.bangers_and_mash.blocks.entities;

import dev.turnr.bangers_and_mash.items.Food;
import dev.turnr.bangers_and_mash.screen.FoodProcessorMenu;
import java.util.Random;
import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.commons.lang3.Range;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class FoodProcessorEntity extends BlockEntity implements MenuProvider {

  public static final int TOOL_SLOT = 0;
  public static final Range<Integer> INPUT_SLOTS = Range.between(1, 9);
  public static final int OUTPUT_SLOT = 10;

  public final int getInventorySize() {
    return this.inventory.getSlots();
  }

  protected final ItemStackHandler inventory = new ItemStackHandler(11) {
    @Override
    protected void onContentsChanged(int slot) {
      setChanged();
    }
  };

  private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

  public FoodProcessorEntity(BlockPos pPos,
      BlockState pBlockState) {
    super(GenericBlockEntities.FOOD_PROCESSOR.get(), pPos, pBlockState);
  }

  public static void tick(Level pLevel, BlockPos pPos, BlockState pState,
      FoodProcessorEntity pBlockEntity) {
    if (hasRecipe(pBlockEntity) && hasNotReachedStackLimit(pBlockEntity)) {
      craftItem(pBlockEntity);
    }
  }

  private static void craftItem(FoodProcessorEntity entity) {
    entity.inventory.getStackInSlot(TOOL_SLOT).hurt(1, new Random(), null);
    entity.inventory.extractItem(INPUT_SLOTS.getMinimum(), 1, false);

    entity.inventory.setStackInSlot(OUTPUT_SLOT, new ItemStack(Food.Items.SOSIG.get(),
        entity.inventory.getStackInSlot(OUTPUT_SLOT).getCount() + 1));
  }

  private static boolean hasRecipe(FoodProcessorEntity entity) {
    boolean hasItemInFirstSlot =
        entity.inventory.getStackInSlot(INPUT_SLOTS.getMinimum()).getItem() == Items.PORKCHOP;
    boolean hasItemInSecondSlot =
        entity.inventory.getStackInSlot(TOOL_SLOT).getItem() == Items.IRON_SWORD;

    return hasItemInFirstSlot && hasItemInSecondSlot;
  }

  private static boolean hasNotReachedStackLimit(FoodProcessorEntity entity) {
    return entity.inventory.getStackInSlot(OUTPUT_SLOT).getCount()
        < entity.inventory.getStackInSlot(OUTPUT_SLOT)
        .getMaxStackSize();
  }

  @Override
  public Component getDisplayName() {
    return new TextComponent("Food Processor");
  }

  @Nullable
  @Override
  public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory,
      Player pPlayer) {
    return new FoodProcessorMenu(pContainerId, pPlayerInventory, this);
  }

  @Nonnull
  @Override
  public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
    if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
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
    super.saveAdditional(tag);
  }

  @Override
  public void load(CompoundTag tag) {
    super.load(tag);
    inventory.deserializeNBT(tag.getCompound("inventory"));
  }

  public void drops() {
    SimpleContainer container = new SimpleContainer(inventory.getSlots());
    for (int i = 0; i < inventory.getSlots(); i++) {
      container.setItem(i, inventory.getStackInSlot(i));
    }

    Containers.dropContents(this.level, this.worldPosition, container);
  }
}

package dev.turnr.farmlore.screen;

import dev.turnr.farmlore.blockentities.FoodProcessorEntity;
import dev.turnr.farmlore.screen.slot.ResultSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class FoodProcessorMenu extends AbstractContainerMenu {

  private static final int HOTBAR_SLOT_COUNT = 9;
  private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
  private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
  private static final int PLAYER_INVENTORY_SLOT_COUNT =
      PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
  private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
  private static final int VANILLA_FIRST_SLOT_INDEX = 0;
  private static final int TE_INVENTORY_FIRST_SLOT_INDEX =
      VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

  private final FoodProcessorEntity entity;
  private final Level level;

  private final ContainerData data;

  public FoodProcessorMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
    this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()),
        new SimpleContainerData(2));
  }

  public FoodProcessorMenu(int pContainerId, Inventory inv, BlockEntity entity,
      ContainerData data) {
    super(MenuTypeRegistry.FOOD_PROCESSOR_MENU.get(), pContainerId);
    this.entity = (FoodProcessorEntity) entity;
    checkContainerSize(inv, this.entity.getInventorySize());
    this.level = inv.player.level();
    this.data = data;

    addPlayerInventory(inv);
    addPlayerHotbar(inv);

    this.entity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
      this.addSlot(new SlotItemHandler(handler, FoodProcessorEntity.ATTACHMENT_SLOT_ID, 17, 37));
      this.addSlot(new ResultSlot(handler, FoodProcessorEntity.OUTPUT_SLOT_ID, 144, 36));

      // Loop over the range FoodProcessorEntity.INPUT_SLOTS, adding new slots in a 3x3 grid pattern

      int slotIndex = FoodProcessorEntity.INPUT_SLOTS_ID_RANGE.getMinimum();
      for (int row = 0; row < 3; ++row) {
        for (int col = 0; col < 3; ++col) {
          int x = 53 + col * 18;
          int y = 19 + row * 18;
          this.addSlot(new SlotItemHandler(handler, slotIndex, x, y));
          slotIndex++;
        }
      }
    });

    addDataSlots(data);

  }

  public boolean isCrafting() {
    return this.data.get(0) > 0;
  }

  public int getScaledProgress() {
    int craftingProgress = this.data.get(0);
    int craftingTotalTime = this.data.get(1);
    int progressArrowSize = 24; // The width of the arrow in pixels

    return craftingTotalTime != 0 && craftingProgress != 0
        ? craftingProgress * progressArrowSize / craftingTotalTime
        : 0;
  }

  @Override
  public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
    Slot sourceSlot = slots.get(index);
    if (!sourceSlot.hasItem()) {
      return ItemStack.EMPTY;  //EMPTY_ITEM
    }
    ItemStack sourceStack = sourceSlot.getItem();
    ItemStack copyOfSourceStack = sourceStack.copy();

    // Check if the slot clicked is one of the vanilla container slots
    if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
      // This is a vanilla container slot so merge the stack into the tile inventory
      if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
          + this.entity.getInventorySize(), false)) {
        return ItemStack.EMPTY;  // EMPTY_ITEM
      }
    } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + this.entity.getInventorySize()) {
      // This is a TE slot so merge the stack into the players inventory
      if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX,
          VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
        return ItemStack.EMPTY;
      }
    } else {
      System.out.println("Invalid slotIndex:" + index);
      return ItemStack.EMPTY;
    }
    // If stack size == 0 (the entire stack was moved) set slot contents to null
    if (sourceStack.getCount() == 0) {
      sourceSlot.set(ItemStack.EMPTY);
    } else {
      sourceSlot.setChanged();
    }
    sourceSlot.onTake(playerIn, sourceStack);
    return copyOfSourceStack;
  }

  @Override
  public boolean stillValid(@NotNull Player pPlayer) {
    return stillValid(ContainerLevelAccess.create(this.level, this.entity.getBlockPos()), pPlayer,
        this.entity.getBlockState().getBlock());
  }

  private void addPlayerInventory(Inventory playerInventory) {
    for (int row = 0; row < PLAYER_INVENTORY_ROW_COUNT; ++row) {
      for (int col = 0; col < PLAYER_INVENTORY_COLUMN_COUNT; ++col) {
        this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 86 + row * 18));
      }
    }
  }

  private void addPlayerHotbar(Inventory playerInventory) {
    for (int col = 0; col < HOTBAR_SLOT_COUNT; ++col) {
      this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 144));
    }
  }

}

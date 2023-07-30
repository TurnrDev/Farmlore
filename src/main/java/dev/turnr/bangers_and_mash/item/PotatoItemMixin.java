package dev.turnr.bangers_and_mash.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.class)
public class PotatoItemMixin extends Item {

  public PotatoItemMixin(Properties pProperties) {
    super(pProperties);
  }

  @Override
  public InteractionResult useOn(UseOnContext pContext) {
    if (!(pContext.getItemInHand().getItem() == Items.POTATO)) {
      return super.useOn(pContext);
    }

    InteractionHand hand = pContext.getHand();
    InteractionHand otherHand =
        hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;

    Player player = pContext.getPlayer();

    // We want to check if there is a knife or axe in otherHand
    // If there is, we want to return InteractionResult.SUCCESS
    // If there isn't, we want to return super.useOn(pContext)
    if (player.getItemInHand(otherHand).getItem() instanceof SwordItem || pContext.getPlayer()
        .getItemInHand(otherHand).getItem() instanceof AxeItem) {
      // Now we want to replace one item from the stack with 4 potato quarters
      // We want to do this by removing one item from the stack, and giving the player 4 potato quarters
      pContext.getItemInHand().shrink(1);
      player.addItem(new ItemStack(ModItems.POTATO_QUARTER.get(), 4));

      return InteractionResult.SUCCESS;
    }

    return super.useOn(pContext);
  }
}

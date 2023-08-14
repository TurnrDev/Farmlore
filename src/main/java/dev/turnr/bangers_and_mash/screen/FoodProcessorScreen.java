package dev.turnr.bangers_and_mash.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.turnr.bangers_and_mash.BangersAndMash;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class FoodProcessorScreen extends AbstractContainerScreen<FoodProcessorMenu> {

  private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(
      BangersAndMash.MOD_ID,
      "textures/gui/food_processor.png");

  public FoodProcessorScreen(FoodProcessorMenu pMenu,
      Inventory pPlayerInventory,
      Component pTitle) {
    super(pMenu, pPlayerInventory, pTitle);
  }

  @Override
  protected void renderBg(GuiGraphics graphics, float pPartialTick, int pMouseX, int pMouseY) {
    RenderSystem.setShader(GameRenderer::getPositionTexShader);
    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
    int x = (this.width - this.imageWidth) / 2;
    int y = (this.height - this.imageHeight) / 2;

    graphics.blit(BACKGROUND_TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);

    if (this.menu.isCrafting()) {
      graphics.blit(BACKGROUND_TEXTURE, x + 109, y + 37, 176, 0, this.menu.getScaledProgress(), 17);
    }

  }

  @Override
  public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
    this.renderBackground(graphics);
    super.render(graphics, mouseX, mouseY, delta);
    this.renderTooltip(graphics, mouseX, mouseY);
  }
}

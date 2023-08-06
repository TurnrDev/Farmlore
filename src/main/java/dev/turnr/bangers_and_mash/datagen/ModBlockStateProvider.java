package dev.turnr.bangers_and_mash.datagen;

import com.mojang.logging.LogUtils;
import dev.turnr.bangers_and_mash.BangersAndMash;
import dev.turnr.bangers_and_mash.blocks.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class ModBlockStateProvider extends BlockStateProvider {

  private static final Logger LOGGER = LogUtils.getLogger();
  private final ExistingFileHelper existingFileHelper;

  public ModBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super(generator, BangersAndMash.MOD_ID, existingFileHelper);
    this.existingFileHelper = existingFileHelper;
  }

  private ResourceLocation textureOrRicky(ResourceLocation texture) {
    if (existingFileHelper.exists(texture, PackType.CLIENT_RESOURCES, ".png", "textures")) {
      return texture;
    } else {
      LOGGER.warn("Missing texture for {}", texture);
      return new ResourceLocation(BangersAndMash.MOD_ID, "ricky");
    }
  }

  @Override
  protected void registerStatesAndModels() {
    for (Block block : BlockRegistry.getItems().map(RegistryObject::get).toArray(Block[]::new)) {
      if (block instanceof ButtonBlock) {
        buttonBlock((ButtonBlock) block, blockTexture(block));
      } else if (block instanceof PressurePlateBlock) {
        pressurePlateBlock((PressurePlateBlock) block, blockTexture(block));
      } else if (block instanceof WallBlock) {
        wallBlock((WallBlock) block, blockTexture(block));
      } else if (block instanceof FenceBlock) {
        fenceBlock((FenceBlock) block, blockTexture(block));
      } else if (block instanceof StairBlock) {
        stairsBlock((StairBlock) block, blockTexture(block));
      } else {
        simpleBlock(block);
      }
    }
  }

  @Override
  public ResourceLocation blockTexture(Block block) {
    ResourceLocation texture = super.blockTexture(block);
    return textureOrRicky(texture);
  }
}

package dev.turnr.bangers_and_mash.datagen;

import com.mojang.logging.LogUtils;
import dev.turnr.bangers_and_mash.BangersAndMash;
import dev.turnr.bangers_and_mash.blocks.BlockRegistry;
import dev.turnr.bangers_and_mash.blocks.machines.FoodProcessor;
import java.util.function.Function;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
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
    for (Block block : BlockRegistry.getEntries().map(RegistryObject::get).toArray(Block[]::new)) {
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
      } else if (block instanceof CropBlock) {
        cropBlock((CropBlock) block);
      } else if (block instanceof FoodProcessor) {
        FoodProcessor.model(block, this);
      } else {
        simpleBlock(block);
      }
    }
  }

  public void cropBlock(CropBlock block) {
    Function<BlockState, ConfiguredModel[]> function = state -> states(state, block,
        block.getRegistryName().getPath() + "_stage_",
        block.getRegistryName().getPath() + "_stage_");

    getVariantBuilder(block).forAllStates(function);
  }

  private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName,
      String textureName) {
    ConfiguredModel[] models = new ConfiguredModel[1];
    models[0] = new ConfiguredModel(
        models().crop(modelName + state.getValue(block.getAgeProperty()),
            textureOrRicky(new ResourceLocation(BangersAndMash.MOD_ID,
                "block/" + textureName + state.getValue(block.getAgeProperty())))));

    return models;
  }

  @Override
  public ResourceLocation blockTexture(Block block) {
    ResourceLocation texture = super.blockTexture(block);
    return textureOrRicky(texture);
  }
}

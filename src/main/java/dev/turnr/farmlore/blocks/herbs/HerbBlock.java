package dev.turnr.farmlore.blocks.herbs;

import static dev.turnr.farmlore.Farmlore.REGISTRATE;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.turnr.farmlore.Farmlore;
import dev.turnr.farmlore.items.PlantableItems;
import java.util.function.Function;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.registries.ForgeRegistries;

public class HerbBlock extends CropBlock {

  public static final int MAX_AGE = BlockStateProperties.MAX_AGE_3;
  public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

  public HerbBlock(Properties pProperties) {
    super(pProperties);
  }

  public int getMaxAge() {
    return MAX_AGE;
  }

  public IntegerProperty getAgeProperty() {
    return AGE;
  }

  @Override
  protected Item getBaseSeedId() {
    // Get the seed item from the block's item
    // Get the block's id, append "_seeds", and get the item with that id
    String block_id = ForgeRegistries.BLOCKS.getKey(this).getPath();

    // Get the item with the id "block_id + "_seeds""
    String seed_id = block_id + "_seeds";

    // Get the item with the id "seed_id"
    RegistryEntry<Item> seed = REGISTRATE.get(seed_id, ForgeRegistries.ITEMS.getRegistryKey());
    return seed.get();
  }

  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
    pBuilder.add(AGE);
  }

  public void getBlockState(BlockStateProvider prov) {
    // String blockID = ForgeRegistries.BLOCKS.getKey(this).getPath();

    // TODO: Replace this when we have assets for the other herbs.
    String blockID = "thyme";

    Function<BlockState, ConfiguredModel[]> function = state -> states(prov, state, this,
        blockID + "_stage_",
        blockID + "_stage_");

    prov.getVariantBuilder(this).forAllStates(function);
  }

  private static ConfiguredModel[] states(BlockStateProvider prov, BlockState state, CropBlock block, String modelName,
      String textureName) {
    ConfiguredModel[] models = new ConfiguredModel[1];
    models[0] = new ConfiguredModel(
        prov.models().crop(modelName + block.getAge(state), (new ResourceLocation(Farmlore.MOD_ID,
            "block/" + textureName + block.getAge(state)))));

    return models;
  }
}

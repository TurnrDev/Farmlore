package dev.turnr.bangers_and_mash.datagen;

import com.mojang.logging.LogUtils;
import dev.turnr.bangers_and_mash.BangersAndMash;
import dev.turnr.bangers_and_mash.items.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class ModItemModelProvider extends ItemModelProvider {

  private static final Logger LOGGER = LogUtils.getLogger();

  public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super(generator, BangersAndMash.MOD_ID, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    for (Item item : ItemRegistry.getEntries().map(RegistryObject::get).toArray(Item[]::new)) {
      if (item instanceof BlockItem) {
        blockItem((BlockItem) item);
      } else {
        basicItem(item);
      }
    }
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
  public ItemModelBuilder basicItem(Item item) {
    return withExistingParent(item.getRegistryName().getPath(),
        new ResourceLocation("item/generated")).texture("layer0", textureOrRicky(
        new ResourceLocation(BangersAndMash.MOD_ID, "item/" + item.getRegistryName().getPath())));
  }

  public ItemModelBuilder blockItem(BlockItem item) {
    return withExistingParent(item.getRegistryName().getPath(),
        new ResourceLocation(modid, "block/" + item.getRegistryName().getPath()));
  }

  public ItemModelBuilder handheldItem(Item item) {
    return withExistingParent(item.getRegistryName().getPath(),
        new ResourceLocation("item/handheld")).texture("layer0", textureOrRicky(
        new ResourceLocation(BangersAndMash.MOD_ID, "item/" + item.getRegistryName().getPath())));
  }
}


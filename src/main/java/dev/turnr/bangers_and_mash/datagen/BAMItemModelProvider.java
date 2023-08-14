package dev.turnr.bangers_and_mash.datagen;

import com.mojang.logging.LogUtils;
import dev.turnr.bangers_and_mash.BangersAndMash;
import dev.turnr.bangers_and_mash.items.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class BAMItemModelProvider extends ItemModelProvider {

  private static final Logger LOGGER = LogUtils.getLogger();

  public BAMItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
    super(output, BangersAndMash.MOD_ID, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    for (RegistryObject<Item> registryObject : ItemRegistry.getEntries().toList()) {
      Item item = registryObject.get();
      if (item instanceof BlockItem && !(item instanceof ItemNameBlockItem)) {
        blockItem(registryObject);
      } else {
        simpleItem(registryObject);
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

  public ItemModelBuilder simpleItem(RegistryObject<Item> registryObject) {
    return withExistingParent(registryObject.getId().getPath(),
        new ResourceLocation("item/generated")).texture("layer0", textureOrRicky(
        new ResourceLocation(BangersAndMash.MOD_ID, "item/" + registryObject.getId().getPath())));
  }

  public ItemModelBuilder blockItem(RegistryObject<Item> registryObject) {
    return withExistingParent(registryObject.getId().getPath(),
        new ResourceLocation(modid, "block/" + registryObject.getId().getPath()));
  }
}

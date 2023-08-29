package dev.turnr.farmlore.items;

import static dev.turnr.farmlore.Farmlore.REGISTRATE;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.turnr.farmlore.FarmloreTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

public class OtherItems {
  public static final ItemEntry<ArmorItem> BURLAP_SACK = REGISTRATE.item("burlap_sack",
          p -> new ArmorItem(ArmorMaterials.BURLAP, Type.CHESTPLATE, p))
      .tab(CreativeModeTabs.INGREDIENTS)
      .register();

  public static final RegistryEntry<Item>
      PARSLEY = REGISTRATE.item("parsley", Item::new).register(),
      BASIL = REGISTRATE.item("basil", Item::new).register(),
      CORIANDER = REGISTRATE.item("coriander", Item::new).register(),
      MINT = REGISTRATE.item("mint", Item::new).register(),
      ROSEMARY = REGISTRATE.item("rosemary", Item::new).register(),
      THYME = REGISTRATE.item("thyme", Item::new).register(),
      SAGE = REGISTRATE.item("sage", Item::new).register(),
      MINCED_PORK = REGISTRATE.item("minced_pork", Item::new).register(),
      MINCED_BEEF = REGISTRATE.item("minced_beef", Item::new).register(),
      COOKED_MINCED_PORK = REGISTRATE.item("cooked_minced_pork", Item::new).register(),
      COOKED_MINCED_BEEF = REGISTRATE.item("cooked_minced_beef", Item::new).register(),
      BURLAP = REGISTRATE.item("burlap", Item::new).register(),
      RAW_ELDORITE = taggedIngredient("raw_eldorite", FarmloreTags.Items.forgeTag("raw_materials/eldorite"), Tags.Items.RAW_MATERIALS),
      ELDORITE_INGOT = taggedIngredient("eldorite_ingot", FarmloreTags.Items.forgeTag("ingots/eldorite"), Tags.Items.INGOTS),
      ELDORITE_NUGGET = taggedIngredient("eldorite_nugget", FarmloreTags.Items.forgeTag("nuggets/eldorite"), Tags.Items.NUGGETS);

  @SafeVarargs
  private static RegistryEntry<Item> taggedIngredient(String name, TagKey<Item>... tags) {
    return REGISTRATE.item(name, Item::new)
        .tag(tags)
        .tab(CreativeModeTabs.INGREDIENTS)
        .register();
  }

  public static void register() {}

}

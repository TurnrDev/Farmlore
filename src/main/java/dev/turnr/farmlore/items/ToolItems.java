package dev.turnr.farmlore.items;

import static dev.turnr.farmlore.Farmlore.REGISTRATE;
import static dev.turnr.farmlore.FarmloreTags.Items.FOOD_PROCESSOR_ATTACHMENTS;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class ToolItems {

  public static final RegistryEntry<Item> METAL_CAN = REGISTRATE.item("metal_can", Item::new)
      .tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
      .lang("Metal Can")
      .register();

  /**
   * A standard attachment that's included with all food processors. The knife blade is used for
   * mixing, mincing, mashing and puréeing as well as chopping. It's sometimes called a multipurpose
   * blade.
   */
  public static final RegistryEntry<Item> FOOD_PROCESSOR_ATTACHMENT_STEEL_BLADE = foodProcessorAttachment(
      "food_processor_attachment_steel_blade",
      "Steel Blade");

  /**
   * This is for kneading recipes that use yeast, such as sweet or savoury bread doughs.
   */
  public static final RegistryEntry<Item> FOOD_PROCESSOR_ATTACHMENT_DOUGH_HOOK = foodProcessorAttachment(
      "food_processor_attachment_dough_hook",
      "Dough Hook");

  /**
   * This is for shredding or grating vegetables, cheese and other foods.
   */
  public static final RegistryEntry<Item> FOOD_PROCESSOR_ATTACHMENT_SHREDDING_DISC = foodProcessorAttachment(
      "food_processor_attachment_shredding_disc",
      "Shredding Disc");

  public static RegistryEntry<Item> foodProcessorAttachment(String name, String verboseName) {
    return REGISTRATE.item(name, Item::new)
        .tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
        .tag(FOOD_PROCESSOR_ATTACHMENTS)
        .lang(verboseName)
        .register();
  }

  public static void register() {
  }
}

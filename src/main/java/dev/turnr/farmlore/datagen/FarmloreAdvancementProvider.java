package dev.turnr.farmlore.datagen;

import com.google.common.collect.ImmutableSet;
import dev.turnr.farmlore.Farmlore;
import dev.turnr.farmlore.items.ClothingItems;
import java.util.function.Consumer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.EntityEquipmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds.Ints;
import net.minecraft.advancements.critereon.NbtPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.common.extensions.IForgeAdvancementBuilder;

public class FarmloreAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {

  /**
   * A method used to generate advancements for a mod. Advancements should be built via
   * {@link IForgeAdvancementBuilder#save(Consumer, ResourceLocation, ExistingFileHelper)}.
   *
   * @param registries         a lookup for registries and their objects
   * @param saver              a consumer used to write advancements to a file
   * @param existingFileHelper a helper used to find whether a file exists
   */
  @Override
  public void generate(Provider registries, Consumer<Advancement> saver,
      ExistingFileHelper existingFileHelper) {

    Advancement povertyAdvancement = Advancement.Builder.advancement().display(
            new DisplayInfo(new ItemStack(
                ClothingItems.BURLAP_SACK.get()),
                Component.translatableWithFallback("advancements.farmlore.poverty.title", "Poverty"),
                Component.translatableWithFallback("advancements.farmlore.poverty.description",
                    "Dress in rags."),
                null,
                FrameType.CHALLENGE, true, true, false))
        .parent(new ResourceLocation("husbandry/root"))
        .addCriterion("wore_burlap_sack", PlayerTrigger.TriggerInstance.located(
            EntityPredicate.Builder.entity().of(EntityType.PLAYER).equipment(
                EntityEquipmentPredicate.Builder.equipment()
                    .chest(new ItemPredicate(null, ImmutableSet.of(ClothingItems.BURLAP_SACK.get()),
                        Ints.ANY, Ints.ANY, EnchantmentPredicate.NONE, EnchantmentPredicate.NONE,
                        null, NbtPredicate.ANY)).build()).build()))
        .save(saver, new ResourceLocation(Farmlore.MOD_ID, "poverty"), existingFileHelper);


  }
}

package dev.turnr.farmlore.items;

import dev.turnr.farmlore.Farmlore;
import java.util.EnumMap;
import java.util.function.Supplier;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public enum ArmorMaterials implements ArmorMaterial {
  BURLAP("burlap", 1, Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
    enumMap.put(ArmorItem.Type.BOOTS, 1);
    enumMap.put(ArmorItem.Type.LEGGINGS, 1);
    enumMap.put(ArmorItem.Type.CHESTPLATE, 1);
    enumMap.put(ArmorItem.Type.HELMET, 1);
  }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
    return Ingredient.of(OtherItems.BURLAP.get());
  });

  public static final StringRepresentable.EnumCodec<net.minecraft.world.item.ArmorMaterials> CODEC = StringRepresentable.fromEnum(
      net.minecraft.world.item.ArmorMaterials::values);
  private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(
      new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
      });
  private final String name;
  private final int durabilityMultiplier;
  private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
  private final int enchantmentValue;
  private final SoundEvent sound;
  private final float toughness;
  private final float knockbackResistance;
  private final Supplier<Ingredient> repairIngredient;

  ArmorMaterials(String pName, int pDurabilityMultiplier,
      EnumMap<ArmorItem.Type, Integer> pProtectionFunctionForType, int pEnchantmentValue,
      SoundEvent pSound, float pToughness, float pKnockbackResistance,
      Supplier<Ingredient> pRepairIngredient) {
    this.name = pName;
    this.durabilityMultiplier = pDurabilityMultiplier;
    this.protectionFunctionForType = pProtectionFunctionForType;
    this.enchantmentValue = pEnchantmentValue;
    this.sound = pSound;
    this.toughness = pToughness;
    this.knockbackResistance = pKnockbackResistance;
    this.repairIngredient = pRepairIngredient;
  }

  public int getDurabilityForType(ArmorItem.Type pType) {
    return HEALTH_FUNCTION_FOR_TYPE.get(pType) * this.durabilityMultiplier;
  }

  public int getDefenseForType(ArmorItem.Type pType) {
    return this.protectionFunctionForType.get(pType);
  }

  public int getEnchantmentValue() {
    return this.enchantmentValue;
  }

  public SoundEvent getEquipSound() {
    return this.sound;
  }

  public Ingredient getRepairIngredient() {
    return this.repairIngredient.get();
  }

  public String getName() {
    return Farmlore.MOD_ID + ":" + this.name;
  }

  public float getToughness() {
    return this.toughness;
  }

  /**
   * Gets the percentage of knockback resistance provided by armor of the material.
   */
  public float getKnockbackResistance() {
    return this.knockbackResistance;
  }

  public String getSerializedName() {
    return Farmlore.MOD_ID + ":" + this.name;
  }
}

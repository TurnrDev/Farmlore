package dev.turnr.bangers_and_mash.item;

import dev.turnr.bangers_and_mash.BangersAndMash;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BangersAndMash.MOD_ID);

    public static final RegistryObject<Item> CANNED_BAKED_BEANS = ITEMS.register("canned_baked_beans", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));

    public static final RegistryObject<Item> TIN_CAN = ITEMS.register("tin_can", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> TINPLATE = ITEMS.register("tinplate", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

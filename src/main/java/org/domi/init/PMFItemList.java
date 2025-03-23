package org.domi.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

public class PMFItemList {
    public static final RegistryObject<Item> MUSHROOM_BURGER = FoodItem.registerFood("mushroom_burger",10, 0.6f);
    public static final RegistryObject<Item> IKEK = FoodItem.registerFood("ikek",10, 0.6f);

    public static void register(IEventBus eventBus) {
        PMFItem.ITEMS.register(eventBus);
    }

}

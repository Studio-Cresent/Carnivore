package org.domi.init;

import net.minecraft.world.food.FoodProperties;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ItemFood extends PMFItem {
    public ItemFood(Properties properties) {
        super(properties);
    }

    protected static RegistryObject<Item> registerFood(String name, int hunger, float saturation) {
        return ITEMS.register(name, () -> new ItemFood(new Item.Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(hunger)
                        .saturationMod(saturation)
                        .build())));
    }

}

package org.domi.init.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class PMFFoodItem extends PMFItem {
    public PMFFoodItem(Properties properties) {
        super(properties);
    }

    /*
        * 그냥 음식 등록
     */
    public static RegistryObject<Item> registerFood(String name, int hunger, float saturation) {
        return ITEMS.register(name, () -> new PMFFoodItem(new Item.Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(hunger)
                        .saturationMod(saturation)
                        .build())));
    }


    /**
     * 먹으면 효과 걸리는 음식
     */
    protected static RegistryObject<Item> registerFoodWithEffect(String name, int hunger, float saturation, MobEffectInstance effect, float probability) {
        return ITEMS.register(name, () -> new PMFFoodItem(new Item.Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(hunger)
                        .saturationMod(saturation)
                        .effect(effect, probability)
                        .build())));
    }

    //빨리 먹는 음식
    protected static RegistryObject<Item> registerFastFood(String name, int hunger, float saturation) {
        return ITEMS.register(name, () -> new PMFFoodItem(new Item.Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(hunger)
                        .saturationMod(saturation)
                        .fast()  // 빨리 먹을 수 있음
                        .build())));
    }

}

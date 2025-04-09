package org.domi.init.itemlists;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.domi.init.block.PMFBlock;
import org.domi.init.items.PMFFoodItem;

import static org.domi.init.items.PMFItem.ITEMS;

public class PMFItemList {
    //default food
    public static final RegistryObject<Item> RAW_AXOLOTL = PMFFoodItem.registerFood("raw_axolotl", 2, 1.0f);
    public static final RegistryObject<Item> COOKED_AXOLOTL = PMFFoodItem.registerFood("cooked_axolotl", 6, 6.0f);
    public static final RegistryObject<Item> COOKED_BAT = PMFFoodItem.registerFood("cooked_bat", 4, 3.5f);
    public static final RegistryObject<Item> COOKED_BIRD = PMFFoodItem.registerFood("cooked_bird", 5, 6.8f);
    public static final RegistryObject<Item> COOKED_CAMEL_MEAT = PMFFoodItem.registerFood("cooked_camel_meat", 8, 8.0f);
    public static final RegistryObject<Item> COOKED_RABBIT_FOOT = PMFFoodItem.registerFood("cooked_rabbit_foot", 10, 0.6f);
    public static final RegistryObject<Item> COOKED_SNIFFER_MEAT = PMFFoodItem.registerFood("cooked_sniffer_meat", 10, 15.0f);
    public static final RegistryObject<Item> COOKED_SQUID_TENTACLE = PMFFoodItem.registerFood("cooked_squid_tentacle", 4, 5.1f);
    public static final RegistryObject<Item> COOKED_TADPOLE = PMFFoodItem.registerFood("cooked_tadpole", 4, 1.4f);
    public static final RegistryObject<Item> COOKED_TURTLE = PMFFoodItem.registerFood("cooked_turtle", 7, 7.5f);
    public static final RegistryObject<Item> RAW_HORSE_MEAT = PMFFoodItem.registerFood("raw_horse_meat", 3, 2.0f);
    public static final RegistryObject<Item> HORSE_MEAT_STEAK = PMFFoodItem.registerFood("horse_meat_steak", 9, 8.5f);
    public static final RegistryObject<Item> MYCELIUM_STEAK = PMFFoodItem.registerFood("mycelium_steak", 8, 12.8f);
    public static final RegistryObject<Item> RAW_WHALE_MEAT = PMFFoodItem.registerFood("raw_whale_meat", 4, 0.5f);
    public static final RegistryObject<Item> COOKED_WHALE_MEAT = PMFFoodItem.registerFood("cooked_whale_meat", 9, 6.0f);
    public static final RegistryObject<Item> RAW_CAMEL_MEAT = PMFFoodItem.registerFood("raw_camel_meat", 3, 1.9f);
    public static final RegistryObject<Item> RAW_SNIFFER_MEAT = PMFFoodItem.registerFood("raw_sniffer_meat", 5, 2.0f);
    public static final RegistryObject<Item> RAW_TURTLE = PMFFoodItem.registerFood("raw_turtle", 3, 1.8f);
    public static final RegistryObject<Item> SALMON_ROE = PMFFoodItem.registerFood("salmon_roe", 1, 2.0f);
    public static final RegistryObject<Item> SILVERFISH = PMFFoodItem.registerFood("silverfish", 2, 0.5f);
    public static final RegistryObject<Item> SQUID_TENTACLE = PMFFoodItem.registerFood("squid_tentacle", 2, 1.0f);
    public static final RegistryObject<Item> STRIDER_MEAT = PMFFoodItem.registerFood("strider_meat", 2, 1.0f);

    // 효과가 있는 음식 (알파벳순)
    public static final RegistryObject<Item> FROGSPAWN = PMFFoodItem.registerFoodWithEffect(
            "frogspawn", 0, 0.3f,
            new MobEffectInstance(MobEffects.CONFUSION, 1200, 0), 1.0f
    );
    public static final RegistryObject<Item> GLOW_SQUID_TENTACLE = PMFFoodItem.registerFoodWithEffect(
            "glow_squid_tentacle", 2, 1.0f,
            new MobEffectInstance(MobEffects.GLOWING, 300, 0), 1.0f
    );
    public static final RegistryObject<Item> RAW_BAT = PMFFoodItem.registerFoodWithEffect(
            "raw_bat", 1, 1.0f,
            new MobEffectInstance(MobEffects.WEAKNESS, 600, 0), 1.0f
    );
    public static final RegistryObject<Item> RAW_BIRD = PMFFoodItem.registerFoodWithEffect(
            "raw_bird", 1, 0.8f,
            new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.8f
    );
    public static final RegistryObject<Item> RAW_MYCELIUM_BEEF = PMFFoodItem.registerFoodWithEffect(
            "raw_mycelium_beef", 4, 1.2f,
            new MobEffectInstance(MobEffects.WEAKNESS, 600, 0), 1.0f
    );
    public static final RegistryObject<Item> SQUID_EYEBALL = PMFFoodItem.registerFoodWithEffect(
            "squid_eyeball", 2, 3.0f,
            new MobEffectInstance(MobEffects.CONFUSION, 600, 0), 1.0f
    );
    public static final RegistryObject<Item> TADPOLE = PMFFoodItem.registerFoodWithEffect(
            "tadpole", 1, 1.0f,
            new MobEffectInstance(MobEffects.CONFUSION, 600, 0), 1.0f
    );

    // 다중 효과 음식 (알파벳순)
    public static final RegistryObject<Item> GOLDEN_SQUID_TENTACLE = PMFFoodItem.registerFoodWithMultipleEffects(
            "golden_squid_tentacle", 4, 9.0f,
            new MobEffectInstance(MobEffects.WATER_BREATHING, 2400, 0),
            new MobEffectInstance(MobEffects.ABSORPTION, 2400, 1)
    );


    //logo
    public static final RegistryObject<Item> TAB_ICON_ITEM = ITEMS.register("tab_icon_item", () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        PMFBlock.BLOCKS.register(eventBus);
    }
}

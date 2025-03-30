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
//    public static final RegistryObject<Item> MUSHROOM_BURGER = PMFFoodItem.registerFood("mushroom_burger",10, 0.6f);
//    public static final RegistryObject<Item> IKEK = PMFFoodItem.registerFood("ikek",10, 0.6f);
    public static final RegistryObject<Item> RAW_SNIFFER_MEAT = PMFFoodItem.registerFood("raw_sniffer_meat", 5, 2.0f);
    public static final RegistryObject<Item> COOKED_RABBIT_FOOT = PMFFoodItem.registerFood("cooked_rabbit_foot", 10, 0.6f);
    public static final RegistryObject<Item> RAW_MYCELIUM_BEEF = PMFFoodItem.registerFoodWithEffect("raw_mycelium_beef",
            4, 1.2f,
            new MobEffectInstance(MobEffects.WEAKNESS, 600, 0), 1.0f);
    public static final RegistryObject<Item> MYCELIUM_STEAK = PMFFoodItem.registerFood("mycelium_steak", 8, 12.8f);
    public static final RegistryObject<Item> COOKED_SNIFFER_MEAT = PMFFoodItem.registerFood("cooked_sniffer_meat", 10, 15.0f);
    public static final RegistryObject<Item> TADPOLE = PMFFoodItem.registerFoodWithEffect("tadpole",
            1, 1.0f,
            new MobEffectInstance(MobEffects.CONFUSION, 600, 0), 1.0f);
    public static final RegistryObject<Item> SQUID_TENTACLE = PMFFoodItem.registerFood("squid_tentacle", 2, 1.0f);
    public static final RegistryObject<Item> GLOW_SQUID_TENTACLE = PMFFoodItem.registerFoodWithEffect("glow_squid_tentacle",
            2, 1.0f,
            new MobEffectInstance(MobEffects.GLOWING, 300, 0), 1.0f);
    public static final RegistryObject<Item> GOLDEN_SQUID_TENTACLE = PMFFoodItem.registerFoodWithMultipleEffects("golden_squid_tentacle",
            4, 9.0f);


//    //bowl
//    public static final RegistryObject<Item> PUMPKIN_SOUP = PMFBowlItem.registerBowlItem("pumpkin_soup", 10 , 0.6f);

    //block
//    public static final RegistryObject<Block> SNOWHEAD = ConsumableBlock.registerConsumableBlock(
//            "snowhead",
//            BlockBehaviour.Properties.copy(Blocks.PUMPKIN).noOcclusion(),
//            () -> PMFItemList.PUMPKIN_SOUP.get(),
//            () -> Items.BOWL,
//            true
//    );
//
//
//    //block item
//    public static final RegistryObject<Item> SNOWHEAD_ITEM = PMFBlockItem.registerBlockItem("snowhead", SNOWHEAD);
//

    //logo
    public static final RegistryObject<Item> TAB_ICON_ITEM = ITEMS.register("tab_icon_item",
            () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        PMFBlock.BLOCKS.register(eventBus);
    }
}

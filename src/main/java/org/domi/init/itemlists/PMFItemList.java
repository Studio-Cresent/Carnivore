package org.domi.init.itemlists;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.domi.init.ConsumableBlock;
import org.domi.init.block.PMFBlock;
import org.domi.init.block.PMFBlockItem;
import org.domi.init.items.*;

public class PMFItemList {
    //default food
    public static final RegistryObject<Item> MUSHROOM_BURGER = PMFFoodItem.registerFood("mushroom_burger",10, 0.6f);
    public static final RegistryObject<Item> IKEK = PMFFoodItem.registerFood("ikek",10, 0.6f);
    public static final RegistryObject<Item> A_MEAT = PMFFoodItem.registerFood("sniffer_meat", 10, 0.6f);

    //bowl
    public static final RegistryObject<Item> PUMPKIN_SOUP = BowlItem.registerBowlItem("pumpkin_soup", 10 , 0.6f);

    //block
    public static final RegistryObject<Block> SNOWHEAD = ConsumableBlock.registerConsumableBlock(
            "snowhead",
            BlockBehaviour.Properties.copy(Blocks.PUMPKIN).noOcclusion(),
            () -> PMFItemList.PUMPKIN_SOUP.get(),
            () -> Items.BOWL,
            true
    );


    //block item
    public static final RegistryObject<Item> SNOWHEAD_ITEM = PMFBlockItem.registerBlockItem("snowhead", SNOWHEAD);

    public static void register(IEventBus eventBus) {
        PMFItem.ITEMS.register(eventBus);
        PMFBlock.BLOCKS.register(eventBus);
    }
}

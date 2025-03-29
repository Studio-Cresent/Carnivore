package org.domi.init.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.domi.init.items.PMFItem;

public class PMFBlockItem extends BlockItem {

    public PMFBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    public static RegistryObject<Item> registerBlockItem(String name, RegistryObject<Block> block) {
        return PMFItem.ITEMS.register(name, () -> new PMFBlockItem(block.get(), new Item.Properties()));
    }
}
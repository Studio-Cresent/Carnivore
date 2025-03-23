package org.domi.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.domi.Pmf;

public class PMFItem extends Item {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Pmf.MODID);

    // 생성자
    public PMFItem(Properties properties) {
        super(properties);
    }

    // 아이템 등록 용
    protected static RegistryObject<Item> registerItem(String name) {
        return ITEMS.register(name, () -> new PMFItem(new Item.Properties()));
    }

}

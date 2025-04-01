package org.domi.init.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.domi.PMF;

public class PMFItem extends Item {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PMF.MODID);

    public PMFItem(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("CustomVariant")) {
            String variant = tag.getString("CustomVariant");
            return Component.translatable("item." + variant);
        }
        return super.getName(stack);
    }

    public static RegistryObject<Item> registerItem(String name) {
        return ITEMS.register(name, () -> new PMFItem(new Item.Properties()));
    }

    public static RegistryObject<Item> registerVariantItem(String name) {
        return ITEMS.register(name, () -> new PMFItem(new Item.Properties()));
    }

    public static ItemStack createVariant(Item item, String variantName, int customModelData) {
        ItemStack stack = new ItemStack(item);
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString("CustomVariant", variantName);
        tag.putInt("CustomModelData", customModelData);
        return stack;
    }

}

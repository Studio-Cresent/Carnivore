package org.domi.init.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.RegistryObject;


public class PMFBowlItem extends PMFFoodItem {
    public PMFBowlItem(Item.Properties properties) {
        super(properties);
    }

    public static RegistryObject<Item> registerBowl(String name, int hunger, float saturation) {
        return PMFFoodItem.registerFood(name, hunger, saturation);
    }

    public static RegistryObject<Item> registerBowlItem(String name, int hunger, float saturation) {
        return ITEMS.register(name, () -> new PMFBowlItem(
                new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(hunger)
                                .saturationMod(saturation)
                                .build())
        ));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {
        ItemStack resultStack = super.finishUsingItem(stack, level, entityLiving);

        if (entityLiving instanceof Player player) {
            ItemStack bowlStack = new ItemStack(Items.BOWL);
            if (!player.getAbilities().instabuild) {
                ItemHandlerHelper.giveItemToPlayer(player, bowlStack);
            }
        }
        return resultStack;
    }
}
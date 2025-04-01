package org.domi.entityDrops;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Random;

public class EntityDropUtil {
    private static final Random RANDOM = new Random();

    //넥?슨 버전
    public static boolean entityDrop(LivingDropsEvent event, String entityId, RegistryObject<Item> dropItem, int minAmount, int addAmount, float chance) {
        LivingEntity entity = event.getEntity();
        ResourceLocation entityResLoc = ForgeRegistries.ENTITY_TYPES.getKey(entity.getType());
        ResourceLocation targetEntityId = new ResourceLocation(entityId);

        if (entityResLoc != null && entityResLoc.equals(targetEntityId)) {
            if (RANDOM.nextFloat() <= chance) {
                int amount = minAmount;
                if (addAmount > 0) {
                    amount += RANDOM.nextInt(addAmount + 1);
                }

                // 아이템 엔티티 생성 및 이벤트에 추가
                ItemStack stack = new ItemStack(dropItem.get(), amount);
                ItemEntity itemEntity = new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(), stack);

                event.getDrops().add(itemEntity);
                return true;
            }
        }
        return false;
    }

    /**
     * 무조건 드롭 버전
     */
    public static boolean entityDrop(LivingDropsEvent event, String entityId, RegistryObject<Item> dropItem, int minAmount, int addAmount) {
        return entityDrop(event, entityId, dropItem, minAmount, addAmount, 1.0f);
    }

    //넥?슨 버전
    public static boolean entityDrops(LivingDropsEvent event, String entityId, RegistryObject<Item>[] dropItems, int[] minAmounts, int[] addAmounts, float[] chances) {
        LivingEntity entity = event.getEntity();
        ResourceLocation entityResLoc = ForgeRegistries.ENTITY_TYPES.getKey(entity.getType());
        ResourceLocation targetEntityId = new ResourceLocation(entityId);

        if (entityResLoc != null && entityResLoc.equals(targetEntityId)) {
            boolean anyDropped = false;

            for (int i = 0; i < dropItems.length; i++) {
                if (RANDOM.nextFloat() <= chances[i]) {
                    int amount = minAmounts[i];
                    if (addAmounts[i] > 0) {
                        amount += RANDOM.nextInt(addAmounts[i] + 1);
                    }

                    ItemStack stack = new ItemStack(dropItems[i].get(), amount);
                    ItemEntity itemEntity = new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(), stack);

                    event.getDrops().add(itemEntity);
                    anyDropped = true;
                }
            }

            return anyDropped;
        }
        return false;
    }

    /**
     * 무조건 드롭 버전
     */
    public static boolean entityDrops(LivingDropsEvent event, String entityId, RegistryObject<Item>[] dropItems, int[] minAmounts, int[] addAmounts) {
        float[] chances = new float[dropItems.length];
        for (int i = 0; i < chances.length; i++) {
            chances[i] = 1.0f;
        }

        return entityDrops(event, entityId, dropItems, minAmounts, addAmounts, chances);
    }
}
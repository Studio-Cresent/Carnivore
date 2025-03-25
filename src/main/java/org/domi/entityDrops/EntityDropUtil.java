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

    /**
     * @param entityId  엔티티 ID ("minecraft:zombie", "alexsmobs:tiger" 형식)
     * @param dropItem  드롭할 아이템
     * @param minAmount 최소 드롭 량
     * @param addAmount 추가 드롭 량 (최소 수량에 이 값을 더함)
     * @param chance    아이템 드롭 확률 (0.0 ~ 1.0, 기본값 1.0은 항상 드롭)
     */
    public static boolean entityDrop(LivingDropsEvent event, String entityId, RegistryObject<Item> dropItem, int minAmount, int addAmount, float chance) {
        LivingEntity entity = event.getEntity();
        ResourceLocation entityResLoc = ForgeRegistries.ENTITY_TYPES.getKey(entity.getType());
        ResourceLocation targetEntityId = new ResourceLocation(entityId);

        if (entityResLoc != null && entityResLoc.equals(targetEntityId)) {
            // 확률 계산
            if (RANDOM.nextFloat() <= chance) {
                // 드롭할 아이템 수량 계산
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
     * 무조건 드롭 버전 (100% 확률)
     */
    public static boolean entityDrop(LivingDropsEvent event, String entityId, RegistryObject<Item> dropItem, int minAmount, int addAmount) {
        return entityDrop(event, entityId, dropItem, minAmount, addAmount, 1.0f);
    }

    /**
     * @param entityId   엔티티 ID ("minecraft:zombie", "alexsmobs:tiger" 형식)
     * @param dropItems  드롭할 아이템들
     * @param minAmounts 각 아이템의 최소 드롭 량
     * @param addAmounts 각 아이템의 추가 드롭 량
     * @param chances    각 아이템의 드롭 확률 (0.0 ~ 1.0)
     */
    public static boolean entityDrops(LivingDropsEvent event, String entityId, RegistryObject<Item>[] dropItems, int[] minAmounts, int[] addAmounts, float[] chances) {
        LivingEntity entity = event.getEntity();
        ResourceLocation entityResLoc = ForgeRegistries.ENTITY_TYPES.getKey(entity.getType());
        ResourceLocation targetEntityId = new ResourceLocation(entityId);

        if (entityResLoc != null && entityResLoc.equals(targetEntityId)) {
            boolean anyDropped = false;

            for (int i = 0; i < dropItems.length; i++) {
                // 확률 계산
                if (RANDOM.nextFloat() <= chances[i]) {
                    // 드롭할 아이템 수량 계산
                    int amount = minAmounts[i];
                    if (addAmounts[i] > 0) {
                        amount += RANDOM.nextInt(addAmounts[i] + 1);
                    }

                    // 아이템 엔티티 생성 및 이벤트에 추가
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
     * 여러 아이템을 무조건 드롭하는 메소드 (100% 확률)
     */
    public static boolean entityDrops(LivingDropsEvent event, String entityId, RegistryObject<Item>[] dropItems, int[] minAmounts, int[] addAmounts) {
        float[] chances = new float[dropItems.length];
        for (int i = 0; i < chances.length; i++) {
            chances[i] = 1.0f;
        }

        return entityDrops(event, entityId, dropItems, minAmounts, addAmounts, chances);
    }
}
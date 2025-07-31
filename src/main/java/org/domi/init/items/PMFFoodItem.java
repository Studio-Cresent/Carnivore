package org.domi.init.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;

import java.util.Random;

public class PMFFoodItem extends PMFItem {
    private final boolean isTeleporting;

    public PMFFoodItem(Properties properties, boolean isTeleporting) {
        super(properties);
        this.isTeleporting = isTeleporting;
    }

    public PMFFoodItem(Properties properties) {
        this(properties, false);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (isTeleporting && entity instanceof Player) {
            Player player = (Player) entity;
            if (!world.isClientSide) {
                teleport(player);
            }
        }
        return super.finishUsingItem(stack, world, entity);
    }

    private void teleport(Player player) {
        Random random = new Random();
        for (int i = 0; i < 32; ++i) {
            double x = player.getX() + (random.nextDouble() - 0.5) * 20.0D;
            double y = player.getY() + (random.nextInt(21) - 10);
            double z = player.getZ() + (random.nextDouble() - 0.5) * 20.0D;
            if (player.randomTeleport(x, y, z, true)) {
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
                break;
            }
        }
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
     * 순간이동하는 음식 등록
     */
    public static RegistryObject<Item> registerTeleportingFood(String name, int hunger, float saturation) {
        return ITEMS.register(name, () -> new PMFFoodItem(new Item.Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(hunger)
                        .saturationMod(saturation)
                        .build()), true));
    }

    /**
     * 먹으면 효과 걸리는 음식
     */
    public static RegistryObject<Item> registerFoodWithEffect(String name, int hunger, float saturation, MobEffectInstance effect, float probability) {
        return ITEMS.register(name, () -> new PMFFoodItem(new Item.Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(hunger)
                        .saturationMod(saturation)
                        .effect(effect, probability)
                        .build())));
    }

    /**
     * 여러 효과가 걸리는 음식
     */
    public static RegistryObject<Item> registerFoodWithMultipleEffects(String name, int hunger, float saturation,
                                                                       MobEffectInstance... effects) {
        FoodProperties.Builder foodBuilder = new FoodProperties.Builder()
                .nutrition(hunger)
                .saturationMod(saturation);

        for (MobEffectInstance effect : effects) {
            foodBuilder.effect(effect, 1.0f);
        }

        return ITEMS.register(name, () -> new PMFFoodItem(new Item.Properties()
                .food(foodBuilder.build())));
    }


    //빨리 먹는 음식
    protected static RegistryObject<Item> registerFastFood(String name, int hunger, float saturation) {
        return ITEMS.register(name, () -> new PMFFoodItem(new Item.Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(hunger)
                        .saturationMod(saturation)
                        .fast()  // 그 뭐시기 캘프? 처럼 빨리 먹기
                        .build())));
    }
}

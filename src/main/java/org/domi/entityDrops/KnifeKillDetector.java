package org.domi.entityDrops;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class KnifeKillDetector {

    public static boolean isKilledByKnife(LivingDropsEvent event) {
        if (event.getSource().getDirectEntity() != null &&
                event.getSource().getDirectEntity() instanceof Player) {

            Player player = (Player) event.getSource().getDirectEntity();
            ItemStack mainHandItem = player.getMainHandItem();
            String itemId = mainHandItem.getItem().getDescriptionId();

            return itemId.equals("item.farmersdelight.flint_knife") ||
                    itemId.equals("item.farmersdelight.iron_knife") ||
                    itemId.equals("item.farmersdelight.diamond_knife") ||
                    itemId.equals("item.farmersdelight.netherite_knife") ||
                    itemId.equals("item.farmersdelight.golden_knife");
        }
        return false;
    }
}

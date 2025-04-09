package org.domi.entityDrops.minecraft;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.domi.entityDrops.EntityDropUtil;
import org.domi.init.itemlists.PMFItemList;

public class Dolphin {
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();

        boolean isFireKill = entity.isOnFire() || event.getSource().is(net.minecraft.tags.DamageTypeTags.IS_FIRE);

        if (!isFireKill) {
            EntityDropUtil.entityDrop(event, "minecraft:dolphin", PMFItemList.RAW_WHALE_MEAT, 1, 1);
        } else {
            EntityDropUtil.entityDrop(event, "minecraft:dolphin", PMFItemList.COOKED_WHALE_MEAT, 1, 1);
        }
    }
}

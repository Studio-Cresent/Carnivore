package org.domi.entityDrops.alexmobs;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.domi.entityDrops.EntityDropUtil;
import org.domi.init.itemlists.PMFItemList;

public class Potoo {
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();

        boolean isFireKill = entity.isOnFire() || event.getSource().is(net.minecraft.tags.DamageTypeTags.IS_FIRE);

        if (!isFireKill) {
            EntityDropUtil.entityDrop(event, "alexsmobs:potoo", PMFItemList.RAW_BIRD, 1, 1);
        } else {
            EntityDropUtil.entityDrop(event, "alexsmobs:potoo", PMFItemList.COOKED_BIRD, 1, 1);
        }
    }
}

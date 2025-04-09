package org.domi.entityDrops.alexmobs;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.domi.entityDrops.EntityDropUtil;
import org.domi.entityDrops.KnifeKillDetector;
import org.domi.init.itemlists.PMFItemList;

public class GiantSquid {
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();

        boolean isKnifeKill = KnifeKillDetector.isKilledByKnife(event);
        boolean isFireKill = entity.isOnFire() || event.getSource().is(net.minecraft.tags.DamageTypeTags.IS_FIRE);

        if (isKnifeKill || "alexsmobs:giant_squid".equals(entity.getType().getDescriptionId())) {
            if (!isFireKill) {
                EntityDropUtil.entityDrop(event, "alexsmobs:giant_squid", PMFItemList.SQUID_TENTACLE, 1, 2);
            } else {
                EntityDropUtil.entityDrop(event, "alexsmobs:giant_squid", PMFItemList.COOKED_SQUID_TENTACLE, 1, 2);
            }
        }
    }
}

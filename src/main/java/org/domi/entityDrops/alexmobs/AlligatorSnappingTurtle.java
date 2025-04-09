package org.domi.entityDrops.alexmobs;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.domi.entityDrops.EntityDropUtil;
import org.domi.entityDrops.KnifeKillDetector;
import org.domi.init.itemlists.PMFItemList;

public class AlligatorSnappingTurtle {
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();

        boolean isKnifeKill = KnifeKillDetector.isKilledByKnife(event);
        boolean isFireKill = entity.isOnFire() || event.getSource().is(net.minecraft.tags.DamageTypeTags.IS_FIRE);

        if (isKnifeKill || "alexsmobs:alligator_snapping_turtle".equals(entity.getType().getDescriptionId())) {
            if (!isFireKill) {
                EntityDropUtil.entityDrop(event, "alexsmobs:alligator_snapping_turtle", PMFItemList.RAW_TURTLE, 1, 1);
            } else {
                EntityDropUtil.entityDrop(event, "alexsmobs:alligator_snapping_turtle", PMFItemList.COOKED_TURTLE, 1, 1);
            }
        }
    }
}

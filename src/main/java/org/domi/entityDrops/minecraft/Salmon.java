package org.domi.entityDrops.minecraft;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.domi.entityDrops.EntityDropUtil;
import org.domi.entityDrops.KnifeKillDetector;
import org.domi.init.itemlists.PMFItemList;

public class Salmon {
    @SubscribeEvent
    public void onLivingDrops(net.minecraftforge.event.entity.living.LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();

        boolean isKnifeKill = KnifeKillDetector.isKilledByKnife(event);

        if (isKnifeKill || "minecraft:salmon".equals(entity.getType().getDescriptionId())) {
            EntityDropUtil.entityDrop(event, "minecraft:salmon", PMFItemList.SALMON_ROE, 1, 2);
        }
    }
}

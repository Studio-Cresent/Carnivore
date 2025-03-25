package org.domi.entityDrops.alexmobs;

import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.domi.entityDrops.EntityDropUtil;
import org.domi.init.PMFItemList;

public class AlexTiger {

    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        EntityDropUtil.entityDrop(event, "alexsmobs:tiger", PMFItemList.IKEK, 1, 3);
    }
}

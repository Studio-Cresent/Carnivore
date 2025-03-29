package org.domi.entityDrops.minecraft;

import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.domi.entityDrops.EntityDropUtil;
import org.domi.init.itemlists.PMFItemList;

public class Sniffer {
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        EntityDropUtil.entityDrop(event, "minecraft:sniffer", PMFItemList.SNIFFER_MEAT, 1, 3);
    }
}

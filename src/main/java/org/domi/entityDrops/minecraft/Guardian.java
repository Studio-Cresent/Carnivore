package org.domi.entityDrops.minecraft;

import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.domi.entityDrops.EntityDropUtil;
import org.domi.init.itemlists.PMFItemList;

public class Guardian {
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        EntityDropUtil.entityDrop(event, "minecraft:guardian", PMFItemList.GUARDIAN_EYE, 1, 2);
        EntityDropUtil.entityDrop(event, "minecraft:guardian", PMFItemList.GUARDIAN_MEAT, 1, 1, 0.5f);
    }
}

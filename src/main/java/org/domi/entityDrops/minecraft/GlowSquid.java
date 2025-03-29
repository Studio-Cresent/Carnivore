package org.domi.entityDrops.minecraft;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.domi.entityDrops.EntityDropUtil;
import org.domi.init.itemlists.PMFItemList;

public class GlowSquid {
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        EntityDropUtil.entityDrop(event, "minecraft:glow_squid", PMFItemList.GLOW_SQUID_TENTACLE, 1, 3);
    }
}

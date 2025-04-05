package org.domi.entityDrops.minecraft;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.domi.entityDrops.EntityDropUtil;
import org.domi.init.itemlists.PMFItemList;

public class Squid {
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();

        boolean isFireKill = entity.isOnFire() || event.getSource().is(net.minecraft.tags.DamageTypeTags.IS_FIRE);

        if (!isFireKill) {
            EntityDropUtil.entityDrop(event, "minecraft:squid", PMFItemList.SQUID_TENTACLE, 1, 2);
        } else {
            EntityDropUtil.entityDrop(event, "minecraft:squid", PMFItemList.COOKED_SQUID_TENTACLE, 1, 2);
        }
    }
}

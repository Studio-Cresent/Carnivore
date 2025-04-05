package org.domi.entityDrops.minecraft;

import com.google.common.eventbus.Subscribe;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import org.domi.entityDrops.EntityDropUtil;
import org.domi.init.itemlists.PMFItemList;

public class Camel {
    @Subscribe
    public void onLivingDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();

        boolean isFireKill = entity.isOnFire() || event.getSource().is(net.minecraft.tags.DamageTypeTags.IS_FIRE);

        if (!isFireKill) {
            EntityDropUtil.entityDrop(event, "minecraft:camel", PMFItemList.RAW_CAMEL_MEAT, 1, 2);
        } else {
            EntityDropUtil.entityDrop(event, "minecraft:camel", PMFItemList.COOKED_CAMEL_MEAT, 1, 2);
        }
    }
}

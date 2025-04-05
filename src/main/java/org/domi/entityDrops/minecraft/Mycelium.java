package org.domi.entityDrops.minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.domi.entityDrops.EntityDropUtil;
import org.domi.init.itemlists.PMFItemList;

public class Mycelium {
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();

        boolean isFireKill = entity.isOnFire() || event.getSource().is(net.minecraft.tags.DamageTypeTags.IS_FIRE);

        if (!isFireKill) {
            EntityDropUtil.entityDrop(event, "minecraft:mooshroom", PMFItemList.RAW_MYCELIUM_BEEF, 1, 2);
        } else {
            EntityDropUtil.entityDrop(event, "minecraft:mooshroom", PMFItemList.MYCELIUM_STEAK, 1, 2);
        }
        if (event.getEntity().getType().getDescriptionId().equals("entity.minecraft.mooshroom")) {
            event.getDrops().removeIf(entityItem ->
                    entityItem.getItem().getItem() == Items.BEEF);
        }
    }
}

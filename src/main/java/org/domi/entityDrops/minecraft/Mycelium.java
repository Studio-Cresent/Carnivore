package org.domi.entityDrops.minecraft;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.domi.entityDrops.EntityDropUtil;
import org.domi.init.itemlists.PMFItemList;

public class Mycelium {
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        EntityDropUtil.entityDrop(event, "minecraft:mooshroom", PMFItemList.RAW_MYCELIUM_BEEF, 1, 3);
        if (event.getEntity().getType().getDescriptionId().equals("entity.minecraft.mooshroom")) {
            // 드롭 목록에서 일반 소고기 제거
            event.getDrops().removeIf(entityItem ->
                    entityItem.getItem().getItem() == Items.BEEF);
        }

    }
}

package org.domi.init.itemlists;

import net.minecraft.world.level.block.ComposterBlock;

public class ComposterList {
    public static void register(){
        ComposterBlock.COMPOSTABLES.put(PMFItemList.MUSHROOM_BURGER.get(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(PMFItemList.IKEK.get(), 0.85f);
    }
}

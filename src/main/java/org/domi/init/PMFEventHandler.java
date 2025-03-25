package org.domi.init;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import org.domi.entityDrops.alexmobs.AlexTiger;

public class PMFEventHandler {
    public static void registerModEvents(IEventBus modEventBus) {
    }

    public static void registerForgeEvents() {
        // AlexTiger 이벤트 리스너 등록
        MinecraftForge.EVENT_BUS.register(new AlexTiger());
    }
    public static void register(IEventBus modEventBus) {
        registerModEvents(modEventBus);
        registerForgeEvents();
    }
}
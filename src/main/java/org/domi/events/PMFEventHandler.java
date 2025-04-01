package org.domi.events;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import org.domi.entityDrops.minecraft.*;

public class PMFEventHandler {
    public static void registerModEvents(IEventBus modEventBus) {
    }

    public static void registerForgeEvents() {
//        MinecraftForge.EVENT_BUS.register(new AlexTiger());
        MinecraftForge.EVENT_BUS.register(new Sniffer());
        MinecraftForge.EVENT_BUS.register(new GlowSquid());
        MinecraftForge.EVENT_BUS.register(new Mycelium());
        MinecraftForge.EVENT_BUS.register(new Squid());
        MinecraftForge.EVENT_BUS.register(new Tadpole());
    }
    public static void register(IEventBus modEventBus) {
        registerModEvents(modEventBus);
        registerForgeEvents();
    }
}
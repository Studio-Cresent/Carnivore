package org.domi.events;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import org.domi.entityDrops.alexmobs.*;
import org.domi.entityDrops.minecraft.*;

public class PMFEventHandler {
    public static void registerModEvents(IEventBus modEventBus) {

    }

    public static void registerForgeEvents() {
        MinecraftForge.EVENT_BUS.register(new AlligatorSnappingTurtle());
        MinecraftForge.EVENT_BUS.register(new BaldEagle());
        MinecraftForge.EVENT_BUS.register(new BlueJay());
        MinecraftForge.EVENT_BUS.register(new CachalotWhale());
        MinecraftForge.EVENT_BUS.register(new GiantSquid());
        MinecraftForge.EVENT_BUS.register(new Orca());
        MinecraftForge.EVENT_BUS.register(new Potoo());
        MinecraftForge.EVENT_BUS.register(new Terrapin());

        MinecraftForge.EVENT_BUS.register(new Axolotl());
        MinecraftForge.EVENT_BUS.register(new Bat());
        MinecraftForge.EVENT_BUS.register(new Camel());
        MinecraftForge.EVENT_BUS.register(new Dolphin());
        MinecraftForge.EVENT_BUS.register(new Donkey());
        MinecraftForge.EVENT_BUS.register(new Frog());
        MinecraftForge.EVENT_BUS.register(new GlowSquid());
        MinecraftForge.EVENT_BUS.register(new Horse());
        MinecraftForge.EVENT_BUS.register(new Mule());
        MinecraftForge.EVENT_BUS.register(new Mycelium());
        MinecraftForge.EVENT_BUS.register(new Parrot());
        MinecraftForge.EVENT_BUS.register(new Salmon());
        MinecraftForge.EVENT_BUS.register(new Silverfish());
        MinecraftForge.EVENT_BUS.register(new Sniffer());
        MinecraftForge.EVENT_BUS.register(new Squid());
        MinecraftForge.EVENT_BUS.register(new Strider());
        MinecraftForge.EVENT_BUS.register(new Tadpole());
        MinecraftForge.EVENT_BUS.register(new Turtle());
        MinecraftForge.EVENT_BUS.register(new Endermite());
    }
    public static void register(IEventBus modEventBus) {
        registerModEvents(modEventBus);
        registerForgeEvents();
    }
}
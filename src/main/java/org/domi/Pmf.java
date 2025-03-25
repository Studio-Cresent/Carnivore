package org.domi;


import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.domi.init.PMFEventHandler;
import org.domi.init.PMFItemList;


@Mod(Pmf.MODID)
public class Pmf {
    public static final String MODID = "pmf";

    public static final Logger LOGGER = LogManager.getLogger();

    public Pmf(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        setup(modEventBus);
    }


    private void setup(IEventBus eventBus){
        PMFItemList.register(eventBus);
        PMFCreativeTab.initialize(eventBus);
        PMFEventHandler.register(eventBus);
        //왜 등록이 안됨???????????????
        eventBus.addListener(PMFCreativeTab::addItemsToTabs);
    }

    //블럭 렌더링용 ㅇㅇ
    private void doClientStuff(final FMLCommonSetupEvent event){

    }
}
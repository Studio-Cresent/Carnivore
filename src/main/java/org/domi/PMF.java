package org.domi;


import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.domi.events.PMFEventHandler;
import org.domi.init.itemlists.PMFItemList;
import org.domi.init.itemlists.PMFRecipeList;


@Mod(PMF.MODID)
public class PMF {
    public static final String MODID = "carnivore";

    public static final Logger LOGGER = LogManager.getLogger();

    public PMF(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        setup(modEventBus);
    }

    private void setup(IEventBus eventBus){
        PMFItemList.register(eventBus);
        PMFCreativeTab.initialize(eventBus);
        PMFEventHandler.register(eventBus);
        eventBus.addListener(PMFCreativeTab::addItemsToTabs);
        eventBus.addListener(this::gatherData);
    }

    private void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();

        if (event.includeServer()) {
            generator.addProvider(true, new PMFRecipeList(generator));
        }

    }


    //블럭 렌더링용 ㅇㅇ
    private void doClientStuff(final FMLCommonSetupEvent event){

    }
}
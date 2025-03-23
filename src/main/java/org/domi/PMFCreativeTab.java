package org.domi;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.domi.init.PMFItem;
import org.domi.init.PMFItemList;


public class PMFCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB , Pmf.MODID);


    public static final RegistryObject<CreativeModeTab> PMF_Tab = CREATIVE_TABS.register("pmf_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(PMFItemList.MUSHROOM_BURGER.get()))
                    .title(Component.translatable("itemGroup." + Pmf.MODID + ".pmf_tab"))
                    .build()
    );

    public static void initialize(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus); // 탭 등록
    }


    //TODO: shit
    public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
        // 그냥 다 추가하자ㅏㅏ 코드는 깔끔하게ㅔ
        if (event.getTabKey() == PMF_Tab.getKey()) {
            for (RegistryObject<Item> item : PMFItem.ITEMS.getEntries()) {
                event.accept(item.get());
            }
        }
    }

}

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
import org.domi.init.items.PMFItem;

import static org.domi.init.itemlists.PMFItemList.TAB_ICON_ITEM;


public class PMFCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB , PMF.MODID);


    public static final RegistryObject<CreativeModeTab> PMF_Tab = CREATIVE_TABS.register("carnivore_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(TAB_ICON_ITEM.get()))
                    .title(Component.translatable("itemGroup." + PMF.MODID + ".carnivore_tab"))
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
                // 아이콘으로 사용하는 아이템만 제외
                if (item.get() != TAB_ICON_ITEM.get()) {
                    event.accept(item.get());
                }
            }
        }
    }
}

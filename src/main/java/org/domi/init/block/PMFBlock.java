package org.domi.init.block;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.domi.PMF;

public class PMFBlock extends Block {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PMF.MODID);
    
    public PMFBlock(Properties properties) {
        super(properties);
    }
    
    public static RegistryObject<Block> registerBlock(String name, Properties properties) {
        return BLOCKS.register(name, () -> new PMFBlock(properties));
    }
}
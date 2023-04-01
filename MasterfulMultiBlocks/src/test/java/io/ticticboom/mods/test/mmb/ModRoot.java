package io.ticticboom.mods.test.mmb;

import io.ticticboom.mods.test.mmb.setup.MMBBlocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Ref.ID)
public class ModRoot {
    public ModRoot() {
        MMBBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        MMBBlocks.BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}

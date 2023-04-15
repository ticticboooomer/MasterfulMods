package io.ticticboom.mods.mm;

import io.ticticboom.mods.mm.setup.MMRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Ref.ID)
public class ModRoot {
    public ModRoot() {
        MMRegistries.registerConfigTypes();
        MMRegistries.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        MMRegistries.BLOCK_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        MMRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}

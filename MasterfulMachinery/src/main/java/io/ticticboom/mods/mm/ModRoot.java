package io.ticticboom.mods.mm;

import io.ticticboom.mods.mconf.setup.MConfRegistries;
import io.ticticboom.mods.mm.data.MMBlockModelProvider;
import io.ticticboom.mods.mm.data.MMBlockStateProvider;
import io.ticticboom.mods.mm.data.MMItemModelProvider;
import io.ticticboom.mods.mm.data.MMLangProvider;
import io.ticticboom.mods.mm.setup.MMRegistries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Ref.ID)
public class ModRoot {
    public ModRoot() {
        MMRegistries.registerConfigTypes();
        MMRegistries.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        MMRegistries.BLOCK_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        MMRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        MConfRegistries.DATA_GEN_PROVIDERS.put(Ref.rl("block_models"), (d, e) -> new MMBlockModelProvider(d.getPackOutput(), e));
        MConfRegistries.DATA_GEN_PROVIDERS.put(Ref.rl("block_states"), (d, e) -> new MMBlockStateProvider(d.getPackOutput(), e));
        MConfRegistries.DATA_GEN_PROVIDERS.put(Ref.rl("lang"), (d, e) -> new MMLangProvider(d.getPackOutput(), "en_us"));
        MConfRegistries.DATA_GEN_PROVIDERS.put(Ref.rl("item_models"), (d, e) -> new MMItemModelProvider(d.getPackOutput(), e));
    }
}

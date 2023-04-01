package io.ticticboom.mods.mmb;

import io.ticticboom.mods.mmb.setup.MMBRegistries;
import net.minecraftforge.fml.common.Mod;

@Mod(Ref.ID)
public class ModRoot {
    public ModRoot() {
        MMBRegistries.registerConfigs();
    }
}
package io.ticticboom.mods.ma;

import io.ticticboom.mods.ma.setup.MABlocks;
import net.minecraftforge.fml.common.Mod;

@Mod(Ref.ID)
public class ModRoot {
    public ModRoot () {
        MABlocks.register();
    }
}

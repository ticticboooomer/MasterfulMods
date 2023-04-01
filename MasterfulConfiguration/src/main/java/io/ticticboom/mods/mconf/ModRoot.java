package io.ticticboom.mods.mconf;

import io.ticticboom.mods.mconf.setup.MConfRegistries;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Ref.ID)
public class ModRoot {
    public static final Logger LOG = LoggerFactory.getLogger("Masterful Configuration");
    public ModRoot() {
        MConfRegistries.registerParsers();
    }
}

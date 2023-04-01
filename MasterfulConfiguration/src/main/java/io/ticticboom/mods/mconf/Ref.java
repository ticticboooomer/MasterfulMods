package io.ticticboom.mods.mconf;

import net.minecraft.resources.ResourceLocation;

import java.sql.PreparedStatement;

public class Ref {
    public static final String ID = "mconf";

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(ID, path);
    }

    public static final class Parser {
        public static final ResourceLocation JSON = rl("json");
    }
}

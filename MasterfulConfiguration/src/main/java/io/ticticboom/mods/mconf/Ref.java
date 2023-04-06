package io.ticticboom.mods.mconf;

import net.minecraft.resources.ResourceLocation;

public class Ref {
    public static final String ID = "mconf";

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(ID, path);
    }

    public static final class Parser {
        public static final ResourceLocation JSON = rl("json");
    }

    public static final class DocTypes {
        public static final ResourceLocation TEXT_COMPONENT = rl("component/text");
        public static final ResourceLocation TRANSLATED_COMPONENT = rl("component/translated");
        public static final ResourceLocation BLOCK_PROPERTIES = rl("block/properties");
    }

    public static final class FunctionTypes {
        public static final ResourceLocation LITERAL = rl("literal");
        public static final ResourceLocation REFERENCE = rl("reference");
    }
}

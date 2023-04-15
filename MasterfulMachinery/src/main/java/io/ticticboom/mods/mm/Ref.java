package io.ticticboom.mods.mm;

import net.minecraft.resources.ResourceLocation;

public class Ref {
    public static final String ID = "mm";
    public static ResourceLocation rl(String path) {
        return new ResourceLocation(ID, path);
    }

    public static final ResourceLocation TAB = rl("tab");

    public static final class DocTypes {
        public static final ResourceLocation CONTROLLER = rl("controller");
        public static final ResourceLocation ITEM_PORT = rl("port/item");
        public static final ResourceLocation BLOCK_PROPS = rl("block/properties");
    }
}

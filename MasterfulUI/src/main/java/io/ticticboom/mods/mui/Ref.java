package io.ticticboom.mods.mui;

import net.minecraft.resources.ResourceLocation;

public class Ref {
    public static final String ID = "mui";

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(ID, path);
    }
    public static final class DocTypes {
        public static final ResourceLocation SCREENS = new ResourceLocation(ID, "screen");
        public static final ResourceLocation IMAGE_ELEMENT = new ResourceLocation(ID, "element/image");
        public static final ResourceLocation TEXT_ELEMENT = new ResourceLocation(ID, "element/text");
    }
}

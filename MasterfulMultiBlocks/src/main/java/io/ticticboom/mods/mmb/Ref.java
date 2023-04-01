package io.ticticboom.mods.mmb;

import net.minecraft.resources.ResourceLocation;

public class Ref {
    public static final String ID = "mmb";

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(ID, path);
    }
    public static final class DocTypes {
        public static final ResourceLocation STRUCTURE_KEY = rl("structure/key");
        public static final ResourceLocation STRUCTURE_PATTERN = rl("structure/pattern");
        public static final ResourceLocation FIXED_MULTIBLOCK = rl("multiblock/fixed");

        public static final class StructureParts {
            public static final ResourceLocation BLOCK = rl("structure/part/block");
        }

        public static final class CoreBlocks {
            public static final ResourceLocation CUSTOM = rl("structure/coreblock/custom");
        }
    }
}

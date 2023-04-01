package io.ticticboom.mods.mmb.runtime;

import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class MultiBlockRegistry {
    protected final Map<ResourceLocation, MultiBlockPartList> multiBlocks = new HashMap<>();

    public Map<ResourceLocation, MultiBlockPartList> multiBlocks() {
        return multiBlocks;
    }
}

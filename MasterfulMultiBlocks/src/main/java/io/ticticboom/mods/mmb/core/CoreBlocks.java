package io.ticticboom.mods.mmb.core;

import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class CoreBlocks {
    public static Map<ResourceLocation, CustomCoreBlockConfig.Spec> REGISTRY = new HashMap<>();
    public static Map<ResourceLocation, ICoreBlockSpecHandler> HANDLERS = new HashMap<>();
}

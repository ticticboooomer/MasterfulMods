package io.ticticboom.mods.mconf.parser;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public interface IParseableDocument {
    ResourceLocation getType();
    ResourceLocation getId();
    IParseableDocumentSpec getSpec();
    void runConsumers();
}

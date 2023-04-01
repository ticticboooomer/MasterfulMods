package io.ticticboom.mods.mconf.parser;

import net.minecraft.resources.ResourceLocation;

public interface IParseableDocument {
    ResourceLocation getType();
    ResourceLocation getId();
    IParseableDocumentSpec getSpec();
    void runConsumers();
}

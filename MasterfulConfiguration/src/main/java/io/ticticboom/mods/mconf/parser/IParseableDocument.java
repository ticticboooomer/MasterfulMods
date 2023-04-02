package io.ticticboom.mods.mconf.parser;

import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Optional;

public interface IParseableDocument {
    ResourceLocation getType();
    ResourceLocation getId();
    IParseableDocumentSpec getSpec();
    void runConsumers();
    List<IParseableDocument> getAttachments();
}

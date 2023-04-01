package io.ticticboom.mods.mconf.parser;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public interface IParseableDocumentSpec {
    String getString();
    Integer getInteger();
    Boolean getBoolean();
    Float getFloat();
    Double getDouble();
    Long getLong();

    IParseableDocumentSpec get(String key);
    List<IParseableDocumentSpec> getArray();
    ResourceLocation getSubDocument();
}

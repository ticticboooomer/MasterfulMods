package io.ticticboom.mods.mconf.document;

import net.minecraft.resources.ResourceLocation;

public class ConfigDocument<DATA extends IConfigDocumentData> {
    public ResourceLocation type;
    public ResourceLocation id;

    public DATA data;

    public ConfigDocument(ResourceLocation type, ResourceLocation id, DATA data) {
        this.type = type;
        this.id = id;
        this.data = data;
    }
}
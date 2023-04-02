package io.ticticboom.mods.mconf.setup;

import io.ticticboom.mods.mconf.Ref;
import io.ticticboom.mods.mconf.builtin.TextComponentConfig;
import io.ticticboom.mods.mconf.builtin.TranslatedComponentConfig;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParserFactory;
import io.ticticboom.mods.mconf.parser.json.JsonParserFactory;
import io.ticticboom.mods.mconf.setup.document.ConfigAttachmentType;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class MConfRegistries {
    public static Map<ResourceLocation, IParserFactory> PARSERS = new HashMap<>();
    public static Map<ResourceLocation, IParseableDocument> DOCUMENTS = new HashMap<>();
    public static final Map<ResourceLocation, ConfigDocumentType> DOC_TYPES = new HashMap<>();
    public static final Map<ResourceLocation, ConfigAttachmentType> ATTACHMENT_TYPES = new HashMap<>();
    public static void registerDefaults() {
        PARSERS.put(Ref.Parser.JSON, new JsonParserFactory());
        DOC_TYPES.put(Ref.DocTypes.TEXT_COMPONENT, new TextComponentConfig());
        DOC_TYPES.put(Ref.DocTypes.TRANSLATED_COMPONENT, new TranslatedComponentConfig());
    }
}
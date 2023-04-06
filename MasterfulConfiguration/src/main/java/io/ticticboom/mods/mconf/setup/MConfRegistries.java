package io.ticticboom.mods.mconf.setup;

import io.ticticboom.mods.mconf.Ref;
import io.ticticboom.mods.mconf.builtin.TextDocumentType;
import io.ticticboom.mods.mconf.document.ConfigDocumentType;
import io.ticticboom.mods.mconf.registry.MasterfulRegistry;
import io.ticticboom.mods.mconf.value.DocumentValueFunction;
import io.ticticboom.mods.mconf.value.builtin.LiteralValueFunction;
import io.ticticboom.mods.mconf.value.builtin.ReferenceValueFunction;

public class MConfRegistries {
    public static final MasterfulRegistry<ConfigDocumentType<?, ?>> DOCUMENT_TYPES = MasterfulRegistry.create();
    public static final MasterfulRegistry<DocumentValueFunction<?>> FUNCTION_CODECS = MasterfulRegistry.create();
    public static void registerDefaults() {
        DOCUMENT_TYPES.put(Ref.DocTypes.TEXT_COMPONENT, TextDocumentType.LITERAL);
        DOCUMENT_TYPES.put(Ref.DocTypes.TRANSLATED_COMPONENT, TextDocumentType.TRANSLATE);
        FUNCTION_CODECS.put(Ref.FunctionTypes.LITERAL, new LiteralValueFunction());
        FUNCTION_CODECS.put(Ref.FunctionTypes.REFERENCE, new ReferenceValueFunction());
    }
}
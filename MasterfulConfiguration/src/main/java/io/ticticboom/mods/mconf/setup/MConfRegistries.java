package io.ticticboom.mods.mconf.setup;

import io.ticticboom.mods.mconf.document.ConfigDocumentType;
import io.ticticboom.mods.mconf.registry.MasterfulRegistry;
import io.ticticboom.mods.mconf.value.DocumentValueFunction;

public class MConfRegistries {
    public static final MasterfulRegistry<ConfigDocumentType<?>> DOCUMENT_TYPES = MasterfulRegistry.create();
    public static final MasterfulRegistry<DocumentValueFunction<?>> FUNCTION_CODECS = MasterfulRegistry.create();
    public static void registerDefaults() {

    }
}
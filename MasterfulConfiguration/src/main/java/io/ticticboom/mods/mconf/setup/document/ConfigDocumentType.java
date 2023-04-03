package io.ticticboom.mods.mconf.setup.document;

import io.ticticboom.mods.mconf.document.IConfigSpecProcessor;

public abstract class ConfigDocumentType {
    public abstract IConfigSpecProcessor createSpecConsumer();
}
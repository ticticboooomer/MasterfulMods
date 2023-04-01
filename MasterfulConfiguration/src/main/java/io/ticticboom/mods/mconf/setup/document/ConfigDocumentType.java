package io.ticticboom.mods.mconf.setup.document;

import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;

public abstract class ConfigDocumentType {
    public abstract IConfigSpecConsumer createSpecConsumer();
}
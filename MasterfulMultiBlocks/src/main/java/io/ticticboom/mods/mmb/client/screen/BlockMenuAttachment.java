package io.ticticboom.mods.mmb.client.screen;

import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.setup.document.ConfigAttachmentType;

public abstract class BlockMenuAttachment extends ConfigAttachmentType {
    @Override
    public IConfigSpecConsumer createSpecConsumer() {
        return null;
    }
}

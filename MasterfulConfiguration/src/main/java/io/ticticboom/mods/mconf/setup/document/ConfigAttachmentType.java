package io.ticticboom.mods.mconf.setup.document;

import io.ticticboom.mods.mconf.document.attachment.IConfigAttachmentProcessor;

import java.util.ArrayList;
import java.util.List;

public abstract class ConfigAttachmentType extends ConfigDocumentType {
    protected List<IConfigAttachmentProcessor> attachmentProcessors = new ArrayList<>();
    public abstract void populateProcessors();
    public IConfigAttachmentProcessor createAttachmentConsumer(Class<?> owner) {
        for (IConfigAttachmentProcessor attProc : attachmentProcessors) {
            if (attProc.getOwnerClass() == owner) {
                return attProc;
            }
        }
        throw new IllegalArgumentException("No attachment processor found for owner class " + owner.getName());
    }
}

package io.ticticboom.mods.mconf.document.attachment;

import io.ticticboom.mods.mconf.parser.IParseableDocument;

public interface IConfigAttachmentProcessor {
    void consume(Object value, Object owner, IParseableDocument doc);
    Class<?> getOwnerClass();
}

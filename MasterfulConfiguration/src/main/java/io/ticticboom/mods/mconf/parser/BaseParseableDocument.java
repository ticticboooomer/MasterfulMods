package io.ticticboom.mods.mconf.parser;

import io.ticticboom.mods.mconf.document.DocumentValidationError;
import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.setup.MConfRegistries;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseParseableDocument implements IParseableDocument {
    public List<ResourceLocation> subDocuments = new ArrayList<>();
    protected void consumeSpec(IParseableDocumentSpec spec) {
        var rootDocType = MConfRegistries.DOC_TYPES.get(this.getType());
        IConfigSpecConsumer consumer = rootDocType.createSpecConsumer();
        var result = consumer.parse(spec);
        var errors = new ArrayList<DocumentValidationError>();
        for (ResourceLocation id : subDocuments) {
            MConfRegistries.DOCUMENTS.get(id).runConsumers();
        }
        if (consumer.validate(result, errors)) {
            consumer.consume(result, this);
        } else {
            consumer.handleValidationErrors(result, errors);
        }

        handleAttachments(rootDocType, result);
    }

    protected void handleAttachments(ConfigDocumentType rootDocType, Object rootSpec) {
        for (IParseableDocument attachment : getAttachments()) {
            IParseableDocument doc = MConfRegistries.DOCUMENTS.get(attachment.getType());
            IConfigSpecConsumer attachmentSpecConsumer = MConfRegistries.DOC_TYPES.get(doc.getType()).createSpecConsumer();
            Object attachmentSpec = attachmentSpecConsumer.parse(doc.getSpec());
            var errors = new ArrayList<DocumentValidationError>();
            if (attachmentSpecConsumer.validate(attachmentSpec, errors)) {
                attachmentSpecConsumer.consume(attachmentSpec, doc);
                var attachmentType = MConfRegistries.ATTACHMENT_TYPES.get(doc.getType());
                attachmentType.createAttachmentConsumer(rootDocType.getClass()).consume(attachmentSpec, rootSpec, doc);
            } else {
                attachmentSpecConsumer.handleValidationErrors(attachmentSpec, errors);
            }
        }
    }
}

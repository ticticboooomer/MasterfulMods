package io.ticticboom.mods.mconf.parser;

import io.ticticboom.mods.mconf.setup.MConfRegistries;
import io.ticticboom.mods.mconf.document.DocumentValidationError;
import io.ticticboom.mods.mconf.document.IConfigSpecProcessor;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseParseableDocument implements IParseableDocument {
    public List<ResourceLocation> subDocuments = new ArrayList<>();
    protected void consumeSpec(IParseableDocumentSpec spec) {
        var rootDocType = MConfRegistries.DOC_TYPES.get(this.getType());
        IConfigSpecProcessor consumer = rootDocType.createSpecConsumer();
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
    }
}

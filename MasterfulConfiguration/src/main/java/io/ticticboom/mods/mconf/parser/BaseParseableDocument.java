package io.ticticboom.mods.mconf.parser;

import io.ticticboom.mods.mconf.document.DocumentValidationError;
import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.setup.MConfRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseParseableDocument implements IParseableDocument {
    public List<ResourceLocation> subDocuments = new ArrayList<>();
    protected void consumeSpec(IParseableDocumentSpec spec) {
        IConfigSpecConsumer consumer = MConfRegistries.DOC_TYPES.get(this.getType()).createSpecConsumer();
        var result = consumer.parse(spec);
        var errors = new ArrayList<DocumentValidationError>();
        if (consumer.validate(result, errors)) {
            consumer.consume(result, this);
        } else {
            consumer.handleValidationErrors(result, errors);
        }
        for (ResourceLocation id : subDocuments) {
            MConfRegistries.DOCUMENTS.get(id).runConsumers();
        }
    }
}

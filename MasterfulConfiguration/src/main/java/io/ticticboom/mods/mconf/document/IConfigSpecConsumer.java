package io.ticticboom.mods.mconf.document;

import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;

import java.util.List;

public interface IConfigSpecConsumer {
    Object parse(IParseableDocumentSpec spec);
    boolean validate(Object value, List<DocumentValidationError> errors);
    void handleValidationErrors(Object value, List<DocumentValidationError> errors);
    void consume(Object value, IParseableDocument doc);
}

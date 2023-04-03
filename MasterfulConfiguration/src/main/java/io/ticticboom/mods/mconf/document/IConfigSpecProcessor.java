package io.ticticboom.mods.mconf.document;

import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;

import java.util.List;

public interface IConfigSpecProcessor {
    Object parse(IParseableDocumentSpec spec);
    void consume(Object value, IParseableDocument doc);
    boolean validate(Object value, List<DocumentValidationError> errors);
    void handleValidationErrors(Object value, List<DocumentValidationError> errors);
}

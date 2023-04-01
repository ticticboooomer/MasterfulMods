package io.ticticboom.mods.mconf.document;

import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;

import java.util.List;

public abstract class TypedConfigSpecConsumer<T> implements IConfigSpecConsumer {
    @Override
    public Object parse(IParseableDocumentSpec spec) {
        return safeParse(spec);
    }

    @Override
    public void consume(Object value, IParseableDocument doc) {
        safeConsume((T)value, doc);
    }

    @Override
    public void handleValidationErrors(Object value, List<DocumentValidationError> errors) {
        safeHandleValidationErrors((T)value, errors);
    }

    @Override
    public boolean validate(Object value, List<DocumentValidationError> errors) {
        return safeValidate((T)value, errors);
    }
    public abstract T safeParse(IParseableDocumentSpec value);
    public abstract boolean safeValidate(T value, List<DocumentValidationError> errors);
    public abstract void safeConsume(T value, IParseableDocument doc);
    public abstract void safeHandleValidationErrors(T value, List<DocumentValidationError> errors);
}

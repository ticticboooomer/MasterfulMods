package io.ticticboom.mods.mconf.document;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ThrowingConfigSpecProcessor<T> extends TypedConfigSpecProcessor<T> {
    @Override
    public void safeHandleValidationErrors(T value, List<DocumentValidationError> errors) {
        throw new IllegalStateException("Document Validation Failed with the following errors: " + errors.stream().map(x -> x.toString() + "\n").collect(Collectors.joining()));
    }
}
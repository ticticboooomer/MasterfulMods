package io.ticticboom.mods.mconf.document;

import io.ticticboom.mods.mconf.ModRoot;

import java.util.List;
import java.util.stream.Collectors;

public abstract class LoggingConfigSpecConsumer<T> extends  TypedConfigSpecConsumer<T> {
    @Override
    public void safeHandleValidationErrors(T value, List<DocumentValidationError> errors) {
        ModRoot.LOG.error("Document Validation Failed with the following errors: " + errors.stream().map(x -> x.toString() + "\n").collect(Collectors.joining()));
    }
}
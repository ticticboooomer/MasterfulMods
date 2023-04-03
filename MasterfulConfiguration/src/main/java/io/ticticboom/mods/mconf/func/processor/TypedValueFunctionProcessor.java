package io.ticticboom.mods.mconf.func.processor;

import io.ticticboom.mods.mconf.func.IDocumentValue;
import io.ticticboom.mods.mconf.func.parse.IParseableValueFunction;

public abstract class TypedValueFunctionProcessor<T> implements IValueFunctionProcessor {
    @Override
    public Object parse(IParseableValueFunction spec) {
        return safeParse(spec);
    }

    @Override
    public IDocumentValue consume(Object value, IParseableValueFunction doc) {
        return safeConsume((T) value, doc);
    }

    public abstract T safeParse(IParseableValueFunction doc);

    public abstract <R> IDocumentValue safeConsume(T value, IParseableValueFunction doc);
}

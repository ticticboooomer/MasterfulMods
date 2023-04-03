package io.ticticboom.mods.mconf.func.processor;

import io.ticticboom.mods.mconf.func.IDocumentValue;
import io.ticticboom.mods.mconf.func.parse.IParseableValueFunction;

public interface IValueFunctionProcessor {
    Object parse(IParseableValueFunction spec);
    IDocumentValue consume(Object value, IParseableValueFunction doc);
}

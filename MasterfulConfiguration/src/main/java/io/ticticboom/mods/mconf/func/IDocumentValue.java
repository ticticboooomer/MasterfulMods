package io.ticticboom.mods.mconf.func;

import com.google.gson.JsonPrimitive;

public interface IDocumentValue<T> {
    JsonPrimitive eval();
}

package io.ticticboom.mods.mconf.value.builtin;

import com.google.gson.JsonPrimitive;
import com.mojang.serialization.Codec;
import io.ticticboom.mods.mconf.value.DocumentValue;
import io.ticticboom.mods.mconf.value.DocumentValueFunction;

public class LiteralValueFunction extends DocumentValueFunction<String> {
    @Override
    public Codec<String> createCodec() {
        return Codec.STRING;
    }

    @Override
    public DocumentValue transform(String data) {
        return new DocumentValue(json -> new JsonPrimitive(data));
    }
}

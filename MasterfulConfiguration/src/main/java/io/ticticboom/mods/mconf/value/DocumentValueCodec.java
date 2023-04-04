package io.ticticboom.mods.mconf.value;

import com.google.gson.JsonPrimitive;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import io.ticticboom.mods.mconf.setup.MConfRegistries;

public class DocumentValueCodec implements Codec<DocumentValue> {
    public static final Codec<DocumentValue> INSTANCE = new DocumentValueCodec();
    @Override
    public <T> DataResult<Pair<DocumentValue, T>> decode(DynamicOps<T> ops, T input) {
        for (Codec<DocumentValue> value : MConfRegistries.FUNCTION_CODECS.values()) {
            DataResult<Pair<DocumentValue, T>> decode = value.decode(ops, input);
            if (decode.error().isEmpty()) {
                return decode;
            }
        }
        throw new RuntimeException("Value function not found, ensure all json is valid");
    }

    @Override
    public <T> DataResult<T> encode(DocumentValue input, DynamicOps<T> ops, T prefix) {
        return DataResult.success(ops.createString("Not Implemented"));
    }
}

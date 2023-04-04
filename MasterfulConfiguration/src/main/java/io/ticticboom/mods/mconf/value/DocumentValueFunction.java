package io.ticticboom.mods.mconf.value;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public abstract class DocumentValueFunction<D> implements Codec<DocumentValue> {


    private final Codec<D> codec;

    public DocumentValueFunction() {
        codec = createCodec();
    }

    @Override
    public <T> DataResult<Pair<DocumentValue, T>> decode(DynamicOps<T> ops, T input) {
        var res = codec.decode(ops, input);
        D data = res.get().orThrow().getFirst();
        return DataResult.success(new Pair<>(transform(data), input));
    }


    @Override
    public <T> DataResult<T> encode(DocumentValue input, DynamicOps<T> ops, T prefix) {
        return DataResult.error(() -> "Not Implemented");
    }

    public abstract Codec<D> createCodec();

    public abstract DocumentValue transform(D data);
}

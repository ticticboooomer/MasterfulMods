package io.ticticboom.mods.mconf.codec;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.document.IConfigDocumentData;
import io.ticticboom.mods.mconf.setup.MConfRegistries;
import net.minecraft.resources.ResourceLocation;

public class SubDocumentCodec implements Codec<ConfigDocument<?>> {

    @Override
    public <T> DataResult<T> encode(ConfigDocument<?> input, DynamicOps<T> ops, T prefix) {
        return DataResult.error(() -> "Not implemented yet");
    }

    @Override
    public <T> DataResult<Pair<ConfigDocument<?>, T>> decode(DynamicOps<T> ops, T input) {
        var header = MCodecs.DOCUMENT_HEADER.parse(ops, input).get().orThrow();
        var codec = MConfRegistries.DOCUMENT_TYPES.get(header.type());
        var result = codec.getCodec().decode(ops, input);
        return DataResult.success(Pair.of(new ConfigDocument<>(header.type(), header.id(), result.get().orThrow().getFirst().data), input));
    }
}
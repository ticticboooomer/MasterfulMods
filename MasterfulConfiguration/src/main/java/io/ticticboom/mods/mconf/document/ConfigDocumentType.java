package io.ticticboom.mods.mconf.document;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.ticticboom.mods.mconf.codec.MCodecs;

import java.util.function.Supplier;

public abstract class ConfigDocumentType<DATA extends IConfigDocumentData, RESULT> {
    private Codec<ConfigDocument<DATA>> codec;
    public ConfigDocumentType() {
        codec = RecordCodecBuilder.create(b ->
                b.group(MCodecs.RESOURCE_LOCATION.fieldOf("type").forGetter(x -> x.type))
                        .and(MCodecs.RESOURCE_LOCATION.fieldOf("id").forGetter(x -> x.id))
                        .and(createCodec().fieldOf("data").forGetter(x -> x.data))
                        .apply(b, ConfigDocument::new));
    }

    public Codec<ConfigDocument<DATA>> getCodec() {
        return codec;
    }

    protected abstract Codec<DATA> createCodec();

    public abstract ConfigDocumentResult<DATA, RESULT> createResult(ConfigDocument<DATA> doc, JsonObject state);
    public void consume(ConfigDocument<DATA> result) {

    }

    public record ConfigDocumentResult<DATA extends IConfigDocumentData, RESULT>(Pair<Supplier<RESULT>, ConfigDocument<DATA>> resultData) {}
}
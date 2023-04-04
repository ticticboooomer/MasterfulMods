package io.ticticboom.mods.mconf.document;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.ticticboom.mods.mconf.codec.MCodecs;

public abstract class ConfigDocumentType<DATA extends IConfigDocumentData> {

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

    public abstract void process(ConfigDocument<DATA> doc);
}
package io.ticticboom.mods.msc.spell;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.ticticboom.mods.mconf.codec.MCodecs;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.document.ConfigDocumentType;
import io.ticticboom.mods.mconf.document.IConfigDocumentData;

public class SpellConfig extends ConfigDocumentType<SpellConfig.Data, SpellConfig.Data> {
    @Override
    protected Codec<Data> createCodec() {
        return RecordCodecBuilder.create(b ->
                b.group(MCodecs.SUB_DOCUMENT.fieldOf("name").forGetter(x -> x.name))
                .and(MCodecs.SUB_DOCUMENT.fieldOf("description").forGetter(x -> x.description))
                        .and(MCodecs.SUB_DOCUMENT.fieldOf("cost").forGetter(x -> x.cost))
                        .and(MCodecs.SUB_DOCUMENT.fieldOf("range").forGetter(x -> x.range))
                        .and(MCodecs.SUB_DOCUMENT.fieldOf("action").forGetter(x -> x.action))
                        .apply(b, Data::new));
    }

    @Override
    public ConfigDocumentResult<Data, Data> createResult(ConfigDocument<Data> configDocument, JsonObject jsonObject) {
        return new ConfigDocumentResult<>(new Pair<>(() -> configDocument.data, configDocument));
    }

    public record Data(
            ConfigDocument<?> name,
            ConfigDocument<?> description,
            ConfigDocument<?> cost,
            ConfigDocument<?> range,
            ConfigDocument<?> action
    ) implements IConfigDocumentData {
    }
}

package io.ticticboom.mods.msc.cost;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.document.ConfigDocumentType;
import io.ticticboom.mods.mconf.document.IConfigDocumentData;

public class FreeSpellCostConfig extends ConfigDocumentType<FreeSpellCostConfig.Data, ISpellCostHandler> {
    @Override
    protected Codec<Data> createCodec() {
        return RecordCodecBuilder.create(instance -> instance.group(Codec.EMPTY.forGetter(x -> null)).apply(instance, z -> new Data()));
    }

    @Override
    public ConfigDocumentResult<Data, ISpellCostHandler> createResult(ConfigDocument<Data> configDocument, JsonObject jsonObject) {
        ISpellCostHandler handler = (a, b, c) -> {};
        return new ConfigDocumentResult<>(new Pair<>(() -> handler, configDocument));
    }

    public record Data(
    ) implements IConfigDocumentData {

    }
}

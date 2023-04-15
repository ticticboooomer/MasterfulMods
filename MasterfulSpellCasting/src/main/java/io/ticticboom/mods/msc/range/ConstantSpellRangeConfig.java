package io.ticticboom.mods.msc.range;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.document.ConfigDocumentType;
import io.ticticboom.mods.mconf.document.IConfigDocumentData;
import io.ticticboom.mods.msc.spell.SpellConfig;

public class ConstantSpellRangeConfig extends ConfigDocumentType<ConstantSpellRangeConfig.Data, ISpellRangeHandler> {

    @Override
    protected Codec<ConstantSpellRangeConfig.Data> createCodec() {
        return RecordCodecBuilder.create(instance -> instance.group(Codec.INT.fieldOf("value").forGetter(x -> x.value)).apply(instance, Data::new));
    }

    @Override
    public ConfigDocumentResult<Data, ISpellRangeHandler> createResult(ConfigDocument<Data> configDocument, JsonObject jsonObject) {
        ISpellRangeHandler handler = (l) -> configDocument.data.value;
        return new ConfigDocumentResult<>(new Pair<>(() -> handler, configDocument));
    }

    public record Data(
            int value
    ) implements IConfigDocumentData {

    }
}

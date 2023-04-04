package io.ticticboom.mods.mconf.value.builtin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.ticticboom.mods.mconf.value.DocumentValue;
import io.ticticboom.mods.mconf.value.DocumentValueCodec;
import io.ticticboom.mods.mconf.value.DocumentValueFunction;

public class ReferenceValueFunction extends DocumentValueFunction<ReferenceValueFunction.Data> {
    @Override
    public Codec<Data> createCodec() {
        return RecordCodecBuilder.create(b ->
                b.group(DocumentValueCodec.INSTANCE.fieldOf("value").forGetter(data -> data.variable))
                        .apply(b, Data::new));
    }

    @Override
    public DocumentValue transform(Data data) {
        return new DocumentValue(json -> json.get(data.variable.get(json).getAsString()));
    }

    public record Data(DocumentValue variable) {}
}

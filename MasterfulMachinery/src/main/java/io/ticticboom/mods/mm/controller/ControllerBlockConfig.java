package io.ticticboom.mods.mm.controller;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.ticticboom.mods.mconf.codec.MCodecs;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.document.ConfigDocumentType;
import io.ticticboom.mods.mconf.document.IConfigDocumentData;
import io.ticticboom.mods.mm.setup.MMControllers;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ControllerBlockConfig extends ConfigDocumentType<ControllerBlockConfig.Spec, ControllerBlockConfig.Spec> {
    @Override
    protected Codec<Spec> createCodec() {
        return RecordCodecBuilder.create(b -> b.group(MCodecs.SUB_DOCUMENT.fieldOf("name").forGetter(x -> x.name))
                .and(Codec.list(MCodecs.SUB_DOCUMENT).fieldOf("ports").forGetter(x -> x.ports))
                .and(MCodecs.SUB_DOCUMENT.optionalFieldOf("blockProperties").forGetter(x -> x.blockProps))
                .apply(b, Spec::new));
    }

    @Override
    public void consume(ConfigDocument<Spec> result) {
        MMControllers.CONTROLLERS.put(result.id, result);
    }

    @Override
    public ConfigDocumentResult<Spec, Spec> createResult(ConfigDocument<Spec> configDocument, JsonObject jsonObject) {
        return new ConfigDocumentResult<>(new Pair<>(() -> configDocument.data, configDocument));
    }

    public record Spec(
            ConfigDocument<?> name,
            List<ConfigDocument<?>> ports,
            Optional<ConfigDocument<?>> blockProps
    ) implements IConfigDocumentData {}
}
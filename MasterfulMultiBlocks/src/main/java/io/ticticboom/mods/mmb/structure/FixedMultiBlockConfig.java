package io.ticticboom.mods.mmb.structure;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.ticticboom.mods.mconf.codec.MCodecs;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.document.ConfigDocumentType;
import io.ticticboom.mods.mconf.document.IConfigDocumentData;
import io.ticticboom.mods.mmb.core.ICoreBlockData;
import io.ticticboom.mods.mmb.runtime.list.FixedMultiBlockPartList;
import io.ticticboom.mods.mmb.setup.MMBRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FixedMultiBlockConfig extends ConfigDocumentType<FixedMultiBlockConfig.Data, FixedMultiBlockConfig.Data> {
    public static final Map<ResourceLocation, Data> REGISTRY = new HashMap<>();
    @Override
    protected Codec<Data> createCodec() {
        return RecordCodecBuilder.create(b ->
                b.group(MCodecs.SUB_DOCUMENT.fieldOf("coreBlock").forGetter(x -> x.coreBlock))
                        .and(MCodecs.SUB_DOCUMENT.fieldOf("pattern").forGetter(x -> x.pattern))
                        .and(MCodecs.SUB_DOCUMENT.fieldOf("key").forGetter(x -> x.key))
                        .and(MCodecs.SUB_DOCUMENT.fieldOf("displayName").forGetter(x -> x.displayName))
                        .apply(b, Data::new));
    }

    @Override
    public ConfigDocumentResult<Data, Data> createResult(ConfigDocument<Data> configDocument, JsonObject jsonObject) {
        return new ConfigDocumentResult<>(new Pair<>(() -> configDocument.data, configDocument));
    }

    @Override
    public void consume(ConfigDocument<Data> result) {
        MMBRegistries.MULTIBLOCKS.put(result.id, () -> new FixedMultiBlockPartList(result.data));
    }

    public record Data(
            ConfigDocument<?> coreBlock,
            ConfigDocument<?> pattern,
            ConfigDocument<?> key,
            ConfigDocument<?> displayName
    ) implements IConfigDocumentData {
    }
}

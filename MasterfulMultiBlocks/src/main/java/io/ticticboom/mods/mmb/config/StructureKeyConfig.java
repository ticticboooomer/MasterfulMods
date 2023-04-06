package io.ticticboom.mods.mmb.config;

import com.google.common.base.Supplier;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.ticticboom.mods.mconf.codec.MCodecs;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.document.ConfigDocumentType;
import io.ticticboom.mods.mconf.document.IConfigDocumentData;
import io.ticticboom.mods.mconf.setup.MConfRegistries;
import io.ticticboom.mods.mconf.value.DocumentValue;
import io.ticticboom.mods.mconf.value.DocumentValueCodec;
import io.ticticboom.mods.mmb.structure.part.StructureParts;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructureKeyConfig extends ConfigDocumentType<StructureKeyConfig.Spec, Map<Character, StructureParts.IPartHandlerFactory>> {
    private static final Codec<SpecEntry> INNER = RecordCodecBuilder.create(b ->
            b.group(Codec.STRING.fieldOf("character").forGetter(s -> s.chr))
                    .and(MCodecs.SUB_DOCUMENT.fieldOf("part").forGetter(x -> x.part)).apply(b, StructureKeyConfig.SpecEntry::new));

    @Override
    protected Codec<Spec> createCodec() {
        return RecordCodecBuilder.create(b -> b.group(Codec.list(INNER).fieldOf("keys").forGetter(x -> x.entries)).apply(b, Spec::new));
    }

    @Override
    public ConfigDocumentResult<Spec, Map<Character, StructureParts.IPartHandlerFactory>> createResult(ConfigDocument<Spec> configDocument, JsonObject jsonObject) {
        Supplier<Map<Character, StructureParts.IPartHandlerFactory>> supplier = () -> {
            var result = new HashMap<Character, StructureParts.IPartHandlerFactory>();
            configDocument.data.entries.forEach(e -> {
                Character chr = e.chr.charAt(0);
                var type = MConfRegistries.DOCUMENT_TYPES.get(e.part.type);

                result.put(chr, (StructureParts.IPartHandlerFactory) type.createResult(e.part.cast(), jsonObject).resultData().getFirst().get());
            });
            return result;
        };
        return new ConfigDocumentResult<>(new Pair<>(supplier, configDocument));
    }

    public record Spec(
            List<SpecEntry> entries
    ) implements IConfigDocumentData {
    }

    public record SpecEntry(String chr, ConfigDocument<?> part) {
    }
}

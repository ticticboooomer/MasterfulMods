package io.ticticboom.mods.mconf.document;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.ticticboom.mods.mconf.codec.MCodecs;
import net.minecraft.resources.ResourceLocation;

public record ConfigDocumentHeader(
        ResourceLocation type,
        ResourceLocation id
) {
    public static final Codec<ConfigDocumentHeader> CODEC = RecordCodecBuilder.create(b ->
            b.group(MCodecs.RESOURCE_LOCATION.fieldOf("id").forGetter(x -> x.id))
                    .and(MCodecs.RESOURCE_LOCATION.fieldOf("type").forGetter(x -> x.type))
                    .apply(b, (id, type) -> new ConfigDocumentHeader(type, id)));
}

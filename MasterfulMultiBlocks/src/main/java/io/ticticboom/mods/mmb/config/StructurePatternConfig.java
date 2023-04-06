package io.ticticboom.mods.mmb.config;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.document.ConfigDocumentType;
import io.ticticboom.mods.mconf.document.IConfigDocumentData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructurePatternConfig extends ConfigDocumentType<StructurePatternConfig.Spec, StructurePatternConfig.Spec> {
    @Override
    protected Codec<Spec> createCodec() {
        return RecordCodecBuilder.create(b ->
                b.group(Codec.list(Codec.list(Codec.STRING)).fieldOf("pattern").forGetter(x -> x.pattern))
                        .and(StringRepresentable.fromEnum(Spec.YDirection::values).fieldOf("yDirection").forGetter(x -> x.direction))
                        .and(StringRepresentable.fromEnum(Spec.FacingDirection::values).fieldOf("facingDirection").forGetter(x -> x.facing))
                        .apply(b, StructurePatternConfig.Spec::new));
    }

    @Override
    public ConfigDocumentResult<Spec, Spec> createResult(ConfigDocument<Spec> configDocument, JsonObject jsonObject) {
        return null;
    }


    public record Spec(
            List<List<String>> pattern,
            YDirection direction,
            FacingDirection facing
    ) implements IConfigDocumentData {
        public enum YDirection implements StringRepresentable {
            TOP_FIRST,
            BOTTOM_FIRST;

            @Override
            public String getSerializedName() {
                return name();
            }
        }
        public enum FacingDirection implements StringRepresentable {
            NORTH_FIRST,
            EAST_FIRST,
            SOUTH_FIRST,
            WEST_FIRST;

            @Override
            public String getSerializedName() {
                return name();
            }
        }
    }
}

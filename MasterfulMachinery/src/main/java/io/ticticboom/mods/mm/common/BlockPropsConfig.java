package io.ticticboom.mods.mm.common;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.document.ConfigDocumentType;
import io.ticticboom.mods.mconf.document.IConfigDocumentData;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Optional;

public class BlockPropsConfig extends ConfigDocumentType<BlockPropsConfig.Spec, BlockPropsConfig.BlockPropsHandler> {

    @Override
    protected Codec<Spec> createCodec() {
        return RecordCodecBuilder.create(b ->
                b.group(Codec.FLOAT.optionalFieldOf("destroyTime").forGetter(x -> x.destroyTime))
                        .and(Codec.FLOAT.optionalFieldOf("resistance").forGetter(x-> x.resistance))
                        .apply(b, Spec::new));
    }

    @Override
    public ConfigDocumentResult<Spec, BlockPropsHandler> createResult(ConfigDocument<Spec> configDocument, JsonObject jsonObject) {
        return new ConfigDocumentResult<>(new Pair<>(() -> new BlockPropsHandler(configDocument.data), configDocument));
    }

    public record BlockPropsHandler(Spec spec) {
        public BlockBehaviour.Properties populate(BlockBehaviour.Properties props) {
            if (spec.resistance().isPresent()) {
                props.explosionResistance(spec.resistance().get());
            } if (spec.destroyTime().isPresent()) {
                props.destroyTime(spec.destroyTime().get());
            }
            return props;
        }
    }
    public record Spec(
            Optional<Float> destroyTime,
            Optional<Float> resistance
    ) implements IConfigDocumentData {

    }
}

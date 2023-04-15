package io.ticticboom.mods.mm.port.item;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.document.ConfigDocumentType;
import io.ticticboom.mods.mconf.document.IConfigDocumentData;
import io.ticticboom.mods.mm.port.PortTypeConfigs;
import io.ticticboom.mods.mm.port.base.IPortRecipeProcessor;
import io.ticticboom.mods.mm.port.base.IPortStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class ItemPortConfig extends ConfigDocumentType<ItemPortConfig.Spec, ItemPortConfig.PortTypeConfigHandler> {
    @Override
    protected Codec<Spec> createCodec() {
        return RecordCodecBuilder.create(b ->
                b.group(Codec.INT.fieldOf("slotsX").forGetter(spec -> spec.slotsX))
                        .and(Codec.INT.fieldOf("slotsY").forGetter(spec -> spec.slotsY))
                        .apply(b, Spec::new));
    }

    @Override
    public ConfigDocumentResult<Spec, PortTypeConfigHandler> createResult(ConfigDocument<Spec> configDocument, JsonObject jsonObject) {
        return new ConfigDocumentResult<>(new Pair<>(() -> new PortTypeConfigHandler(configDocument.data), configDocument));
    }

    public static final class PortTypeConfigHandler extends PortTypeConfigs.PortTypeConfigHandler<Spec> {
        private final Spec spec;

        public PortTypeConfigHandler(Spec spec) {
            this.spec = spec;
        }
        @Override
        public IPortStorage createPortStorage(Level level, BlockPos pos) {
            return new ItemPortStorage(spec.slotsX(), spec.slotsY());
        }

        @Override
        public IPortRecipeProcessor createPortRecipeProcessor(Level level, BlockPos pos) {
            return new IPortRecipeProcessor() {};
        }
    }

    public record Spec(
            int slotsX,
            int slotsY
    ) implements IConfigDocumentData {
    }
}
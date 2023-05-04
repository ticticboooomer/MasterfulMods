package io.ticticboom.mods.mm.port.fluid;

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
import io.ticticboom.mods.mm.port.item.ItemPortConfig;
import io.ticticboom.mods.mm.port.item.ItemPortStorage;
import io.ticticboom.mods.mm.setup.holder.RegisteredPort;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class FluidPortConfig extends ConfigDocumentType<FluidPortConfig.Spec, FluidPortConfig.PortTypeConfigHandler> {
    @Override
    protected Codec<Spec> createCodec() {
        return RecordCodecBuilder.create(b -> b.group(Codec.INT.fieldOf("capacity").forGetter(x -> x.capacity)).apply(b, Spec::new));
    }

    @Override
    public ConfigDocumentResult<Spec, FluidPortConfig.PortTypeConfigHandler> createResult(ConfigDocument<Spec> configDocument, JsonObject jsonObject) {
        return new ConfigDocumentResult<>(new Pair<>(() -> new PortTypeConfigHandler(configDocument.data), configDocument));
    }

    public static final class PortTypeConfigHandler extends PortTypeConfigs.PortTypeConfigHandler {
        private final Spec spec;

        public PortTypeConfigHandler(Spec spec) {
            this.spec = spec;
        }
        @Override
        public IPortStorage createPortStorage(Level level, BlockPos pos) {
            return new ItemPortStorage(spec.capacity, spec.capacity);
        }
        @Override
        public IPortRecipeProcessor createPortRecipeProcessor(Level level, BlockPos pos) {
            return new IPortRecipeProcessor() {};
        }

        @Override
        public String createName(ConfigDocument doc, RegisteredPort port) {
            return "maybe";
        }
    }

    public record Spec(
            int capacity
    ) implements IConfigDocumentData {
    }
}

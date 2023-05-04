package io.ticticboom.mods.mm.port.item;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.ticticboom.mods.mconf.builtin.TextDocumentType;
import io.ticticboom.mods.mconf.codec.MCodecs;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.document.ConfigDocumentType;
import io.ticticboom.mods.mconf.document.IConfigDocumentData;
import io.ticticboom.mods.mconf.setup.MConfRegistries;
import io.ticticboom.mods.mm.port.PortTypeConfigs;
import io.ticticboom.mods.mm.port.base.IPortRecipeProcessor;
import io.ticticboom.mods.mm.port.base.IPortStorage;
import io.ticticboom.mods.mm.setup.holder.RegisteredPort;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class ItemPortConfig extends ConfigDocumentType<ItemPortConfig.Spec, ItemPortConfig.PortTypeConfigHandler> {
    @Override
    protected Codec<Spec> createCodec() {
        return RecordCodecBuilder.create(b ->
                b.group(Codec.INT.fieldOf("slotsX").forGetter(spec -> spec.slotsX))
                        .and(Codec.INT.fieldOf("slotsY").forGetter(spec -> spec.slotsY))
                        .and(MCodecs.SUB_DOCUMENT.fieldOf("name").forGetter(spec -> spec.name))
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

        @Override
        public String createName(ConfigDocument<Spec> doc, RegisteredPort port) {
            var textType = MConfRegistries.DOCUMENT_TYPES.get(doc.data.name.type);
            var res = textType.createResult(doc.data.name.cast(), new JsonObject());
            var comp = ((Component) res.resultData().getFirst().get());
            return comp.getString();
        }
    }

    public record Spec(
            int slotsX,
            int slotsY,
            ConfigDocument<?> name
    ) implements IConfigDocumentData {
    }
}
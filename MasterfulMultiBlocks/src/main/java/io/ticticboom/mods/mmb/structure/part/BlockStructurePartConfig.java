package io.ticticboom.mods.mmb.structure.part;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.ticticboom.mods.mconf.codec.MCodecs;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.document.ConfigDocumentType;
import io.ticticboom.mods.mconf.document.IConfigDocumentData;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockStructurePartConfig extends ConfigDocumentType<BlockStructurePartConfig.Data, StructureParts.IPartHandlerFactory> {
    @Override
    protected Codec<Data> createCodec() {
        return RecordCodecBuilder.create(b ->
                b.group(MCodecs.RESOURCE_LOCATION.fieldOf("block").forGetter(x -> x.block)).apply(b, Data::new));
    }

    @Override
    public ConfigDocumentResult<Data, StructureParts.IPartHandlerFactory> createResult(ConfigDocument<Data> configDocument, JsonObject jsonObject) {
        return new ConfigDocumentResult<>(new Pair<>(() -> new Factory(configDocument.data), configDocument));
    }

    public record Data(ResourceLocation block) implements StructureParts.IPartSpec, IConfigDocumentData {
    }

    public static final class Factory implements StructureParts.IPartHandlerFactory {

        private final Data data;

        public Factory(Data data) {

            this.data = data;
        }
        @Override
        public StructureParts.SpecHandler create(BlockPos offset) {
            return new Handler(data, offset);
        }
    }

    public static final class Handler extends StructureParts.SpecHandler {
        private final Data data;

        public Handler(Data spec, BlockPos offset) {
            super(spec, offset);
            data = spec;
        }

        @Override
        public boolean verifyPlacement(Level level) {
            var required = ForgeRegistries.BLOCKS.getValue(data.block());
            return level.getBlockState(this.offset).is(required);
        }
    }
}

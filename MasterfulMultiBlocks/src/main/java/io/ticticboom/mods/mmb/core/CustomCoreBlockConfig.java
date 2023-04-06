package io.ticticboom.mods.mmb.core;

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
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class CustomCoreBlockConfig extends ConfigDocumentType<CustomCoreBlockConfig.Data, CustomCoreBlockConfig.Data> {
    @Override
    protected Codec<Data> createCodec() {
        return RecordCodecBuilder.create(b -> b.group(MCodecs.RESOURCE_LOCATION.fieldOf("block")
                .forGetter(x -> x.block)).apply(b, Data::new));
    }

    @Override
    public ConfigDocumentResult<Data, Data> createResult(ConfigDocument<Data> configDocument, JsonObject jsonObject) {
        return new ConfigDocumentResult<>(new Pair<>(() -> configDocument.data, configDocument));
    }

    public record Data(ResourceLocation block) implements ICoreBlockData, IConfigDocumentData {
        @Override
        public boolean isCoreBlock(Level level, BlockPos pos) {
            Block blk = level.getBlockState(pos).getBlock();
            return ForgeRegistries.BLOCKS.getKey(blk).equals(block);
        }
    }
}

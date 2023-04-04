package io.ticticboom.mods.mconf.codec;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;
import net.minecraft.resources.ResourceLocation;

public class ResourceLocationCodec implements PrimitiveCodec<ResourceLocation> {
    @Override
    public <T> DataResult<ResourceLocation> read(DynamicOps<T> ops, T input) {
        var str = ops.getStringValue(input).getOrThrow(false, e -> {throw new RuntimeException(e);});
        try {
            return DataResult.success(new ResourceLocation(str));
        } catch (Exception e) {
            return DataResult.error(e::getMessage);
        }
    }

    @Override
    public <T> T write(DynamicOps<T> ops, ResourceLocation value) {
        return ops.createString(value.toString());
    }

}

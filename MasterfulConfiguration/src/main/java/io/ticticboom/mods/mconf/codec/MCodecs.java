package io.ticticboom.mods.mconf.codec;

import com.mojang.serialization.Codec;
import io.ticticboom.mods.mconf.builtin.TextDocumentType;
import io.ticticboom.mods.mconf.document.ConfigDocumentHeader;
import io.ticticboom.mods.mconf.registry.MasterfulRegistry;
import net.minecraft.network.chat.Component;

public class MCodecs {
    public static final ResourceLocationCodec RESOURCE_LOCATION = new ResourceLocationCodec();
    public static final Codec<ConfigDocumentHeader> DOCUMENT_HEADER = ConfigDocumentHeader.CODEC;

    public static final SubDocumentCodec SUB_DOCUMENT = new SubDocumentCodec();

}

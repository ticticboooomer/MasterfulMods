package io.ticticboom.mods.mconf.document;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;

public class DocumentParser<DATA extends IConfigDocumentData> {
    private final ConfigDocumentType<DATA> type;

    public DocumentParser(ConfigDocumentType<DATA> type) {

        this.type = type;
    }

    public ConfigDocument<DATA> parse(JsonObject document) {
        return type.getCodec().parse(JsonOps.INSTANCE, document).getOrThrow(false, (e) -> {
            throw new RuntimeException(e);
        });
    }
}

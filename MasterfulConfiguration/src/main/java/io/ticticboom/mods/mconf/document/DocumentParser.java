package io.ticticboom.mods.mconf.document;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;

public class DocumentParser<DATA extends IConfigDocumentData, R> {
    private final ConfigDocumentType<DATA, R> type;

    public DocumentParser(ConfigDocumentType<DATA, R> type) {

        this.type = type;
    }

    public ConfigDocument<DATA> parse(JsonObject document) {
        return JsonOps.INSTANCE.withParser(type.getCodec()).apply(document).getOrThrow(false, e -> {
            throw new RuntimeException(e);
        });
    }
}

package io.ticticboom.mods.mconf.loader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.ticticboom.mods.mconf.document.DocumentParser;
import io.ticticboom.mods.mconf.setup.MConfRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.Collection;

public class JsonLoader implements IDocumentLoader {

    private final Collection<JsonObject> jsonObjects;

    public JsonLoader(Collection<JsonObject> jsonObjects) {

        this.jsonObjects = jsonObjects;
    }

    @Override
    public void loadDocuments() {
        jsonObjects.forEach(this::loadDocument);
    }

    private void loadDocument(JsonObject json) {
        var type = new ResourceLocation(json.get("type").getAsString());
        var cdt = MConfRegistries.DOCUMENT_TYPES.get(type);
        var doc = new DocumentParser<>(cdt).parse(json);
        cdt.consume(doc.cast());
    }
}
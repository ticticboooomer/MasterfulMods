package io.ticticboom.mods.mconf.loader.memory;

import com.google.gson.JsonObject;
import io.ticticboom.mods.mconf.Ref;
import io.ticticboom.mods.mconf.loader.IDocumentLoader;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.setup.MConfRegistries;

import java.util.ArrayList;
import java.util.List;

public class JsonDocumentLoader implements IDocumentLoader {

    private final List<JsonObject> jsonObjects;

    public JsonDocumentLoader(List<JsonObject> jsonObjects) {

        this.jsonObjects = jsonObjects;
    }

    @Override
    public void loadDocuments() {
        var docs = new ArrayList<IParseableDocument>();
        for (JsonObject jsonObject : jsonObjects) {
            var factory = MConfRegistries.PARSERS.get(Ref.Parser.JSON);
            var doc = factory.createParser(jsonObject.toString());
            docs.add(doc);
        }
        for (var doc : docs) {
            doc.runConsumers();
        }
    }
}

package io.ticticboom.mods.mconf.parser.json;

import com.google.gson.JsonParser;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParserFactory;

public class JsonParserFactory implements IParserFactory {
    @Override
    public IParseableDocument createParser(String contents) {
        var json = JsonParser.parseString(contents);
        return new JsonDocument(json.getAsJsonObject());
    }

    @Override
    public boolean matchFormat(String filename) {
        return filename.endsWith(".json");
    }
}

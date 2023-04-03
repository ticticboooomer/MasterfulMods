package io.ticticboom.mods.mconf.func.parse.json;

import com.google.gson.JsonElement;
import io.ticticboom.mods.mconf.func.parse.IParseableValueFunction;

import java.util.List;

public class JsonValueFunction implements IParseableValueFunction {
    private final JsonElement json;

    public JsonValueFunction(JsonElement json) {
        this.json = json;
    }

    @Override
    public String getString() {
        return json.getAsString();
    }

    @Override
    public Integer getInteger() {
        return json.getAsInt();
    }

    @Override
    public Boolean getBoolean() {
        return json.getAsBoolean();
    }

    @Override
    public Float getFloat() {
        return json.getAsFloat();
    }

    @Override
    public Double getDouble() {
        return json.getAsDouble();
    }

    @Override
    public Long getLong() {
        return json.getAsLong();
    }

    @Override
    public boolean has(String key) {
        return json.getAsJsonObject().has(key);
    }

    @Override
    public IParseableValueFunction get(String key) {
        return new JsonValueFunction(json.getAsJsonObject().get(key));
    }

    @Override
    public List<JsonValueFunction> getArray() {
        return json.getAsJsonArray().asList().stream().map(JsonValueFunction::new).toList();
    }
}

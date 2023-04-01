package io.ticticboom.mods.mconf.parser.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.setup.MConfRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class JsonDocumentSpec implements IParseableDocumentSpec {
    private final JsonElement json;
    private final JsonDocument parent;

    protected JsonDocumentSpec(JsonElement json, JsonDocument parent) {
        this.json = json;
        this.parent = parent;
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
    public IParseableDocumentSpec get(String key) {
        return new JsonDocumentSpec(json.getAsJsonObject().get(key), parent);
    }

    @Override
    public List<IParseableDocumentSpec> getArray() {
        return json.getAsJsonArray().asList().stream().map(element -> (IParseableDocumentSpec) new JsonDocumentSpec(element, parent)).toList();
    }

    @Override
    public ResourceLocation getSubDocument() {
        JsonObject optionalInline = json.getAsJsonObject();
        if (!optionalInline.has("type") || !optionalInline.has("id")) {
            throw new RuntimeException("Invalid Sub Document");
        }
        if (optionalInline.has("spec")) {
            var parser = new JsonDocument(optionalInline);
            parent.subDocuments.add(parser.getId());
            return parser.getId();
        }
        var rl = new ResourceLocation(optionalInline.get("id").getAsString());
        parent.subDocuments.add(rl);
        return rl;
    }
}

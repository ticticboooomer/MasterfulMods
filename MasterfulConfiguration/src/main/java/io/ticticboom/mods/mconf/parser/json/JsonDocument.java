package io.ticticboom.mods.mconf.parser.json;

import com.google.gson.JsonObject;
import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.BaseParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.setup.MConfRegistries;
import net.minecraft.resources.ResourceLocation;

public class JsonDocument extends BaseParseableDocument {

    private final JsonObject json;
    private final JsonDocumentSpec spec;

    public JsonDocument(JsonObject json) {
        this.json = json;
        MConfRegistries.DOCUMENTS.put(this.getId(), this);
        spec = new JsonDocumentSpec(json.get("spec").getAsJsonObject(), this);

    }
    @Override
    public ResourceLocation getType() {
        return new ResourceLocation(json.get("type").getAsString());
    }

    @Override
    public ResourceLocation getId() {
        return new ResourceLocation(json.get("id").getAsString());
    }

    @Override
    public IParseableDocumentSpec getSpec() {
        return spec;
    }

    @Override
    public void runConsumers() {
        consumeSpec(spec);
    }
}

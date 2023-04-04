package io.ticticboom.mods.mconf.value;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class DocumentValue {

    @FunctionalInterface
    public interface ValueFunction {
        JsonElement get(JsonObject json);
    }
    protected final ValueFunction supplier;

    public DocumentValue(ValueFunction supplier) {
        this.supplier = supplier;
    }

    public JsonElement get(JsonObject json) {
        return supplier.get(json);
    }
}

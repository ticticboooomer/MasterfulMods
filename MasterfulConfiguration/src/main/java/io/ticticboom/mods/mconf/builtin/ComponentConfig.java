package io.ticticboom.mods.mconf.builtin;

import io.ticticboom.mods.mconf.document.DocumentValidationError;
import io.ticticboom.mods.mconf.document.ThrowingConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentConfig {
    public static final Map<ResourceLocation, IComponentSpec> REGISTRY = new HashMap<>();
    public static final Map<ResourceLocation, Component> COMPONENTS = new HashMap<>();
    public static abstract class SpecConsumer<T extends IComponentSpec> extends ThrowingConfigSpecConsumer<T> {

        protected abstract Component getComponent(T spec);
        @Override
        public boolean safeValidate(T value, List<DocumentValidationError> errors) {
            return true;
        }

        @Override
        public void safeConsume(T value, IParseableDocument doc) {
            REGISTRY.put(doc.getId(), value);
            COMPONENTS.put(doc.getId(), getComponent(value));
        }
    }

    public interface IComponentSpec{}
}

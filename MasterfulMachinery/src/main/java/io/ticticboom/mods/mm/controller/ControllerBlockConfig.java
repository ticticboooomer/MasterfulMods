package io.ticticboom.mods.mm.controller;

import io.ticticboom.mods.mconf.document.DocumentValidationError;
import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.document.ThrowingConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerBlockConfig extends ConfigDocumentType {
    public static final Map<ResourceLocation, Spec> CONTROLLERS = new HashMap<>();
    @Override
    public IConfigSpecConsumer createSpecConsumer() {
        return new SpecConsumer();
    }

    public static final class SpecConsumer extends ThrowingConfigSpecConsumer<Spec> {
        @Override
        public Spec safeParse(IParseableDocumentSpec doc) {
            var name = doc.get("name").getSubDocument();
            var ports = doc.get("ports").getArray().stream().map(IParseableDocumentSpec::getSubDocument).toList();
            return new Spec(name, ports);
        }

        @Override
        public boolean safeValidate(Spec spec, List<DocumentValidationError> list) {
            return true;
        }

        @Override
        public void safeConsume(Spec spec, IParseableDocument doc) {
            CONTROLLERS.put(doc.getId(), spec);
        }
    }

    public record Spec(
            ResourceLocation name,
            List<ResourceLocation> ports
    ) {}
}

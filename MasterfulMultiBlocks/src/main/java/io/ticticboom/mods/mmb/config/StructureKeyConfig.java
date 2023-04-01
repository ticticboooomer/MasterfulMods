package io.ticticboom.mods.mmb.config;

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
import java.util.stream.Collectors;

public class StructureKeyConfig extends ConfigDocumentType {
    public static final Map<ResourceLocation, Spec> REGISTRY = new HashMap<>();
    @Override
    public IConfigSpecConsumer createSpecConsumer() {
        return new SpecConsumer();
    }

    public static final class SpecConsumer extends ThrowingConfigSpecConsumer<Spec> {
        @Override
        public void safeConsume(Spec value, IParseableDocument doc) {
            REGISTRY.put(doc.getId(), value);
        }

        @Override
        public Spec safeParse(IParseableDocumentSpec value) {
            var rls = value.get("keys").getArray().stream().map(x -> {
                var chr = x.get("character").getString().charAt(0);
                var part = x.get("part").getSubDocument();
                return new SpecEntry(chr, part);
            }).collect(Collectors.toList());
            return new Spec(rls);
        }

        @Override
        public boolean safeValidate(Spec value, List<DocumentValidationError> errors) {
            return true;
        }
    }

    public record Spec(
            List<SpecEntry> entries
    ) {
    }
    public record SpecEntry(Character chr, ResourceLocation part) {}
}

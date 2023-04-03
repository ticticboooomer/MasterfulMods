package io.ticticboom.mods.mm.port;

import io.ticticboom.mods.mconf.document.DocumentValidationError;
import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.document.ThrowingConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Optional;

public class PortTypeAttachmentConfig extends ConfigDocumentType {
    @Override
    public IConfigSpecConsumer createSpecConsumer() {
        return new SpecConsumer();
    }

    public static final class SpecConsumer extends ThrowingConfigSpecConsumer<Spec> {
        @Override
        public Spec safeParse(IParseableDocumentSpec doc) {
            Optional<ResourceLocation> gui = Optional.empty();
            if (doc.has("gui")) {
                gui = Optional.of(doc.get("gui").getSubDocument());
            }
            return new Spec(gui);
        }

        @Override
        public boolean safeValidate(Spec spec, List<DocumentValidationError> list) {
            return true;
        }

        @Override
        public void safeConsume(Spec spec, IParseableDocument doc) {
            PortTypeConfigs.ATTACHMENTS.put(doc.getId(), spec);
        }
    }

    public record Spec(
            Optional<ResourceLocation> gui
    ) {
    }
}

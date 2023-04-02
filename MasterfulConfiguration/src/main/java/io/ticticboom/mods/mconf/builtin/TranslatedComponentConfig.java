package io.ticticboom.mods.mconf.builtin;

import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import net.minecraft.network.chat.Component;

public class TranslatedComponentConfig extends ConfigDocumentType {
    @Override
    public IConfigSpecConsumer createSpecConsumer() {
        return new SpecConsumer();
    }

    public static final class SpecConsumer extends ComponentConfig.SpecConsumer<Spec> {

        @Override
        protected Component getComponent(Spec spec) {
            return Component.translatable(spec.translationKey());
        }

        @Override
        public Spec safeParse(IParseableDocumentSpec value) {
            return new Spec(value.get("translationKey").getString());
        }
    }

    public record Spec(
            String translationKey
    ) implements ComponentConfig.IComponentSpec {}
}

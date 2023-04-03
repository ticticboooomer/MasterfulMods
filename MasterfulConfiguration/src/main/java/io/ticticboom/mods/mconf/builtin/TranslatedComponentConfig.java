package io.ticticboom.mods.mconf.builtin;

import io.ticticboom.mods.mconf.document.IConfigSpecProcessor;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import net.minecraft.network.chat.Component;

public class TranslatedComponentConfig extends ConfigDocumentType {
    @Override
    public IConfigSpecProcessor createSpecConsumer() {
        return new SpecProcessor();
    }

    public static final class SpecProcessor extends ComponentConfig.SpecProcessor<Spec> {

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

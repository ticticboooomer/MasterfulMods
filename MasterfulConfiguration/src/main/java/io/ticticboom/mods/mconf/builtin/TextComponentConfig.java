package io.ticticboom.mods.mconf.builtin;

import io.ticticboom.mods.mconf.document.IConfigSpecProcessor;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import net.minecraft.network.chat.Component;

public class TextComponentConfig extends ConfigDocumentType {
    @Override
    public IConfigSpecProcessor createSpecConsumer() {
        return new SpecProcessor();
    }

    public static final class SpecProcessor extends ComponentConfig.SpecProcessor<Spec> {

        @Override
        protected Component getComponent(Spec spec) {
            return Component.literal(spec.text());
        }

        @Override
        public Spec safeParse(IParseableDocumentSpec value) {
            return new Spec(value.get("text").getString());
        }
    }

    public record Spec(
            String text
    ) implements ComponentConfig.IComponentSpec {}
}

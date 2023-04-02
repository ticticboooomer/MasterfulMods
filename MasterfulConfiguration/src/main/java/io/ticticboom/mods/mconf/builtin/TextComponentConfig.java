package io.ticticboom.mods.mconf.builtin;

import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import net.minecraft.network.chat.Component;

public class TextComponentConfig extends ConfigDocumentType {
    @Override
    public IConfigSpecConsumer createSpecConsumer() {
        return new SpecConsumer();
    }

    public static final class SpecConsumer extends ComponentConfig.SpecConsumer<Spec> {

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

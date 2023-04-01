package io.ticticboom.mods.mui.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import io.ticticboom.mods.mconf.document.DocumentValidationError;
import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.document.ThrowingConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenConfig extends ConfigDocumentType {

    public static final Map<ResourceLocation, Spec> REGISTRY = new HashMap<>();
    public static final Map<ResourceLocation, HandlerFactory> HANDLERS = new HashMap<>();
    @Override
    public IConfigSpecConsumer createSpecConsumer() {
        return new SpecConsumer();
    }

    public static final class SpecConsumer extends ThrowingConfigSpecConsumer<Spec> {

        @Override
        public Spec safeParse(IParseableDocumentSpec spec) {
            return new Spec(spec.get("elements").getArray().stream().map(IParseableDocumentSpec::getSubDocument).toList());
        }

        @Override
        public boolean safeValidate(Spec spec, List<DocumentValidationError> list) {
            return true;
        }

        @Override
        public void safeConsume(Spec spec, IParseableDocument doc) {
            REGISTRY.put(doc.getId(), spec);
            HANDLERS.put(doc.getId(), new HandlerFactory());
        }

    }

    public static final class SpecHandler {

        private final Screen screen;

        public SpecHandler(Screen screen) {

            this.screen = screen;
        }
        
        public void render(PoseStack pose, int x, int y, float partialTicks) {

        }
    }
    
    public static final class HandlerFactory {
        public SpecHandler create(Screen scrn) {
            return new SpecHandler(scrn);
        }
    }
    


    public record Spec(List<ResourceLocation> elements) {}
}

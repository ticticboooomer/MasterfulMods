package io.ticticboom.mods.mui.screen.element;

import com.mojang.blaze3d.vertex.PoseStack;
import io.ticticboom.mods.mconf.builtin.ComponentConfig;
import io.ticticboom.mods.mconf.document.DocumentValidationError;
import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.document.ThrowingConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class TextElement extends ConfigDocumentType {
    @Override
    public IConfigSpecConsumer createSpecConsumer() {
        return new SpecConsumer();
    }

    public static final class SpecConsumer extends ThrowingConfigSpecConsumer<Spec> {
        @Override
        public Spec safeParse(IParseableDocumentSpec doc) {
            var text = doc.get("text").getSubDocument();
            var x = doc.get("x").getInteger();
            var y = doc.get("y").getInteger();
            return new Spec(text, x, y);
        }

        @Override
        public boolean safeValidate(Spec spec, List<DocumentValidationError> list) {
            return true;
        }

        @Override
        public void safeConsume(Spec spec, IParseableDocument doc) {
            ScreenElements.REGISTRY.put(doc.getId(), spec);
            ScreenElements.HANDLERS.put(doc.getId(), new SpecHandler(spec));
        }
    }

    public static final class SpecHandler extends ScreenElements.SpecHandler {

        private final Spec spec;

        public SpecHandler(Spec spec) {
            this.spec = spec;
        }


        @Override
        public void render(Screen screen, PoseStack pose, int mouseX, int mouseY, float partialTicks) {
            var text = ComponentConfig.COMPONENTS.get(spec.text);
            Minecraft.getInstance().font.draw(pose, text, spec.x, spec.y, 0x474747);
        }
    }

    public record Spec(
            ResourceLocation text,
            int x,
            int y
    ) implements ScreenElements.IElementSpec {

    }
}
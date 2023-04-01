package io.ticticboom.mods.mui.screen.element;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.ticticboom.mods.mconf.document.DocumentValidationError;
import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.document.ThrowingConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class ImageElement extends ConfigDocumentType {
    @Override
    public IConfigSpecConsumer createSpecConsumer() {
        return new SpecConsumer();
    }

    public static final class SpecConsumer extends ThrowingConfigSpecConsumer<Spec> {

        @Override
        public Spec safeParse(IParseableDocumentSpec doc) {
            var x = doc.get("x").getInteger();
            var y = doc.get("y").getInteger();
            var width = doc.get("width").getInteger();
            var height = doc.get("height").getInteger();
            var image = new ResourceLocation(doc.get("image").getString());
            var ix = doc.get("ix").getInteger();
            var iy = doc.get("iy").getInteger();
            return new Spec(x, y, width, height, image, ix, iy);
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
            RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, spec.image);
            GuiComponent.blit(pose, spec.x, spec.y, spec.ix, spec.iy, spec.w, spec.h);
        }
    }

    public record Spec(int x,
                       int y,
                       int w,
                       int h,
                       ResourceLocation image,
                       int ix,
                       int iy) implements ScreenElements.IElementSpec {

    }

}

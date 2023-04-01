package io.ticticboom.mods.mui.screen.element;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ScreenElements {
    public static final Map<ResourceLocation, IElementSpec> REGISTRY = new HashMap<>();
    public static final Map<ResourceLocation, SpecHandler> HANDLERS = new HashMap<>();

    public static abstract class SpecHandler {
        public abstract void render(Screen screen, PoseStack pose, int mouseX, int mouseY, float partialTicks);
    }
    public interface IElementSpec {
        
    }
}

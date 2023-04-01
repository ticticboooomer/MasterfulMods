package io.ticticboom.mods.mui.screen.element;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ScreenElements {
    public static final Map<ResourceLocation, IElementSpec> REGISTRY = new HashMap<>();
    public static final Map<ResourceLocation, SpecHandler> HANDLERS = new HashMap<>();

    public static abstract class SpecHandler {
        public abstract void render(Screen screen, int mouseX, int mouseY, float partialTicks);
    }
    public interface IElementSpec {
        
    }
}

package io.ticticboom.mods.test.mui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import io.ticticboom.mods.mui.screen.ScreenConfig;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class TestScreen extends Screen {

    private final ScreenConfig.SpecHandler renderer;

    public TestScreen() {
        super(Component.empty());
        renderer = ScreenConfig.HANDLERS.values().iterator().next().create(this);
    }

    @Override
    public void render(PoseStack p_96562_, int p_96563_, int p_96564_, float p_96565_) {
        renderer.render(p_96562_, p_96563_, p_96564_, p_96565_);
    }
}

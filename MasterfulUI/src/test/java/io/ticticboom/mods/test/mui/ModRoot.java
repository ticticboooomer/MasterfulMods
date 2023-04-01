package io.ticticboom.mods.test.mui;

import com.mojang.blaze3d.platform.InputConstants;
import io.ticticboom.mods.test.mui.screens.TestScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.controls.KeyBindsList;
import net.minecraft.client.player.KeyboardInput;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.gui.ScreenUtils;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;
import org.lwjgl.glfw.GLFW;

import java.util.Queue;

@Mod(Ref.ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModRoot {
    public ModRoot() {
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (event.getKey() == GLFW.GLFW_KEY_RIGHT_ALT) {
            Minecraft.getInstance().setScreen(new TestScreen());
        }
    }

}

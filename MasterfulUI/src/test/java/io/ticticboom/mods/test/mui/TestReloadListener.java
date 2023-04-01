package io.ticticboom.mods.test.mui;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import io.ticticboom.mods.mconf.loader.memory.JsonDocumentLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TestReloadListener extends SimpleJsonResourceReloadListener {
    public TestReloadListener() {
        super(new Gson(), "screens");
    }

    @SubscribeEvent
    public static void register(AddReloadListenerEvent event) {
        event.addListener(new TestReloadListener());
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> p_10793_, ResourceManager p_10794_, ProfilerFiller p_10795_) {
        var jsonObjects = p_10793_.values().stream().map(JsonElement::getAsJsonObject).toList();
        new JsonDocumentLoader(jsonObjects).loadDocuments();
    }
}

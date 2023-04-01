package io.ticticboom.mods.test.mmb.data;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import io.ticticboom.mods.mconf.loader.memory.JsonDocumentLoader;
import io.ticticboom.mods.mmb.runtime.MultiBlockRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StructureReloadListener extends SimpleJsonResourceReloadListener {
    public StructureReloadListener() {
        super(new Gson(), "structures");
    }

    public static final MultiBlockRegistry REGISTRY = new MultiBlockRegistry();

    @SubscribeEvent
    public static void on(AddReloadListenerEvent event){
        event.addListener(new StructureReloadListener());
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> p_10793_, ResourceManager p_10794_, ProfilerFiller p_10795_) {
        var jsonObjects = p_10793_.values().stream().map(JsonElement::getAsJsonObject).toList();
        new JsonDocumentLoader(jsonObjects).loadDocuments();
    }
}

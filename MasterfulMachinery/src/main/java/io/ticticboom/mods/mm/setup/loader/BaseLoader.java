package io.ticticboom.mods.mm.setup.loader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.ticticboom.mods.mconf.loader.JsonLoader;
import lombok.SneakyThrows;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BaseLoader {
    public BaseLoader() {

    }

    public static void load() {
       var dir =  ensureExists(FMLPaths.CONFIGDIR.get().resolve("mm"));
       var jsons = load(dir, new ArrayList<>());
        new JsonLoader(jsons).loadDocuments();
    }


    @SneakyThrows
    public static List<com.google.gson.JsonObject> load(Path dir, ArrayList<JsonObject> jsons) {
        if (!Files.exists(dir)) {
            return jsons;
        }
        var files = Files.list(dir).toList();
        for (var file : files) {
            if (file.toString().endsWith(".json")) {
                jsons.add(JsonParser.parseReader(new FileReader(file.toFile())).getAsJsonObject());
            } else if (file.toFile().isDirectory()) {
                return load(file, jsons);
            }
        }
        return jsons;
    }

    public static Path ensureExists(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return path;
    }
}

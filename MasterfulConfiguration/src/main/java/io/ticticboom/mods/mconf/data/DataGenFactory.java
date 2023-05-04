package io.ticticboom.mods.mconf.data;

import lombok.SneakyThrows;
import net.minecraft.SharedConstants;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Files;
import java.nio.file.Path;

public class DataGenFactory {
    public static Path ROOT_PATH;

    @SneakyThrows
    public static void init() {
        ROOT_PATH = FMLPaths.CONFIGDIR.get().resolve("mconf/do_not_touch");
        if (!Files.exists(ROOT_PATH)) {
            Files.createDirectories(ROOT_PATH);
        }
    }

    public static DataGenerator createDataGenerator() {
        return new DataGenerator(ROOT_PATH, SharedConstants.getCurrentVersion(), true);
    }
}

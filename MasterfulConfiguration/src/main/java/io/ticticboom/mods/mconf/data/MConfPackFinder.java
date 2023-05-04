package io.ticticboom.mods.mconf.data;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.world.flag.FeatureFlagSet;

import java.nio.file.Path;
import java.util.function.Consumer;

public class MConfPackFinder implements RepositorySource {
    private final PackType type;

    public MConfPackFinder(PackType type) {

        this.type = type;
    }

    @Override
    public void loadPacks(Consumer<Pack> p_10542_) {
        Path rootPath = DataGenFactory.ROOT_PATH;
        var id = "mconf_pack_" + type.getDirectory();
        Pack pack = Pack.create(id, Component.literal("Generated Datapack"), true,
                (s) -> new GeneratedPack(rootPath.resolve(type.getDirectory()), id), new Pack.Info(Component.empty(), 12, FeatureFlagSet.of()),
                type, Pack.Position.BOTTOM, true, PackSource.DEFAULT);
        if (pack != null) {
            p_10542_.accept(pack);
        }
    }
}

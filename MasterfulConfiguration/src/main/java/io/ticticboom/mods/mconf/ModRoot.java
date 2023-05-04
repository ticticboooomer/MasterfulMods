package io.ticticboom.mods.mconf;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import io.ticticboom.mods.mconf.data.DataGenFactory;
import io.ticticboom.mods.mconf.data.MConfPackFinder;
import io.ticticboom.mods.mconf.setup.MConfRegistries;
import net.minecraft.client.Minecraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

import java.io.IOException;
import java.util.function.Function;

@Mod(Ref.ID)
public class ModRoot {
    private static DataGenerator generator;
    private static boolean hasGenerated = false;

    private static ModRoot instance;
    public static final Logger LOG = LoggerFactory.getLogger("Masterful Configuration");
    public ModRoot() {
        MConfRegistries.registerDefaults();
        DataGenFactory.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> Minecraft.getInstance().getResourcePackRepository().addPackFinder(new MConfPackFinder(PackType.CLIENT_RESOURCES)));
    }

    public static void injectDatapackFinder(PackRepository resourcePacks) {
        DistExecutor.<Boolean>unsafeRunForDist(() -> () -> {
            if (resourcePacks != Minecraft.getInstance().getResourcePackRepository()) {
                resourcePacks.addPackFinder(new MConfPackFinder(PackType.CLIENT_RESOURCES));
            }
            return false;
        }, () -> () -> {
            resourcePacks.addPackFinder(new MConfPackFinder(PackType.SERVER_DATA));
            return false;
        });
    }


    public static void generate() {
        if (!hasGenerated) {
            try {
                if(generator == null)
                    registerDataGen();
                generator.run();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            hasGenerated = true;
        }
    }

    private static void registerDataGen() {
        generator = DataGenFactory.createDataGenerator();
        var efh = new ExistingFileHelper(ImmutableList.of(), ImmutableSet.of(), false, null, null);
        for (var gen : MConfRegistries.DATA_GEN_PROVIDERS.values()) {
            generator.addProvider(true, gen.apply(generator, efh));
        }
    }
}

package io.ticticboom.mods.mmb.structure.part;

import io.ticticboom.mods.mconf.document.IConfigDocumentData;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

public class StructureParts {

    public interface IPartSpec extends IConfigDocumentData {
    }

    public static abstract class SpecHandler {

        protected final IPartSpec spec;
        protected final BlockPos offset;

        public SpecHandler(IPartSpec spec, BlockPos offset) {
            this.spec = spec;
            this.offset = offset;
        }
        public IPartSpec getSpec()  {
            return spec;
        }

        public abstract boolean verifyPlacement(Level level);
    }

    @FunctionalInterface
    public interface IPartHandlerFactory {
        SpecHandler create(BlockPos offset);
    }
}
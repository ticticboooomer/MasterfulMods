package io.ticticboom.mods.mmb.structure.part;

import io.ticticboom.mods.mconf.document.ThrowingConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class StructureParts {
    public static final Map<ResourceLocation, IPartSpec> REGISTRY = new HashMap<>();
    public static final Map<ResourceLocation, IPartHandlerFactory> HANDLER_FACTORIES = new HashMap<>();

    public static abstract class SpecConsumer<T extends IPartSpec> extends ThrowingConfigSpecConsumer<T> {
        @Override
        public void safeConsume(T value, IParseableDocument doc) {
            REGISTRY.put(doc.getId(), value);
            HANDLER_FACTORIES.put(doc.getId(), createHandlerFactory(value, doc));
        }

        public abstract IPartHandlerFactory createHandlerFactory(T value, IParseableDocument doc);
    }

    public interface IPartSpec {

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
package io.ticticboom.mods.mmb.core;

import io.ticticboom.mods.mconf.document.DocumentValidationError;
import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.document.ThrowingConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomCoreBlockConfig extends ConfigDocumentType {
     @Override
    public IConfigSpecConsumer createSpecConsumer() {
        return new SpecConsumer();
    }

    public static final class SpecConsumer extends ThrowingConfigSpecConsumer<Spec> {

        @Override
        public Spec safeParse(IParseableDocumentSpec value) {
            return new Spec(new ResourceLocation(value.get("block").getString()));
        }

        @Override
        public boolean safeValidate(Spec value, List<DocumentValidationError> errors) {
            return true;
        }

        @Override
        public void safeConsume(Spec value, IParseableDocument doc) {
            CoreBlocks.REGISTRY.put(doc.getId(), value);
            CoreBlocks.HANDLERS.put(doc.getId(), new SpecHandler(value));
        }
    }

    public static final class SpecHandler implements ICoreBlockSpecHandler{

        private final Spec spec;

        public SpecHandler(Spec spec) {

            this.spec = spec;
        }
        @Override
        public boolean isCoreBlock(Level level, BlockPos pos) {
            var block = level.getBlockState(pos).getBlock();
            return ForgeRegistries.BLOCKS.getKey(block).equals(spec.block);
        }
    }
    public record Spec(ResourceLocation block) {}
}

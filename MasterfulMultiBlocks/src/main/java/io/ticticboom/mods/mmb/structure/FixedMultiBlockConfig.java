package io.ticticboom.mods.mmb.structure;

import io.ticticboom.mods.mconf.document.DocumentValidationError;
import io.ticticboom.mods.mconf.document.ThrowingConfigSpecConsumer;
import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import io.ticticboom.mods.mmb.runtime.MultiBlockPartList;
import io.ticticboom.mods.mmb.runtime.list.FixedMultiBlockPartList;
import io.ticticboom.mods.mmb.setup.MMBRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FixedMultiBlockConfig extends ConfigDocumentType {
    public static final Map<ResourceLocation, Spec> REGISTRY = new HashMap<>();

    @Override
    public IConfigSpecConsumer createSpecConsumer() {
        return new SpecConsumer();
    }

    public static final class SpecConsumer extends ThrowingConfigSpecConsumer<Spec> {
        @Override
        public Spec safeParse(IParseableDocumentSpec value) {
            var coreBlock = value.get("coreBlock").getSubDocument();
            var displayName = value.get("displayName").getString();
            var pattern = value.get("pattern").getSubDocument();
            var key = value.get("key").getSubDocument();
            return new Spec(coreBlock, displayName, pattern, key);
        }

        @Override
        public boolean safeValidate(Spec value, List<DocumentValidationError> errors) {
            return true;
        }

        @Override
        public void safeConsume(Spec value, IParseableDocument doc) {
            REGISTRY.put(doc.getId(), value);
            MMBRegistries.MULTIBLOCKS.put(doc.getId(), () -> new FixedMultiBlockPartList(value));
        }
    }

    public record Spec(
            ResourceLocation coreBlock,
            String displayName,
            ResourceLocation pattern,
            ResourceLocation key
    ) {


    }
}

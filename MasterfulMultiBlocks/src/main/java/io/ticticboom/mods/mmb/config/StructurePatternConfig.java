package io.ticticboom.mods.mmb.config;

import io.ticticboom.mods.mconf.document.DocumentValidationError;
import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.document.ThrowingConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructurePatternConfig extends ConfigDocumentType {
    public static final Map<ResourceLocation, Spec> REGISTRY = new HashMap<>();
    @Override
    public IConfigSpecConsumer createSpecConsumer() {
        return new SpecConsumer();
    }

    public static final class SpecConsumer extends ThrowingConfigSpecConsumer<Spec> {
        @Override
        public Spec safeParse(IParseableDocumentSpec value) {
            var patternResult = new ArrayList<List<String>>();
            for (IParseableDocumentSpec patternLayer : value.get("pattern").getArray()) {
                var layer = new ArrayList<String>();
                for (IParseableDocumentSpec pattern : patternLayer.getArray()) {
                    layer.add(pattern.getString());
                }
                patternResult.add(layer);
            }
            var yDirection = Enum.valueOf(Spec.YDirection.class, value.get("verticalDirection").getString());
            var facing = Enum.valueOf(Spec.FacingDirection.class, value.get("facingDirection").getString());
            return new Spec(patternResult, yDirection, facing);
        }

        @Override
        public boolean safeValidate(Spec value, List<DocumentValidationError> errors) {
            return true;
        }

        @Override
        public void safeConsume(Spec value, IParseableDocument doc) {
            REGISTRY.put(doc.getId(), value);
        }
    }

    public record Spec(
            List<List<String>> pattern,
            YDirection direction,
            FacingDirection facing
    ) {
        public enum YDirection {
            TOP_FIRST,
            BOTTOM_FIRST
        }
        public enum FacingDirection {
            NORTH_FIRST,
            EAST_FIRST,
            SOUTH_FIRST,
            WEST_FIRST
        }
    }
}

package io.ticticboom.mods.mconf.builtin.block;

import io.ticticboom.mods.mconf.document.DocumentValidationError;
import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.document.ThrowingConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.registry.MasterfulRegistry;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.List;

public class BlockPropertiesConfig extends ConfigDocumentType {
    public static final MasterfulRegistry<Spec> REGISTRY = MasterfulRegistry.create();
    @Override
    public IConfigSpecConsumer createSpecConsumer() {
        return new SpecConsumer();
    }

    public static final class SpecConsumer extends ThrowingConfigSpecConsumer<Spec> {

        @Override
        public Spec safeParse(IParseableDocumentSpec value) {
            Material material = Material.STONE;
            MaterialColor materialColor = MaterialColor.STONE;
            var destroyTime = value.get("destroyTime").getFloat();
            var resistance = value.get("resistance").getFloat();
            return new Spec(destroyTime, resistance);
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

    public static class SpecHandler {
        private final Spec spec;

        public SpecHandler(Spec spec) {

            this.spec = spec;
        }

        public BlockBehaviour.Properties properties(BlockBehaviour.Properties props) {
            return props.explosionResistance(spec.resistance())
                    .destroyTime(spec.destroyTime());
        }
    }

    public record Spec(
            float destroyTime,
            float resistance
    ) {
    }
}

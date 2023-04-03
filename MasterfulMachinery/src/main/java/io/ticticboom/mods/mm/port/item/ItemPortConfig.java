package io.ticticboom.mods.mm.port.item;

import io.ticticboom.mods.mconf.document.DocumentValidationError;
import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.document.ThrowingConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.registry.MasterfulRegistry;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import io.ticticboom.mods.mm.port.PortTypeConfigs;
import io.ticticboom.mods.mm.port.base.IPortStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.List;

public class ItemPortConfig extends ConfigDocumentType {
    public static final MasterfulRegistry<Spec> REGISTRY = MasterfulRegistry.create();
    @Override
    public IConfigSpecConsumer createSpecConsumer() {
        return null;
    }

    public static final class SpecConsumer extends ThrowingConfigSpecConsumer<Spec> {

        @Override
        public Spec safeParse(IParseableDocumentSpec doc) {
            var x = doc.get("slotsX").getInteger();
            var y = doc.get("slotsY").getInteger();
            return new Spec(x, y);
        }

        @Override
        public boolean safeValidate(Spec spec, List<DocumentValidationError> list) {
            return true;
        }

        @Override
        public void safeConsume(Spec spec, IParseableDocument doc) {
            REGISTRY.put(doc.getId(), spec);
            PortTypeConfigs.HANDLERS.put(doc.getId(), new PortTypeConfigHandler(spec));
        }
    }

    public static final class PortTypeConfigHandler extends PortTypeConfigs.PortTypeConfigHandler {
        private final Spec spec;

        public PortTypeConfigHandler(Spec spec) {
            this.spec = spec;
        }
        @Override
        public IPortStorage createPortStorage(Level level, BlockPos pos) {
            return new ItemPortStorage(spec.slotsX(), spec.slotsY());
        }
    }

    public record Spec(
            int slotsX,
            int slotsY
    ) {
    }
}

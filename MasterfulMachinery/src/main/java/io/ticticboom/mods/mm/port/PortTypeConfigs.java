package io.ticticboom.mods.mm.port;

import io.ticticboom.mods.mconf.document.ThrowingConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.registry.MasterfulRegistry;
import io.ticticboom.mods.mm.port.base.IPortStorage;
import io.ticticboom.mods.mm.port.item.ItemPortConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PortTypeConfigs {
    public static final MasterfulRegistry<PortSpec> PORT_TYPE_CONFIGS = MasterfulRegistry.create();
    public static final MasterfulRegistry<PortTypeConfigHandler> HANDLERS = MasterfulRegistry.create();
    public static final MasterfulRegistry<PortTypeAttachmentConfig.Spec> ATTACHMENTS = MasterfulRegistry.create();
    public static abstract class SpecConsumer extends ThrowingConfigSpecConsumer<PortSpec> {
        @Override
        public void safeConsume(PortSpec t, IParseableDocument doc) {
            PORT_TYPE_CONFIGS.put(doc.getId(), t);
            HANDLERS.put(doc.getId(), createHandler(t));
        }

        public abstract PortTypeConfigHandler createHandler(PortSpec spec);

        @Override
        public PortSpec safeParse(IParseableDocumentSpec doc) {
            var portType = doc.get("portType").getSubDocument();
            Optional<ResourceLocation> attachments = Optional.empty();
            if (doc.has("attachments")) {
                attachments = Optional.of(doc.get("attachments").getSubDocument());
            }
            return new PortSpec(portType, attachments);
        }
    }

    public static abstract class PortTypeConfigHandler {
        public abstract IPortStorage createPortStorage(Level level, BlockPos pos);
    }

    public record PortSpec(
            ResourceLocation attachments,
            Optional<ResourceLocation> portType
    ) {

    }

}

package io.ticticboom.mods.mm.port;

import io.ticticboom.mods.mconf.document.ThrowingConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mm.port.base.IPortStorage;
import io.ticticboom.mods.mm.port.item.ItemPortConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

public class PortTypeConfigs {
    public static final Map<ResourceLocation, IPortTypeSpec> PORT_TYPE_CONFIGS = new HashMap<>();
    public static final Map<ResourceLocation, PortTypeConfigHandler> HANDLERS = new HashMap<>();
    public static abstract class SpecConsumer<T extends IPortTypeSpec> extends ThrowingConfigSpecConsumer<T> {
        @Override
        public void safeConsume(T t, IParseableDocument doc) {
            PORT_TYPE_CONFIGS.put(doc.getId(), t);
            HANDLERS.put(doc.getId(), createHandler(t));
        }
        public abstract PortTypeConfigHandler createHandler(T spec);
    }

    public static abstract class PortTypeConfigHandler {
        public abstract IPortStorage createPortStorage(Level level, BlockPos pos);
    }

    public interface IPortTypeSpec {
    }
}

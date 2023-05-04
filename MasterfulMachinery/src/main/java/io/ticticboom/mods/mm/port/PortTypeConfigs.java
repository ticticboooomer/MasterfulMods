package io.ticticboom.mods.mm.port;

import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.document.ConfigDocumentType;
import io.ticticboom.mods.mconf.document.IConfigDocumentData;
import io.ticticboom.mods.mconf.registry.MasterfulRegistry;
import io.ticticboom.mods.mm.block.PortBlock;
import io.ticticboom.mods.mm.block.entity.PortBlockEntity;
import io.ticticboom.mods.mm.port.base.IPortRecipeProcessor;
import io.ticticboom.mods.mm.port.base.IPortStorage;
import io.ticticboom.mods.mm.setup.holder.RegisteredController;
import io.ticticboom.mods.mm.setup.holder.RegisteredPort;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;

public class PortTypeConfigs {
    public static final MasterfulRegistry<PortTypeConfigHandler<?>> HANDLERS = MasterfulRegistry.create();
    public static final MasterfulRegistry<ConfigDocumentType<?, ?>> PORT_TYPES = MasterfulRegistry.create();
    public static abstract class PortTypeConfigHandler<DATA extends IConfigDocumentData> {
        public abstract IPortStorage createPortStorage(Level level, BlockPos pos);
        public abstract IPortRecipeProcessor createPortRecipeProcessor(Level level, BlockPos pos);
        public abstract String createName(ConfigDocument<DATA> doc, RegisteredPort port);
        public Block registerBlock(ConfigDocument<DATA> doc, RegisteredPort port) {
            return new PortBlock(port, this);
        }

        public BlockEntityType<?> registerBlockEntity(ConfigDocument<DATA> doc, RegisteredPort port) {
            return BlockEntityType.Builder.of((pos, state) -> new PortBlockEntity(port.blockEntity().GetValue().get(), pos, state, doc, this), port.block().GetValue().get()).build(null);
        }
        public void tick(Level level, BlockPos pos, BlockEntity be) {
        }

    }
}

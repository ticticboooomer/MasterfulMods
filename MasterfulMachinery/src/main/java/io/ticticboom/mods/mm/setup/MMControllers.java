package io.ticticboom.mods.mm.setup;

import com.google.gson.JsonObject;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.registry.MasterfulRegistry;
import io.ticticboom.mods.mm.block.ControllerBlock;
import io.ticticboom.mods.mm.block.entity.ControllerBlockEntity;
import io.ticticboom.mods.mm.controller.ControllerBlockConfig;
import io.ticticboom.mods.mm.port.PortTypeConfigs;
import io.ticticboom.mods.mm.setup.holder.RegisteredController;
import io.ticticboom.mods.mm.setup.holder.RegisteredPort;
import io.ticticboom.mods.mmb.common.Deferred;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MMControllers {
    public static final MasterfulRegistry<ConfigDocument<ControllerBlockConfig.Spec>> CONTROLLERS = MasterfulRegistry.create();
    public static final MasterfulRegistry<RegisteredController> REG_CONTROLLERS = MasterfulRegistry.create();

    public static void registerControllers() {
        for (ConfigDocument<ControllerBlockConfig.Spec> value : CONTROLLERS.values()) {
            Deferred<RegistryObject<ControllerBlock>> block = new Deferred<>();
            Deferred<RegistryObject<BlockEntityType<ControllerBlockEntity>>> blockEntity = new Deferred<>();
            Deferred<RegistryObject<Item>> item = new Deferred<>();
            block.SetValue(MMRegistries.BLOCKS.register(value.id.getPath(), () -> new ControllerBlock(value, blockEntity)));
            blockEntity.SetValue(MMRegistries.BLOCK_ENTITY_TYPES.register(value.id.getPath(), ()-> BlockEntityType.Builder.of((a, b) -> new ControllerBlockEntity(blockEntity.GetValue().get(), a, b), block.GetValue().get()).build(null)));
            item.SetValue(MMRegistries.ITEMS.register(value.id.getPath(), () -> new BlockItem(block.GetValue().get(), new Item.Properties())));
            REG_CONTROLLERS.put(value.id, new RegisteredController(value, block, blockEntity, item));

            for (ConfigDocument<?> port : value.data.ports()) {
                registerPort(port);
            }
        }
    }

    private static void registerPort(ConfigDocument<?> doc) {
        var res = PortTypeConfigs.PORT_TYPES.get(doc.type).createResult(doc.cast(), new JsonObject());
        var handler = (PortTypeConfigs.PortTypeConfigHandler<?>) res.resultData().getFirst().get();
        PortTypeConfigs.HANDLERS.put(doc.id, handler);
        var reg = new RegisteredPort(doc, new Deferred<>(), new Deferred<>(), new Deferred<>());
        reg.block().SetValue(MMRegistries.BLOCKS.register(doc.id.getPath(), () -> handler.registerBlock(doc.cast(), reg)));
        reg.blockEntity().SetValue(MMRegistries.BLOCK_ENTITY_TYPES.register(doc.id.getPath(), () -> handler.registerBlockEntity(doc.cast(), reg)));
        reg.item().SetValue(MMRegistries.ITEMS.register(doc.id.getPath(), () -> new BlockItem(reg.block().GetValue().get(), new Item.Properties())));
    }
}

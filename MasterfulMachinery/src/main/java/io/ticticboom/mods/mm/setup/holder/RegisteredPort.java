package io.ticticboom.mods.mm.setup.holder;

import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mm.block.ControllerBlock;
import io.ticticboom.mods.mm.block.PortBlock;
import io.ticticboom.mods.mm.block.entity.ControllerBlockEntity;
import io.ticticboom.mods.mm.block.entity.PortBlockEntity;
import io.ticticboom.mods.mm.controller.ControllerBlockConfig;
import io.ticticboom.mods.mm.port.PortTypeConfigs;
import io.ticticboom.mods.mmb.common.Deferred;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public record RegisteredPort(
        ConfigDocument<?> doc,
        Deferred<RegistryObject<Block>> block,
        Deferred<RegistryObject<BlockEntityType<?>>> blockEntity,
        Deferred<RegistryObject<Item>> item
) {
}
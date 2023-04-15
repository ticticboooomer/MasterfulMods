package io.ticticboom.mods.mm.block.entity;

import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mm.port.PortTypeConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class PortBlockEntity extends BlockEntity {
    private final ConfigDocument<?> doc;
    private final PortTypeConfigs.PortTypeConfigHandler<?> handler;

    public PortBlockEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_, ConfigDocument<?> doc, PortTypeConfigs.PortTypeConfigHandler<?> handler) {
        super(p_155228_, p_155229_, p_155230_);
        this.doc = doc;
        this.handler = handler;
    }


}

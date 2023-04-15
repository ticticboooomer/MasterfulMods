package io.ticticboom.mods.mm.block;

import io.ticticboom.mods.mm.block.entity.ControllerBlockEntity;
import io.ticticboom.mods.mm.block.entity.PortBlockEntity;
import io.ticticboom.mods.mm.port.PortTypeConfigs;
import io.ticticboom.mods.mm.setup.holder.RegisteredPort;
import io.ticticboom.mods.mmb.common.Deferred;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class PortBlock extends Block implements EntityBlock {
    private final Deferred<RegistryObject<BlockEntityType<?>>> blockEntityType;
    private final PortTypeConfigs.PortTypeConfigHandler<?> handler;

    public PortBlock(RegisteredPort reg, PortTypeConfigs.PortTypeConfigHandler<?> handler) {
        super(Properties.of(Material.WOOD));
        this.blockEntityType = reg.blockEntity();
        this.handler = handler;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return blockEntityType.GetValue().get().create(p_153215_, p_153216_);
    }
}

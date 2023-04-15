package io.ticticboom.mods.mm.block.entity;

import com.electronwill.nightconfig.core.AbstractConfig;
import io.ticticboom.mods.mmb.runtime.MultiBlockPartList;
import io.ticticboom.mods.mmb.setup.MMBRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ControllerBlockEntity extends BlockEntity {
    private List<MultiBlockPartList> multiBlocks = new ArrayList<>();

    public ControllerBlockEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
        super(p_155228_, p_155229_, p_155230_);
    }

    @Override
    public void onLoad() {
        for (Supplier<MultiBlockPartList> mb : MMBRegistries.MULTIBLOCKS.values()) {
            var m = mb.get();
            if (m.isCoreBlock(level, getBlockPos())) {
                multiBlocks.add(m);
                m.initHandlers(getBlockPos());
            }
        }
    }

    public void tick() {
        if (level.isClientSide) {
            return;
        }
        for (MultiBlockPartList multiBlock : multiBlocks) {
            if (multiBlock.verifyPlacement(level, getBlockPos())) {
                ((ServerLevel) level).players().forEach(p -> {
                    p.sendSystemMessage(Component.literal("mb formed"));
                });
            }
        }
    }
}

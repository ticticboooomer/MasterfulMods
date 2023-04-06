package io.ticticboom.mods.test.mmb.block;

import io.ticticboom.mods.mmb.runtime.MultiBlockPartList;
import io.ticticboom.mods.mmb.setup.MMBRegistries;
import io.ticticboom.mods.test.mmb.setup.MMBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MultiBlockEntity extends BlockEntity {
    protected List<MultiBlockPartList> multiBlocks = new ArrayList<>();
    public MultiBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(MMBBlocks.MULTI_BLOCK_ENTITY.get(), p_155229_, p_155230_);
    }

    @Override
    public void onLoad() {
        for (Supplier<MultiBlockPartList> mb : MMBRegistries.MULTIBLOCKS.values()) {
            var m = mb.get();
            if (m.isCoreBlock(level, getBlockPos())){
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

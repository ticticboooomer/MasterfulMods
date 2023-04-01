package io.ticticboom.mods.mmb.core;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface ICoreBlockSpecHandler {
    boolean isCoreBlock(Level level, BlockPos pos);
}

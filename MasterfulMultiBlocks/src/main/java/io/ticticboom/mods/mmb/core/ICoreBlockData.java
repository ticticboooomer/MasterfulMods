package io.ticticboom.mods.mmb.core;

import io.ticticboom.mods.mconf.document.IConfigDocumentData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface ICoreBlockData extends IConfigDocumentData {
    boolean isCoreBlock(Level level, BlockPos pos);
}

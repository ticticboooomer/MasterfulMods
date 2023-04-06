package io.ticticboom.mods.mmb.runtime;

import io.ticticboom.mods.mmb.core.ICoreBlockData;
import io.ticticboom.mods.mmb.structure.part.StructureParts;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

import java.util.*;

public abstract class MultiBlockPartList {
    public ICoreBlockData cbHandler;
    public Map<Direction, List<PositionedPartHandlerFactory>> parts = new HashMap<>();
    public Map<Direction, List<StructureParts.SpecHandler>> runtimeParts = new HashMap<>();
    public abstract void initHandlers(BlockPos pos);
    public abstract void tick(Level level, BlockPos pos);
    public abstract boolean verifyPlacement(Level level, BlockPos pos);
    public boolean isCoreBlock(Level level, BlockPos pos) {
        return cbHandler.isCoreBlock(level, pos);
    }
}
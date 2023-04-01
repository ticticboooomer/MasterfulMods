package io.ticticboom.mods.mmb.runtime;

import io.ticticboom.mods.mmb.structure.part.StructureParts;
import net.minecraft.core.BlockPos;

public record PositionedPartHandlerFactory(StructureParts.IPartHandlerFactory factory, BlockPos pos) {
}

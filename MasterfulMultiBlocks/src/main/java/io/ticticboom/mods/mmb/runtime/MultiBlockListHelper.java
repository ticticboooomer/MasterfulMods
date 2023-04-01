package io.ticticboom.mods.mmb.runtime;

import io.ticticboom.mods.mmb.config.StructurePatternConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Rotations;
import net.minecraft.world.level.block.Rotation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MultiBlockListHelper {
    public static void runForPattern(List<List<String>> pattern, BiConsumer<Character, BlockPos> consumer) {
        for (int y = 0; y < pattern.size(); y++) {
            for (int z = 0; z < pattern.get(y).size(); z++) {
                for (int x = 0; x < pattern.get(y).get(z).length(); x++) {
                    var chr = pattern.get(y).get(z).charAt(x);
                    var pos = new BlockPos(x, y, z);
                    consumer.accept(chr, pos);
                }
            }
        }
    }

    public static Direction toDirection(StructurePatternConfig.Spec.FacingDirection facing) {
        return switch (facing) {
            case NORTH_FIRST -> Direction.NORTH;
            case EAST_FIRST -> Direction.EAST;
            case SOUTH_FIRST -> Direction.SOUTH;
            case WEST_FIRST -> Direction.WEST;
        };
    }

    public static List<PositionedPartHandlerFactory> rotateParts(List<PositionedPartHandlerFactory> parts, Rotation rotation) {
        var result = new ArrayList<PositionedPartHandlerFactory>();
        for (PositionedPartHandlerFactory part : parts) {
            result.add(new PositionedPartHandlerFactory(part.factory(), part.pos().rotate(rotation)));
        }
        return result;
    }
}

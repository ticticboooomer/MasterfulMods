package io.ticticboom.mods.mmb.runtime.list;

import io.ticticboom.mods.mmb.common.CharPos;
import io.ticticboom.mods.mmb.common.Deferred;
import io.ticticboom.mods.mmb.config.StructureKeyConfig;
import io.ticticboom.mods.mmb.config.StructurePatternConfig;
import io.ticticboom.mods.mmb.core.CoreBlocks;
import io.ticticboom.mods.mmb.core.CustomCoreBlockConfig;
import io.ticticboom.mods.mmb.runtime.MultiBlockListHelper;
import io.ticticboom.mods.mmb.runtime.MultiBlockPartList;
import io.ticticboom.mods.mmb.runtime.PositionedPartHandlerFactory;
import io.ticticboom.mods.mmb.structure.FixedMultiBlockConfig;
import io.ticticboom.mods.mmb.structure.part.StructureParts;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FixedMultiBlockPartList extends MultiBlockPartList {

    public FixedMultiBlockPartList(FixedMultiBlockConfig.Spec config) {
        initConfig(config);
    }

    private void initConfig(FixedMultiBlockConfig.Spec config) {
        var patternId = config.pattern();
        var pattern = StructurePatternConfig.REGISTRY.get(patternId);
        var keyId = config.key();
        var key = StructureKeyConfig.REGISTRY.get(keyId);
        var clone = new ArrayList<>(pattern.pattern());
        if (pattern.direction() == StructurePatternConfig.Spec.YDirection.TOP_FIRST) {
            Collections.reverse(clone);
        }
        var controllerPos = new Deferred<CharPos>();
        MultiBlockListHelper.runForPattern(clone, (chr, pos) -> {
            if (chr == 'C') {
                controllerPos.SetValue(new CharPos(chr, pos));
            }
        });
        var keyMap = new HashMap<Character, StructureParts.IPartHandlerFactory>();
        for (StructureKeyConfig.SpecEntry entry : key.entries()) {
            keyMap.put(entry.chr(), StructureParts.HANDLER_FACTORIES.get(entry.part()));
        }
        var initialParts = new ArrayList<PositionedPartHandlerFactory>();
        MultiBlockListHelper.runForPattern(clone, (chr, pos) -> {
            if (chr == 'C') {
                if (!keyMap.containsKey(chr)) {
                    return;
                }
            }
            var keyPart = keyMap.get(chr);
            initialParts.add(new PositionedPartHandlerFactory(keyPart, pos.subtract(controllerPos.GetValue().pos())));
        });

        var facing = MultiBlockListHelper.toDirection(pattern.facing());
        parts.put(facing, initialParts);
        parts.put(facing.getClockWise(), MultiBlockListHelper.rotateParts(initialParts, Rotation.CLOCKWISE_90));
        parts.put(facing.getCounterClockWise(), MultiBlockListHelper.rotateParts(initialParts, Rotation.COUNTERCLOCKWISE_90));
        parts.put(facing.getOpposite(), MultiBlockListHelper.rotateParts(initialParts, Rotation.CLOCKWISE_180));

        cbHandler = CoreBlocks.HANDLERS.get(config.coreBlock());
    }

    @Override
    public void initHandlers(BlockPos pos) {
        for (var part : parts.entrySet()) {
            var list = new ArrayList<StructureParts.SpecHandler>();
            for (PositionedPartHandlerFactory phf : part.getValue()) {
                var handler = phf.factory().create(pos.offset(phf.pos()));
                list.add(handler);
            }
            runtimeParts.put(part.getKey(), list);
        }
    }

    @Override
    public void tick(Level level, BlockPos pos) {

    }

    @Override
    public boolean verifyPlacement(Level level, BlockPos pos) {
        for (var partList  : runtimeParts.entrySet()) {
            var found  = true;
            for (var part : partList.getValue()) {
                if (!part.verifyPlacement(level)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }
        return false;
    }
}

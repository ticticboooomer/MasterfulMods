package io.ticticboom.mods.mmb.runtime.list;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.document.IConfigDocumentData;
import io.ticticboom.mods.mconf.setup.MConfRegistries;
import io.ticticboom.mods.mmb.common.CharPos;
import io.ticticboom.mods.mmb.common.Deferred;
import io.ticticboom.mods.mmb.config.StructureKeyConfig;
import io.ticticboom.mods.mmb.config.StructurePatternConfig;
import io.ticticboom.mods.mmb.core.CoreBlocks;
import io.ticticboom.mods.mmb.core.ICoreBlockData;
import io.ticticboom.mods.mmb.runtime.MultiBlockListHelper;
import io.ticticboom.mods.mmb.runtime.MultiBlockPartList;
import io.ticticboom.mods.mmb.runtime.PositionedPartHandlerFactory;
import io.ticticboom.mods.mmb.structure.FixedMultiBlockConfig;
import io.ticticboom.mods.mmb.structure.part.StructureParts;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;

import java.util.*;

public class FixedMultiBlockPartList extends MultiBlockPartList {

    protected final Deferred<CharPos> controllerPos = new Deferred<>();
    public FixedMultiBlockPartList(FixedMultiBlockConfig.Data config) {
        initConfig(config);
    }

    protected Pair<List<CharPos>, StructurePatternConfig.Spec> getPattern(FixedMultiBlockConfig.Data config) {
        List<CharPos> result = new ArrayList<>();
        ConfigDocument<StructurePatternConfig.Spec> pattern =  config.pattern().cast();
        var clone = new ArrayList<>(pattern.data.pattern());
        if (pattern.data.direction() == StructurePatternConfig.Spec.YDirection.TOP_FIRST) {
            Collections.reverse(clone);
        }
        MultiBlockListHelper.runForPattern(clone, (c, p) -> {
            if (c == 'C') {
                controllerPos.SetValue(new CharPos(c, p));
            }
        });
        MultiBlockListHelper.runForPattern(clone, (c, p) -> {
            if (c == 'C') {
                return;
            }
            result.add(new CharPos(c, p.subtract(controllerPos.GetValue().pos())));
        });
        return new Pair<>(result, pattern.data);
    }

    protected Map<Character, StructureParts.IPartHandlerFactory> getKeyMap(FixedMultiBlockConfig.Data config) {
        ConfigDocument<StructureKeyConfig.Spec> cast = config.key().cast();
        var type = MConfRegistries.DOCUMENT_TYPES.get(cast.type);
        var res = type.createResult(cast.cast(), new JsonObject());
        return (Map<Character, StructureParts.IPartHandlerFactory>)res.resultData().getFirst().get();
    }


    private void initConfig(FixedMultiBlockConfig.Data config) {
        var pattern = getPattern(config);
        var keyMap = getKeyMap(config);
        var initialParts = new ArrayList<PositionedPartHandlerFactory>();
        for (var part : pattern.getFirst()) {
            StructureParts.IPartHandlerFactory handler = keyMap.get(part.chr());
            initialParts.add(new PositionedPartHandlerFactory(handler, part.pos()));
        }
        var facing = MultiBlockListHelper.toDirection(pattern.getSecond().facing());
        parts.put(facing, initialParts);
        parts.put(facing.getClockWise(), MultiBlockListHelper.rotateParts(initialParts, Rotation.CLOCKWISE_90));
        parts.put(facing.getCounterClockWise(), MultiBlockListHelper.rotateParts(initialParts, Rotation.COUNTERCLOCKWISE_90));
        parts.put(facing.getOpposite(), MultiBlockListHelper.rotateParts(initialParts, Rotation.CLOCKWISE_180));

        ConfigDocument<ICoreBlockData> cb = config.coreBlock().cast();
        cbHandler = cb.data;
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

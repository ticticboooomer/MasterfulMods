package io.ticticboom.mods.mmb.setup;

import io.ticticboom.mods.mconf.setup.MConfRegistries;
import io.ticticboom.mods.mmb.Ref;
import io.ticticboom.mods.mmb.config.StructureKeyConfig;
import io.ticticboom.mods.mmb.config.StructurePatternConfig;
import io.ticticboom.mods.mmb.core.CustomCoreBlockConfig;
import io.ticticboom.mods.mmb.runtime.MultiBlockPartList;
import io.ticticboom.mods.mmb.structure.FixedMultiBlockConfig;
import io.ticticboom.mods.mmb.structure.part.BlockStructurePartConfig;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MMBRegistries {

    public static final Map<ResourceLocation, Supplier<MultiBlockPartList>> MULTIBLOCKS = new HashMap<>();
    public static void registerConfigs() {
        MConfRegistries.DOC_TYPES.put(Ref.DocTypes.STRUCTURE_KEY, new StructureKeyConfig());
        MConfRegistries.DOC_TYPES.put(Ref.DocTypes.STRUCTURE_PATTERN, new StructurePatternConfig());
        MConfRegistries.DOC_TYPES.put(Ref.DocTypes.FIXED_MULTIBLOCK, new FixedMultiBlockConfig());
        MConfRegistries.DOC_TYPES.put(Ref.DocTypes.StructureParts.BLOCK, new BlockStructurePartConfig());
        MConfRegistries.DOC_TYPES.put(Ref.DocTypes.CoreBlocks.CUSTOM, new CustomCoreBlockConfig());
    }
}

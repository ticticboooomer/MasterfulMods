package io.ticticboom.mods.mmb.structure.part;

import io.ticticboom.mods.mconf.document.DocumentValidationError;
import io.ticticboom.mods.mconf.document.IConfigSpecConsumer;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.setup.document.ConfigDocumentType;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Function;

public class BlockStructurePartConfig extends ConfigDocumentType {
    @Override
    public IConfigSpecConsumer createSpecConsumer() {
        return new SpecConsumer();
    }

    public static final class SpecConsumer extends StructureParts.SpecConsumer<Spec> {

        @Override
        public Spec safeParse(IParseableDocumentSpec value) {
            return new Spec(new ResourceLocation(value.get("block").getString()));
        }

        @Override
        public boolean safeValidate(Spec value, List<DocumentValidationError> errors) {
            return true;
        }

        @Override
        public StructureParts.IPartHandlerFactory createHandlerFactory(Spec value, IParseableDocument doc) {
            return pos -> new SpecHandler(value, pos);
        }
    }

    public static final class SpecHandler extends StructureParts.SpecHandler {

        public SpecHandler(StructureParts.IPartSpec spec, BlockPos offset) {
            super(spec, offset);
        }

        @Override
        public boolean verifyPlacement(Level level) {
            Block block = level.getBlockState(offset).getBlock();
            ResourceLocation key = ForgeRegistries.BLOCKS.getKey(block);
            Spec blockSpec = (Spec)spec;
            return key != null && key.toString().equals(blockSpec.block().toString());
        }
    }

    public record Spec(ResourceLocation block) implements StructureParts.IPartSpec {

    }
}

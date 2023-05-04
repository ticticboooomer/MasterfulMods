package io.ticticboom.mods.mm.data;

import io.ticticboom.mods.mm.Ref;
import io.ticticboom.mods.mm.setup.MMControllers;
import io.ticticboom.mods.mm.setup.holder.RegisteredController;
import io.ticticboom.mods.mm.setup.holder.RegisteredPort;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.CompositeModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MMBlockStateProvider extends BlockStateProvider {
    public MMBlockStateProvider(PackOutput output,ExistingFileHelper exFileHelper) {
        super(output, Ref.ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (RegisteredController value : MMControllers.REG_CONTROLLERS.values()) {
            this.horizontalBlock(value.block().GetValue().get(), new ModelFile.UncheckedModelFile(Ref.rl("block/" + value.block().GetValue().getId().getPath())));
        }

        for (RegisteredPort value : MMControllers.REG_PORTS.values()) {
            this.simpleBlock(value.block().GetValue().get(), new ModelFile.UncheckedModelFile(Ref.rl("block/" + value.block().GetValue().getId().getPath())));
        }
    }
}

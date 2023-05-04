package io.ticticboom.mods.mm.data;

import io.ticticboom.mods.mm.Ref;
import io.ticticboom.mods.mm.setup.MMControllers;
import io.ticticboom.mods.mm.setup.holder.RegisteredController;
import io.ticticboom.mods.mm.setup.holder.RegisteredPort;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MMItemModelProvider extends ItemModelProvider {
    public MMItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Ref.ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegisteredController value : MMControllers.REG_CONTROLLERS.values()) {
            ResourceLocation id = value.block().GetValue().getId();
            this.getBuilder(value.item().GetValue().getId().getPath()).parent(new ModelFile.UncheckedModelFile(Ref.rl("block/" + id.getPath())));
        }

        for (RegisteredPort value : MMControllers.REG_PORTS.values()) {
            ResourceLocation id = value.block().GetValue().getId();
            this.getBuilder(value.item().GetValue().getId().getPath()).parent(new ModelFile.UncheckedModelFile(Ref.rl("block/" + id.getPath())));
        }
    }
}

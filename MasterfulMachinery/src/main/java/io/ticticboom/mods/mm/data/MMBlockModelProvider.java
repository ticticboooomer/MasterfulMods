package io.ticticboom.mods.mm.data;

import io.ticticboom.mods.mm.Ref;
import io.ticticboom.mods.mm.setup.MMControllers;
import io.ticticboom.mods.mm.setup.MMRegistries;
import io.ticticboom.mods.mm.setup.holder.RegisteredController;
import io.ticticboom.mods.mm.setup.holder.RegisteredPort;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.CompositeModel;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.CompositeModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class MMBlockModelProvider extends BlockModelProvider {
    public MMBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Ref.ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegisteredController value : MMControllers.REG_CONTROLLERS.values()) {
            dynamicBlockNorthOverlay(value.block().GetValue().getId(), Ref.rl("block/base_block"), Ref.rl("block/controller_cutout"));
        }

        for (RegisteredPort value : MMControllers.REG_PORTS.values()) {
            dynamicBlockOverlay(value.block().GetValue().getId(), Ref.rl("block/base_block"), Ref.rl("block/test_port"));
        }
    }

    private void dynamicBlockNorthOverlay(ResourceLocation loc, ResourceLocation baseTexture, ResourceLocation overlayTexture) {
        getBuilder(loc.toString()).parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
                .texture("particle", overlayTexture)
                .customLoader(CompositeModelBuilder::begin)
                .child("solid", nested().parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
                        .texture("base", baseTexture)
                        .element()
                        .from(0, 0, 0)
                        .to(16, 16, 16)
                        .cube("#base")
                        .allFaces((dir, uv) -> uv.uvs(0F, 0.0F, 16F, 16F))
                        .end().renderType("solid")
                )
                .child("translucent", nested().parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
                        .texture("overlay", overlayTexture)
                        .element()
                        .from(0, 0, 0)
                        .to(16, 16, 16)
                        .face(Direction.NORTH)
                        .texture("#overlay")
                        .end()
                        .end().renderType("translucent")
                )
                .end();
    }

    private void dynamicBlockOverlay(ResourceLocation loc, ResourceLocation baseTexture, ResourceLocation overlayTexture) {
        getBuilder(loc.toString()).parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
                .texture("particle", overlayTexture)
                .customLoader(CompositeModelBuilder::begin)
                .child("solid", nested().parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
                        .texture("base", baseTexture)
                        .element()
                        .from(0, 0, 0)
                        .to(16, 16, 16)
                        .cube("#base")
                        .end().renderType("solid")
                )
                .child("translucent", nested().parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
                        .texture("overlay", overlayTexture)
                        .element()
                        .from(0, 0, 0)
                        .to(16, 16, 16)
                        .cube("#overlay")
                        .end().renderType("translucent")
                )
                .end();
    }
}

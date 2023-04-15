package io.ticticboom.mods.ml.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ControllerBlock extends Block {
    public ControllerBlock() {
        super(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK));
    }
}

package io.ticticboom.mods.test.mmb.block;

import io.ticticboom.mods.test.mmb.setup.MMBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.jetbrains.annotations.Nullable;

public class MultiBlock extends Block implements EntityBlock {
    public MultiBlock() {
        super(Block.Properties.of(Material.AMETHYST, MaterialColor.CLAY));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        return (p_155253_, p_155254_, p_155255_, p_155256_) -> {
            if (p_155256_ instanceof MultiBlockEntity) {
                ((MultiBlockEntity) p_155256_).tick();
            }
        };
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return MMBBlocks.MULTI_BLOCK_ENTITY.get().create(p_153215_, p_153216_);
    }
}

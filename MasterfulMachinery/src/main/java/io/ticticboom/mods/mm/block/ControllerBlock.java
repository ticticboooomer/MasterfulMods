package io.ticticboom.mods.mm.block;

import com.google.gson.JsonObject;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mm.block.entity.ControllerBlockEntity;
import io.ticticboom.mods.mm.common.BlockPropsConfig;
import io.ticticboom.mods.mm.controller.ControllerBlockConfig;
import io.ticticboom.mods.mm.setup.MMRegistries;
import io.ticticboom.mods.mmb.common.Deferred;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class ControllerBlock extends HorizontalDirectionalBlock implements EntityBlock {
    private final Deferred<RegistryObject<BlockEntityType<ControllerBlockEntity>>> be;

    public ControllerBlock(ConfigDocument<ControllerBlockConfig.Spec> doc, Deferred<RegistryObject<BlockEntityType<ControllerBlockEntity>>> be) {
        super(createProps(doc));
        this.be = be;
    }

    public static Properties createProps(ConfigDocument<ControllerBlockConfig.Spec> doc) {
        var result = Properties.of(Material.WOOD);
        if (doc.data.blockProps().isPresent()) {
            var returned = MMRegistries.BLOCK_PROPS.createResult(doc.data.blockProps().get().cast(), new JsonObject());
            return returned.resultData().getFirst().get().populate(result);
        }
        return result;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        return (l, p, s, be) -> {
            if (be instanceof ControllerBlockEntity) {
                ((ControllerBlockEntity) be).tick();
            }
        };
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return be.GetValue().get().create(p_153215_, p_153216_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        super.createBlockStateDefinition(p_49915_.add(FACING));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_49820_) {
        return super.getStateForPlacement(p_49820_).setValue(FACING, p_49820_.getHorizontalDirection().getOpposite());
    }
}

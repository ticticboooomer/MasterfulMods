package io.ticticboom.mods.test.mmb.setup;

import io.ticticboom.mods.test.mmb.Ref;
import io.ticticboom.mods.test.mmb.block.MultiBlock;
import io.ticticboom.mods.test.mmb.block.MultiBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMBBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ref.ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Ref.ID);

    public static final RegistryObject<Block> FIXED_MULTI_BLOCK = BLOCKS.register("fixed_multi_block", MultiBlock::new);
    public static final RegistryObject<BlockEntityType<MultiBlockEntity>> MULTI_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("multi_block_entity", () -> BlockEntityType.Builder.of(MultiBlockEntity::new, FIXED_MULTI_BLOCK.get()).build(null));
}

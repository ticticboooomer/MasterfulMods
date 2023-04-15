package io.ticticboom.mods.mm.setup;

import io.ticticboom.mods.mconf.setup.MConfRegistries;
import io.ticticboom.mods.mm.Ref;
import io.ticticboom.mods.mm.common.BlockPropsConfig;
import io.ticticboom.mods.mm.controller.ControllerBlockConfig;
import io.ticticboom.mods.mm.port.PortTypeConfigs;
import io.ticticboom.mods.mm.port.item.ItemPortConfig;
import io.ticticboom.mods.mm.setup.loader.BaseLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MMRegistries {
    public static final ControllerBlockConfig CONTROLLER = new ControllerBlockConfig();
    public static final BlockPropsConfig BLOCK_PROPS = new BlockPropsConfig();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ref.ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ref.ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Ref.ID);

    public static void registerConfigTypes() {
        var itemPort = new ItemPortConfig();
        MConfRegistries.DOCUMENT_TYPES.put(Ref.DocTypes.ITEM_PORT, itemPort);
        MConfRegistries.DOCUMENT_TYPES.put(Ref.DocTypes.CONTROLLER, CONTROLLER);
        MConfRegistries.DOCUMENT_TYPES.put(Ref.DocTypes.BLOCK_PROPS, BLOCK_PROPS);
        PortTypeConfigs.PORT_TYPES.put(Ref.DocTypes.ITEM_PORT, itemPort);
    }

    @SubscribeEvent
    public static void registerTabs(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(Ref.TAB, b -> b.displayItems(MMTab::itemsToDisplay).title(Component.literal("Masterful Machinery")).icon(() -> new ItemStack(Items.ACACIA_BOAT)));
    }

    @SubscribeEvent
    public static void construct(FMLConstructModEvent event) {
        BaseLoader.load();
        MMControllers.registerControllers();
    }
}
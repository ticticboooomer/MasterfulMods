package io.ticticboom.mods.mm.setup;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Collectors;

public class MMTab {
    public static void itemsToDisplay(CreativeModeTab.ItemDisplayParameters params, CreativeModeTab.Output output) {
        MMRegistries.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
    }
}

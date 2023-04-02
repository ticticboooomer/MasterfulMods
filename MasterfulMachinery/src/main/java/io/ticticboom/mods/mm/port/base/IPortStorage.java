package io.ticticboom.mods.mm.port.base;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

public interface IPortStorage {
    void reset();

    <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap);

    default <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
        return getCapability(cap);
    }
}

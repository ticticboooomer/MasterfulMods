package io.ticticboom.mods.mm.port.item;

import io.ticticboom.mods.mm.port.base.IPortStorage;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class ItemPortStorage implements IPortStorage {

    private final int x;
    private final int y;
    protected ItemStackHandler items;
    protected LazyOptional<IItemHandler> handler = LazyOptional.of(() -> items);

    public ItemPortStorage(int x, int y) {
        this.x = x;
        this.y = y;
        this.items = new ItemStackHandler(x * y);
    }

    @Override
    public void reset() {
        this.items = new ItemStackHandler(x * y);
    }

    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return handler.cast();
    }
}
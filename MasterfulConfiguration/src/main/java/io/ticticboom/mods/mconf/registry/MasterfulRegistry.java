package io.ticticboom.mods.mconf.registry;

import net.minecraft.resources.ResourceLocation;

import java.util.*;

public class MasterfulRegistry<T> {
    private final Map<ResourceLocation, T> registry = new HashMap<>();

    public static <T> MasterfulRegistry<T> create() {
        return new MasterfulRegistry<>();
    }
    public T get(ResourceLocation key) {
        return registry.get(key);
    }

    public void put(ResourceLocation key, T value) {
        registry.put(key, value);
    }

    public void remove(ResourceLocation key) {
        registry.remove(key);
    }

    public void clear() {
        registry.clear();
    }

    public int size() {
        return registry.size();
    }

    public boolean isEmpty() {
        return registry.isEmpty();
    }

    public boolean containsKey(ResourceLocation key) {
        return registry.containsKey(key);
    }

    public boolean containsValue(T value) {
        return registry.containsValue(value);
    }

    public T getOrDefault(ResourceLocation key, T defaultValue) {
        return registry.getOrDefault(key, defaultValue);
    }

    public Collection<T> values() {
        return registry.values();
    }
}

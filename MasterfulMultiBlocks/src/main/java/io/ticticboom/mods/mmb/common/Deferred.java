package io.ticticboom.mods.mmb.common;

public class Deferred<T> {
    protected T value;
    public void SetValue(T value) {
        this.value = value;
    }
    public T GetValue() {
        return value;
    }
}

package io.ticticboom.mods.msc.range;

@FunctionalInterface
public interface ISpellRangeHandler {
    long getRange(long spellLevel);
}

package io.ticticboom.mods.msc.cost;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

@FunctionalInterface
public interface ISpellCostHandler {
    void getCost(int spellLevel, Player player, Level level);
}
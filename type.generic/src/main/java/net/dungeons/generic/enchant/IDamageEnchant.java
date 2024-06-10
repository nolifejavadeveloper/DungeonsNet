package net.dungeons.generic.enchant;

import net.dungeons.generic.items.SkyblockItem;
import net.dungeons.generic.player.SkyblockPlayer;

public interface IDamageEnchant {
    public boolean isMultiplicative();
    public double getModifier(SkyblockPlayer player, SkyblockItem item);
}

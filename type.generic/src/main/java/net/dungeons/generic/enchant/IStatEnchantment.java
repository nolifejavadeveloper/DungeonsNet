package net.dungeons.generic.enchant;

import net.dungeons.generic.items.SkyblockItem;
import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.stats.SkyblockStats;

public interface IStatEnchantment {
    public SkyblockStats getStatModifier(SkyblockPlayer player, SkyblockItem item);
}

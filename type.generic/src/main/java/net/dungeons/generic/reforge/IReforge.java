package net.dungeons.generic.reforge;

import net.dungeons.generic.items.SkyblockItem;
import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.stats.SkyblockStats;

public interface IReforge {
    public SkyblockStats getStats(SkyblockPlayer player, SkyblockItem item);
    public String getReforgeName();
}

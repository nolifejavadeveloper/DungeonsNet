package net.dungeons.generic.items;

import net.dungeons.generic.player.SkyblockPlayer;

public class GearScore {
    public static int getGearScore(SItem item, SkyblockPlayer player)
    {
        int value = item.getItemRarity(player, null).rarity;

        value *= item.getStars(player, null);
        value *= item.getEnchantments(player, null).size();

        return value;
    }
}

package net.dungeons.generic.pet;

import net.dungeons.generic.items.ItemRarity;
import net.dungeons.generic.items.SkyblockItem;
import net.dungeons.generic.stats.SkyblockStats;

public abstract class SkyblockPet {
    public abstract SkyblockStats getStats();
    public abstract int getMaxLevel();
    public abstract double getNextLevelTotalExp();
    public abstract double getMaxLevelTotalExp();
    public abstract int getLevel();
    public abstract SkyblockItem getAsItem();
    public abstract double getExperienceToLevel(int level);
    public abstract ItemRarity getRarity();

    //
    private String name;
    private double exp;
    private ItemRarity rarity;

    public SkyblockPet(String name, double exp, ItemRarity rarity) {
        this.name = name;
        this.exp = exp;
        this.rarity = rarity;
    }
}

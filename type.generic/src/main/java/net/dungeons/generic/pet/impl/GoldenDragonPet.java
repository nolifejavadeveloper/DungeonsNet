package net.dungeons.generic.pet.impl;


import net.dungeons.generic.items.ItemRarity;
import net.dungeons.generic.items.SkyblockItem;
import net.dungeons.generic.pet.SkyblockPet;
import net.dungeons.generic.stats.SkyblockStats;

public class GoldenDragonPet extends SkyblockPet {
    public GoldenDragonPet(double exp, ItemRarity rarity) {
        super("Golden Dragon", exp, rarity);
    }

    @Override
    public SkyblockStats getStats()
    {
        return new SkyblockStats()
                .setBonusAttackSpeed(78)
                .setMagicFind(50)
                .setStrength(198);
    }

    @Override
    public int getMaxLevel()
    {
        return 200;
    }

    @Override
    public double getNextLevelTotalExp()
    {
        return this.getLevel() * 398;
    }

    @Override
    public double getMaxLevelTotalExp()
    {
        return 0;
    }

    @Override
    public int getLevel()
    {
        return 3;
    }

    @Override
    public double getExperienceToLevel(int level)
    {
        return 4 * level;
    }

    @Override
    public SkyblockItem getAsItem()
    {
        //pog
        return null;
    }
}

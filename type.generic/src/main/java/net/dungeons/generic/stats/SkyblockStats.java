package net.dungeons.generic.stats;

import java.util.HashMap;

public class SkyblockStats {
    private final HashMap<Stat, Double> STAT_MAP = new HashMap<>(Stat.values().length);
    public SkyblockStats() {
        for (Stat stat : Stat.values())
        {
            STAT_MAP.put(stat, 0d);
        }
    }

    public double getStat(Stat stat) {
        return STAT_MAP.get(stat);
    }

    /*
    * Builder functions
    * */

    public SkyblockStats setDamage(double value) {
        STAT_MAP.put(Stat.DAMAGE, value);
        return this;
    }

    public SkyblockStats setStrength(double value) {
        STAT_MAP.put(Stat.STRENGTH, value);
        return this;
    }

    public SkyblockStats setHealth(double value) {
        STAT_MAP.put(Stat.HEALTH, value);
        return this;
    }

    public SkyblockStats setDefence(double value) {
        STAT_MAP.put(Stat.DEFENSE, value);
        return this;
    }

    public SkyblockStats setTrueDefence(double value) {
        STAT_MAP.put(Stat.TRUE_DEFENSE, value);
        return this;
    }

    public SkyblockStats setSpeed(double value) {
        STAT_MAP.put(Stat.SPEED, value);
        return this;
    }

    public SkyblockStats setIntelligence(double value) {
        STAT_MAP.put(Stat.INTELLIGENCE, value);
        return this;
    }

    public SkyblockStats setCritChance(double value) {
        STAT_MAP.put(Stat.CRIT_CHANCE, value);
        return this;
    }

    public SkyblockStats setCritDamage(double value) {
        STAT_MAP.put(Stat.CRIT_DAMAGE, value);
        return this;
    }

    public SkyblockStats setBonusAttackSpeed(double value) {
        STAT_MAP.put(Stat.BONUS_ATTACK_SPEED, value);
        return this;
    }

    public SkyblockStats setFerocity(double value) {
        STAT_MAP.put(Stat.FEROCITY, value);
        return this;
    }

    public SkyblockStats setMagicFind(double value) {
        STAT_MAP.put(Stat.MAGIC_FIND, value);
        return this;
    }

    public SkyblockStats setPetLuck(double value) {
        STAT_MAP.put(Stat.PET_LUCK, value);
        return this;
    }

    public SkyblockStats setAbilityDamage(double value) {
        STAT_MAP.put(Stat.ABILITY_DAMAGE, value);
        return this;
    }

    public SkyblockStats setStat(Stat stat, double value) {
        STAT_MAP.put(stat, value);

        return this;
    }

    public SkyblockStats addStat(Stat stat, double value) {
        setStat(stat, STAT_MAP.get(stat) + value);

        return this;
    }

    public SkyblockStats add(SkyblockStats stats) {
        for (Stat stat : Stat.values()) {
            this.addStat(stat, stats.getStat(stat));
        }

        return this;
    }

    public SkyblockStats copy()
    {
        SkyblockStats stats = new SkyblockStats();

        for (Stat stat : Stat.values())
        {
            stats.setStat(stat, this.getStat(stat));
        }

        return stats;
    }
}

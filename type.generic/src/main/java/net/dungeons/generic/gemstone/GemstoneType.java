package net.dungeons.generic.gemstone;


import net.dungeons.generic.stats.Stat;

public enum GemstoneType {
    RUBY(Stat.HEALTH, new double[]{7, 10, 14, 22, 30}, 'c'),
    AMETHYST(Stat.DEFENSE, new double[]{7, 10, 14, 22, 30}, '5'),
    SAPPHIRE(Stat.INTELLIGENCE, new double[]{7, 10, 12, 20, 30}, 'b'),
    CARNELIAN(Stat.FEROCITY, new double[]{3, 3.5, 5, 7, 10}, '4'),
    JASPER(Stat.STRENGTH, new double[]{4, 5, 7, 12, 16}, 'd');

    public final Stat stat;
    private final double[] values;
    public final char color;
    GemstoneType(Stat stat, double[] values, char color) {
        this.stat = stat;
        this.values = values;
        this.color = color;
    }

    public double getStatBonusByQuality(GemstoneQuality gemstoneQuality) {
        return values[gemstoneQuality.index];
    }
    public boolean isCorrectStat(Stat stat) {return stat == this.stat;}
}

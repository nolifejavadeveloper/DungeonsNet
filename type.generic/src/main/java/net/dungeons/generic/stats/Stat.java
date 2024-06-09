package net.dungeons.generic.stats;

public enum Stat {
    DAMAGE('❁', 'c', 0, Double.MAX_VALUE, 5, false, true, false),
    STRENGTH('❁', 'c', 0, Double.MAX_VALUE, 30, true, true, false),
    HEALTH('❤', 'c', 1, Double.MAX_VALUE, 100, true, false, false),
    DEFENSE('❈', 'a', 0, Double.MAX_VALUE, 30, true, false, false),
    TRUE_DEFENSE('❂', 'f', 0, Double.MAX_VALUE, 0, true, false, false),
    SPEED('✦', 'f', 0, 400, 100, true, false, false),
    INTELLIGENCE('✎', 'b', 0, Double.MAX_VALUE, 100, true, false, false),
    CRIT_CHANCE('☣', '9', 0, Double.MAX_VALUE, 30, true, true, true),
    CRIT_DAMAGE('☠', '9', 0, Double.MAX_VALUE, 50, true, true, true),
    BONUS_ATTACK_SPEED('⚔', 'e', 0, 100, 0, true, true, true),
    FEROCITY('⫽', 'c', 0, 500, 0, true, true, false),
    MAGIC_FIND('✯', 'b', 0, Double.MAX_VALUE, 50, true, false, false),
    PET_LUCK('♣', 'd', 0, Double.MAX_VALUE, 20, true, false, false),
    ABILITY_DAMAGE('๑', 'c', 0, Double.MAX_VALUE, 10, true, true, false);

    public final char icon;
    public final char color;
    public final double min;
    public final double max;
    public final double base;
    public final boolean showInMenu;
    public final boolean isRed;
    public final boolean percent;

    Stat(char i, char c, double min, double max, double base, boolean showInMenu, boolean isRed, boolean percent)
    {
        this.icon = i;
        this.color = c;
        this.min = min;
        this.max = max;
        this.base = base;
        this.showInMenu = showInMenu;
        this.isRed = isRed;
        this.percent = percent;
    }
}

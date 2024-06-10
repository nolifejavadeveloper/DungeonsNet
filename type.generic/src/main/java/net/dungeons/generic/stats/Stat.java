package net.dungeons.generic.stats;

public enum Stat {
    DAMAGE('❁', 'c', "Damage", 0, Double.MAX_VALUE, 5, false, true, false),
    STRENGTH('❁', 'c', "Strength", 0, Double.MAX_VALUE, 30, true, true, false),
    HEALTH('❤', 'c', "Health", 1, Double.MAX_VALUE, 100, true, false, false),
    DEFENSE('❈', 'a', "Defense", 0, Double.MAX_VALUE, 30, true, false, false),
    TRUE_DEFENSE('❂', 'f', "True Defense, ", 0, Double.MAX_VALUE, 0, true, false, false),
    SPEED('✦', 'f', "Speed", 0, 400, 100, true, false, false),
    INTELLIGENCE('✎', 'b', "Intelligence", 0, Double.MAX_VALUE, 100, true, false, false),
    CRIT_CHANCE('☣', '9', "Crit Chance", 0, Double.MAX_VALUE, 30, true, true, true),
    CRIT_DAMAGE('☠', '9', "Crit Damage", 0, Double.MAX_VALUE, 50, true, true, true),
    BONUS_ATTACK_SPEED('⚔', 'e', "Bonus Attack Speed", 0, 100, 0, true, true, true),
    FEROCITY('⫽', 'c', "Ferocity", 0, 500, 0, true, true, false),
    MAGIC_FIND('✯', 'b', "Magic Find", 0, Double.MAX_VALUE, 50, true, false, false),
    PET_LUCK('♣', 'd', "Pet Luck", 0, Double.MAX_VALUE, 20, true, false, false),
    ABILITY_DAMAGE('๑', 'c', "Ability Damage", 0, Double.MAX_VALUE, 10, true, true, false);

    public final char icon;
    public final char color;
    public final String name;
    public final double min;
    public final double max;
    public final double base;
    public final boolean showInMenu;
    public final boolean isRed;
    public final boolean percent;

    Stat(char i, char c, String name, double min, double max, double base, boolean showInMenu, boolean isRed, boolean percent)
    {
        this.icon = i;
        this.color = c;
        this.name = name;
        this.min = min;
        this.max = max;
        this.base = base;
        this.showInMenu = showInMenu;
        this.isRed = isRed;
        this.percent = percent;
    }
}

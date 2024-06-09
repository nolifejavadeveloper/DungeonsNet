package net.dungeons.generic.level;

public enum SkyblockLevelPrestige {
    GREY('7', 0),
    WHITE('f', 4000),
    YELLOW('e', 8000),
    LIME('a', 12000),
    DARK_GREEN('2', 16000),
    AQUA('b', 20000),
    DARK_AQUA('3', 24000),
    BLUE('1', 28000),
    PINK('d', 32000),
    PURPLE('5', 36000),
    GOLD('6', 40000),
    RED('c', 44000),
    DARK_RED('4', 48000);

    private final char c;
    private final int xpRequired;

    SkyblockLevelPrestige(char c, int xpRequired) {
        this.c = c;
        this.xpRequired = xpRequired;
    }

    public static SkyblockLevelPrestige getByXp(int xp) {
        SkyblockLevelPrestige previous = SkyblockLevelPrestige.GREY;
        for (SkyblockLevelPrestige current : values()) {
            if (current.xpRequired > xp) {
                return previous;
            }
            previous = current;
        }

        return SkyblockLevelPrestige.GREY;
    }


}

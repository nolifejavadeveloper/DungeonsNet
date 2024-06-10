package net.dungeons.generic.gemstone;

public enum GemstoneQuality {

    ROUGH('f', 0),
    FLAWED('a', 1),
    FINE('9', 2),
    FLAWLESS('5', 3),
    PERFECT('6', 4);

    public final char color;
    public final int index;
    GemstoneQuality(char color, int index) {
        this.color = color;
        this.index = index;
    }



}

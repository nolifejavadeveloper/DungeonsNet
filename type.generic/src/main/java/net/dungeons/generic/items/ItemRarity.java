package net.dungeons.generic.items;

public enum ItemRarity {
    COMMON('f', "COMMON", 1),
    UNCOMMON('a', "UNCOMMON", 2),
    RARE('9', "RARE", 3),
    EPIC('5', "EPIC", 4),
    LEGENDARY('6', "LEGENDARY", 5),
    MYTHIC('d', "MYTHIC", 6),
    DIVINE('b', "DIVINE", 7),
    SPECIAL('c', "SPECIAL", 8),
    VERYSPECIAL('c', "VERY SPECIAL", 9);

    public final char color;
    public final String name;
    public final int rarity;

    ItemRarity(char c, String name, int rarityNumber) {
        this.color = c;
        this.name = name;
        this.rarity = rarityNumber;
    }
}

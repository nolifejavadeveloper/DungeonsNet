package net.dungeons.generic.world;

public enum SkyblockLocation {
    DUNGEON_HUB("Dungeon Hub"),
    DUNGEON("Dungeon"),
    DEV("Developer"),
    NULL("NULL");

    public final String name;

    SkyblockLocation(String name)
    {
        this.name = name;
    }

    public static SkyblockLocation getDefault()
    {
        return SkyblockLocation.NULL;
    }
}

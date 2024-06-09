package net.dungeons.generic.level;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkyblockLevel {
    private int xp;
    private SkyblockLevelPrestige prestige;
    public int getLevel() {
        return (xp - (xp % 100)) / 100;
    }

    public int getExperience() {
        return xp % 100;
    }
}

package net.dungeons.generic.level;

import lombok.Getter;
import lombok.Setter;
import net.dungeons.generic.player.SkyblockPlayer;

@Getter
@Setter
public class SkyblockLevel {
    private SkyblockPlayer player;
    public SkyblockLevel(int xp, SkyblockPlayer player)
    {
        this.xp = xp;
        this.player = player;
    }

    public SkyblockPlayer getPlayer()
    {
        return this.player;
    }

    private int xp;
    private SkyblockLevelPrestige prestige;
    public int getLevel() {
        return (xp - (xp % 100)) / 100;
    }

    public int getExperience() {
        return xp % 100;
    }
}

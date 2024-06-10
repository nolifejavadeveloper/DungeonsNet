package net.dungeons.generic.level;

import lombok.Getter;
import lombok.Setter;
import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.util.IStorable;
import org.bson.Document;

@Getter
@Setter
public class SkyblockLevel implements IStorable {
    private SkyblockPlayer player;
    public SkyblockLevel(int xp, SkyblockPlayer player)
    {
        this.xp = xp;
        this.player = player;
    }

    public void save(Document doc)
    {
        doc.put("level", this.xp);
    }

    public void load(Document doc)
    {
        doc.get("level", 0);
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

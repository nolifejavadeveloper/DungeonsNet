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
    private int xp;
    private SkyblockLevelPrestige prestige;
    public SkyblockLevel(int xp, SkyblockPlayer player)
    {
        this.xp = xp;
        this.player = player;

        this.prestige = SkyblockLevelPrestige.find(xp);
    }

    public void save(Document doc)
    {
        doc.put("level", this.xp);
    }

    public void load(Document doc)
    {
        setXp(doc.get("level", 0));
    }
    public int getLevel() {
        return (xp - (xp % 100)) / 100;
    }

    public int getExperience() {
        return xp % 100;
    }

    public void setXp(int xp)
    {
        this.xp = xp;

        prestige = SkyblockLevelPrestige.find(this.xp);
    }

    public String getFormatted()
    {
        return "&8[&" + this.prestige.getColor() + this.getLevel() + "&8]";
    }
}

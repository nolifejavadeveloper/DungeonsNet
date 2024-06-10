package net.dungeons.generic.skills;

import lombok.Getter;
import lombok.Setter;
import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.util.IStorable;
import org.bson.Document;

public abstract class Skill implements IStorable {
    protected static final double[] SKILL_XP = new double[]{0, 50, 175, 375, 675, 1175, 1925, 2925, 4425, 6425, 9925, 14925, 22425, 32425, 47425, 67425, 97425, 147425, 222425, 322425,
    522425, 822425, 1222425, 1722425, 2322425, 3022425, 3822425, 4722425, 5722425, 6822425, 8022425, 9322425, 10722425, 12222425, 13822425, 15522425, 17322425,
    19222425, 21222425, 23322425, 25522425, 27822425, 30222425, 32722425, 35322425, 38072425, 40972425, 44072425, 47472425, 51172425, 55172425, 59472425,
    64072425, 68972425, 74172425, 79672425, 85472425, 91572425, 97972425, 104672425, 111672425};

    public abstract String getSkillName();
    public abstract String getKey();
    public abstract int getMaxLevel();
    public abstract int getLevelFromExperience(double experience);

    @Getter
    @Setter
    private double xp;
    @Getter
    private final SkyblockPlayer player;

    public Skill(SkyblockPlayer player)
    {
        this.xp = 0;
        this.player = player;
    }

    public double addXp(double xp)
    {
        return this.xp += xp;
    }

    public void save(Document doc)
    {
        doc.put(this.getKey(), this.getXp());
    }

    public void load(Document doc)
    {
        this.xp = doc.get(this.getKey(), 0d);
    }
}

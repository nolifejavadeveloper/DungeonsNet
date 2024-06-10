package net.dungeons.generic.skills.impl;


import net.dungeons.generic.skills.Skill;

public class DungeonSkill extends Skill {
    private double xp;
    private static final double[] SKILL_XP = new double[]{0, 50, 125, 235, 395, 625, 955, 1425, 2095, 3045, 4385, 6275, 8940, 12700,
            17940, 25340, 35640, 50040, 70040, 97640, 135640, 188140, 259640, 356640, 488640, 668640, 911640, 1239640, 1684640,
            2284640, 3084640, 4149640, 5559640, 7459640, 9959640, 13259640, 17559640, 23159640, 30359640, 39559640, 51559640, 665596640,
            85559640, 109559640, 139559640, 177559640, 225559640, 285559640, 360559640, 453559640, 569809640};

    public DungeonSkill(double xp) {
        this.xp = xp;
    }

    @Override
    public String getSkillName() {
        return "Dungeoneering";
    }

    @Override
    public int getMaxLevel() {
        return 50;
    }

    @Override
    public int getLevelFromExperience(double experience) {
        //TODO: do this
        return 0;
    }
}

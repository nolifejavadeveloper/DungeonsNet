package net.dungeons.generic.skills.impl;

import net.dungeons.generic.skills.Skill;

public class FishingSkill extends Skill {
    private double xp;

    public FishingSkill(double xp) {
        this.xp = xp;
    }

    @Override
    public String getSkillName() {
        return "Fishing";
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

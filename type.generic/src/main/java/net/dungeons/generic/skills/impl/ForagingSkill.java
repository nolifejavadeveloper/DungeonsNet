package net.dungeons.generic.skills.impl;

import net.dungeons.generic.skills.Skill;

public class ForagingSkill extends Skill {
    private double xp;

    public ForagingSkill(double xp) {
        this.xp = xp;
    }

    @Override
    public String getSkillName() {
        return "Foraging";
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

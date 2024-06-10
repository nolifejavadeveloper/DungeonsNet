package net.dungeons.generic.skills.impl;

import net.dungeons.generic.skills.Skill;

public class CombatSkill extends Skill {
    private double xp;

    public CombatSkill(double xp) {
        this.xp = xp;
    }

    @Override
    public String getSkillName() {
        return "Combat";
    }

    @Override
    public int getMaxLevel() {
        return 60;
    }

    @Override
    public int getLevelFromExperience(double experience) {
        //TODO: do this
        return 0;
    }
}

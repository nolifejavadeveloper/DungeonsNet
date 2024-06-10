package net.dungeons.generic.skills.impl;


import net.dungeons.generic.skills.Skill;

public class EnchantingSkill extends Skill {
    private double xp;

    public EnchantingSkill(double xp) {
        this.xp = xp;
    }

    @Override
    public String getSkillName() {
        return "Mining";
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

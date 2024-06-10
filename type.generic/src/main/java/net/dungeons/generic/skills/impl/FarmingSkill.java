package net.dungeons.generic.skills.impl;

import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.skills.Skill;

public class FarmingSkill extends Skill {
    public FarmingSkill(SkyblockPlayer player) {
        super(player);
    }
    @Override
    public String getSkillName() {
        return "Farming";
    }

    @Override
    public String getKey() {
        return "farming";
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

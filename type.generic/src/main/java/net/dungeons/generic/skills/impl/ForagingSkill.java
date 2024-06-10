package net.dungeons.generic.skills.impl;

import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.skills.Skill;

public class ForagingSkill extends Skill {
    public ForagingSkill(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public String getSkillName() {
        return "Foraging";
    }

    @Override
    public String getKey() {
        return "foraging";
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

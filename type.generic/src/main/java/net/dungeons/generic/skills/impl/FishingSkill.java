package net.dungeons.generic.skills.impl;

import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.skills.Skill;

public class FishingSkill extends Skill {
    public FishingSkill(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public String getSkillName() {
        return "Fishing";
    }

    @Override
    public String getKey() {
        return "fishing";
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

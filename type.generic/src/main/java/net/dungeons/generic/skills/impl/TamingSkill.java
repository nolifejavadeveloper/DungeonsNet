package net.dungeons.generic.skills.impl;

import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.skills.Skill;

public class TamingSkill extends Skill {
    public TamingSkill(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public String getSkillName() {
        return "Taming";
    }

    @Override
    public String getKey() {
        return "taming";
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

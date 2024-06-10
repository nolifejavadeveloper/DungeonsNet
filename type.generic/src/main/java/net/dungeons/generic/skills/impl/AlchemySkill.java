package net.dungeons.generic.skills.impl;

import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.skills.Skill;

public class AlchemySkill extends Skill {
    public AlchemySkill(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public String getSkillName() {
        return "Alchemy";
    }

    @Override
    public String getKey() {
        return "alchemy";
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

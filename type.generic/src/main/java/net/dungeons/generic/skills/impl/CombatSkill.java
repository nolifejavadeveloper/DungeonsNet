package net.dungeons.generic.skills.impl;

import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.skills.Skill;

public class CombatSkill extends Skill {
    public CombatSkill(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public String getSkillName() {
        return "Combat";
    }

    @Override
    public String getKey() {
        return "combat";
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
